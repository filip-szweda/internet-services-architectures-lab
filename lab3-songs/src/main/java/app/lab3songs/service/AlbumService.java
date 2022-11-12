package app.lab3songs.service;

import app.lab3songs.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab3songs.entity.Album;
import app.lab3songs.repository.AlbumRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository repository) {
        this.albumRepository = repository;
    }

    public Optional<Album> find(Long id) {
        return albumRepository.findById(id);
    }

    @Transactional
    public Album create(Album entity) {
        return albumRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}
