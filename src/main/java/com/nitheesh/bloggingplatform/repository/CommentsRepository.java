package com.nitheesh.bloggingplatform.repository;

import com.nitheesh.bloggingplatform.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
