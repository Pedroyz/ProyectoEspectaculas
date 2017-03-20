<%-- 
    Document   : espectaculos
    Created on : 20-mar-2017, 11:46:10
    Author     : Pedro
--%>



<%@page import="java.sql.Date"%>
<%@page import="ProyectoEspectaculos.modelo.ConsultaEspectaculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<%@page import="ProyectoEspectaculos.modelo.Silla"%> 
<%@page import="java.util.LinkedList"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/login.css">
        <title>Detalle Espectaculo</title>
    </head>
    <body>
        <div>
            
 
            
            
            
            <%
                HttpSession sesion = request.getSession();
                if (sesion.getAttribute("usuario") != null && sesion.getAttribute("usuario").toString().length() > 0) {
                    String usuario = sesion.getAttribute("usuario").toString();
                    out.println("<p> Bienvenido " + usuario + "</p>");
                } else {
                    response.sendRedirect("index.jsp");
                }
                
                if (request.getParameter("idespectaculo") == null ||  request.getParameter("fechaespectaculo") == null)
                    response.sendRedirect("menu.jsp");
                
                int idespectaculo =  Integer.parseInt(request.getParameter("idespectaculo"));
                String nombreespectaculo = request.getParameter("nombreespectaculo");
                String descripcionespectaculo = request.getParameter("descripcionespectaculo");
                String fechaespectaculo = request.getParameter("fechaespectaculo");

            
            %>

            <div class="table-title">
                <h3>Espectaculo <%=nombreespectaculo%></h3>
                <h2>Fecha: <%=fechaespectaculo%></h2>
                <p>Descripcion:</p>
                <p><%=descripcionespectaculo%></p>
            </div>
            <table class="table-fill">
                <thead>
                    <tr>
                        <th class="text-left">Tipo</th>
                        <th class="text-left">Disponibles</th>
                        <th class="text-left">Precio</th>
                        <th class="text-left">Nº Comprar</th>
                        <th class="text-left"></th>
                    </tr>
                </thead>
                <tbody class="table-hover">
                    <%
                        ConsultaEspectaculo cons = new ConsultaEspectaculo();
                        LinkedList<Silla> lista = cons.getSillas(idespectaculo);
                        for (int i = 0; i < lista.size(); i++) {
                            out.println("<form  action =\"comprarEntradas\" method=\"post\">");
                            out.println("<tr>");
                            out.println("<input type=\"hidden\" name=\"idespectaculo\" value=\""+idespectaculo+"\">");
                            out.println("<input type=\"hidden\" name=\"tipoSilla\" value=\""+lista.get(i).getTipo()+"\">");
                            out.println("<td class=\"text-left\">" + lista.get(i).getTipo()+ "</td>");
                            out.println("<td class=\"text-left\">" + lista.get(i).getNumeroLibres() + "</td>");
                            out.println("<td class=\"text-left\">" + lista.get(i).getPrecio()+ "</td>");
                            out.println("<td class=\"text-left\"><input type=\"number\" name=\"cantidad\" min=\"0\" max=\""+ lista.get(i).getNumeroLibres() +"\"/></td>");
                            out.println("<td class=\"text-left\"><button  type=\"submit\" name=\"Comprar\" id=\"Boton\" value=\"Comprar\"> Comprar</button></td>");
                            out.println("</tr>");
                            out.println("</form>");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
