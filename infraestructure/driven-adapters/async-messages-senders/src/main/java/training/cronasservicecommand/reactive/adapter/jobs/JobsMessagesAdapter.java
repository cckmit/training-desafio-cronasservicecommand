package training.cronasservicecommand.reactive.adapter.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.job.gateway.JobMessageGateway;

import java.util.UUID;
import java.util.logging.Level;

@Log
@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class JobsMessagesAdapter implements JobMessageGateway {

    static final String JOB_APP_NAME = "cronasserviceprocess";
    private final DirectAsyncGateway asyncGateway;

//    @Override
//    public Mono<Void> send(Event event) {
//        log.log(Level.INFO, "Enviando comando: {0} ", new String[] {event.name()});
//        final Command<Event> command = new Command<>(event.name(), uuid(), event);
//        return asyncGateway.sendCommand(command, JOB_APP_NAME);
//    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Mono<Void> send(DomainEvent event) {
        log.log(Level.INFO, "Enviando comando: {0} ", new String[] {event.getType()});
        final Command<DomainEvent> command = new Command<>(event.getType(), uuid(), event);
        return asyncGateway.sendCommand(command, JOB_APP_NAME);
    }
}
