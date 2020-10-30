package org.company.protocol.crane;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetlinks.core.device.DeviceRegistry;
import org.jetlinks.core.message.DeviceRegisterMessage;
import org.jetlinks.core.message.Message;
import org.jetlinks.core.message.codec.*;
import org.jetlinks.core.message.codec.http.HttpExchangeMessage;
import org.jetlinks.core.message.codec.http.SimpleHttpResponseMessage;
import org.jetlinks.core.server.session.DeviceSession;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.Duration;

@AllArgsConstructor
@Setter
@Slf4j(topic = "system.crane")
public class CraneDeviceMessageCodec extends UrlTopicMessageCodec implements DeviceMessageCodec{

    private DeviceRegistry registry;

    @Override
    public Transport getSupportTransport() {
        return DefaultTransport.HTTP;
    }

    @NotNull
    @Override
    public Publisher<? extends Message> decode(@NotNull MessageDecodeContext messageDecodeContext) {
        HttpExchangeMessage message = (HttpExchangeMessage) messageDecodeContext.getMessage();
        String url = message.getUrl();
        JSONObject payload = message.payloadAsJson();
        String deviceId = payload.getString("imei");
        DeviceSession session = ((FromDeviceMessageContext)messageDecodeContext).getSession();
        session.setKeepAliveTimeout(Duration.ofSeconds(600));
        return Mono.defer(() -> {
            return registry.getDevice(deviceId)
                            .flatMap(operator -> {
                            return Mono.justOrEmpty(doDecode(null, url, payload))
                                    .switchIfEmpty(Mono.defer(() -> {
                                        //未转换成功，响应404
                                        return message.response(SimpleHttpResponseMessage
                                                .builder()
                                                .status(404)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .payload(Unpooled.wrappedBuffer("{\"success\":false}".getBytes()))
                                                .build()).then(Mono.empty());
                                    }))
                                    .flatMap(msg -> {
                                        //响应成功
                                        return message.response(SimpleHttpResponseMessage
                                                .builder()
                                                .status(200)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .payload(Unpooled.wrappedBuffer("{\"success\":true}".getBytes()))
                                                .build())
                                                .thenReturn(msg);
                                    });
                            })
                        .switchIfEmpty( Mono.defer(() -> {
                            DeviceRegisterMessage registerMessage = new DeviceRegisterMessage();
                            registerMessage.addHeader("productId", "004");
                            registerMessage.addHeader("deviceName", "特种-" + deviceId);
                            registerMessage.setDeviceId(deviceId);
                            return Mono.just(registerMessage);
                        }));
        });
    }

    @NotNull
    @Override
    public Publisher<? extends EncodedMessage> encode(@NotNull MessageEncodeContext messageEncodeContext) {
        log.info("CraneDeviceMessageCodec encode");
        return Mono.empty();
    }
}
