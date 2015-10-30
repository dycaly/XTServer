package com.ktboys.XTServer.Entity;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private User userBySenderId;
	private User userByReceverId;
	private String content;
	private Timestamp sendate;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Integer messageId, User userBySenderId,
			User userByReceverId, String content, Timestamp sendate) {
		this.messageId = messageId;
		this.userBySenderId = userBySenderId;
		this.userByReceverId = userByReceverId;
		this.content = content;
		this.sendate = sendate;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public User getUserBySenderId() {
		return this.userBySenderId;
	}

	public void setUserBySenderId(User userBySenderId) {
		this.userBySenderId = userBySenderId;
	}

	public User getUserByReceverId() {
		return this.userByReceverId;
	}

	public void setUserByReceverId(User userByReceverId) {
		this.userByReceverId = userByReceverId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendate() {
		return this.sendate;
	}

	public void setSendate(Timestamp sendate) {
		this.sendate = sendate;
	}

}