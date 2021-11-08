package training.cronasservicecommand.domain.job.gateway;

import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.generic.DomainEvent;

public interface JobMessageGateway {
    Mono<Void> send(DomainEvent event);
}
