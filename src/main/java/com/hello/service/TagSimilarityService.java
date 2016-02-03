package com.hello.service;

import com.hello.model.Track;

import java.util.Collection;
import java.util.List;

public interface TagSimilarityService {

    List<Track> getSimilarTracks(Integer trackID);

    List<Track> getSimilarTracks(Collection<Integer> trackIds);
}
