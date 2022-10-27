package app.lab2.dto;

import app.lab2.entity.Album;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {
    private String name;
    private String length;
    private int streams;
    private Album album;
}
