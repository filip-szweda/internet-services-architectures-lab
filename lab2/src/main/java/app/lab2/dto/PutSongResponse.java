package app.lab2.dto;

import app.lab2.entity.Album;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutSongResponse {
    private String name;
    private String length;
    private int streams;
    private Album album;
}
