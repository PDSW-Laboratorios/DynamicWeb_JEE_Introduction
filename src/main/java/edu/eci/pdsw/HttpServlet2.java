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
 * Ingrese los datos y verifique los resultados. Cambie el formulario para que 
 * ahora en lugar de POST, use el método GET. Qué diferencia observa?
 * 
 * La diferencia que se observa es que cuando la consulta se realiza con el 
 * metodo GET, la consulta se puede ver en la barra del navegador, mientras que
 * en la otra permanece oculto, y solo es conocida por el navegador a la hora
 * de enviar la informacion, despues el unico que conoce la informacion es el
 * servidor.
 * 
 * @author fabian y skinman95
 */

@WebServlet(
        urlPatterns = "/infoClient"
)
public class HttpServlet2 extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        try {
            DataSourceStub data = DataSourceStub.getInstance();
            Client client = data.getClientById(  Integer.parseInt(req.getParameter("fid")) );
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
            resp.setStatus(200);
            
        } catch (ClientNotFoundException e){
            Logger.logMsg(Logger.ERROR, e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe un cliente con el identificador dado.");
            resp.flushBuffer();
        }
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        try {
            DataSourceStub data = DataSourceStub.getInstance();
            Client client = data.getClientById(  Integer.parseInt(req.getParameter("fid")) );
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
            resp.setStatus(200);
            
        } catch (ClientNotFoundException e){
            Logger.logMsg(Logger.ERROR, e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe un cliente con el identificador dado.");
            resp.flushBuffer();
        }
    }
}
