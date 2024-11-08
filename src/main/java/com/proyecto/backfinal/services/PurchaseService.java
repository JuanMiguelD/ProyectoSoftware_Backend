package com.proyecto.backfinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.backfinal.models.AbstractBook;
import com.proyecto.backfinal.models.Purchase;
import com.proyecto.backfinal.models.AbstractUser;
import com.proyecto.backfinal.repositories.PurchaseRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    // Método para obtener libros comprados
    public List<AbstractBook> getBooksPurchasedByUserId(Long userId) {
        return purchaseRepository.findBooksPurchasedByUserId(userId);
    }
    
    @Transactional
    // Método para guardar una nueva compra
    public Purchase savePurchase(AbstractUser buyer, AbstractBook book) {
        Purchase purchase = new Purchase();
        purchase.setUser(buyer);
        purchase.setBook(book);
        purchase.setPurchasePrice((double) book.getPrecio());
        purchase.setPurchaseDate(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }
}
