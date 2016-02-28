package com.hello.model;

import javax.persistence.*;

@Entity
@Table(name = "artists_final")
public class  Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    public Integer id;

    public String url;

    public String mbid;

    @Column(name = "artist_name")
    public String name;

    public String image;

    public Artist() {

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

    public String getImage() {
        return image;
    }
}
