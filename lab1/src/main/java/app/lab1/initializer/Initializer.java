package app.lab1.initializer;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.lab1.entity.Album;
import app.lab1.entity.Song;
import app.lab1.repository.AlbumRepository;
import app.lab1.repository.SongRepository;
import app.lab1.service.AlbumService;
import app.lab1.service.SongService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class Initializer {
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public Initializer(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @PostConstruct
    private synchronized void init() {
    Album pinkerton = Album.builder()
            .
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
