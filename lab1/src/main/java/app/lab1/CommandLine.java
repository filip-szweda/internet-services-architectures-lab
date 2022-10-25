package app.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import app.lab1.service.AlbumService;

@Component
public class CommandLine implements CommandLineRunner {

    private AlbumService albumService;

    @Autowired
    public CommandLine(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public void run(String... args) throws Exception {
        albumService.findAll().forEach(System.out::println);
    }

}
