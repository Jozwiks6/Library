package pl.camp.it.library.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.library.model.Author;
import pl.camp.it.library.model.Book;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IBookService;
import pl.camp.it.library.services.IUserService;

@Controller
@RequestMapping(value = "/admin/utils")
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        User user = new User();
        user.setLogin("admin");
        String hashedPassword = DigestUtils.md5Hex("admin");
        user.setPassword(hashedPassword);

        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "addBooks", method = RequestMethod.GET)
    public String addBooks() {
        Book book1 = new Book();
        book1.setIsbn("S31253132G");
        book1.setTitle("Sekrety Wloskiej Kuchni");
        book1.setCategory(Book.Category.COOKING);

        Author author1 = new Author();
        author1.setName("Elena");
        author1.setSurname("Kustowich");

        book1.setAuthor(author1);

        Book book2 = new Book();
        book2.setIsbn("S366435452PL");
        book2.setTitle("Smacznie i Zdrowo");
        book2.setCategory(Book.Category.COOKING);

        Author author2 = new Author();
        author2.setName("Igor");
        author2.setSurname("Pieczarka");

        book2.setAuthor(author2);

        Book book3 = new Book();
        book3.setIsbn("87771623R");
        book3.setTitle("Wypieki");
        book3.setCategory(Book.Category.BAKING);

        Author author3 = new Author();
        author3.setName("Anastazja");
        author3.setSurname("Pryncypalka");

        book3.setAuthor(author3);

        Book book4 = new Book();
        book4.setIsbn("123RDAW543");
        book4.setTitle("Chleb i Wino");
        book4.setCategory(Book.Category.COOKING);

        Author author4 = new Author();
        author4.setName("Kuba");
        author4.setSurname("Portowinko");

        book4.setAuthor(author4);

        Book book5 = new Book();
        book5.setIsbn("99093210RRG");
        book5.setTitle("Desery");
        book5.setAuthor(author3);
        book5.setCategory(Book.Category.BAKING);

        this.bookService.addBook(book1);
        this.bookService.addBook(book2);
        this.bookService.addBook(book3);
        this.bookService.addBook(book4);
        this.bookService.addBook(book5);

        return "main";
    }
}
