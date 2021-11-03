package training.cronasservicecommand.domain.job.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.cronasservicecommand.domain.common.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobCommand implements Event {

    public static final String COMMAND_NAME = "cas.command.job.createjob";
    //private String jobId;
    private String url;
    private String httpMethod;
    private String requestBody;
    private String interval;
    private String timezone;

    @Override
    public String name() {
        return COMMAND_NAME;
    }
}
