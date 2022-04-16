/**
 * 
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author 2ndco
 *
 */
@Data
@Entity
public class Post extends Writing {
	private String title;
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="post")
	private List<Comment> comments;

	public Post() {

	}

	public Post (String title, String content) {
		this.title = title;
		this.setContent(content); // hmm
	}

	public void addComment(Comment c1) {
		// TODO Auto-generated method stub
		if (comments == null) comments = new ArrayList<Comment>();
		c1.setPost(this);
		comments.add(c1);
	}
}
