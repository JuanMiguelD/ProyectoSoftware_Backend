package com.proyecto.backfinal.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.repositories.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public AbstractBook createBook(AbstractBook book) {
        return bookRepository.save(book);
    }
    

    public void  deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public AbstractBook updateBook(Long isbn, AbstractBook updatedBook){

        return bookRepository.findById(isbn).map(book -> {

                book.setTitle(updatedBook.getTitle());                
                book.setGenre(updatedBook.getGenre());

                return bookRepository.save(book);
            }).orElseThrow(() -> new RuntimeException("Book not found"));
    
    }

    public  List<AbstractBook> getAllBooks() {
        return bookRepository.findAll();
    }

    public  AbstractBook getBook(Long isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public  List<AbstractBook> getBooksByWriterId(Long writer) {
        return bookRepository.findByWriterId(writer);
    }

    public  List<AbstractBook> getBooksByGenre(String Genre) {
        return bookRepository.findByGenre(Genre);
    }

}
