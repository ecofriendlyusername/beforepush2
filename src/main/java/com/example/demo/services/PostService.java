package com.example.demo.services;

import com.example.demo.CommentRepository;
import com.example.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AccountRepository;
import com.example.demo.PostRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Post;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PostService {
	PostRepository postRepo;
	AccountRepository accRepo;
	CommentRepository comRepo;

	@Autowired
	public PostService(PostRepository postRepo, AccountRepository accRepo, CommentRepository comRepo) {
		this.postRepo = postRepo;
		this.accRepo = accRepo;
		this.comRepo = comRepo;
	}

	public void post(String sub, String title, String content) {
		// TODO Auto-generated method stub
		try {
			Account acc = accRepo.getBySub(sub);
			Post newPost = new Post(title, content);
			acc.addPost(newPost);
			accRepo.save(acc);
		} catch (IllegalArgumentException illegalArgumentException) {

		} catch (EntityNotFoundException entityNotFoundException) {

		}
	}

	public String delete(String sub, int id) {
		try {
			//
			postRepo.deleteById(id);
		} catch (EntityNotFoundException entityNotFoundException) {

		}
		return null;
	}

	public Post getPost(int id) {
		try {
			return postRepo.getById(id);
		} catch (EntityNotFoundException entityNotFoundException) {

		}
		return null;
	}

    public List<Post> getPosts(String sub) {
		try {
			Account acc = accRepo.getBySub(sub);
			return postRepo.getByAccount(acc);
		} catch (EntityNotFoundException entityNotFoundException) {

		}
		return null;
    }
}

