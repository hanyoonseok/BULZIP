package com.ssafy.happyhouse.model.dto;

public class QnareplyDto {
	private int comment_id;
	private int qna_id;
	private String user_id;
	private String comment_content;
	private String comment_date;
	public QnareplyDto() {}
	public QnareplyDto(int qna_id, String user_id, String comment_content, String comment_date) {
		this.qna_id = qna_id;
		this.user_id = user_id;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
	}
	public QnareplyDto(int comment_id, int qna_id, String user_id, String comment_content, String comment_date) {
		this.comment_id = comment_id;
		this.qna_id = qna_id;
		this.user_id = user_id;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getcomment_content() {
		return comment_content;
	}
	public void setcomment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getcomment_date() {
		return comment_date;
	}
	public void setcomment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	@Override
	public String toString() {
		return "QnareplyDto [comment_id=" + comment_id + ", qna_id=" + qna_id + ", user_id=" + user_id
				+ ", comment_content=" + comment_content + ", comment_date=" + comment_date + "]";
	}
	
}

