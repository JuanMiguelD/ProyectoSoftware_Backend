package com.proyecto.backfinal.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backfinal.DTO.BookDTO;
import com.proyecto.backfinal.models.AbstractBook;
import com.proyecto.backfinal.models.AbstractUser;
import com.proyecto.backfinal.models.AudioBook;
import com.proyecto.backfinal.models.ElectronicBook;
import com.proyecto.backfinal.models.Writer;
import com.proyecto.backfinal.services.BookService;
import com.proyecto.backfinal.services.UserService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService  userService;


    @PostMapping("addBook")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO){

        AbstractBook book;
        Optional<AbstractUser>  writercontent;
        writercontent = userService.getUserById(bookDTO.getWriter());

        if(!writercontent.isEmpty()){
            Writer writer =  (Writer) writercontent.get();

            if ("EBook".equalsIgnoreCase(bookDTO.getType())) {
                book = new ElectronicBook(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getGenre(), bookDTO.getPublication(), writer, bookDTO.getContent(), bookDTO.getPrice());
            }
            else if ("audio".equalsIgnoreCase(bookDTO.getType())){
                book = new AudioBook(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getGenre(), bookDTO.getPublication(),writer, bookDTO.getContent(), bookDTO.getPrice());
            }
            else {
                return ResponseEntity.badRequest().build();
            }
            AbstractBook newBook  = bookService.createBook(book);

            return ResponseEntity.ok().body("Usuario registrado exitosamente: " + newBook.getIsbn());
        }
        

        return ResponseEntity.badRequest().body("Error: No se halló el escritor");
        
    }

    @PostMapping("/bygenres")
    public ResponseEntity<List<AbstractBook>> getBookByGenres(@RequestBody String genre){
         List<AbstractBook> books = bookService.getBooksByGenre(genre);
        if(books.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("DeleteBook")
    public ResponseEntity<?> deleteBook(@RequestBody String isbn){

        bookService.deleteBook(isbn);

        return  ResponseEntity.ok().build();

    }

    
}
