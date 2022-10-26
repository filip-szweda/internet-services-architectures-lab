package app.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import app.lab2.entity.Song;
import app.lab2.service.AlbumService;
import app.lab2.service.SongService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private AlbumService albumService;
    private SongService songService;

    @Autowired
    public CommandLine(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean exit = false;
        while(!exit) {
            System.out.println(
                """
                
                1. List all albums
                2. List all songs
                3. Add new song
                4. Delete existing song
                5. Stop the application
                """
            );
            Scanner optionInput = new Scanner(System.in);
            int option = optionInput.nextInt();
            switch (option) {
                case 1 -> {
                    albumService.findAll().forEach(System.out::println);
                }
                case 2 -> {
                    songService.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    Scanner songInput = new Scanner(System.in);
                    System.out.print("Name: ");
                    String name = songInput.nextLine();
                    System.out.print("Length: ");
                    String length = songInput.nextLine();
                    System.out.print("Streams: ");
                    int streams = songInput.nextInt();
                    System.out.print("Album id: ");
                    long albumId = songInput.nextLong();
                    albumService.findByID(albumId).ifPresentOrElse(
                        (album) -> {
                            Song song = Song.builder()
                            .name(name)
                            .length(length)
                            .streams(streams)
                            .album(album)
                            .build();
                            songService.saveNew(song);
                        },
                        () -> { System.out.println("<Invalid album id>"); }
                    );
                }
                case 4 -> {
                    Scanner songInput = new Scanner(System.in);
                    System.out.print("Song id: ");
                    long songId = songInput.nextLong();
                    try {
                        songService.deleteExisting(songId);
                    }
                    catch(Exception e) {
                        System.out.println("<Invalid song id>");
                    }
                }
                case 5 -> exit = true;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
