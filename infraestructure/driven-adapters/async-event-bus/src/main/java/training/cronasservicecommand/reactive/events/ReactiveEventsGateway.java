package training.cronasservicecommand.reactive.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.async.impl.config.annotations.EnableDomainEventBus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.EventsGateway;
import training.cronasservicecommand.domain.generic.Command;

import java.util.UUID;
import java.util.logging.Level;

import static reactor.core.publisher.Mono.from;

@Log
@Component
@EnableDomainEventBus
@RequiredArgsConstructor
//Permite personalizar la emisión de eventos, enriquecerla o interceptarla.
// Por defecto delega el proceso en reactive-commons.
public class ReactiveEventsGateway implements EventsGateway {

    private final DomainEventBus domainEventBus;

    @Override
    public Mono<Void> emit(Command command) {
        log.log(Level.INFO, "Emitiendo evento de interno: {0}: {1}", new String[]{command.getType(), command.toString()});
        return from(domainEventBus.emit(new org.reactivecommons.api.domain.DomainEvent<>(command.getType(), UUID.randomUUID().toString(), command)));
    }
}
