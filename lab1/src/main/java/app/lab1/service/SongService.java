package app.lab1.service;

import app.lab1.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab1.entity.Song;
import app.lab1.repository.SongRepository;

import java.util.List;
import java.util.Optional;
@Service
public class SongService {
    private SongRepository repository;
    @Autowired
    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public Optional<Song> findByID(Long id) {
        return repository.findByID(id);
    }

    public List<Song> findAll() {
        return repository.findAll();
    }

    public void saveNew(Song song) {
        repository.saveNew(song);
    }

    public void deleteExisting(Long song) {
        repository.deleteExisting(repository.findByID(song).orElseThrow());
    }
}
