package com.hello.service.impl;

import com.hello.model.Track;
import com.hello.repository.TrackRepository;
import com.hello.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository repository;

    @Override
    public List<Track> getTracksByNameOrArtist(String name, Set<Integer> artists) {
        return repository.findByNameContainingIgnoreCaseOrArtistIdInOrderByUserCountDesc(name, artists);
    }

    @Override
    public Track findById(Integer id) {
        return repository.findOne(id);
    }
}
