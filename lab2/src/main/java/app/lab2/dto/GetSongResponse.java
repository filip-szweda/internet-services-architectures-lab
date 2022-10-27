package app.lab2.dto;

import app.lab2.entity.Album;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import app.lab2.entity.Song;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSongResponse {
    private Long id;
    private String name;
    private String length;
    private int streams;
    private Album album;

    public static Function<Song, GetSongResponse> entityToDtoMapper() {
        return song -> GetSongResponse.builder()
                .id(song.getId())
                .name(song.getName())
                .length(song.getLength())
                .streams(song.getStreams())
                .album(song.getAlbum())
                .build();
    }
}
