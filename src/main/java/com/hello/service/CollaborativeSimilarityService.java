package com.hello.service;

import com.hello.model.CollaborativeSimilarity;

import java.util.Collection;
import java.util.List;

public interface CollaborativeSimilarityService {

    List<CollaborativeSimilarity> getSimilarTracks(Collection<Integer> trackIds);
}
