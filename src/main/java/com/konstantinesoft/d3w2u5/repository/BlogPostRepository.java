package com.konstantinesoft.d3w2u5.repository;

import com.konstantinesoft.d3w2u5.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
