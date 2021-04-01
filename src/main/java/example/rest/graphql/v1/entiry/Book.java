package example.rest.graphql.v1.entiry;

import lombok.Data;

/**
 * @author lr
 * @date 2021/3/15
 */
@Data
public class Book {
    private Integer id;
    private String name;
    private int pageCount;
    private Author author;

}
