package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String objectId;
    private String name;
    private String description;

    public String getLink() {
        return String.format("/books/%s", objectId);
    }
}
