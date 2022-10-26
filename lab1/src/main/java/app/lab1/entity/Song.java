package app.lab1.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Song implements Serializable {
    private Long id;
    private String name;
    private String length;
    private int streams;
    private Album album;
}
