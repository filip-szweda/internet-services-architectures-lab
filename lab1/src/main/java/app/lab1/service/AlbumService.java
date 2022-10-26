package app.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab1.entity.Album;
import app.lab1.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private AlbumRepository repository;

    @Autowired
    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    public Optional<Album> findByID(Long id) {
        return repository.findByID(id);
    }

    public List<Album> findAll() {
        return repository.findAll();
    }

    public void saveNew(Album album) {
        repository.saveNew(album);
    }

    public void deleteExisting(Long song) {
        repository.deleteExisting(repository.findByID(song).orElseThrow());
    }
}
