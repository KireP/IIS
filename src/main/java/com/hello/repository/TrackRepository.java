package com.hello.repository;

import com.hello.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findByNameContainingIgnoreCaseOrArtistIdInOrderByUserCountDesc(String name, Collection<Integer> artists);
}
