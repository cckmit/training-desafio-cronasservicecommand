package training.cronasservicecommand.mongodb.job;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "job")
@NoArgsConstructor
public class JobData {
    @Id
    private String id;
    private String url;
    private String interval;
    private String timezone;
}
