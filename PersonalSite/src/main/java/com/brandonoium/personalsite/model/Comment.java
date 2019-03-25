package com.brandonoium.personalsite.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author boium
 *
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinTable(name = "comment_user", joinColumns = @JoinColumn(name = "comment_id"),
								inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	@OneToOne
	@JoinTable(name = "comment_post", joinColumns = @JoinColumn(name = "comment_id"),
								inverseJoinColumns = @JoinColumn(name = "post_id"))
	private Post post;
	
	private Date timedate;
	private String content;
	private boolean visible;
	
	
	public Comment() {
		
	}
	
	public Comment(long id, User user, Post post, Date timeDate, String content, boolean visible) {
		this.id = id;
		this.user = user;
		this.post = post;
		this.timedate = timeDate;
		this.content = content;
		this.visible = visible;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getTimeDate() {
		return timedate;
	}

	public void setTimeDate(Date timeDate) {
		this.timedate = timeDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
