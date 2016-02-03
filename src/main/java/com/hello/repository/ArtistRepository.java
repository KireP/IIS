package com.hello.repository;

import com.hello.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    List<Artist> findByNameContainingIgnoreCase(String name);
}
