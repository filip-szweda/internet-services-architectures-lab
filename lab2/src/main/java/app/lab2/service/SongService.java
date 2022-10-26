package app.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab2.entity.Song;
import app.lab2.repository.SongRepository;

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

    public void deleteExisting(Long id) {
        repository.deleteExisting(repository.findByID(id).orElseThrow());
    }
}
