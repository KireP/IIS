package com.hello.service;

import com.hello.model.Track;

import java.util.Collection;
import java.util.List;

public interface TrackService {

    List<Track> getTracksByNameOrArtist(String name, Collection<Integer> artists);

    Track findById(Integer id);

    List<Track> findAllById(Iterable<Integer> ids);
}
