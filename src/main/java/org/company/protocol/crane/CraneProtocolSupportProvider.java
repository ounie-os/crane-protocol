package org.company.protocol.crane;

import lombok.extern.slf4j.Slf4j;
import org.jetlinks.core.ProtocolSupport;
import org.jetlinks.core.defaults.CompositeProtocolSupport;
import org.jetlinks.core.device.DeviceRegistry;
import org.jetlinks.core.message.codec.DefaultTransport;
import org.jetlinks.core.metadata.DefaultConfigMetadata;
import org.jetlinks.core.metadata.types.StringType;
import org.jetlinks.core.server.session.DeviceSessionManager;
import org.jetlinks.core.spi.ProtocolSupportProvider;
import org.jetlinks.core.spi.ServiceContext;
import org.jetlinks.supports.official.JetLinksDeviceMetadataCodec;
import org.jetlinks.supports.server.DecodedClientMessageHandler;
import reactor.core.publisher.Mono;

@Slf4j(topic = "system.crane")
public class CraneProtocolSupportProvider implements ProtocolSupportProvider {

    private static final DefaultConfigMetadata httpRequest = new DefaultConfigMetadata("Http请求配置", "")
            .add("url", "url", " http请求地址", new StringType());

    @Override
    public Mono<? extends ProtocolSupport> create(ServiceContext serviceContext) {
        CompositeProtocolSupport support = new CompositeProtocolSupport();

        support.setId("crane");
        support.setName("塔吊设备");
        support.setDescription("塔吊设备协议");
        support.setMetadataCodec(new JetLinksDeviceMetadataCodec());

        serviceContext.getService(DeviceRegistry.class)
                .ifPresent(deviceRegistry -> {
                    CraneDeviceMessageCodec codec = new CraneDeviceMessageCodec(deviceRegistry);
                    support.addMessageCodecSupport(DefaultTransport.HTTP, () -> Mono.just(codec));
                });

//            CraneDeviceMessageCodec codec = new CraneDeviceMessageCodec();
//            support.addMessageCodecSupport(DefaultTransport.HTTP, () -> Mono.just(codec));
        support.addConfigMetadata(DefaultTransport.HTTP, httpRequest);
        CraneDeviceStateChecker httpDeviceStateChecker = new CraneDeviceStateChecker();
        support.setDeviceStateChecker(httpDeviceStateChecker);

        serviceContext.getService(DecodedClientMessageHandler.class)
                .ifPresent(handler -> support.addMessageSenderInterceptor(new CraneMessageSenderInterceptor(handler)));

        return Mono.just(support);
    }
}
