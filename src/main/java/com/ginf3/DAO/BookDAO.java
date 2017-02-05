package com.ginf3.DAO;

import com.ginf3.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDAO {

  @Autowired
  private SessionFactory _sessionFactory;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public Iterable<Book> listAllBooks() {
    return getSession().createQuery("from Book").list();
  }

  public Book getBookById(Integer id) {
    return (Book) getSession().load(Book.class, id);
  }

  public Book saveBook(Book book) {
    getSession().save(book);
    return getBookById(book.getId());
  }

  public Book updateBook(Book book) {
    getSession().update(book);
    return getBookById(book.getId());
  }

  public void deleteBook(Integer id) {
    getSession().delete(getBookById(id));
  }

}
