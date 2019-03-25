package com.brandonoium.personalsite.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author boium
 *
 */
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinTable(name = "post_user", joinColumns = @JoinColumn(name = "post_id"),
								inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	private Date timedate;
	private String title;
	private String content;
	private boolean visible;
	
	public Post() {
		
	}
	
	public Post(long id, User user, Date timeDate, String title, String content, boolean visible) {
		this.id = id;
		this.user = user;
		this.timedate = timeDate;
		this.title = title;
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

	public Date getTimeDate() {
		return timedate;
	}

	public void setTimeDate(Date timeDate) {
		this.timedate = timeDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
