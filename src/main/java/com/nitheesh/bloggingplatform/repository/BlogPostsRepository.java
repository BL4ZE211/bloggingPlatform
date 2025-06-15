package com.nitheesh.bloggingplatform.repository;

import com.nitheesh.bloggingplatform.entity.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPosts,Long> {
}
