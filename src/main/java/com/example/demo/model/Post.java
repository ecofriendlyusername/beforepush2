/**
 * 
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author 2ndco
 *
 */
@Data
@Entity
public class Post extends Writing {
	private String title;
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	public void addComment(Comment c1) {
		// TODO Auto-generated method stub
		if (comments == null) comments = new ArrayList<Comment>();
		c1.setPost(this);
		comments.add(c1);
	}
}
