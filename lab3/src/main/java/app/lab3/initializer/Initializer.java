package app.lab3.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.lab3.entity.Album;
import app.lab3.entity.Song;
import app.lab3.service.AlbumService;
import app.lab3.service.SongService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

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
    @Transactional
    private synchronized void init() {
        Album pinkerton = Album.builder()
                .name("Pinkerton")
                .artist("Weezer")
                .genres(new String[]{"Alternative Rock", "Power Pop", "Emo", "Noise Pop"})
                .releaseDate(LocalDate.of(1996, 9, 24))
                .score(3.82)
                .songs(new ArrayList<>())
                .build();
        Album exmilitary = Album.builder()
                .name("Exmilitary")
                .artist("Death Grips")
                .genres(new String[]{"Hardcore Hip Hop", "Industrial Hip Hop", "Abstract Hip Hop", "Experimental Hip Hop"})
                .releaseDate(LocalDate.of(2011, 4, 25))
                .score(4.08)
                .songs(new ArrayList<>())
                .build();
        Album deathconsciousness = Album.builder()
                .name("Deathconsciousness")
                .artist("Have a Nice Life")
                .genres(new String[]{"Post-Punk", "Shoegaze", "Drone", "Post-Rock", "Post-Industrial", "Gothic Rock"})
                .releaseDate(LocalDate.of(2008, 1, 24))
                .score(4.05)
                .songs(new ArrayList<>())
                .build();

        albumService.create(pinkerton);
        albumService.create(exmilitary);
        albumService.create(deathconsciousness);

        Song theGoodLife = Song.builder()
                .name("The Good Life")
                .length("4:19")
                .streams(382910)
                .album(pinkerton)
                .build();
        Song butterfly = Song.builder()
                .name("Butterfly")
                .length("2:53")
                .streams(213221)
                .album(pinkerton)
                .build();
        Song beware = Song.builder()
                .name("Beware")
                .length("5:53")
                .streams(932110)
                .album(exmilitary)
                .build();
        Song cultureShock = Song.builder()
                .name("Culture Shock")
                .length("4:21")
                .streams(516591)
                .album(exmilitary)
                .build();
        Song telefony = Song.builder()
                .name("Telefony")
                .length("4:38")
                .streams(98760)
                .album(deathconsciousness)
                .build();
        Song earthmover = Song.builder()
                .name("Earthmover")
                .length("11:28")
                .streams(46787)
                .album(deathconsciousness)
                .build();

        songService.create(theGoodLife);
        songService.create(butterfly);
        songService.create(beware);
        songService.create(cultureShock);
        songService.create(telefony);
        songService.create(earthmover);
    }
}
