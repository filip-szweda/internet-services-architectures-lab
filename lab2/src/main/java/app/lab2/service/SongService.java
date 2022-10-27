package app.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab2.entity.Song;
import app.lab2.repository.SongRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class SongService {
    private SongRepository repository;
    @Autowired
    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Song> findByID(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Song> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Song saveNew(Song song) {
        return repository.save(song);
    }

    @Transactional
    public void deleteExisting(Long id) {
        repository.delete(repository.findById(id).orElseThrow());
    }
}
