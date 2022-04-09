package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Comment;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
