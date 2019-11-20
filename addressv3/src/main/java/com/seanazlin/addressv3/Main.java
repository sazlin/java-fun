package com.seanazlin.addressv3;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.UriBuilder;


// Build into fat jar and run like so:
// java -jar /Users/seanazlin/IdeaProjects/addressv3/target/addressv3-1.0-SNAPSHOT-jar-with-dependencies.jar
public class Main {

    public static void main(String[] args) {

//        URI baseUri = UriBuilder.fromUri("http://localhost").port(8080).build();
//        ResourceConfig config = new ResourceConfig(JacksonJsonProvider.class);
//        Server server = JettyHttpContainerFactory.createServer(baseUri, config, false);
        Server server = new Server(8080);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/rest/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages","com.seanazlin.addressv3.resources");
        // TODO: figure out how to inject Jackson objectmapper for POJO<->JSON mapping

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}
