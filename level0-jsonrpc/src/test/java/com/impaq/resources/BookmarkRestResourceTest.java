package com.impaq.resources;

import static com.impaq.rest.BookmarkRequest.createBookmarkRequest;
import static com.impaq.rest.BookmarkRequest.createDeleteBookmarkRequest;
import static com.impaq.rest.BookmarkRequest.createGetAllRequest;
import static com.impaq.rest.BookmarkRequest.createUpdateBookmarkRequest;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;
import com.impaq.bookmarks.Bookmark;
import com.impaq.bookmarks.BookmarkRepository;
import com.impaq.config.Application;
import com.impaq.rest.BookmarkRequest;

/**
 * @author Josh Long
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BookmarkRestResourceTest {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private String userName = "bdussault";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Account account;

    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountRepository accountRepository;

	private Bookmark bookmark1;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.bookmarkRepository.deleteAllInBatch();
        this.accountRepository.deleteAllInBatch();

        this.account = accountRepository.save(new Account(userName, "password"));
        bookmark1 = bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + userName, "A description"));
		this.bookmarkList.add(bookmark1);
        this.bookmarkList.add(bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + userName, "A description")));
    }

    @Test
    public void createBookmark() throws Exception {
        String bookmarkJson = json(createBookmarkRequest(
        		new Bookmark(account, "http://test/test", "bookmark"), userName));
        mockMvc.perform(post("/test")
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isOk());
    }
    
    @Test
    public void readBookmarks() throws Exception {
    	String bookmarkJson = json(createGetAllRequest(userName));
    	mockMvc.perform(post("/test")
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    
    @Test
    public void deleteBookmark() throws Exception {
        String bookmarkJson = json(createDeleteBookmarkRequest(
        		userName, bookmark1.getId()));
        mockMvc.perform(post("/test")
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isOk());
    }
    
    @Test
    public void updateBookmark() throws Exception {
        String bookmarkJson = json(createUpdateBookmarkRequest(
        		userName, bookmark1.getId(), new Bookmark(null, null, "new description")));
        mockMvc.perform(post("/test")
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
