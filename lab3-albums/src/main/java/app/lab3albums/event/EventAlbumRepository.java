package app.lab3albums.event;

import app.lab3albums.event.EventPostAlbumRequest;
import app.lab3albums.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EventAlbumRepository {
    private RestTemplate restTemplate;

    static private final String BASE_URL = "http://localhost:8082/api/albums/";

    @Autowired
    public EventAlbumRepository() {
        restTemplate = new RestTemplateBuilder().rootUri(BASE_URL).build();
    }

    public boolean deleteById(Long id) {
        try {
            restTemplate.delete("/{id}", id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save(Album album) {
        try {
            restTemplate.postForLocation("/", EventPostAlbumRequest.builder().name(album.getName()).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
