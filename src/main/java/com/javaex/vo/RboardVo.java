package com.javaex.vo;

public class RboardVo {

	
	private int no;
	private int userNo;
	private String title;
	private String date;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String name;
	
	public RboardVo() {
		super();
	}
	public RboardVo(int no, int userNo, String title, String date, int groupNo, int orderNo, int depth, String name) {
		super();
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.date = date;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", date=" + date + ", groupNo="
				+ groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", name=" + name + "]";
	}
	
	
	
}
