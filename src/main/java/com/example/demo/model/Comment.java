/**
 * 
 */
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * @author 2ndco
 *
 */

@Data
@Entity
public class Comment extends Writing {
	@ManyToOne(optional=false)
	private Post post;
}
