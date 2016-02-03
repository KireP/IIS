package com.hello.service;

import com.hello.model.Track;

import java.util.List;
import java.util.Set;

public interface TrackService {

    List<Track> getTracksByNameOrArtist(String name, Set<Integer> artists);

    Track findById(Integer id);
}
