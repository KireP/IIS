package com.hello.repository;

import com.hello.model.ColaborativeSimilarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * CollaborativeSimilarityRepository.
 */
public interface CollaborativeSimilarityRepository extends JpaRepository<ColaborativeSimilarity, Integer> {

    List<ColaborativeSimilarity> findByTrack1IdIn(Collection<Integer> trackIds);
}
