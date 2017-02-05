package com.ginf3.controllers;

import com.ginf3.DAO.BookDAO;
import com.ginf3.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Book controller.
 */
@Controller
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    /**
     * List all books.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookDAO.listAllBooks());
        return "books";
    }

    /**
     * View a specific book by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("book/{id}")
    public String showBook(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "bookshow";
    }

    // Afficher le formulaire de modification du Book
    @RequestMapping("book/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "bookform";
    }

    /**
     * New book.
     *
     * @param model
     * @return
     */
    @RequestMapping("book/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "bookform";
    }

    /**
     * Save book to database.
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveBook(Book book) {
        if (book.getId() != null)
            bookDAO.updateBook(book);
        else
            bookDAO.saveBook(book);
        return "redirect:/book/" + book.getId();
    }

    /**
     * Delete book by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("book/delete/{id}")
    public String delete(@PathVariable Integer id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

}
