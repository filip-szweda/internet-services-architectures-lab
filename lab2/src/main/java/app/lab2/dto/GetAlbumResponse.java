package app.lab2.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import app.lab2.entity.Album;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAlbumResponse {
    private Long id;
    private String name;
    private String artist;
    private String[] genres;
    private LocalDate releaseDate;
    private double score;

    public static Function<Album, GetAlbumResponse> entityToDtoMapper() {
        return album -> GetAlbumResponse.builder()
                .id(album.getId())
                .name(album.getName())
                .artist(album.getArtist())
                .genres(album.getGenres())
                .releaseDate(album.getReleaseDate())
                .score(album.getScore())
                .build();
    }
}
