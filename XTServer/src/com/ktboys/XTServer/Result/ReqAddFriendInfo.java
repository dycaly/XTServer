package com.ktboys.XTServer.Result;

public class ReqAddFriendInfo {

	private boolean issaw;
	private String susername;
	private String snickname;
	private String rusername;
	private String date;
	public ReqAddFriendInfo(boolean issaw, String susername, String snickname,
			String rusername, String date) {
		this.issaw = issaw;
		this.susername = susername;
		this.snickname = snickname;
		this.rusername = rusername;
		this.date = date;
	}
	
	
	
	
}
