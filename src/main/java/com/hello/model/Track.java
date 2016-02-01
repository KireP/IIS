package com.hello.model;

import javax.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    public Integer id;

    public String url;

    public String mbid;

    @Column(name = "track_name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    public Artist artist;

    public Track() {

    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return artist.name + " - " + name;
    }

}
