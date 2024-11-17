package com.proyecto.backfinal.services;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        boolean exists = bookRepository.existsByTitleAndWriterId(book.getTitle(), book.getWriter().getId());
        if (exists) {
            throw new IllegalArgumentException("A book with the same title and writer already exists.");
        }
        return bookRepository.save(book);
    }
    

    public void  deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public AbstractBook updateBook(Long id, AbstractBook updatedBook){

        return bookRepository.findById(id).map(book -> {

                book.setTitle(updatedBook.getTitle());                
                book.setGenre(updatedBook.getGenre());

                return bookRepository.save(book);
            }).orElseThrow(() -> new RuntimeException("Book not found"));
    
    }

    public  List<AbstractBook> getAllBooks() {
        return bookRepository.findAll();
    }

    public Set<String> getAllGenre(){
        List<AbstractBook> books = getAllBooks();
        return books.stream().map(AbstractBook::getGenre).collect(Collectors.toSet());
    }

    public  AbstractBook getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public  List<AbstractBook> getBooksByWriterId(Long writer) {
        return bookRepository.findByWriterId(writer);
    }

    public  List<AbstractBook> getBooksByGenre(String Genre) {
        return bookRepository.findByGenre(Genre);
    }

    

}
