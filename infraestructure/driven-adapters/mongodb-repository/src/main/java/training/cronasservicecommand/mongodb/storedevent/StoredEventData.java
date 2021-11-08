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
    private String id;
    private String aggregateId;
    private String name;
    private String eventBody;
    private Date createDate;
}
