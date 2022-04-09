package com.example.demo.services;

import com.example.demo.AccountRepository;
import com.example.demo.CommentRepository;
import com.example.demo.PostRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepo;
    @Autowired
    AccountRepository accRepo;

    @Autowired
    CommentRepository comRepo;
    public void post(String sub, int postId, String content) {
        // TODO Auto-generated method stub
        Account acc = accRepo.getBySub(sub);
        Post post = postRepo.getById(postId);
        Comment newComment = new Comment();
        newComment.setContent(content);
        acc.addComment(newComment);
        post.addComment(newComment);
        comRepo.save(newComment);
        postRepo.save(post);
        accRepo.save(acc);
    }
}
