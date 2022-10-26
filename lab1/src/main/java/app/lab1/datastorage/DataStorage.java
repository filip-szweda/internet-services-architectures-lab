package app.lab1.datastorage;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import app.lab1.entity.Album;
import app.lab1.entity.Song;
import app.lab1.utility.Cloning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@Component
public class DataStorage {
    private Set<Album> albums = new HashSet<>();
    private Set<Song> songs = new HashSet<>();
    public synchronized List<Album> findAllAlbums() {
        return new ArrayList<>(albums);
    }
    
    public Optional<Album> findByIDAlbum(Long id) {
        return albums.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public synchronized void saveNewAlbum(Album album) throws IllegalArgumentException {
        album.setId(findAllAlbums().stream().mapToLong(Album::getId).max().orElse(0) + 1);
        albums.add(album);
    }

    public synchronized void deleteExistingAlbum(Long id) throws IllegalArgumentException {
        findByIDAlbum(id).ifPresentOrElse(
                original -> albums.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The album with id \"%d\" does not exist", id));
                });
    }

    public synchronized List<Song> findAllSongs() {
        return songs.stream().map(Cloning::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Song> findByIDSong(Long id) {
        return songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public synchronized void saveNewSong(Song song) throws IllegalArgumentException {
        song.setId(findAllSongs().stream().mapToLong(Song::getId).max().orElse(0) + 1);
        songs.add(song);
    }

    public synchronized void deleteExistingSong(Long id) throws IllegalArgumentException {
        findByIDSong(id).ifPresentOrElse(
                original -> {
                    songs.remove(original);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The song with id \"%d\" does not exist", id));
                });
    }
}
