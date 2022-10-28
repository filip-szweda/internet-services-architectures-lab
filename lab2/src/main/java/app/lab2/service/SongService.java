package app.lab2.service;

import app.lab2.entity.Album;
import app.lab2.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab2.entity.Song;
import app.lab2.repository.SongRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    @Autowired
    public SongService(SongRepository repository, AlbumRepository albumRepository) {
        this.songRepository = repository;
        this.albumRepository = albumRepository;
    }

    public Optional<Song> find(Long id) {
        return songRepository.findById(id);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Transactional
    public List<Song> findAllByAlbumId(Long courseId) {
        return songRepository.findAllByAlbumId(courseId);
    }

    @Transactional
    public Song create(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public void update(Long id, Song song) {
        find(id).ifPresentOrElse(
                (original) -> {
                    original.setName(song.getName());
                    original.setLength(song.getLength());
                    original.setStreams(song.getStreams());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update song");
                }
        );
    }

    @Transactional
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Transactional
    public void addAlbumToSong(Long albumId, Song song) {
        Optional<Album> course = albumRepository.findById(albumId);
        course.ifPresentOrElse(
                song::setAlbum,
                () -> {
                    throw new IllegalArgumentException("Cannot add song to album");
                });
    }
}
