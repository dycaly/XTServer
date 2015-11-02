package com.ktboys.XTServer.Result;

public class ProductInfo {

	private String producturl;
	private String productname;
	private String productintro;
	private int hightestprice;
	private int lowestprice;
	private int cuttime;
	private int status;
	private String sellername;
	private String sellernickname;
	private String selldate;
	
	private String buyername;
	private int lastprice;
	private String classify;
	public ProductInfo(String producturl, String productname,String sellernickname,
			String productintro, int hightestprice, int lowestprice,
			int cuttime, int status, String sellername, String selldate,
			String buyername, int lastprice, String classify) {

		this.producturl = producturl;
		this.productname = productname;
		this.productintro = productintro;
		this.hightestprice = hightestprice;
		this.lowestprice = lowestprice;
		this.cuttime = cuttime;
		this.status = status;
		this.sellername = sellername;
		this.sellernickname = sellernickname;
		this.selldate = selldate;
		this.buyername = buyername;
		this.lastprice = lastprice;
		this.classify = classify;
	}
	
}
