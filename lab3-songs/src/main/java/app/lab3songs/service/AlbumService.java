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
    private final SongService songService;

    @Autowired
    public AlbumService(AlbumRepository repository, SongService songService) {
        this.albumRepository = repository;
        this.songService = songService;
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

    public List<Song> findAllByAlbumId(Long albumId) {
        return songService.findAllByAlbumId(albumId);
    }
}
