package com.cloudcontrolled.sample.jetty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;


public class App extends HttpServlet
{
    private static final long serialVersionUID = -1;
    private static final String CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.cloudcontrol.com/documentation/Guides/helloworld.css\" media=\"screen\" />";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(CSS);
        out.print("Hello world, I'm a Jetty app running on cloudControl!");
        out.flush();
        out.close();
    }
    
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()),"/");
        server.start();
        server.join();
    }
}
