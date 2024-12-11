package com.securite.Securite.pdf;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.securite.Securite.model.DeclarationNaissance;
import com.securite.Securite.service.DeclarationNaissanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PdfDeclarationNaissanceService {
    private final DeclarationNaissanceService declarationNaissanceService;

    String logoPath = "src/main/resources/static/image/drapeau.png";
    String backgroundImagePath = "src/main/resources/static/image/img.png";

    public byte[] generateDeclarationNaissancePdf(long id) {
        DeclarationNaissance declaration = declarationNaissanceService.findUniqueById(id);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Création du document PDF
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfPage page = pdf.addNewPage();
            Rectangle pageSize = page.getPageSize();

            // Ajouter une image en arrière-plan
            ImageData background = ImageDataFactory.create(backgroundImagePath);
            PdfCanvas canvas = new PdfCanvas(page);
            canvas.addImageFittedIntoRectangle(background, pageSize, false);

// En-tête avec logo, titre centré et informations de l'organisme
            Table headerTable = new Table(3).useAllAvailableWidth(); // Trois colonnes pour l'en-tête

// Colonne 1 : Logo du drapeau (Aligné à gauche)
            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData).scaleToFit(80, 50);
            headerTable.addCell(new com.itextpdf.layout.element.Cell()
                    .add(logo)
                    .setBorder(com.itextpdf.layout.borders.Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

// Colonne 2 : Titre centré
            Paragraph title = new Paragraph()
                    .add(new com.itextpdf.layout.element.Text("RÉPUBLIQUE DE GUINÉE\n").setBold().setFontSize(14))
                    .add(new com.itextpdf.layout.element.Text("TRAVAIL - JUSTICE - SOLIDARITÉ").setItalic().setFontSize(10))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setPaddingTop(30); // Ajouter un padding-top

            headerTable.addCell(new com.itextpdf.layout.element.Cell()
                    .add(title)
                    .setBorder(com.itextpdf.layout.borders.Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.CENTER));

// Colonne 3 : Informations de l'organisme (Alignées à droite)
            String organismeInfo = String.format("Commune de %s\nAdresse: %s\nTel: %s\nEmail: %s",
                    declaration.getOrganisme().getName(),
                    declaration.getOrganisme().getAddress(),
                    declaration.getOrganisme().getTel(),
                    declaration.getOrganisme().getEmail());

            headerTable.addCell(new com.itextpdf.layout.element.Cell()
                    .add(new Paragraph(organismeInfo)
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setFontSize(10))
                    .setBorder(com.itextpdf.layout.borders.Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));

// Ajouter la table de l'en-tête au document
            document.add(headerTable);

// Phrase en dessous de l'en-tête
            document.add(new Paragraph("\nDÉCLARATION DE NAISSANCE\n")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16));

// Remplacement des blocs de texte par des paragraphes fluides
            document.add(new Paragraph("\nJe soussigné, Officier d'État Civil, certifie que Madame " +
                    declaration.getMere().getLastName() + " " + declaration.getMere().getFirtName() +
                    ", née le " + formatDate(declaration.getMere().getDateOfBirth()) +
                    ", exerçant la profession de " + declaration.getMere().getProfession() +
                    ", résidant à " + declaration.getMere().getAddress() +
                    ", a donné naissance à un enfant en date du " +
                    formatDate(declaration.getEnfant().getDateOfBirth()) + " à " +
                    declaration.getEnfant().getPlaceOfBirth() + ".")
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("\nL'enfant, de sexe " + declaration.getEnfant().getGenre() +
                    ", a été nommé " + declaration.getEnfant().getLastName() + " " +
                    declaration.getEnfant().getFirtName() + ", né à " +
                    declaration.getEnfant().getPlaceOfBirth() + ", et enregistré sous le rang de naissance : " +
                    declaration.getRangNaissance() + ".")
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("\nLe père de l'enfant, Monsieur " +
                    declaration.getPere().getLastName() + " " + declaration.getPere().getFirtName() +
                    ", né le " + formatDate(declaration.getPere().getDateOfBirth()) +
                    ", exerçant la profession de " + declaration.getPere().getProfession() +
                    ", résidant à " + declaration.getPere().getAddress() + ".")
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("\nLa déclaration de naissance a été faite par " +
                    declaration.getDeclarant().getLastName() + " " +
                    declaration.getDeclarant().getFirtName() + ", agissant en qualité de " +
                    declaration.getLienDeclarant() + ". L'acte a été établi à " +
                    declaration.getOrganisme().getAddress() + ", le " + formatDate(new Date()) + ".")
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

// Signature et footer
            document.add(new Paragraph("\nFait à " + declaration.getOrganisme().getAddress() +
                    ", le " + formatDate(new Date()) + ".\n\nSignature de l'officier d'état civil :")
                    .setTextAlignment(TextAlignment.RIGHT).setFontSize(12));

            // Génération du QRCode
            String qrCodeValue = declaration.getIdDeclarationBirth() + "-" + declaration.getCodeUnique();
            BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeValue);
            Image qrImage = new Image(qrCode.createFormXObject(pdf)).scaleToFit(80, 80);

            // Positionner le QRCode en bas à gauche
            qrImage.setFixedPosition(50, 50);
            document.add(qrImage);

            // Texte sous le QRCode
            document.add(new Paragraph(qrCodeValue)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFixedPosition(50, 35, 200)); // Position et largeur du texte

            // Fermer le document
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }

    private String formatDate(Date date) {
        if (date == null) return "Non spécifié";
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return localDate.format(formatter);
    }
}
