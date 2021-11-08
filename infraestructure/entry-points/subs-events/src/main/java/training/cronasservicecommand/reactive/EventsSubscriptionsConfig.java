package training.cronasservicecommand.reactive;

import lombok.RequiredArgsConstructor;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.job.command.AddTaskCommand;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;
import training.cronasservicecommand.usecase.generic.UseCaseHandle;
import training.cronasservicecommand.usecase.job.AddTaskUseCase;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;

import static org.reactivecommons.async.api.HandlerRegistry.register;

@Configuration
@EnableMessageListeners
@RequiredArgsConstructor
public class EventsSubscriptionsConfig extends UseCaseHandle {

    private final String CREATE_JOB_COMMAND = "cas.job.createjobcommand";
    private final String ADD_TASK_COMMAND = "cas.job.addtaskcommand";
    private final CreateJobUseCase createJobUseCase;
    private final AddTaskUseCase addTaskUseCase;

    @Bean
    public HandlerRegistry eventSubscriptions() {
        return register()
            .listenEvent(CREATE_JOB_COMMAND, command -> createJobUseCase.apply(command.getData()).collectList()
                            .flatMap(domainEvents -> saveJob(command.getData().getJobId(), domainEvents)), CreateJobCommand.class)
            .listenEvent(ADD_TASK_COMMAND, addTaskCommand -> addTaskUseCase.apply(addTaskCommand.getData()).collectList()
                        .flatMap(domainEvents -> saveJob(addTaskCommand.getData().getJobId(), domainEvents)), AddTaskCommand.class);
    }

}
