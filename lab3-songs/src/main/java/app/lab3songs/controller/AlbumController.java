package app.lab3songs.controller;

import app.lab3songs.dto.*;
import app.lab3songs.entity.Album;
import app.lab3songs.entity.Song;
import app.lab3songs.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/albums")
public class AlbumController {
    private AlbumService albumService;
    
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody PostAlbumRequest postAlbumRequest, UriComponentsBuilder builder) {
        Album album = Album.builder().build();
        album = albumService.create(album);
        return ResponseEntity.created(builder.pathSegment("api", "album", "{id}")
                .buildAndExpand(album.getId()).toUri()).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        Optional<Album> album = albumService.find(id);
        if (album.isPresent()) {
            albumService.delete(album.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{albumId}/songs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSongsResponse> readAllByAlbumId(@PathVariable(name="albumId") Long albumId) {
        List<Song> songs = albumService.findAllByAlbumId(albumId);
        if (songs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                GetSongsResponse.builder()
                        .songs(
                                songs.stream().map(
                                        (song) -> GetSongResponse.builder()
                                                .id(song.getId())
                                                .name(song.getName())
                                                .length(song.getLength())
                                                .streams(song.getStreams())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }
}

