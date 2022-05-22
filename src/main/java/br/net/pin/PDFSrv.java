package br.net.pin;

import br.net.pin.pdf_srv.PDFServer;

public class PDFSrv {

  public static void main(String[] args) throws Exception {
    new PDFServer().start();
  }

}
