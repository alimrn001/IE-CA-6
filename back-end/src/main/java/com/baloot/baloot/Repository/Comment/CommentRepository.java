package com.baloot.baloot.Repository.Comment;

import com.baloot.baloot.models.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment getCommentByCommentId(int commentId);

    List<Comment> getCommentsByCommodity_Id(int commodity_id);

    List<Comment> getCommentsByUserUsername(String username);

}
