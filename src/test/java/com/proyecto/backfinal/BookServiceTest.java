package com.proyecto.backfinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.repositories.BookRepository;
import com.proyecto.backfinal.services.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private AbstractBook testBook;
    private Writer testWriter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testWriter = new Writer("test Writer","examplemail@example.com","1234","hi");
        
        testBook = new ElectronicBook("978-950-563-656-3","Title Test", "Genre Test", "2020-10-17",testWriter,"urlTest",100);
        
        testBook.setWriter(testWriter);
    }

    @Test
    void createBook_ShouldReturnSavedBook() {
        when(bookRepository.save(any(AbstractBook.class))).thenReturn(testBook);

        AbstractBook result = bookService.createBook(testBook);

        assertNotNull(result);
        assertEquals(testBook.getIsbn(), result.getIsbn());
        assertEquals(testBook.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).save(any(AbstractBook.class));
    }

    @Test
    void deleteBook_ShouldCallRepositoryDelete() {
        bookService.deleteBook("978-950-563-656-3");

        verify(bookRepository, times(1)).deleteByIsbn("978-950-563-656-3");
    }

    @Test
    void updateBook_WhenBookExists_ShouldReturnUpdatedBook() {
        AbstractBook updatedBook = new AudioBook("978-950-563-656-3","Updated Title", "Updated Genre", "2020-10-17",testWriter,"urlTest",100);


        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(AbstractBook.class))).thenReturn(testBook);

        AbstractBook result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getGenre(), result.getGenre());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(AbstractBook.class));
    }

    @Test
    void updateBook_WhenBookDoesNotExist_ShouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> 
            bookService.updateBook(1L, testBook)
        );
        
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(AbstractBook.class));
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        List<AbstractBook> expectedBooks = Arrays.asList(testBook);
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<AbstractBook> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(expectedBooks.size(), result.size());
        assertEquals(expectedBooks.get(0), result.get(0));
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBook_WhenBookExists_ShouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        AbstractBook result = bookService.getBook(1L);

        assertNotNull(result);
        assertEquals(testBook.getIsbn(), result.getIsbn());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBook_WhenBookDoesNotExist_ShouldThrowException() {
        testBook = new ElectronicBook("978-950-563-656-3","Title Test", "Genre Test", "2020-10-17",testWriter,"urlTest",100);
        
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> 
            bookService.getBook(1L)
        );

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBooksByWriter_ShouldReturnWriterBooks() {
        List<AbstractBook> expectedBooks = Arrays.asList(testBook);
        
        when(bookRepository.findByWriterId(1L)).thenReturn(expectedBooks);

        List<AbstractBook> result = bookService.getBooksByWriterId(1L);

        assertNotNull(result);
        assertEquals(expectedBooks.size(), result.size());
        assertEquals(expectedBooks, result);
        verify(bookRepository, times(1)).findByWriterId(1L);
    }
}
