package app.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumDTO {
    private String name;
    private String artist;
    private String[] genres;
    private LocalDate releaseDate;
    private double score;
}
