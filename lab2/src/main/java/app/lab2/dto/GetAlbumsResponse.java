package app.lab2.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import app.lab2.entity.Album;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAlbumsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class AlbumEntry {
        private Long id;
        private String name;
        private String artist;
        private String[] genres;
        private LocalDate releaseDate;
        private double score;
    }

    @Singular
    private List<AlbumEntry> albums;

    public static Function<Collection<Album>, GetAlbumsResponse> entityToDtoMapper() {
        return albums -> {
            GetAlbumsResponseBuilder response = GetAlbumsResponse.builder();
            albums.stream()
                    .map(album -> AlbumEntry.builder()
                            .id(album.getId())
                            .name(album.getName())
                            .artist(album.getArtist())
                            .genres(album.getGenres())
                            .releaseDate(album.getReleaseDate())
                            .score(album.getScore())
                            .build())
                    .forEach(response::album);
            return response.build();
        };
    }
}
