package com.securite.Securite.service.empreinte;


import com.securite.Securite.model.Empreinte;
import com.securite.Securite.repository.nature.EmpreinteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpreinteService {

    private final EmpreinteRepository empreinteRepository;

    public Empreinte saveEmpreinte(Long personneId, MultipartFile file) throws IOException {
        Empreinte empreinte = Empreinte.builder()
                .personneId(personneId)
                .image(file.getBytes())
                .build();
        return empreinteRepository.save(empreinte);
    }

    public Optional<Empreinte> getEmpreinte(Long id) {
        return empreinteRepository.findById(id);
    }
}

