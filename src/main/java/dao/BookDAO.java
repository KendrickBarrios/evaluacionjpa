package dao;

import interfaces.IBook;
import jakarta.persistence.EntityManager;
import models.Book;

import java.util.List;

public class BookDAO implements IBook {
    @Override
    public void save(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getBooks() {
        EntityManager em = EntityManagerAdmin.getInstance();
        List<Book> books = em.createQuery("select c from Book c", Book.class).getResultList();
        return books;
    }

    @Override
    public void update(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }

    public Book findById(int id) {
        EntityManager em = EntityManagerAdmin.getInstance();
        return em.find(Client.class, id);
    }
}
