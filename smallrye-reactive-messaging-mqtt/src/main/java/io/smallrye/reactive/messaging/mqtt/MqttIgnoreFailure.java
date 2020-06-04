package io.smallrye.reactive.messaging.mqtt;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;

public class MqttIgnoreFailure implements MqttFailureHandler {

    private final Logger logger;
    private final String channel;

    public MqttIgnoreFailure(Logger logger, String channel) {
        this.logger = logger;
        this.channel = channel;
    }

    @Override
    public CompletionStage<Void> handle(Throwable reason) {
        // We commit the message, log and continue
        logger.warn("A message sent to channel `{}` has been nacked, ignored failure is: {}.", channel,
                reason.getMessage());
        logger.debug("The full ignored failure is", reason);
        return CompletableFuture.completedFuture(null);
    }
}