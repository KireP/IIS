package com.hello.service.impl;

import com.hello.model.Track;
import com.hello.repository.TrackRepository;
import com.hello.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository repository;

    @Override
    public List<Track> getTracksByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
