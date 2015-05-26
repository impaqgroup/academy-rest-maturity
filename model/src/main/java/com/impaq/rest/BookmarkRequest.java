package com.impaq.rest;

import com.impaq.accounts.Account;
import com.impaq.bookmarks.Bookmark;

public class BookmarkRequest {

    private String method;
    private Bookmark input;
    private Long bookmarkId;
    private String userId;
    private Account account;
    
    public static BookmarkRequest createBookmarkRequest(Bookmark input, String userId) {
    	BookmarkRequest request = new BookmarkRequest();
    	request.setMethod("CreateBookmark");
    	request.setInput(input);
    	request.setUserId(userId);
    	return request;
    }
    
    public static BookmarkRequest createGetAllRequest(String userId) {
    	BookmarkRequest request = new BookmarkRequest();
    	request.setMethod("GetAll");
    	request.setUserId(userId);
    	return request;
    }
    
    public static BookmarkRequest createDeleteBookmarkRequest(String userId, Long bookmarkId) {
    	BookmarkRequest request = new BookmarkRequest();
    	request.setMethod("DeleteBookmark");
    	request.setBookmarkId(bookmarkId);
    	request.setUserId(userId);
    	return request;
    }
    
    public static BookmarkRequest createUpdateBookmarkRequest(String userId, Long bookmarkId, Bookmark input) {
    	BookmarkRequest request = new BookmarkRequest();
    	request.setMethod("UpdateBookmark");
    	request.setBookmarkId(bookmarkId);
    	request.setInput(input);
    	request.setUserId(userId);
    	return request;
    }
    
    public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Bookmark getInput() {
        return input;
    }
    public void setInput(Bookmark input) {
        this.input = input;
    }
    public Long getBookmarkId() {
        return bookmarkId;
    }
    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
