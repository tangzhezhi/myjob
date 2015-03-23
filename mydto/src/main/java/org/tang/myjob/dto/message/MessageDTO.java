package org.tang.myjob.dto.message;

import java.io.Serializable;
import java.util.Date;

public class MessageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -187482008493620835L;

	private String title;
	private String content;
	private Date createDate;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
