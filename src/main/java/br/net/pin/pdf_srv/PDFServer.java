package br.net.pin.pdf_srv;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class PDFServer {

  private final QueuedThreadPool threadPool;
  private final Server server;
  private final HttpConfiguration httpConfig;
  private final HttpConnectionFactory httpFactory;
  private final ServerConnector connector;
  private final ServletContextHandler context;

  public PDFServer() throws Exception {
    this.threadPool = new QueuedThreadPool(10, 3, 120);
    this.server = new Server(this.threadPool);
    this.httpConfig = new HttpConfiguration();
    this.httpConfig.setSendDateHeader(false);
    this.httpConfig.setSendServerVersion(false);
    this.httpFactory = new HttpConnectionFactory(this.httpConfig);
    this.connector = new ServerConnector(this.server, httpFactory);
    connector.setHost("localhost");
    connector.setPort(4592);
    this.server.setConnectors(new Connector[] { this.connector });
    this.context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    this.context.setContextPath("");
    this.server.setHandler(this.context);
    PDFService.init(this.context);
  }

  public void start() throws Exception {
    this.server.start();
    this.server.join();
  }

}
