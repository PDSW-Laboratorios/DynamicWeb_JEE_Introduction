/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw;

import com.sun.media.jfxmedia.logging.Logger;
import edu.eci.pdsw.stubs.datasourcestub.Client;
import edu.eci.pdsw.stubs.datasourcestub.ClientNotFoundException;
import edu.eci.pdsw.stubs.datasourcestub.DataSourceStub;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fabian
 */

@WebServlet(
        urlPatterns = "/infoClient"
)
public class HttpServlet2 extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        System.out.println("1: "+resp.getStatus());
        try {
            System.out.println("2: "+resp.getStatus());
            DataSourceStub data = DataSourceStub.getInstance();
            Client client = data.getClientById(  Integer.parseInt(req.getParameter("id")) );
            responseWriter.write("<html>\n" +
"  <head>\n" +
"  </head>\n" +
"\n" +
"  <style>\n" +
"    table, th, td {\n" +
"    border: 1px solid black;\n" +
"    }\n" +
"  </style>\n" +
"  \n" +
"  <body>\n" +
"    <table style=\"width:100%\">\n" +
"      <tr>\n" +
"	<th> Nombre </th>\n" +
"	<th> Salario </th>\n" +
"	<th> Direccion </th>\n" +
"	<th> Email </th>\n" +
"      </tr>\n" +
"      <tr>\n" +
"	<th> "+client.getName()+" </th>\n" +
"	<th> "+client.getSallary()+" </th>\n" +
"	<th> "+client.getAddress()+"</th>\n" +
"	<th> "+client.getEmail()+" </th>\n" +
"      </tr>\n" +
"    </table>\n" +
"\n" +
"  </body>\n" +
"</html>");
            System.out.println("3: "+resp.getStatus());
            resp.setStatus(200);
            System.out.println("4: "+resp.getStatus());
            
        } catch (ClientNotFoundException e){
            System.out.println("5: "+resp.getStatus());
            responseWriter.write("<html><center><h1>404</h1></center><center><p>No existe un cliente con el identificador dado.</p></center></html>");
            responseWriter.flush();
            Logger.logMsg(Logger.ERROR, e.getMessage());
            System.out.println("6: "+resp.getStatus());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            resp.flushBuffer();
            System.out.println("7: "+resp.getStatus());
            throw new ServletException(e.getMessage(), e);
        }
        System.out.println("8: "+resp.getStatus());
    }
}
