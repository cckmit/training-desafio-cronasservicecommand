package training.cronasservicecommand.domain.job.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.job.JobExecution;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobGenerateExecutionPlan implements Event {

    public static final String EVENT_NAME = "cas.events.job.generateexecutionplan";
    private String jobId;
    private List<JobExecution> executions;

    @Override
    public String name() {
        return EVENT_NAME;
    }
}
