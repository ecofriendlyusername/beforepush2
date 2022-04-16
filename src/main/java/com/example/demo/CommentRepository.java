package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> getByAccount(Account acc);

    Comment getById(Integer id);
}
