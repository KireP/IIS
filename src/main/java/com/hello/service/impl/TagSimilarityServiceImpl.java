package com.hello.service.impl;

import com.hello.model.TagSimilarity;
import com.hello.model.Track;
import com.hello.repository.TagSimilarityRepository;
import com.hello.service.TagSimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagSimilarityServiceImpl implements TagSimilarityService {

    @Autowired
    private TagSimilarityRepository repository;

    @Override
    public List<Track> getSimilarTracks(Integer trackID) {
        List<TagSimilarity> tagSimilarities = repository.findByTrack1Id(trackID);
        return tagSimilarities.stream().map(similarity -> similarity.track2).collect(Collectors.toList());
    }

    @Override
    public List<Track> getSimilarTracks(Collection<Integer> trackIds) {
        List<TagSimilarity> tagSimilarities = repository.findByTrack1IdInOrderBySimilarityDesc(trackIds);
        return tagSimilarities.stream().map(similarity -> similarity.track2).collect(Collectors.toList());
    }
}
