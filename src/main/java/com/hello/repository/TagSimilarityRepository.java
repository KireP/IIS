package com.hello.repository;

import com.hello.model.TagSimilarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagSimilarityRepository extends JpaRepository<TagSimilarity, Integer> {

    List<TagSimilarity> findByTrack1Id(Integer id);

    List<TagSimilarity> findByTrack1IdInOrderBySimilarityDesc(List<Integer> trackIds);
}
