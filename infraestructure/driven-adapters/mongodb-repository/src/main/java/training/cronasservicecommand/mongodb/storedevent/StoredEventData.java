package training.cronasservicecommand.mongodb.storedevent;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "job")
@NoArgsConstructor
public class StoredEventData {
    @Id
    public String id;
    public String jobId;
    public String name;
    private Object body;
    private Date createDate;
}
