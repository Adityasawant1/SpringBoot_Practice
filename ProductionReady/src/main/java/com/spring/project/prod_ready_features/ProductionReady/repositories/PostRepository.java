package com.spring.project.prod_ready_features.ProductionReady.repositories;

import com.spring.project.prod_ready_features.ProductionReady.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
