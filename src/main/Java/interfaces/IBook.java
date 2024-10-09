package interfaces;

import models.Book;

import java.util.List;

public interface IBook {
    public void save (Book book);

    public List<Book> getBooks ();

    public void update (Book book);

    public void delete (Book book);

    public void markAsUnavailable (Book book);
}
