package training.cronasservicecommand.domain.job;

import training.cronasservicecommand.domain.generic.AggregateRoot;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.generic.EventChange;
import training.cronasservicecommand.domain.job.events.JobCreated;
import training.cronasservicecommand.domain.job.events.TaskAssigned;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Job extends AggregateRoot implements EventChange {

    public enum JobStatus {
        ENABLE,
        DISABLE
    }

    private Map<String, Task> tasks;
    private String name;
    private String url;
    private String httpMethod;
    private String requestBody;
    private String interval;
    private String timezone;
    private Date lastExecutionDate;
    private JobStatus status;
    private String email;

    public Job(String id, String name, String url, String httpMethod, String requestBody, String interval, String timezone, String email){
        super(id);
        appendChange(new JobCreated(name,url,httpMethod,requestBody,interval,timezone, email)).apply();
    }

    public void addTask(String taskId, Date scheduleDate, Date startDate, Date endDate, Long executionTimeSecond, String httpCode, String status){
        appendChange(new TaskAssigned(taskId, scheduleDate, startDate, endDate, executionTimeSecond, httpCode, status)).apply();
    }

    private Job(String id){
        super(id);
        subscribe(this);
        listener((JobCreated event) -> {
            this.name = event.getName();
            this.url = event.getUrl();
            this.httpMethod = event.getHttpMethod();
            this.requestBody = event.getRequestBody();
            this.interval = event.getInterval();
            this.timezone = event.getTimezone();
            this.status = JobStatus.ENABLE;
        });
        listener((TaskAssigned event) -> {
            Task task = new Task(event.getTaskId(), event.getScheduleDate(), event.getStartDate(),event.getEndDate(), event.getExecutionTimeSecond(), event.getHttpCode(), event.getStatus());
            this.lastExecutionDate = event.getEndDate();
            this.tasks.put(event.getTaskId(), task);
        });
    }

    public static Job from(String id, List<DomainEvent> events){
        Job job = new Job(id);
        events.forEach(job::applyEvent);
        return job;
    }

    public Task getTasksById(String taskId) {
        return tasks.get(taskId);
    }

    public Map<String, Task> tasks() {
        return tasks;
    }

    public String name() {
        return name;
    }

    public String url() {
        return url;
    }

    public String httpMethod() {
        return httpMethod;
    }

    public String requestBody() {
        return requestBody;
    }

    public String interval() {
        return interval;
    }

    public String timezone() {
        return timezone;
    }

    public Date lastExecutionDate() {
        return lastExecutionDate;
    }

    public JobStatus status() {
        return status;
    }
}
