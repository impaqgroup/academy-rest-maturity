package com.impaq.rest;

import com.impaq.accounts.Account;
import com.impaq.bookmarks.Bookmark;

public class BookmarkRequest {

    private String method;
    private Bookmark input;
    private Long bookmarkId;
    private String userId;
    private Account account;
    
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
