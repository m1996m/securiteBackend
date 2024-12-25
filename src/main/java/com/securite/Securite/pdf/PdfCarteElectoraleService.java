package com.securite.Securite.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.service.CarteElectoraleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PdfCarteElectoraleService {
    private final CarteElectoraleService carteElectoraleService;

    String drapeauPath = "src/main/resources/static/image/drapeau.png"; // Logo du pays
    String fondPath = "src/main/resources/static/image/img.png";       // Image de fond

    public byte[] generateCarteElectoralePdf(long id) {
        CarteElectorale carte = carteElectoraleService.findUniqueById(id);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A6); // Format A6 pour la carte

            // Ajouter l'image de fond
            PdfCanvas canvas = new PdfCanvas(pdf.addNewPage());
            ImageData fond = ImageDataFactory.create(fondPath);
            canvas.addImageFittedIntoRectangle(fond, pdf.getFirstPage().getPageSize(), false);

            // Contenu principal
            document.add(new Paragraph("RÉPUBLIQUE DE GUINÉE\nCARTE ÉLECTORALE")
                    .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(12));

            // Logo
            Image logo = new Image(ImageDataFactory.create(drapeauPath)).scaleToFit(60, 40);
            document.add(logo.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // Table pour les informations personnelles
            Table table = new Table(2);
            table.setWidth(pdf.getFirstPage().getPageSize().getWidth() - 70);
            //table.setWidth(UnitValue.createPercentValue(100));

            table.addCell("N°:");
            table.addCell(String.valueOf(carte.getCarteNumber()));

            table.addCell("Nom:");
            table.addCell(carte.getElecteur().getPerson().getLastName());

            table.addCell("Prénom:");
            table.addCell(carte.getElecteur().getPerson().getFirtName());

            table.addCell("Date de naissance:");
            table.addCell(formatDate(carte.getElecteur().getPerson().getDateOfBirth()));

            table.addCell("Lieu de naissance:");
            table.addCell(carte.getElecteur().getPerson().getPlaceOfBirth());

            table.addCell("Adresse:");
            table.addCell(carte.getElecteur().getPerson().getAddress());

            table.addCell("Numéro unique:");
            table.addCell(carte.getElecteur().getPerson().getCodeUnique());

            document.add(table);

            // QR Code basé sur le numéro unique
            BarcodeQRCode qrCode = new BarcodeQRCode(carte.getElecteur().getPerson().getCodeUnique());
            Image qrImage = new Image(qrCode.createFormXObject(pdf)).scaleToFit(60, 60);
            qrImage.setFixedPosition(30, 30); // Positionner en bas à gauche
            document.add(qrImage);

            // Texte sous le QR Code
            document.add(new Paragraph(carte.getCarteNumber() + " - " + carte.getCodeUnique())
                    .setFontSize(8)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFixedPosition(30, 10, 150));

            // Footer
            document.add(new Paragraph("\nDélivré le : " + formatLocaleDate(LocalDate.from(carte.getCreatedAt())))
                    .setTextAlignment(TextAlignment.RIGHT).setFontSize(8));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de la carte électorale PDF", e);
        }
    }

    private String formatDate(Date date) {
        if (date == null) return "Non spécifié";
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return localDate.format(formatter);
    }

    private String formatLocaleDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }
}

