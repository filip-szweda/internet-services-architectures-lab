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
public class PostAlbumRequest {
    private String name;
    private String artist;
    private String[] genres;
    private LocalDate releaseDate;
    private double score;

    public static Function<PostAlbumRequest, Album> dtoToEntityMapper() {
        return request -> Album.builder()
                .name(request.getName())
                .artist(request.getArtist())
                .genres(request.getGenres())
                .releaseDate(request.getReleaseDate())
                .score(request.getScore())
                .build();
    }

}
