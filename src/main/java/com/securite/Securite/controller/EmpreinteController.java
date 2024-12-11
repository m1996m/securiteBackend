package com.securite.Securite.controller;


import com.securite.Securite.model.Empreinte;
import com.securite.Securite.service.empreinte.EmpreinteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/empreintes")
@RequiredArgsConstructor
public class EmpreinteController {

    private final EmpreinteService empreinteService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadEmpreinte(
            @RequestParam Long personneId,
            @RequestParam("file") MultipartFile file) throws IOException {
        empreinteService.saveEmpreinte(personneId, file);
        return ResponseEntity.ok("Empreinte enregistrée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getEmpreinte(@PathVariable Long id) {
        Optional<Empreinte> empreinte = empreinteService.getEmpreinte(id);

        if (empreinte.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // ou IMAGE_JPEG selon le format
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"empreinte.png\"")
                    .body(empreinte.get().getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

