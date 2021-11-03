package training.cronasservicecommand.reactive;

import lombok.*;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.job.JobExecution;
import training.cronasservicecommand.domain.job.events.JobGenerateExecutionPlan;
import training.cronasservicecommand.domain.job.events.JobRequestExecution;
import training.cronasservicecommand.usecase.job.GenerateExecutionJobUseCase;
import training.cronasservicecommand.usecase.job.RequestExecutionJobUseCase;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMessageListeners
@RequiredArgsConstructor
public class CommandHandlersConfig {

    private final GenerateExecutionJobUseCase generateExecutionJobUseCase;
    private final RequestExecutionJobUseCase requestExecutionJobUseCase;

    @Bean
    public HandlerRegistry registry() {
        return HandlerRegistry.register()
                .handleCommand(JobGenerateExecutionPlan.EVENT_NAME, event -> generateExecutionJobUseCase.execute(event.getData().toDomainEntity(event.getData())), JobGenerateExcecutionDTO.class)
                .handleCommand(JobRequestExecution.EVENT_NAME, event -> requestExecutionJobUseCase.execute(event.getData().toDomainEntity(event.getData())), JobRequestExecutionDTO.class);
    }

    @Data
    public static class JobGenerateExcecutionDTO{
        private String jobId;
        private List<JobExecutionDTO> executions;

        public JobGenerateExecutionPlan toDomainEntity(JobGenerateExcecutionDTO dto){
            List<JobExecution> jobExecutions = dto.executions.stream()
                    .map(d -> JobExecution.builder()
                            .id(d.getId())
                            .scheduleDate(d.getScheduleDate())
                            .startDate(d.getStartDate())
                            .endDate(d.getEndDate())
                            .executionTimeSecond(d.getExecutionTimeSecond())
                            .httpCode(d.getHttpCode())
                            .status(d.getStatus())
                            .build()).collect(Collectors.toList());
            return new JobGenerateExecutionPlan(dto.jobId,jobExecutions);
        }
    }

    @Data
    public static class JobExecutionDTO {
        private String id;
        private Date scheduleDate;
        private Date startDate;
        private Date endDate;
        private Long executionTimeSecond;
        private String httpCode;
        private String status;
    }

    @Data
    public static class JobRequestExecutionDTO{
        private String jobId;
        private JobExecutionDTO executions;

        public JobRequestExecution toDomainEntity(JobRequestExecutionDTO dto){
            return new JobRequestExecution(dto.jobId,JobExecution.builder()
                    .id(dto.executions.getId())
                    .scheduleDate(dto.executions.getScheduleDate())
                    .startDate(dto.executions.getStartDate())
                    .endDate(dto.executions.getEndDate())
                    .executionTimeSecond(dto.executions.getExecutionTimeSecond())
                    .httpCode(dto.executions.getHttpCode())
                    .status(dto.executions.getStatus())
                    .build());
        }
    }
}
