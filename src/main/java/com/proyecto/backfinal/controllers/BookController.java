// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 
package com.proyecto.backfinal.controllers;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.proyecto.backfinal.models.JwtValidator;
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

                // Ruta absoluta para guardar físicamente el archivo
                String absolutePath = System.getProperty("user.dir") + "/" +"uploads/" + contentFile.getOriginalFilename();
                
                contentFile.transferTo(new File(absolutePath));

                bookDTO.setContent(contentFile.getOriginalFilename());

                AbstractBook book;
                Optional<AbstractUser> author;
                author =  userService.getUserById(bookDTO.getWriter());

                if(!author.isEmpty()){
                    Writer writer = (Writer) author.get();
                    if(bookDTO.getType().equalsIgnoreCase("EBook")){
                        book = new AudioBook(bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getPublication(),writer,bookDTO.getContent(),bookDTO.getPrice(), bookDTO.getFormat());
                    } else{
                        book = new ElectronicBook(bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getPublication(),writer,bookDTO.getContent(),bookDTO.getPrice(), bookDTO.getFormat());
                    }
                    
                    bookService.createBook(book);
                    return ResponseEntity.ok().body(book);
                }
                               
                
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error al obtener libro"));
            }
            return null;
    }


    @DeleteMapping("DeleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam long id){

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
    public ResponseEntity<?> getBooksByWriter(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            
            token = token.substring(7);  // Elimina los 7 primeros caracteres ("Bearer ")
        }
        if(JwtValidator.validateJwtToken(token)){
            Long userId = JwtValidator.getUserIdFromJwtToken(token);
            Optional<AbstractUser> author = userService.getUserById(userId);
            if(!author.isEmpty()){
                Writer writer = (Writer) author.get();
                List<AbstractBook> books = bookService.getBooksByWriterId(writer.getId());
                if (books.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
                return ResponseEntity.ok(books);

            }
            
            
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");

        

    } 
}
