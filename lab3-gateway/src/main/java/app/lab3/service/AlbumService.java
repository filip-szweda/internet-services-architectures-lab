package app.lab3.service;

import app.lab3.dto.PutAlbumResponse;
import app.lab3.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab3.entity.Album;
import app.lab3.repository.AlbumRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongService songService;

    @Autowired
    public AlbumService(AlbumRepository repository, SongService songService) {
        this.albumRepository = repository;
        this.songService = songService;
    }

    public Optional<Album> find(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Transactional
    public Album create(Album entity) {
        return albumRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
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

    public List<Song> findAllByAlbumId(Long albumId) {
        return songService.findAllByAlbumId(albumId);
    }
}
