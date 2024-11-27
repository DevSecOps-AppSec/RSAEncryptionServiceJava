
package com.example.rsaservice.controller;

import com.example.rsaservice.util.RSAUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RSAController {

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody Map<String, String> request) {
        try {
            String plaintext = request.get("plaintext");
            String encryptedText = RSAUtil.encrypt(plaintext);
            Map<String, String> response = new HashMap<>();
            response.put("encryptedText", encryptedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Encryption failed: " + e.getMessage()));
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody Map<String, String> request) {
        try {
            String encryptedText = request.get("encryptedText");
            String decryptedText = RSAUtil.decrypt(encryptedText);
            Map<String, String> response = new HashMap<>();
            response.put("decryptedText", decryptedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Decryption failed: " + e.getMessage()));
        }
    }
}
