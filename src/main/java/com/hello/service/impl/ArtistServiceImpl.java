package com.hello.service.impl;

import com.hello.model.Artist;
import com.hello.repository.ArtistRepository;
import com.hello.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository repository;

    @Override
    public Set<Integer> getArtistsByName(String name) {
        List<Artist> artists = repository.findByNameContainingIgnoreCase(name);
        return artists.stream().map(artist -> artist.id).collect(Collectors.toSet());
    }
}
