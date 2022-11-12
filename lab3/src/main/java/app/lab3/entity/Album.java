package app.lab3.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "albums")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    private String artist;

    @Column(name = "genres")
    private String[] genres;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "score")
    private double score;

    @OneToMany(mappedBy = "album",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Song> songs = new ArrayList<>();
}
