package com.proyecto.backfinal.controllers;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<?> addBook(@RequestParam("book") String bookJson, @RequestParam("content") MultipartFile contentFile){

            try{
                
                ObjectMapper objectMapper = new ObjectMapper();
                BookDTO bookDTO = objectMapper.readValue(bookJson, BookDTO.class);

                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                String filePath = uploadDir + contentFile.getOriginalFilename();

                contentFile.transferTo(new File(filePath));

                bookDTO.setContent(filePath);

                AbstractBook book;
                Optional<AbstractUser> writer;
                writer = userService.getUserById(bookDTO.getWriter());
                if(!writer.isEmpty()){
                    Writer Author = (Writer) writer.get();
                    
                    switch (bookDTO.getType()) {
                        case "EBook":
                            book = new AudioBook(bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getPublication(),Author,bookDTO.getContent(),bookDTO.getPrice());
                            break;
                        default:
                            book = new ElectronicBook(bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getPublication(),Author,bookDTO.getContent(),bookDTO.getPrice());
                            break;
                    }
                    bookService.createBook(book);
                    return ResponseEntity.ok().body(book);
                }
               
                
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error al obtener libro"));
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Ocurrio un error al tratar de hacer la operación"));      
    }


    @PostMapping("DeleteBook")
    public ResponseEntity<?> deleteBook(@RequestBody long id){

        bookService.deleteBook(id);

        return  ResponseEntity.ok().build();

    }

    @GetMapping("/allGenres")
    public ResponseEntity<Set<String>> getAllGenres() {
        Set<String> genres = bookService.getAllGenre();
        if (genres.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(genres);   
    }

    @GetMapping("allBooks")
    public ResponseEntity<List<AbstractBook>> getAllBooks() {
        List<AbstractBook> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
            }
            
        return ResponseEntity.ok(books);
    }

    @GetMapping("BooksByWriter")
    public ResponseEntity<List<AbstractBook>> getBooksByWriter(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Elimina los 7 primeros caracteres ("Bearer ")
        }
    
        AbstractUser writer = userService.getUserByToken(token);

        List<AbstractBook> books = bookService.getBooksByWriterId(writer.getId());
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);

    } 
}
