package app.lab3albums.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.lab3albums.entity.Album;
import app.lab3albums.service.AlbumService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class Initializer {
    private final AlbumService albumService;

    @Autowired
    public Initializer(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostConstruct
    @Transactional
    private synchronized void init() {
        Album pinkerton = Album.builder()
                .name("Pinkerton")
                .artist("Weezer")
                .genres(new String[]{"Alternative Rock", "Power Pop", "Emo", "Noise Pop"})
                .releaseDate(LocalDate.of(1996, 9, 24))
                .score(3.82)
                .build();
        Album exmilitary = Album.builder()
                .name("Exmilitary")
                .artist("Death Grips")
                .genres(new String[]{"Hardcore Hip Hop", "Industrial Hip Hop", "Abstract Hip Hop", "Experimental Hip Hop"})
                .releaseDate(LocalDate.of(2011, 4, 25))
                .score(4.08)
                .build();
        Album deathconsciousness = Album.builder()
                .name("Deathconsciousness")
                .artist("Have a Nice Life")
                .genres(new String[]{"Post-Punk", "Shoegaze", "Drone", "Post-Rock", "Post-Industrial", "Gothic Rock"})
                .releaseDate(LocalDate.of(2008, 1, 24))
                .score(4.05)
                .build();

        albumService.create(pinkerton);
        albumService.create(exmilitary);
        albumService.create(deathconsciousness);
    }
}
