package com.baloot.baloot.services.comments;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.CommentDTO;
import com.baloot.baloot.models.Comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    BalootService balootService;

    CommentDTO getCommentDTOFromComment(Comment comment) {
        int likes = balootService.getNumberOfCommentLikes(comment);
        int disliked = balootService.getNumberOfCommentDislikes(comment);
        CommentDTO commentDTO = new CommentDTO(
                                                comment.getCommentId(), comment.getUser().getUsername(),
                                                comment.getCommodityId(), comment.getText(),
                                                comment.getDate(), likes, disliked);
        return commentDTO;
    }

    public Map<Integer, CommentDTO> getCommodityComments(int commodityId) throws Exception {
        List<Comment> comments = balootService.getCommodityComments(commodityId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        Map<Integer, CommentDTO> result = new HashMap<>();
        for(Comment comment : comments) {
            commentDTOList.add(getCommentDTOFromComment(comment));
        }
        for(CommentDTO commentDTO : commentDTOList)
            result.put(commentDTO.getCommentId(), commentDTO);
        return result;
    }

    public void voteComment(String username, int commentId, int vote) throws Exception {
        balootService.voteComment(username, commentId, vote);
    }

}
