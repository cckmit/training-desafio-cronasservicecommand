package training.cronasservicecommand.domain.job.gateway;

import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;
import training.cronasservicecommand.domain.job.events.JobCreated;

public interface JobMessageGateway {
    Mono<Void> send(Event event);
}
