package com.hello.repository;

import com.hello.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findByNameContainingIgnoreCaseOrArtistIdInOrderByUserCountDesc(String name, Set<Integer> artists);
}
