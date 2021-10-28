package springproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * The data object.
 *
 * @author Evgeniy Presnov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"book_id", "author", "title", "price"})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("book_id")
    private int id;

    private String author;

    private String title;

    private Double price;
}
