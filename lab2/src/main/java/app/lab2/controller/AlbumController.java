package app.lab2.controller;

import app.lab2.dto.*;
import app.lab2.entity.Album;
import app.lab2.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/albums")
public class AlbumController {
    private AlbumService albumService;
    
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<GetAlbumsResponse> getAlbums() {
        List<Album> all = albumService.findAll();
        Function<Collection<Album>, GetAlbumsResponse> mapper = GetAlbumsResponse.entityToDtoMapper();
        GetAlbumsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetAlbumResponse> getAlbum(@PathVariable("name") Long id){
        return albumService.find(id)
                .map(value -> ResponseEntity.ok(GetAlbumResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostAlbumRequest request, UriComponentsBuilder builder){
        Album album = PostAlbumRequest.dtoToEntityMapper().apply(request);
        Optional<Album> tmp = albumService.find(album.getId());
        if(tmp.isEmpty()){
            albumService.create(album);
            return ResponseEntity.created(builder.pathSegment("api", "albums", "{name}")
                    .buildAndExpand(album.getName()).toUri()).build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable("id") Long id){
        if(albumService.find(id).isPresent()){
            albumService.delete(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

