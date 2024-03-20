package com.lms.librarymanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    private String title;

    private String content;

    private boolean published;

    private String tags;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public boolean isPublished() {
		return published;
	}

	public String getTags() {
		return tags;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Post(String id, String title, String content, boolean published, String tags) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.published = published;
		this.tags = tags;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

    // getters and setters
    
}