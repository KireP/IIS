package com.hello.repository;

import com.hello.model.CollaborativeSimilarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CollaborativeSimilarityRepository extends JpaRepository<CollaborativeSimilarity, Integer> {

    List<CollaborativeSimilarity> findByTrack1IdIn(Collection<Integer> trackIds);
}
