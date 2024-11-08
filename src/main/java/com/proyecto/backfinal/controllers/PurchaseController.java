package com.proyecto.backfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.proyecto.backfinal.models.Purchase;
import com.proyecto.backfinal.models.AbstractUser;
import com.proyecto.backfinal.models.AbstractBook;
import com.proyecto.backfinal.services.PurchaseService;
import com.proyecto.backfinal.repositories.UserRepository;
import com.proyecto.backfinal.repositories.BookRepository;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/new")
    public Purchase createPurchase(@RequestParam Long userId, @RequestParam Long bookId) {
        AbstractUser buyer = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        AbstractBook book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        return purchaseService.savePurchase(buyer, book);
    }
}

