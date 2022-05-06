package com.example.demo.services;

import com.example.demo.AccountRepository;
import com.example.demo.CommentRepository;
import com.example.demo.PostRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentService {
    PostRepository postRepo;
    AccountRepository accRepo;
    CommentRepository comRepo;

    @Autowired
    public CommentService(PostRepository postRepo, AccountRepository accRepo, CommentRepository comRepo) {
        this.postRepo = postRepo;
        this.accRepo = accRepo;
        this.comRepo = comRepo;
    }

    public void post(String sub, int id, String content) {
        // TODO Auto-generated method stub
        Account acc = accRepo.getBySub(sub);
        Post post = postRepo.getById(id);
        Comment newComment = new Comment();
        newComment.setContent(content);
        acc.addComment(newComment);
        post.addComment(newComment);
        comRepo.save(newComment);
    }

    public void deleteComment(String sub, int id) {
        try {
            if (comRepo.getById(id).getAccount() != accRepo.getBySub(sub)) return;
            comRepo.deleteById(id);
        } catch (IllegalArgumentException illegalArgumentException) {

        } catch (EntityNotFoundException entityNotFoundException) {

        }
    }

    public List<Comment> getComments(String sub) {
        try  {
            return comRepo.getByAccount(accRepo.getBySub(sub));
        } catch (IllegalArgumentException illegalArgumentException) {

        } catch (EntityNotFoundException entityNotFoundException) {

        }
        return null;
    }
}
