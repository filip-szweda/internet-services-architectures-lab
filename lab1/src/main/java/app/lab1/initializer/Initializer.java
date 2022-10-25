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
import java.time.LocalDate;
import java.time.LocalTime;

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

        albumService.saveNew(pinkerton);
        albumService.saveNew(exmilitary);
        albumService.saveNew(deathconsciousness);

        Song theGoodLife = Song.builder()
                .name("The Good Life")
                .length(LocalTime.parse("4:19"))
                .streams(382910)
                .album(pinkerton)
                .build();
        Song butterfly = Song.builder()
                .name("Butterfly")
                .length(LocalTime.parse("2:53"))
                .streams(213221)
                .album(pinkerton)
                .build();
        Song beware = Song.builder()
                .name("Beware")
                .length(LocalTime.parse("5:53"))
                .streams(932110)
                .album(exmilitary)
                .build();
        Song cultureShock = Song.builder()
                .name("Culture Shock")
                .length(LocalTime.parse("4:21"))
                .streams(516591)
                .album(exmilitary)
                .build();
        Song telefony = Song.builder()
                .name("Telefony")
                .length(LocalTime.parse("4:38"))
                .streams(98760)
                .album(deathconsciousness)
                .build();
        Song earthmover = Song.builder()
                .name("Earthmover")
                .length(LocalTime.parse("11:28"))
                .streams(46787)
                .album(deathconsciousness)
                .build();

        songService.saveNew(theGoodLife);
        songService.saveNew(butterfly);
        songService.saveNew(beware);
        songService.saveNew(cultureShock);
        songService.saveNew(telefony);
        songService.saveNew(earthmover);
    }
}
