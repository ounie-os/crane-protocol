package org.company.protocol.crane;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetlinks.core.device.DeviceOperator;
import org.jetlinks.core.device.DeviceState;
import org.jetlinks.core.device.DeviceStateChecker;
import reactor.core.publisher.Mono;

@Slf4j(topic = "system.crane")
public class CraneDeviceStateChecker implements DeviceStateChecker {

    @Override
    public @NotNull Mono<Byte> checkState(@NotNull DeviceOperator deviceOperator) {

        return Mono.just(DeviceState.online);
    }
}
