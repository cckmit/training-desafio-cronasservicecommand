package training.cronasservicecommand.usecase.job;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.job.Job;
import training.cronasservicecommand.domain.job.command.AddTaskCommand;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

import java.util.function.Function;

@RequiredArgsConstructor
public class AddTaskUseCase implements Function<AddTaskCommand, Flux<DomainEvent>> {

    private final StoredEventRepository repository;

    @Override
    public Flux<DomainEvent> apply(AddTaskCommand command) {
        return repository.getById(command.getJobId())
                .collectList()
                .map(domainEvents -> Job.from(command.getJobId(), domainEvents))
                .map(job -> {
                    job.addTask(command.getId(), command.getScheduleDate(),command.getStartDate(),command.getEndDate() ,command.getExecutionTimeSecond(),command.getHttpCode(),command.getStatus());
                    return job;
                }).flatMapMany(job -> job.getUncommittedChanges());
    }

}
