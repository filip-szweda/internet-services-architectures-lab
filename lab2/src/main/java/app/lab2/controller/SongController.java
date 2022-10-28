package app.lab2.controller;

import app.lab2.dto.*;
import app.lab2.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import app.lab2.entity.Song;
import app.lab2.service.SongService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/songs")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody PostSongRequest postSongRequest, UriComponentsBuilder builder) {
        Song song = Song.builder()
                .name(postSongRequest.getName())
                .length(postSongRequest.getLength())
                .streams(postSongRequest.getStreams())
                .build();
        song = songService.create(song);
        return ResponseEntity.created(builder.pathSegment("api", "song", "{id}")
                .buildAndExpand(song.getId()).toUri()).build();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSongsResponse> readAll() {
        List<Song> songs = songService.findAll();
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSongResponse> read(@PathVariable(name = "id") Long id) {
        Optional<Song> response = songService.find(id);
        if (response.isPresent()) {
            Song song = response.get();
            return ResponseEntity.ok(GetSongResponse.builder()
                    .id(song.getId())
                    .name(song.getName())
                    .length(song.getLength())
                    .streams(song.getStreams())
                    .build());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id,
                                       @RequestBody PutSongResponse songResponse) {
        try {
            Song song = Song.builder()
                    .name(songResponse.getName())
                    .length(songResponse.getLength())
                    .streams(songResponse.getStreams())
                    .build();
            songService.update(id, song);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        Optional<Song> song = songService.find(id);
        if (song.isPresent()) {
            songService.delete(song.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addAlbumToSong(
            @RequestBody PatchSongAlbumAddResponse response,
            @PathVariable(name="id" ) Long id) {
        Optional<Song> song = songService.find(id);
        if (song.isPresent()) {
            songService.addAlbumToSong(response.getAlbumId(), song.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
