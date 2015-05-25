package com.impaq.bookmarks.resources;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;
import com.impaq.bookmarks.Bookmark;
import com.impaq.bookmarks.BookmarkRepository;
import com.impaq.rest.AccountNotFoundException;
import com.impaq.rest.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;


//@RestController
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class BookmarkRestResource {

    private BookmarkRepository bookmarkRepository;
    private AccountRepository accountRepository;

    private Long bookmarkId;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    @Autowired
    public BookmarkRestResource(BookmarkRepository bookmarkRepository,
                                AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bookmark add(@RequestBody Bookmark input) {
        validateUser(userId);
        Account account = accountRepository.findByUsername(userId).orElseThrow(() ->
                        new AccountNotFoundException(userId)
        );
        return bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Collection<Bookmark> readBookmarks(@PathVariable String userId) {
        this.validateUser(userId);
        return bookmarkRepository.findByAccountUsername(userId);
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.POST)
    public Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);
        return bookmarkRepository.findOne(bookmarkId);
    }

    @RequestMapping(value = "/{bookmarkId}/update", method = RequestMethod.POST)
    public Bookmark updateBookmark(@PathVariable String userId, @PathVariable Long bookmarkId, @RequestBody Bookmark input) {
        this.validateUser(userId);
        Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);
        bookmark.description = input.getDescription();
        return bookmarkRepository.save(bookmark);
    }

    @RequestMapping(value = "/{bookmarkId}/delete", method = RequestMethod.POST)
    public void deleteBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);
        bookmarkRepository.delete(bookmarkId);
    }

    private void validateUser(String userId) {
        accountRepository.findByUsername(userId).orElseThrow(() ->
                        new UserNotFoundException(userId)
        );
    }
}
