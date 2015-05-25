package com.impaq.resources;

import com.impaq.rest.BookmarkRequest;
import com.impaq.rest.NotSupportedMethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class BookmarkRestResource {

    private BookmarkService bookmarkService;

    @Autowired
    private BookmarkRestResource(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> handleRequest(@RequestBody BookmarkRequest request) {
    	Object content = null;
        if (request.getMethod().equals("CreateBookmark")) {
        	content = bookmarkService.add(request.getUserId(), request.getInput());
        } else if (request.getMethod().equals("GetAll")) {
        	content = bookmarkService.readBookmarks(request.getUserId());
        } else if (request.getMethod().equals("UpdateBookmark")) {
        	content = bookmarkService.updateBookmark(request.getUserId(), request.getBookmarkId(), request.getInput());
        } else if (request.getMethod().equals("DeleteBookmark")) {
            bookmarkService.deleteBookmark(request.getUserId(), request.getBookmarkId());
        } else if (request.getMethod().equals("GetBookmark")) {
        	content = bookmarkService.readBookmark(request.getUserId(), request.getBookmarkId());
        } else {
            throw new NotSupportedMethodException();
        }
        return new ResponseEntity<Object>(content, HttpStatus.OK);
    }
}
