package org.company.protocol.crane;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetlinks.core.Value;
import org.jetlinks.core.device.DeviceOperator;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.DeviceMessageReply;
import org.jetlinks.core.message.function.FunctionInvokeMessage;
import org.jetlinks.core.message.function.FunctionInvokeMessageReply;
import org.jetlinks.core.message.function.FunctionParameter;
import org.jetlinks.core.message.interceptor.DeviceMessageSenderInterceptor;
import org.jetlinks.core.message.property.ReadPropertyMessage;
import org.jetlinks.core.message.property.ReportPropertyMessage;
import org.jetlinks.core.message.property.WritePropertyMessage;
import org.jetlinks.core.message.property.WritePropertyMessageReply;
import org.jetlinks.supports.server.DecodedClientMessageHandler;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Slf4j(topic = "system.crane")
public class CraneMessageSenderInterceptor implements DeviceMessageSenderInterceptor {
    // 通过构造器注入一个编码消息处理器，用于消息的持久化
    private DecodedClientMessageHandler handler;

    private static final WebClient webclient = WebClient.builder().build();
    /**
     * 在消息发送后触发.
     *
     * @param device  设备操作接口
     * @param message 源消息
     * @param reply   回复的消息
     * @param <R>     回复的消息类型
     * @return 新的回复结果
     */
    public <R extends DeviceMessage> Flux<R> afterSent(DeviceOperator device, DeviceMessage message, Flux<R> reply) {

        WritePropertyMessage writePropertyMessage = (WritePropertyMessage) message;
        String imei = writePropertyMessage.getProperties().get("imei").toString();
        String url = writePropertyMessage.getProperties().get("url").toString();
        String apiKey = writePropertyMessage.getProperties().get("apikey").toString();
        device.setConfig("url", url);
        return Flux.from(
                // 从配置中获取url等各种请求所需参数
                device.getConfigs("url")
                        .flatMap(values->{
                            // 从命令发起的上下文中获取消息体
                            return webclient  // 构造WebClient
                                    .post()  // 指定请求类型
                                    .uri(url) // 请求路径
                                    .bodyValue("apikey=" + apiKey + "&imei="+imei) // 请求参数
                                    .retrieve() // 发起请求
                                    .bodyToMono(String.class) // 响应参数
                                    .flatMap(s -> {
                                        JSONObject object = JSONObject.parseObject(s);
                                        WritePropertyMessageReply writePropertyMessageReply = WritePropertyMessageReply.create();
                                        writePropertyMessageReply.setSuccess(true);
                                        writePropertyMessageReply.setMessageId(message.getMessageId());
                                        writePropertyMessageReply.setProperties(new HashMap<>());
                                        writePropertyMessageReply.setDeviceId(imei);
                                        writePropertyMessageReply.getProperties().put("Imei",object.getString("F_TowerIMEI"));
                                        writePropertyMessageReply.getProperties().put("Time",object.getString("F_WriteTime"));
                                        writePropertyMessageReply.getProperties().put("Name",object.getString("F_Name"));
                                        writePropertyMessageReply.getProperties().put("RopeRate",object.getString("F_RopeRate"));
                                        writePropertyMessageReply.getProperties().put("RelatedX",object.getString("F_RelatedX"));
                                        writePropertyMessageReply.getProperties().put("RelatedY",object.getString("F_RelatedY"));
                                        writePropertyMessageReply.getProperties().put("GpsX",object.getString("F_GpsX"));
                                        writePropertyMessageReply.getProperties().put("GpsY",object.getString("F_GpsY"));
                                        writePropertyMessageReply.getProperties().put("FirstHeight",object.getString("F_FirstHeight"));
                                        writePropertyMessageReply.getProperties().put("NodeHeight",object.getString("F_NodeHeight"));
                                        writePropertyMessageReply.getProperties().put("NodeCount",object.getString("F_NodeCount"));
                                        writePropertyMessageReply.getProperties().put("BodyHeight",object.getString("F_BodyHeight"));
                                        writePropertyMessageReply.getProperties().put("TopHeight",object.getString("F_TopHeight"));
                                        writePropertyMessageReply.getProperties().put("ForeLength",object.getString("F_ForeLength"));
                                        writePropertyMessageReply.getProperties().put("RearLength",object.getString("F_RearLength"));
                                        writePropertyMessageReply.getProperties().put("LeftLimit",object.getString("F_LeftLimit"));
                                        writePropertyMessageReply.getProperties().put("RightLimit",object.getString("F_RightLimit"));
                                        writePropertyMessageReply.getProperties().put("NearLimit",object.getString("F_NearLimit"));
                                        writePropertyMessageReply.getProperties().put("FarLimit",object.getString("F_FarLimit"));
                                        writePropertyMessageReply.getProperties().put("HighLimit",object.getString("F_HighLimit"));
                                        writePropertyMessageReply.getProperties().put("TorqueLimit",object.getString("F_TorqueLimit"));
                                        writePropertyMessageReply.getProperties().put("WindLimit",object.getString("F_WindLimit"));
                                        writePropertyMessageReply.getProperties().put("PitchLimit",object.getString("F_PitchLimit"));
                                        writePropertyMessageReply.getProperties().put("RollLimit",object.getString("F_RollLimit"));
                                        return Mono.just(writePropertyMessageReply)
                                                .map(deviceMessage->(R)deviceMessage);
                                    })
                                    // 消息持久化
                                    .flatMap(msg->handler.handleMessage(device,msg)
                                            .thenReturn(msg));
                        })
        );
    }
}
