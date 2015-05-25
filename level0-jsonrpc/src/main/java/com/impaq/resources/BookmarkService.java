package com.impaq.resources;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;
import com.impaq.bookmarks.Bookmark;
import com.impaq.bookmarks.BookmarkRepository;
import com.impaq.rest.AccountNotFoundException;
import com.impaq.rest.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;
    private AccountRepository accountRepository;

    @Autowired
    private BookmarkService(BookmarkRepository bookmarkRepository,
                            AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    public Bookmark add(String userId, Bookmark input) {
        validateUser(userId);
        Account account = accountRepository.findByUsername(userId).orElseThrow(() ->
                        new AccountNotFoundException(userId)
        );
        return bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));
    }

    public Collection<Bookmark> readBookmarks(String userId) {
        this.validateUser(userId);
        return bookmarkRepository.findByAccountUsername(userId);
    }

    public Bookmark readBookmark(String userId, Long bookmarkId) {
        this.validateUser(userId);
        return bookmarkRepository.findOne(bookmarkId);
    }

    public Bookmark updateBookmark(String userId, Long bookmarkId, Bookmark input) {
        this.validateUser(userId);
        Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);
        bookmark.description = input.getDescription();
        return bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(String userId, Long bookmarkId) {
        this.validateUser(userId);
        bookmarkRepository.delete(bookmarkId);
    }

    private void validateUser(String userId) {
        accountRepository.findByUsername(userId).orElseThrow(() ->
                        new UserNotFoundException(userId)
        );
    }
}
