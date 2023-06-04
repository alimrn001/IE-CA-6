package com.baloot.baloot.Repository.Comment;

import com.baloot.baloot.models.Comment.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote getVoteByCommentCommentIdAndUserUsername(int commentId, String username);

    Vote getVoteByVoteId(long voteId);

    List<Vote> findVotesByComment_CommentId(int commentId);

    List<Vote> findVotesByUserUsername(String username);

}
