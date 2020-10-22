package org.company.protocol.crane;

import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetlinks.core.message.Message;
import org.jetlinks.core.message.codec.*;
import org.jetlinks.core.message.codec.http.SimpleHttpResponseMessage;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;

@AllArgsConstructor
@Slf4j(topic = "system.crane")
public class CraneDeviceMessageCodec implements DeviceMessageCodec{

    private static final SimpleHttpResponseMessage response = SimpleHttpResponseMessage
                    .builder()
                    .payload(Unpooled.wrappedBuffer("{success:true}".getBytes()))
            .contentType(MediaType.APPLICATION_JSON)
            .status(200)
            .build();

    @Override
    public Transport getSupportTransport() {
        return DefaultTransport.HTTP;
    }

    @NotNull
    @Override
    public Publisher<? extends Message> decode(@NotNull MessageDecodeContext messageDecodeContext) {
        log.info("decode");
        return null;
    }

    @NotNull
    @Override
    public Publisher<? extends EncodedMessage> encode(@NotNull MessageEncodeContext messageEncodeContext) {
        log.info("encode");
        return null;
    }
}
