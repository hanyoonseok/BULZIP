package com.ssafy.happyhouse.model.dto;

public class NoticeDto {
	private int notice_no;
	private String notice_title;
	private String notice_writer;
	private String notice_date;
	private String notice_content;
	private String user_id;
	
	public NoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeDto(String notice_title, String notice_writer, String notice_date,
			String notice_content, String user_id) {
		this.notice_title = notice_title;
		this.notice_writer = notice_writer;
		this.notice_date = notice_date;
		this.notice_content = notice_content;
		this.user_id = user_id;
	}
	public NoticeDto(int notice_no, String notice_title, String notice_writer, String notice_date,
			String notice_content, String user_id) {
		this.notice_no = notice_no;
		this.notice_title = notice_title;
		this.notice_writer = notice_writer;
		this.notice_date = notice_date;
		this.notice_content = notice_content;
		this.user_id = user_id;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_writer() {
		return notice_writer;
	}

	public void setNotice_writer(String notice_writer) {
		this.notice_writer = notice_writer;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "NoticeDto [notice_no=" + notice_no + ", notice_title=" + notice_title + ", notice_writer="
				+ notice_writer + ", notice_date=" + notice_date + ", notice_content=" + notice_content + ", user_id="
				+ user_id + "]";
	}
	
	
	
	
	
}
