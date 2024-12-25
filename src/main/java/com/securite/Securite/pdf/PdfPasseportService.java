package com.securite.Securite.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.service.PasseportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class PdfPasseportService {

    private final PasseportService passeportService;

    String fondPage = "src/main/resources/static/image/img.png";
    String photoPath = "src/main/resources/static/avatar/img.png";
    String drapeauPath = "src/main/resources/static/image/drapeau.png";

    public byte[] generatePasseportPdf(long id) {
        Passeport passeport = passeportService.findUniqueById(id);
        PageSize cardSize = new PageSize(220.6f, 350f).rotate();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf, cardSize);
            document.setMargins(10, 10, 10, 10);

            // === Page RECTO ===
            addRectoPage(pdf, document, passeport);

            // === Page VERSO ===
            addVersoPage(pdf, document, passeport);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du passeport PDF", e);
        }
    }

    private void addRectoPage(PdfDocument pdf, Document document, Passeport passeport) throws Exception {
        PageSize cardSize = new PageSize(220.6f, 350f).rotate();

        PdfPage rectoPage = pdf.addNewPage();
        PdfCanvas canvas = new PdfCanvas(rectoPage);

        canvas.addImageFittedIntoRectangle(ImageDataFactory.create(fondPage), cardSize, false);

        // En-tête
        Image drapeau = new Image(ImageDataFactory.create(drapeauPath)).scale(0.025f, 0.025f);
        drapeau.setFixedPosition((float) ((cardSize.getWidth() - 70)*1.02), (float) ((cardSize.getHeight() - 50)*1.085));
        document.add(drapeau);

        document.add(new Paragraph("RÉPUBLIQUE DE GUINÉE")
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14));

        // === Photo en arrière-plan en haut à droite ===
        Image photoArrierePlan = new Image(ImageDataFactory.create(photoPath)).scale(0.05f, 0.05f);
        photoArrierePlan.setFixedPosition((float) (cardSize.getWidth() - 90), (float) (cardSize.getHeight() - 90));
        photoArrierePlan.setOpacity(0.2f);
        document.add(photoArrierePlan);

        // === Photo en arrière-plan en haut à droite ===
        Image photoArrierePlanD = new Image(ImageDataFactory.create(photoPath)).scale(0.05f, 0.05f);
        photoArrierePlanD.setFixedPosition((float) ((cardSize.getWidth() - 90)*0.4), (float) ((cardSize.getHeight() - 90)*0.35));
        photoArrierePlanD.setOpacity(0.3f);
        document.add(photoArrierePlanD);

        // Tableau des informations principales
        Table headerTable = new Table(new float[]{2, 2, 2, 3}).setWidth(cardSize.getWidth() - 20);
        headerTable.addCell(new Cell().add(new Paragraph("Passeport")).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("Type")).add(new Paragraph("P")).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("Code Iso")).add(new Paragraph("GIN")).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("N° Passeport"))
                .add(new Paragraph(String.valueOf(passeport.getPassportNumber()))).setBorder(Border.NO_BORDER));
        headerTable.setTextAlignment(TextAlignment.CENTER).setFontSize(6);

        document.add(headerTable);

        // Tableau avec photo et informations
        Table mainTable = new Table(new float[]{2, 3, 3}).setWidth(cardSize.getWidth() - 20);
        mainTable.addCell(new Cell().add(new Image(ImageDataFactory.create(photoPath)).scaleToFit(80, 100)).setBorder(Border.NO_BORDER));
        mainTable.addCell(new Cell()
                .add(new Paragraph("Nom")).add(new Paragraph(passeport.getPerson().getLastName()))
                .add(new Paragraph("Prénom")).add(new Paragraph(passeport.getPerson().getFirtName()))
                .add(new Paragraph("Sexe")).add(new Paragraph("F"))
                .add(new Paragraph("Date de naissance")).add(new Paragraph(formatDate(passeport.getPerson().getDateOfBirth())))
                .add(new Paragraph("Date delivrance")).add(new Paragraph(formatDate(passeport.getDelivranceDate())))
                .add(new Paragraph("Date d'expiration")).add(new Paragraph(formatDate(passeport.getExpirationDate())))
                .setBorder(Border.NO_BORDER));
        mainTable.setTextAlignment(TextAlignment.LEFT);
        mainTable.addCell(new Cell()
                .add(new Paragraph("Numéro unique")).add(new Paragraph(String.valueOf(passeport.getCodeUnique())))
                .add(new Paragraph("Lieu de naissance")).add(new Paragraph(passeport.getPerson().getPlaceOfBirth()))
                .setBorder(Border.NO_BORDER));

        mainTable.setFontSize(6);

        document.add(mainTable);

        document.add(new Paragraph(generateMRZ(passeport)).setTextAlignment(TextAlignment.CENTER).setFontSize(10));

       // document.add(new Paragraph("\n\n\n\n"));
    }

    private void addVersoPage(PdfDocument pdf, Document document, Passeport passeport) throws Exception {
        PageSize cardSize = new PageSize(220.6f, 350f).rotate();

        PdfPage versoPage = pdf.addNewPage();
        PdfCanvas versoCanvas = new PdfCanvas(versoPage);
        versoCanvas.addImageFittedIntoRectangle(ImageDataFactory.create(fondPage), cardSize, false);

        document.add(new Paragraph("\n"));

        Table versoTable = new Table(2).setWidth(cardSize.getWidth() - 20);
        versoTable.addCell(new Cell().add(new Paragraph("Taille")).add(new Paragraph("1.75m")).setBorder(Border.NO_BORDER));
        versoTable.addCell(new Cell().add(new Paragraph("Distinction Particulière")).add(new Paragraph("Voir la photo")).setBorder(Border.NO_BORDER));
        versoTable.addCell(new Cell().add(new Paragraph("Yeux")).add(new Paragraph("Marron")).setBorder(Border.NO_BORDER));
        versoTable.addCell(new Cell().add(new Paragraph("Domicile")).add(new Paragraph("Conakry, Guinée")).setBorder(Border.NO_BORDER));
        versoTable.addCell(new Cell().add(new Paragraph("Ville")).add(new Paragraph("Conakry")).setBorder(Border.NO_BORDER));
        versoTable.setFontSize(6);
        document.add(versoTable);

        document.add(new Paragraph("Signature du titulaire")
                .setTextAlignment(TextAlignment.CENTER).setFontSize(6));
        document.add(new Paragraph("____________________").setTextAlignment(TextAlignment.CENTER));

        // Code-barres
        Barcode128 barcode = new Barcode128(pdf);
        barcode.setCode(String.valueOf(passeport.getCodeUnique()));
        barcode.setFont(null); // Retirer le texte sous le code-barres
        Image barcodeImage = new Image(barcode.createFormXObject(pdf));
        barcodeImage.scaleToFit(120, 30); // Taille du code-barres
        barcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);

        document.add(barcodeImage);
        document.add(new Paragraph(String.valueOf(passeport.getCodeUnique()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(6));
    }

    private String generateMRZ(Passeport passeport) {
        return "P<GIN" + passeport.getPerson().getLastName().toUpperCase() + "<<" +
                passeport.getPerson().getFirtName().toUpperCase() + "<<" +
                passeport.getPassportNumber();
    }

    private String formatDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
