package app.lab2.repository;

import app.lab2.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByID(Long id);

    List<Album> findAll();

    void saveNew(Album entity);

    void deleteExisting(Album entity);
}
