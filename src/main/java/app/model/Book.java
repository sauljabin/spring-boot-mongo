package app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String objectId;
    private String title;
    private String description;

    public String getLink() {
        return String.format("/books/%s", objectId);
    }
}
