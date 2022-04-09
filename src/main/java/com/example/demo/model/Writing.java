/**
 * 
 */
package com.example.demo.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author 2ndco
 *
 */

@Data // to be replaced with other annotations later
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Writing {
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;
	private String content;
	//(Optional) Whether the association is optional. If set to false then a non-null relationship must always exist.
	@ManyToOne(optional=false, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Account account;
}
