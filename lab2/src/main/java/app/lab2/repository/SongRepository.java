package app.lab2.repository;

import app.lab2.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByID(Long id);

    List<Song> findAll();

    void saveNew(Song entity);

    void deleteExisting(Song entity);
}
