package app.lab3albums.service;

import app.lab3albums.dto.PutAlbumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab3albums.entity.Album;
import app.lab3albums.repository.AlbumRepository;
import app.lab3albums.event.EventAlbumRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final EventAlbumRepository eventAlbumRepository;

    @Autowired
    public AlbumService(AlbumRepository repository, EventAlbumRepository eventAlbumRepository) {
        this.albumRepository = repository;
        this.eventAlbumRepository = eventAlbumRepository;
    }

    public Optional<Album> find(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Transactional
    public Album create(Album entity) {
        eventAlbumRepository.save(entity);
        return albumRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        eventAlbumRepository.deleteById(id);
        albumRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, PutAlbumResponse albumResponse) {
        find(id).ifPresentOrElse(
                (original) -> {
                    original.setName(albumResponse.getName());
                    original.setArtist(albumResponse.getArtist());
                    original.setGenres(albumResponse.getGenres());
                    original.setReleaseDate(albumResponse.getReleaseDate());
                    original.setScore(albumResponse.getScore());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update album");
                }
        );
    }
}
