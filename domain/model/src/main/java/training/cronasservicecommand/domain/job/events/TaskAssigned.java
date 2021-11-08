package training.cronasservicecommand.domain.job.events;

import training.cronasservicecommand.domain.generic.DomainEvent;

import java.util.Date;

public class TaskAssigned extends DomainEvent {
    private final String taskId;
    private final Date scheduleDate;
    private final Date startDate;
    private final Date endDate;
    private final Long executionTimeSecond;
    private final String httpCode;
    private final String status;

    public TaskAssigned(String taskId, Date scheduleDate, Date startDate, Date endDate, Long executionTimeSecond, String httpCode, String status) {
        super("cas.job.taskassigned");
        this.taskId = taskId;
        this.scheduleDate = scheduleDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executionTimeSecond = executionTimeSecond;
        this.httpCode = httpCode;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getExecutionTimeSecond() {
        return executionTimeSecond;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public String getStatus() {
        return status;
    }
}
