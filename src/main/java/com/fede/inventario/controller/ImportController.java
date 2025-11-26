package com.fede.inventario.controller;

import com.fede.inventario.dto.ImportResponse;
import com.fede.inventario.util.ImportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class ImportController {

    @PostMapping("/excel")
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            ImportResponse data = ImportGenerator.readExcel(file.getInputStream());
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error leyendo Excel: " + e.getMessage());
        }
    }
}
