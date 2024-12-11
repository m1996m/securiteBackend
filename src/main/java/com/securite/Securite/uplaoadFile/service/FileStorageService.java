package com.securite.Securite.uplaoadFile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    public String storeFile(MultipartFile file, String dirName) throws IOException {
        // Vérifie si le fichier est vide
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide");
        }

        // Crée le répertoire de destination si nécessaire
        Path uploadPath = Paths.get(UPLOAD_DIR + "/" + dirName);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Sauvegarde le fichier
        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        file.transferTo(filePath.toFile());

        return "Fichier uploadé avec succès : " + filePath.getFileName();
    }
}
