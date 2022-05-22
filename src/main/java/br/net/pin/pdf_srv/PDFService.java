package br.net.pin.pdf_srv;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PDFService {
  public static void init(ServletContextHandler context) {
    initPing(context);
    initExtract(context);
  }

  private static void initPing(ServletContextHandler context) {
    context.addServlet(new ServletHolder(new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print("pong");
      }
    }), "/ping");
  }

  private static void initExtract(ServletContextHandler context) {
    context.addServlet(new ServletHolder(new HttpServlet() {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
        var body = IOUtils.toString(req.getReader());
        var forExtract = ForExtract.fromString(body);
        var hasExtract = new HasExtract();
        var document = PDDocument.load(new File(forExtract.path));
        if (forExtract.textsOfPage != null) {
          hasExtract.textsOfPage = PDFWorker.getTexts(document, forExtract.textsOfPage);
        }
        if (forExtract.imageOfPage != null) {
          hasExtract.imageOfPage = PDFWorker.getImage(document, forExtract.imageOfPage);
        }
        document.close();
        resp.setContentType("application/json");
        resp.getWriter().print(hasExtract.toString());
      }
    }), "/extract");
  }
}
