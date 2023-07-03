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

    /**
     * 회원 가입된 파트너 계정이 확인됐을 경우 store 등록이 가능
     * 
     * @param form
     * @return
     */
    
    @PostMapping("/register")
    public ResponseEntity<Store> storeRegister(@RequestBody StoreRegisterForm form) {
        return ResponseEntity.ok(storeService.storeRegister(form));
    }

    /**
     * 파트너 계정으로 store 삭제 가능
     * 
     * @param form
     * @return
     */

    @DeleteMapping("/delete")
    public ResponseEntity<String> storeDelete(@RequestBody StoreDeleteForm form) {
        return ResponseEntity.ok(storeService.storeDelete(form));
    }

}
