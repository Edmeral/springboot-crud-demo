package com.ensat.DAO;

import com.ensat.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProductDAO {

  @Autowired
  private SessionFactory _sessionFactory;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public Iterable<Product> listAllProducts() {
    return getSession().createQuery("from Product").list();
  }

  public Product getProductById(Integer id) {
    return (Product) getSession().load(Product.class, id);
  }

  public Product saveProduct(Product product) {
    getSession().save(product);
    return getProductById(product.getId());
  }

  public Product updateProduct(Product product) {
    getSession().update(product);
    return getProductById(product.getId());
  }

  public void deleteProduct(Integer id) {
    getSession().delete(getProductById(id));
  }

}
