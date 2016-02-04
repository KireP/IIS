package com.hello.service;

import com.hello.model.ColaborativeSimilarity;
import com.hello.model.Track;

import java.util.Collection;
import java.util.List;

/**
 * Created by Ivica on 2/4/2016.
 */
public interface CollaborativeSimilarityService {

    List<ColaborativeSimilarity> getSimilarTracks(Collection<Integer> trackIds);
}
