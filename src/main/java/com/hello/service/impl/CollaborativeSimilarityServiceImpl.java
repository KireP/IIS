package com.hello.service.impl;

import com.hello.model.CollaborativeSimilarity;
import com.hello.repository.CollaborativeSimilarityRepository;
import com.hello.service.CollaborativeSimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CollaborativeSimilarityServiceImpl implements CollaborativeSimilarityService {

    @Autowired
    private CollaborativeSimilarityRepository collaborativeSimilarityRepository;

    @Override
    public List<CollaborativeSimilarity> getSimilarTracks(Collection<Integer> trackIds) {
        return collaborativeSimilarityRepository.findByTrack1IdIn(trackIds);
    }
}
