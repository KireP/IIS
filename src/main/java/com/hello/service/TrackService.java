package com.hello.service;

import com.hello.model.Track;

import java.util.List;

public interface TrackService {

    List<Track> getTracksByName(String name);
}
