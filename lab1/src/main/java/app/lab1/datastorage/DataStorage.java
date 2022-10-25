package app.lab1.datastorage;

import app.lab1.entity.Album;
import app.lab1.entity.Song;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStorage {
    private Set<Album> albums = new HashSet<>();
    private Set<Song> songs = new HashSet<>();
    public synchronized List<Album> findAllAlbums() {
        return new ArrayList<>(albums);
    }
    
    public Optional<Album> findAlbum(String name) {
        return albums.stream()
                .filter(album -> album.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createAlbum(Album album) throws IllegalArgumentException {
        findAlbum(album.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The album name \"%s\" is not unique", album.getName()));
                },
                () -> albums.add(album));
    }

    public synchronized List<Song> findAllSongs() {
        return songs.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Song> findSong(Long id) {
        return songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createSong(Song song) throws IllegalArgumentException {
        song.setId(findAllSongs().stream().mapToLong(Song::getId).max().orElse(0) + 1);
        songs.add(song);
    }

    public synchronized void updateSong(Song song) throws IllegalArgumentException {
        findSong(song.getId()).ifPresentOrElse(
                original -> {
                    songs.remove(original);
                    songs.add(song);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The song with id \"%d\" does not exist", song.getId()));
                });
    }

    public synchronized void deleteSong(Long id) throws IllegalArgumentException {
        findSong(id).ifPresentOrElse(
                original -> songs.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The song with id \"%d\" does not exist", id));
                });
    }
    
    public Stream<Song> getSongStream() {
        return songs.stream();
    }
}
