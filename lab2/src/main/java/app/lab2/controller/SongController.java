package app.lab2.controller;

import app.lab2.dto.*;
import app.lab2.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import app.lab2.entity.Song;
import app.lab2.service.SongService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/songs")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs() {
        List<Song> all = songService.findAll();
        Function<Collection<Song>, GetSongsResponse> mapper = GetSongsResponse.entityToDtoMapper();
        GetSongsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSongResponse> getSong(@PathVariable("id") long id) {
        return songService.findByID(id)
                .map(value -> ResponseEntity.ok(GetSongResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSong(@RequestBody PostSongRequest request, UriComponentsBuilder builder) {
        Song song = PostSongRequest.dtoToEntityMapper().apply(request);
        song = songService.saveNew(song);
        return ResponseEntity.created(builder.pathSegment("api", "songs", "{id}").buildAndExpand(song.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable("id") long id) {
        if (songService.findByID(id).isPresent()) {
            songService.deleteExisting(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
