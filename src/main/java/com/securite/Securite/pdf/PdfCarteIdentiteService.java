package com.securite.Securite.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.properties.UnitValue;
import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.service.CarteIdentiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PdfCarteIdentiteService {

    private final CarteIdentiteService carteIdentiteService;

    String drapeau = "src/main/resources/static/image/drapeau.png";
    String devise = "src/main/resources/static/image/img.png";
    String avatar = "src/main/resources/static/avatar/img.png";

    public byte[] generateCarteIdentitePdf(long id) {
        CarteIdentite carte = carteIdentiteService.findUniqueById(id);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PageSize cardSize = new PageSize(180.6f, 300f).rotate();

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, cardSize);
            document.setMargins(2, 2, 2, 2);

            // Arrière-plan
            PdfCanvas canvas = new PdfCanvas(pdf.addNewPage());
            canvas.addImageFittedIntoRectangle(
                    ImageDataFactory.create(devise),
                    cardSize,
                    false
            );

            // Ajouter le contenu sur une seule page
            addCombinedContent(document, carte);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de la carte d'identité PDF", e);
        }
    }

    private void addCombinedContent(Document document, CarteIdentite carte) throws WriterException, MalformedURLException {
        PageSize cardSize = new PageSize(185.6f, 300f).rotate();

        // En-tête : Drapeau + Nom du Pays
        Table header = new Table(new float[]{1, 4}).setWidth(UnitValue.createPercentValue(100));
        Image flag = new Image(ImageDataFactory.create(drapeau)).scaleToFit(50, 30);
        header.addCell(new com.itextpdf.layout.element.Cell().add(flag).setBorder(Border.NO_BORDER));

        header.addCell(new com.itextpdf.layout.element.Cell()
                .add(new Paragraph("RÉPUBLIQUE DE GUINÉE").setBold().setTextAlignment(TextAlignment.LEFT))
                .add(new Paragraph("CARTE D'IDENTITÉ NATIONALE").setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));
        document.add(header);

        // === Photo en arrière-plan en haut à droite ===
        Image photoArrierePlan = new Image(ImageDataFactory.create(avatar)).scale(0.05f, 0.05f);
        photoArrierePlan.setFixedPosition((float) (cardSize.getWidth() - 90), (float) (cardSize.getHeight() - 90));
        photoArrierePlan.setOpacity(0.2f);
        document.add(photoArrierePlan);

        // === Tableau des informations avec photo et informations complémentaires ===
        Table table = new Table(new float[]{2, 3, 3}).setWidth(UnitValue.createPercentValue(100));

        // Première colonne : Photo
        Image photo = new Image(ImageDataFactory.create(avatar)).scaleToFit(70, 90);
        table.addCell(new com.itextpdf.layout.element.Cell().add(photo).setBorder(Border.NO_BORDER));

        // Deuxième colonne : Informations personnelles
        table.addCell(new com.itextpdf.layout.element.Cell()
                .add(new Paragraph()
                        .add(new Text("Nom : ").setBold()).add(carte.getPerson().getLastName()).setFontSize(8).add("\n")
                        .add(new Text("Prénom : ").setBold()).add(carte.getPerson().getFirtName()).setFontSize(8).add("\n")
                        .add(new Text("Sexe : ").setBold()).add(String.valueOf(carte.getPerson().getGenre())).setFontSize(8).add("\n")
                        .add(new Text("Date de Naissance : ").setBold()).add(formatDate(carte.getPerson().getDateOfBirth())).setFontSize(8).add("\n")
                        .add(new Text("Lieu de Naissance : ").setBold()).add(carte.getPerson().getPlaceOfBirth()).setFontSize(8).add("\n")
                        .add(new Text("Nationalité : ").setBold()).add(carte.getPerson().getNationality()).setFontSize(8).add("\n")
                        .add(new Text("Profession : ").setBold()).add(carte.getPerson().getProfession()).setFontSize(8)
                ).setBorder(Border.NO_BORDER)
        );

        // Troisième colonne : Informations document
        table.addCell(new com.itextpdf.layout.element.Cell()
                .add(new Paragraph()
                        .add(new Text("Date d'émission\n").setBold()).add(formatDate(carte.getDelivranceDate())).setFontSize(8).add("\n")
                        .add(new Text("Date d'expiration\n").setBold()).add(formatDate(carte.getExpirationDate())).setFontSize(8).add("\n")
                        .add(new Text("N° document\n").setBold()).add(
                                String.valueOf(carte.getCardNumber())+"-"+ carte.getCodeUnique()).setFontSize(8)
                ).setBorder(Border.NO_BORDER)
        );

        document.add(table);

        // === QRCode réduit et MRZ ===
        Table qrTable = new Table(new float[]{1, 3}).setWidth(UnitValue.createPercentValue(100));
        Image qrCode = generateQRCode(String.valueOf(carte.getCardNumber())).scaleToFit(30, 30); // Taille réduite à 50%

        qrTable.addCell(new com.itextpdf.layout.element.Cell().add(qrCode).setBorder(Border.NO_BORDER));

        qrTable.addCell(new com.itextpdf.layout.element.Cell()
                .add(new Paragraph("<<MRZ>>P<GUINEE<" + carte.getPerson().getLastName() + "<<" + carte.getPerson().getFirtName()
                        + "<<" + carte.getCardNumber())
                        .setFontSize(8)
                ).setBorder(Border.NO_BORDER)
        );

        document.add(qrTable);
    }

    private Image generateQRCode(String text) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 80, 80, hints);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(qrImage, "png", baos);
        } catch (Exception e) {
            throw new RuntimeException("Erreur QRCode", e);
        }
        return new Image(ImageDataFactory.create(baos.toByteArray()));
    }

    private String formatDate(Date date) {
        if (date == null) return "Non spécifié";
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
}
