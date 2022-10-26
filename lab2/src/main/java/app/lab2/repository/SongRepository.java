package app.lab2.repository;

import app.lab2.datastorage.DataStorage;
import app.lab2.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SongRepository implements Repository<Song, Long>{
    private DataStorage storage;

    @Autowired
    public SongRepository(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Song> findByID(Long id) {
        return storage.findByIDSong(id);
    }

    @Override
    public List<Song> findAll() {
        return storage.findAllSongs();
    }

    @Override
    public void saveNew(Song entity) {
        storage.saveNewSong(entity);
    }

    @Override
    public void deleteExisting(Song entity) {
        storage.deleteExistingSong(entity.getId());
    }
}
