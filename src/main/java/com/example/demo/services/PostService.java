package com.example.demo.services;

import com.example.demo.CommentRepository;
import com.example.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AccountRepository;
import com.example.demo.PostRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Post;

import java.util.List;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepo;
	@Autowired
	AccountRepository accRepo;

	@Autowired
	CommentRepository comRepo;

	public void post(String sub, String title, String content) {
		// TODO Auto-generated method stub
		Account acc = accRepo.getBySub(sub);
		Post newPost = new Post();
		newPost.setTitle(title);
		newPost.setContent(content);
		acc.addPost(newPost);
		accRepo.save(acc);
	}



    public List<Post> getposts(String sub) {
		Account acc = accRepo.getBySub(sub);
		List<Post> posts = postRepo.getByAccount(acc);
		return posts;
    }
}
