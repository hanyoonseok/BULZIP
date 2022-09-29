package com.ssafy.happyhouse.model.dto;

public class QnaDto {
	private int qna_id;
	private String qna_title;
	private String qna_content;
	private String user_id;
	private String qna_date;
	public QnaDto() {}
	public QnaDto(String qna_title, String qna_content, String user_id, String qna_date) {
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.user_id = user_id;
		this.qna_date = qna_date;
	}
	public QnaDto(int qna_id, String qna_title, String qna_content, String user_id, String qna_date) {
		this.qna_id = qna_id;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.user_id = user_id;
		this.qna_date = qna_date;
	}
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQna_date() {
		return qna_date;
	}
	public void setQna_date(String qna_date) {
		this.qna_date = qna_date;
	}
	@Override
	public String toString() {
		return "QnaDto [qna_id=" + qna_id + ", qna_title=" + qna_title + ", qna_content=" + qna_content + ", user_id="
				+ user_id + ", qna_date=" + qna_date + "]";
	}
	
	
	
}
