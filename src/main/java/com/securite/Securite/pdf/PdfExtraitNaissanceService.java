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
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.service.ExtraitNaissanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PdfExtraitNaissanceService {
    private final ExtraitNaissanceService extraitNaissanceService;

    String drapeau = "src/main/resources/static/image/drapeau.png";
    String devise = "src/main/resources/static/image/img.png";

    public byte[] generateExtraitNaissancePdf(long id) {
        ExtraitNaissance extraitNaissance = extraitNaissanceService.findUniqueById(id);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            String organismeInfo = formatOrganismeInfo(extraitNaissance.getDeclarationNaissance().getOrganisme());

            // Création du document PDF
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfPage page = pdf.addNewPage();
            Rectangle pageSize = page.getPageSize();

            ImageData backgroundImage = ImageDataFactory.create(devise);
            PdfCanvas canvas = new PdfCanvas(page);

            // Supprimer les bordures noires en désactivant le stroke (contour)
            canvas.saveState();
            canvas.rectangle(pageSize.getX(), pageSize.getY(), pageSize.getWidth(), pageSize.getHeight());
            canvas.clip();
            canvas.endPath();
            canvas.addImageFittedIntoRectangle(
                    backgroundImage,
                    pageSize,
                    false
            );
            canvas.restoreState();

            // En-tête : Divisé en deux parties avec image, nom du pays et devise
            ImageData imageData = ImageDataFactory.create(drapeau);
            Image logo = new Image(imageData).scaleToFit(70, 50);

            // Première partie : Logo (image) + Nom du pays + Devise
            Paragraph paysInfo = new Paragraph()
                    .add(
                            logo.setHorizontalAlignment(HorizontalAlignment.LEFT)
                                    .setMarginLeft(40)
                    )
                    .add("\nRÉPUBLIQUE DE GUINÉE")
                    .setBold()
                    .setFontSize(14)
                    .setMarginLeft(5)
                    .setTextAlignment(TextAlignment.LEFT);

            Paragraph deviseInfo = new Paragraph("Travail - Justice - Solidarité")
                    .setItalic()
                    .setFontSize(8)
                    .setMarginLeft(40)
                    .setTextAlignment(TextAlignment.LEFT);

            // Deuxième partie : Informations de l'organisme
            Paragraph organismeDetails = new Paragraph(organismeInfo)
                    .setFontSize(10)
                    .setMarginRight(5)
                    .setPadding(0);
                    //.setTextAlignment(TextAlignment.l);

            // Table pour aligner les deux parties dans l'en-tête
            Table headerTable = new Table(new float[]{1, 1});
            headerTable.setWidth(PageSize.A4.getWidth() - 60);

            // Première colonne : Image + Nom du pays + Devise
            Cell cellPays = new Cell()
                    .add(paysInfo)
                    .add(deviseInfo)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.CENTER);

           // Deuxième colonne : Informations de l'organisme
            Cell cellOrganisme = new Cell()
                    .add(organismeDetails)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT);

            // Ajouter les cellules à la table
            headerTable.addCell(cellPays);
            headerTable.addCell(cellOrganisme);

            // Ajouter l'en-tête au document
            document.add(headerTable);
            document.add(new Paragraph("\n")); // Espacement après l'en-tête

           // Titre "EXTRAIT DE NAISSANCE" centré
            Paragraph extraitTitle = new Paragraph("EXTRAIT DE NAISSANCE")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER);

            document.add(extraitTitle);
            document.add(new Paragraph("\n"));

            // Ajouter les informations détaillées de l'enfant
            document.add(new Paragraph("L'enfant, ")
                    .add(new Text(extraitNaissance.getPerson().getFirtName() + " " + extraitNaissance.getPerson().getLastName()).setBold())
                    .add(", de sexe ")
                    .add(new Text(String.valueOf(extraitNaissance.getPerson().getGenre()).toLowerCase()).setBold())
                    .add(", est né le ")
                    .add(new Text(formatDateHeure(extraitNaissance.getPerson().getDateOfBirth())).setBold())
                    .add(", à ")
                    .add(new Text(extraitNaissance.getPerson().getPlaceOfBirth()).setBold())
                    .add(". Le lieu de naissance est précisé comme étant ")
                    .add(new Text(extraitNaissance.getPerson().getAddress()).setBold())
                    .add(". ")
                    .add("La naissance a eu lieu au/a "+ extraitNaissance.getDeclarationNaissance().getPlace())
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("Son père, ")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getPere().getFirtName() + " " +
                            extraitNaissance.getDeclarationNaissance().getPere().getLastName()).setBold())
                    .add(", né le ")
                    .add(new Text(formatDate(extraitNaissance.getDeclarationNaissance().getPere().getDateOfBirth())).setBold())
                    .add(" à ")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getPere().getPlaceOfBirth()).setBold())
                    .add(", exerce la profession d'")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getPere().getProfession()).setBold())
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("Sa mère, ")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getMere().getFirtName() + " " +
                            extraitNaissance.getDeclarationNaissance().getMere().getLastName()).setBold())
                    .add(", née le ")
                    .add(new Text(formatDate(extraitNaissance.getDeclarationNaissance().getMere().getDateOfBirth())).setBold())
                    .add(" à ")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getMere().getPlaceOfBirth()).setBold())
                    .add(", exerce la profession d'")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getMere().getProfession()).setBold())
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            document.add(new Paragraph("La déclaration de naissance a été faite par ")
                    .add(new Text(extraitNaissance.getDeclarationNaissance().getDeclarant().getFirtName() + " " +
                            extraitNaissance.getDeclarationNaissance().getDeclarant().getLastName()).setBold())
                    .add(", ")
                    .add(new Text(extraitNaissance.getLienDeclarant()).setBold())
                    .add(" de l'enfant, en présence de l'officier d'état civil.")
                    .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12));

            // Footer
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Fait à " + extraitNaissance.getDeclarationNaissance().getOrganisme().getAddress())
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("Date : " + formatDate(new Date())).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Signature et cachet de l'autorité compétente")
                    .setTextAlignment(TextAlignment.CENTER).setItalic());

            String codeUnique = extraitNaissance.getPerson().getCodeUnique();
            String extraitNumber = String.valueOf(extraitNaissance.getExtraitNumber());

            BarcodeQRCode qrCode = new BarcodeQRCode(codeUnique);
            PdfCanvas qrCanvas = new PdfCanvas(pdf.getLastPage());
            Image qrImage = new Image(qrCode.createFormXObject(pdf)).scaleToFit(50, 50); // Taille du QR Code

            // Positionner le QR Code en bas à gauche
            float qrX = 30; // Position X
            float qrY = 30; // Position Y
            qrImage.setFixedPosition(qrX, qrY);
            document.add(qrImage);

            // Ajouter le texte sous le QR Code
            String qrText = "\n\n" + extraitNumber + " - " + codeUnique;
            Paragraph qrInfo = new Paragraph(qrText)
                    .setFontSize(10) // Taille de police
                    .setTextAlignment(TextAlignment.CENTER); // Centrer le texte

            // Positionner le texte sous le QR Code
            qrInfo.setFixedPosition(qrX, qrY - 15, 100); // Position X, Y et largeur de 100 unités
            document.add(qrInfo);

            // Fermer le document
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }

    private String formatDateHeure(Date date) {
        if (date == null) return "Non spécifié";

        // Conversion en LocalDateTime
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        DateTimeFormatter formatter;
        if (localDateTime.getHour() == 0 && localDateTime.getMinute() == 0) {
            // Si heure = 00:00, formater sans heure
            formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        } else {
            // Formater avec heure
            formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'à' HH'h'mm");
        }

        return localDateTime.format(formatter);
    }


    // Fonction pour formater la date en "15 juin 1990"
    private String formatDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return localDate.format(formatter);
    }

    public String formatOrganismeInfo(Organisme organisme) {
        if (organisme == null) {
            return "Informations de l'organisme non disponibles.";
        }

        return new StringBuilder()
                .append("Commune de ").append(organisme.getName() != null ? organisme.getName() : "Non spécifié").append("\n")
                //.append("Adresse: ").append(organisme.getAddress() != null ? organisme.getAddress() : "Non spécifiée").append("\n")
                .append("tel: ").append(organisme.getTel() != null ? organisme.getTel() : "Non spécifié").append("\n")
                .append("E-mail: ").append(organisme.getEmail() != null ? organisme.getEmail() : "Non spécifié").append("\n")
                .toString();
    }

}
