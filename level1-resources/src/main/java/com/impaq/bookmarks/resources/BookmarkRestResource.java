package com.impaq.bookmarks.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;
import com.impaq.bookmarks.Bookmark;
import com.impaq.bookmarks.BookmarkRepository;
import com.impaq.config.AccountNotFoundException;
import com.impaq.config.UserNotFoundException;



@RestController
@RequestMapping("/{userId}/bookmarks")
class BookmarkRestResource {
    
    private BookmarkRepository bookmarkRepository;
    private AccountRepository accountRepository;

    @Autowired
    private BookmarkRestResource(BookmarkRepository bookmarkRepository,
                           AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bookmark add(@PathVariable String userId, @RequestBody Bookmark input) {
        validateUser(userId);
        Account account = accountRepository.findByUsername(userId);
        if (account == null) {
            throw new AccountNotFoundException(userId);
        }
        return bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));
    }
    
    @RequestMapping(value = "/get",method = RequestMethod.POST)
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
        if (accountRepository.findByUsername(userId) == null) {
            throw new UserNotFoundException(userId);
        }
    }
}
