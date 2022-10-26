package app.lab2.repository;

import app.lab2.datastorage.DataStorage;
import app.lab2.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class AlbumRepository implements Repository<Album, Long> {
    private DataStorage storage;

    @Autowired
    public AlbumRepository(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Album> findByID(Long id) {
        return storage.findByIDAlbum(id);
    }

    @Override
    public List<Album> findAll() {
        return storage.findAllAlbums();
    }

    @Override
    public void saveNew(Album entity) {
        storage.saveNewAlbum(entity);
    }

    @Override
    public void deleteExisting(Album entity) {
        storage.deleteExistingAlbum(entity.getId());
    }
}
