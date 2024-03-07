package com.javaex.util;

public class BoardUtil {
	
	private int page;			//현재페이지
	private int recordSize;		//페이지에 올릴 게시물의 개수
	private int totalRecord;	//게시물 총 수
	private int pageSize;		//총 페이지 수
	private String keyword;		//검색
	
	
	public BoardUtil() {
		this.page = 1;
		this.recordSize = 5;
		this.keyword = "";
	}
	public BoardUtil(int page, int recordSize, int totalRecord, int pageSize, String keyword) {
		super();
		this.page = page;
		this.recordSize = recordSize;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.keyword = keyword;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "BoardUtil [page=" + page + ", recordSize=" + recordSize + ", totalRecord=" + totalRecord + ", pageSize="
				+ pageSize + ", keyword=" + keyword + "]";
	}

	
	



	
}
