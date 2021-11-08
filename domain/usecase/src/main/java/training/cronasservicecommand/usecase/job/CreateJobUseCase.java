package training.cronasservicecommand.usecase.job;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.job.Job;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class CreateJobUseCase implements Function<CreateJobCommand, Flux<DomainEvent>> {
    @Override
    public Flux<DomainEvent> apply(CreateJobCommand command) {
        Job job = new Job(command.getJobId(),command.getName(),command.getUrl(),command.getHttpMethod(),command.getRequestBody(),command.getInterval(),command.getTimezone(),command.getEmail());
        return job.getUncommittedChanges();
    }
}
