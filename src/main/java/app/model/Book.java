package app.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
    int id;
    String name;
    String description;
}
