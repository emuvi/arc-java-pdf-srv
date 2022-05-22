package br.net.pin.pdf_srv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFWorker {
  public static String getTexts(PDDocument document, Integer page) throws IOException {
    var reader = new PDFTextStripper();
    reader.setStartPage(page);
    reader.setEndPage(page);
    return reader.getText(document);
  }

  public static String getImage(PDDocument document, Integer page) throws IOException {
    var renderer = new PDFRenderer(document);
    var image = renderer.renderImageWithDPI(page, 300);
    var buffer = new ByteArrayOutputStream();
    ImageIO.write(image, "JPEG", buffer);
    return new Base64().encodeToString(buffer.toByteArray());
  }
}
