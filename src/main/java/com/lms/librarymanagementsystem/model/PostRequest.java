package com.lms.librarymanagementsystem.model;

public class PostRequest {
	    private String title;
	    private String content;
	    private String tags;
	    private boolean published;
	    public PostRequest() {
	    	            super();
	    	            
	    }

		public PostRequest(String title, String content, String tags, boolean published) {
			super();
			this.title = title;
			this.content = content;
			this.tags = tags;
			this.published = published;
		}
		public String getTitle() {
			return title;
		}
		public String getContent() {
			return content;
		}
		public String getTags() {
			return tags;
		}
		public boolean isPublished() {
			return published;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public void setPublished(boolean published) {
			this.published = published;
		}
	    
}
