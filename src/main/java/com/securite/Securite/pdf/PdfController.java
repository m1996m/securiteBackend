package com.securite.Securite.pdf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;
    private final PdfExtraitNaissanceService pdfExtraitNaissanceService;
    private final PdfDeclarationNaissanceService pdfDeclarationNaissanceService;
    private final PdfCarteIdentiteService pdfCarteIdentiteService;
    private final PdfCarteElectoraleService pdfCarteElectoraleService;
    private final PdfPasseportService pdfPasseportService;


    public PdfController(
            PdfService pdfService,
            PdfExtraitNaissanceService pdfExtraitNaissanceService,
            PdfDeclarationNaissanceService pdfDeclarationNaissanceService,
            PdfCarteIdentiteService carteElectoraleService,
            PdfCarteElectoraleService pdfCarteElectoraleService,
            PdfPasseportService pdfPasseportService
    ) {
        this.pdfService = pdfService;
        this.pdfExtraitNaissanceService = pdfExtraitNaissanceService;
        this.pdfDeclarationNaissanceService = pdfDeclarationNaissanceService;
        this.pdfCarteIdentiteService = carteElectoraleService;
        this.pdfCarteElectoraleService = pdfCarteElectoraleService;
        this.pdfPasseportService = pdfPasseportService;
    }

    @GetMapping("/extrait/{id}")
    public ResponseEntity<byte[]> generateExtraitPdf(@PathVariable long id) {
        byte[] pdfBytes = pdfExtraitNaissanceService.generateExtraitNaissancePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=extrait.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/declaration/{id}")
    public ResponseEntity<byte[]> generateDeclarationPdf(@PathVariable long id) {
        byte[] pdfBytes = pdfDeclarationNaissanceService.generateDeclarationNaissancePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=declaration.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/carte/electeur/{id}")
    public ResponseEntity<byte[]> generateCarteElecteurPdf(@PathVariable long id) {
        byte[] pdfBytes = pdfCarteElectoraleService.generateCarteElectoralePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=electeur.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/carte/identite/{id}")
    public ResponseEntity<byte[]> generateCarteIdentitePdf(@PathVariable long id) {
        byte[] pdfBytes = pdfCarteIdentiteService.generateCarteIdentitePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=carteIdentite.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/passeport/{id}")
    public ResponseEntity<byte[]> generatePasseportdf(@PathVariable long id) {
        byte[] pdfBytes = pdfPasseportService.generatePasseportPdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=passeport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

