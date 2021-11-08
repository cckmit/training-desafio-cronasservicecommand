package training.cronasservicecommand;

import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;
import training.cronasservicecommand.usecase.job.AddTaskUseCase;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateJobUseCase createJobUseCase() {
        return new CreateJobUseCase();
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(StoredEventRepository repository) {
        return new AddTaskUseCase(repository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

}
