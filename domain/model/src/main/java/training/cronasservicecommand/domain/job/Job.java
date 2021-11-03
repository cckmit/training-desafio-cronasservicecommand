package training.cronasservicecommand.domain.job;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Job {
    private final String id;
    private final String url;
    private final String httpMethod;
    private final String requestBody;
    private final String interval;
    private final String timezone;
    private final Date lastExecutionDate;
    private final List<JobExecution> executions;
}
