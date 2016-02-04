package com.hello.model;

import javax.persistence.*;

@Entity
@Table(name = "colaborative_similarities")
public class ColaborativeSimilarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "track1_id")
    public Track track1;

    @ManyToOne
    @JoinColumn(name = "track2_id")
    public Track track2;

    public Double similarity;

    public ColaborativeSimilarity() {

    }

    public Integer getId() {
        return id;
    }

    public Track getTrack1() {
        return track1;
    }

    public Track getTrack2() {
        return track2;
    }

    public Double getSimilarity() {
        return similarity;
    }

}