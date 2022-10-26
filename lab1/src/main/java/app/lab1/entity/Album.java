package app.lab1.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Album implements Serializable {
    private Long id;
    private String name;
    private String artist;
    private String[] genres;
    private LocalDate releaseDate;
    private double score;
}
