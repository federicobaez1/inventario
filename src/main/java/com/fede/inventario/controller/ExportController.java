package com.fede.inventario.controller;

import com.fede.inventario.dto.ExportRequest;
import com.fede.inventario.util.ExcelGenerator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/export")
public class ExportController {

    @PostMapping("/excel")
    public ResponseEntity<InputStreamResource> export(@RequestBody ExportRequest req) {

        try {
            ByteArrayInputStream stream = ExcelGenerator.generate(req);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=export.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(stream));

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
