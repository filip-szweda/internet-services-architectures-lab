package app.lab2.dto;

import app.lab2.entity.Album;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import app.lab2.entity.Song;

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
public class GetSongsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class SongEntry {
        private Long id;
        private String name;
        private String length;
        private int streams;
        private Album album;
    }

    @Singular
    private List<SongEntry> songs;

    public static Function<Collection<Song>, GetSongsResponse> entityToDtoMapper() {
        return songs -> {
            GetSongsResponseBuilder response = GetSongsResponse.builder();
            songs.stream()
                    .map(song -> SongEntry.builder()
                            .id(song.getId())
                            .name(song.getName())
                            .length(song.getLength())
                            .streams(song.getStreams())
                            .album(song.getAlbum())
                            .build())
                    .forEach(response::song);
            return response.build();
        };
    }
}
