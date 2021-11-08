package training.cronasservicecommand.domain.job.command;

import training.cronasservicecommand.domain.generic.Command;

import java.util.Date;

public class AddTaskCommand extends Command {
    private String jobId;
    private String id;
    private Date scheduleDate;
    private Date startDate;
    private Date endDate;
    private Long executionTimeSecond;
    private String httpCode;
    private String status;

    public AddTaskCommand() {
        setType("cas.job.addtaskcommand");
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getExecutionTimeSecond() {
        return executionTimeSecond;
    }

    public void setExecutionTimeSecond(Long executionTimeSecond) {
        this.executionTimeSecond = executionTimeSecond;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
