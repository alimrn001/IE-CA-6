package com.baloot.baloot.services.commodities;

import com.baloot.baloot.domain.Baloot.Baloot;
import com.baloot.baloot.domain.Baloot.Comment.Comment;

import java.util.Map;

public class CommentService {
    public static Map<Integer, Comment> getCommodityComments(int commodityId) throws Exception {
        return Baloot.getInstance().getCommodityComments(commodityId);
    }
}
