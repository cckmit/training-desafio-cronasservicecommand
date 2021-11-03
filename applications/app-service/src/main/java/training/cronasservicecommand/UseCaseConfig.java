package training.cronasservicecommand;

import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.job.gateway.JobMessageGateway;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;
import training.cronasservicecommand.usecase.job.GenerateExecutionJobUseCase;
import training.cronasservicecommand.usecase.job.RequestExecutionJobUseCase;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateJobUseCase createJobUseCase(StoredEventRepository repository, JobMessageGateway jobMessageGateway) {
        return new CreateJobUseCase(repository, jobMessageGateway);
    }

    @Bean
    public GenerateExecutionJobUseCase generateExecutionJobUseCase(StoredEventRepository repository) {
        return new GenerateExecutionJobUseCase(repository);
    }

    @Bean
    public RequestExecutionJobUseCase requestExecutionJobUseCase(StoredEventRepository repository) {
        return new RequestExecutionJobUseCase(repository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

}
