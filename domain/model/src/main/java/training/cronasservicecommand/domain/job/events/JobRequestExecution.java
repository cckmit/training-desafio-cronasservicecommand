package training.cronasservicecommand.domain.job.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.job.JobExecution;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestExecution implements Event {

    public static final String EVENT_NAME = "cas.events.job.jobrequestexecution";
    private String jobId;
    private JobExecution executions;

    @Override
    public String name() {
        return EVENT_NAME;
    }
}
