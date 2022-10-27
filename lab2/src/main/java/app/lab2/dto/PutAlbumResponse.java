package app.lab2.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutAlbumResponse {
    private String name;
    private String artist;
    private String[] genres;
    private LocalDate releaseDate;
    private double score;
}
