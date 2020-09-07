package pl.camp.it.library.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.library.AppConfiguration;
import pl.camp.it.library.AppConfigurationTest;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.dao.impl.IAuthorDAO;
import pl.camp.it.library.dao.impl.IBookDAO;
import pl.camp.it.library.model.Author;
import pl.camp.it.library.services.IBookService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class BookServiceImplTest {

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IAuthorDAO authorDAO;

    @MockBean
    IUserDAO userDAO;

    @Autowired
    IBookService bookService;

    @Before
    public void setUpMocks(){
        Mockito.when(this.bookDAO.findBooks(anyString())).thenReturn(new ArrayList<>());

        List<Author> authorList = new ArrayList<>();
        Author a1 = new Author();
        a1.setId(3);
        a1.setName("Tadeusz");
        a1.setSurname("Dolega-Mostowicz");

        Author a2 = new Author();
        a2.setId(4);
        a2.setName("Tadeusz");
        a2.setSurname("Pini");

        authorList.add(a1);
        authorList.add(a2);

        Mockito.when(this.authorDAO.findAuthors(anyString())).thenReturn(authorList);

        Mockito.when(this.bookDAO.getBooksByAuthorId(anyInt())).thenReturn(new ArrayList<>());
    }

    @Test
    public void checkMethodsCallsDuringFindingBooksByPattern(){
        String pattern = "book";

        this.bookService.findBooks(pattern);

        verify(this.bookDAO, times(1)).findBooks(anyString());
        verify(this.authorDAO, times(1)).findAuthors(anyString());
        verify(this.bookDAO, times(2)).getBooksByAuthorId(anyInt());
    }
}
