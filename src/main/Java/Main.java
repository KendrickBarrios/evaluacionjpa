import dao.BookDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Book;

public class Main {
    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
        List<Book> books;
        List<Integer> existingIDs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean pass;
        int counter;
        char op = '0';

        while (op != '6') {
            System.out.println("Book manager\n");
            System.out.println("1. Add new book");
            System.out.println("2. See current books");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Mark a book as available/unavailable\n");
            System.out.println("6. Exit the program");
            op = sc.next().charAt(0);

            switch (op) {
                case '1':
                    System.out.println("\n1. Add new book\n");
                    books = dao.getBooks();
                    counter = 1;
                    existingIDs.clear();
                    if (!books.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        for (Book book : books) {
                            System.out.print("{ " + book.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(book.getId());
                        }
                    }
                    Book newBook = new Book();
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the book’s unique ID: ");
                        newBook.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        pass = true;
                        for (int id : existingIDs) {
                            if (newBook.getId() == id) {
                                pass = false;
                                System.out.println("You cannot use an already existing id\n");
                            }
                        }
                    }
                    System.out.println("Enter the book's title: ");
                    newBook.setTitle(sc.nextLine());
                    System.out.println("Enter the books’s author: ");
                    newBook.setAuthor(sc.nextLine());
                    System.out.println("Enter the book's publish year: ");
                    newBook.setPublishYear(sc.nextLine());
                    newBook.setAvailable(true);
                    dao.save(newBook);
                    System.out.println("\nThe book was successfully added\n");
                    break;

                case '2':
                    books = dao.getBooks();
                    System.out.println("\n2. See current books");
                    if (books.isEmpty()) {
                        System.out.println("There are no books.\n");
                    } else {
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    }
                    break;

                case '3':
                    System.out.println("\n3. Update a book register\n");
                    books = dao.getBooks();
                    counter = 1;
                    existingIDs.clear();
                    if (!books.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        for (Book book : books) {
                            System.out.print("{ " + book.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(book.getId());
                        }
                    } else {
                        System.out.println("There are no books.\n");
                        break;
                    }
                    Book updatedBook = new Book();
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the books unique ID: ");
                        updatedBook.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        pass = existingIDs.contains(updatedBook.getId());
                        if (!pass) {
                            System.out.println("This ID does not exist. Please enter a valid ID.\n");
                        }
                    }
                    System.out.println("Enter the book's title: ");
                    updatedBook.setTitle(sc.nextLine());
                    System.out.println("Enter the books’s author: ");
                    updatedBook.setAuthor(sc.nextLine());
                    System.out.println("Enter the book's publish year: ");
                    updatedBook.setPublishYear(sc.nextLine());
                    updatedBook.setAvailable(true);
                    dao.update(updatedBook);
                    System.out.println("\nThe book was successfully updated in the database.\n");
                    break;

                case '4':
                    System.out.println("\n4. Delete a book register\n");
                    books = dao.getBooks();
                    counter = 1;
                    if (!books.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        existingIDs.clear();
                        for (Book book : books) {
                            System.out.print("{ " + book.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(book.getId());
                        }
                    } else {
                        System.out.println("There are no books.\n");
                        break;
                    }
                    int removedId = 0;
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the ID of the book that you want to delete: ");
                        removedId = Integer.parseInt(sc.next());
                        sc.nextLine();
                        pass = existingIDs.contains(removedId);
                        if (!pass) {
                            System.out.println("This ID does not exist. Please enter a valid ID.\n");
                        }
                    }
                    Book deletedBook = dao.findById(removedId);
                    dao.delete(deletedBook);
                    System.out.println("\nThe book was successfully deleted\n");
                    break;

                case '5':
                    System.out.println("\n5. Mark a book as available/unavailable\n");
                    books = dao.getBooks();
                    counter = 1;
                    if (!books.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        existingIDs.clear();
                        for (Book book : books) {
                            System.out.print("{ " + book.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(book.getId());
                        }
                    } else {
                        System.out.println("There are no books.\n");
                        break;
                    }
                    int updateId = 0;
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the ID of the book that you want to delete: ");
                        updateId = Integer.parseInt(sc.next());
                        sc.nextLine();
                        pass = existingIDs.contains(updateId);
                        if (!pass) {
                            System.out.println("This ID does not exist. Please enter a valid ID.\n");
                        }
                    }
                    Book updatedBook2 = dao.findById(updateId);
                    updatedBook2.setTitle(updatedBook2.getTitle());
                    updatedBook2.setAuthor(updatedBook2.getAuthor());
                    updatedBook2.setPublishYear(updatedBook2.getPublishYear());
                    if (updatedBook2.isAvailable()) {
                        updatedBook2.setAvailable(false);
                    } else {
                        updatedBook2.setAvailable(true);
                    }
                    dao.update(updatedBook2);
                    System.out.println("\nThe book was successfully updated in the database.\n");
                    break;
                case '6':
                    System.out.println("\nThanks for using the program.\n");
                    break;

                default:
                    System.out.println("You have entered an invalid option, Please choose an option between 1 and 5.\n");
                    break;
            }
        }


    }
}
