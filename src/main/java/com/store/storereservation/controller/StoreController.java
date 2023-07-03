package com.store.storereservation.controller;

import com.store.storereservation.domain.store.Store;
import com.store.storereservation.domain.store.StoreDeleteForm;
import com.store.storereservation.domain.store.StoreRegisterForm;
import com.store.storereservation.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<Store> storeRegister(@RequestBody StoreRegisterForm form) {
        return ResponseEntity.ok(storeService.storeRegister(form));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> storeDelete(@RequestBody StoreDeleteForm form) {
        return ResponseEntity.ok(storeService.storeDelete(form));
    }

}
