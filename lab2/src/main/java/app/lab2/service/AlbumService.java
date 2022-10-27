package app.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab2.entity.Album;
import app.lab2.repository.AlbumRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private AlbumRepository repository;

    @Autowired
    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Album> findByID(Long id) {
        return repository.findByID(id);
    }

    @Transactional
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void saveNew(Album album) {
        repository.saveNew(album);
    }

    @Transactional
    public void deleteExisting(Long id) {
        repository.deleteExisting(repository.findByID(id).orElseThrow());
    }
}
