package com.example.demo.model;

import javax.persistence.Entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.hibernate.LazyInitializationException;

import lombok.Data;

@Transactional
@Entity
@Data // to be replaced
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userid;
	private String email;
	private String sub;

	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private List<Post> posts;
	@OneToMany(mappedBy="account")
	private List<Comment> comments;

	public void addPost(Post p1) {
		// TODO Auto-generated method stub
		if (posts == null) posts = new ArrayList<Post>();
		p1.setAccount(this);
		this.posts.add(p1);
	}

	public void addComment(Comment c1) {
		// TODO Auto-generated method stub
		if (comments == null) comments = new ArrayList<Comment>();
		c1.setAccount(this);
		this.comments.add(c1);
	}
}