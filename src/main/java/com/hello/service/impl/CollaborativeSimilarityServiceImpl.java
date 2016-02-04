package com.hello.service.impl;

import com.hello.model.ColaborativeSimilarity;
import com.hello.model.Track;
import com.hello.repository.CollaborativeSimilarityRepository;
import com.hello.service.CollaborativeSimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ivica on 2/4/2016.
 */
@Service
public class CollaborativeSimilarityServiceImpl implements CollaborativeSimilarityService{

    @Autowired
    private CollaborativeSimilarityRepository collaborativeSimilarityRepository;

    @Override
    public List<ColaborativeSimilarity> getSimilarTracks(Collection<Integer> trackIds) {
        List<ColaborativeSimilarity> collaborativeSimilarities = collaborativeSimilarityRepository.findByTrack1IdIn(trackIds);

        return collaborativeSimilarities;
    }
}
