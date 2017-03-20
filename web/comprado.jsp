<%-- 
    Document   : espectaculos
    Created on : 20-mar-2017, 11:46:10
    Author     : Pedro
--%>


<%@page import="ProyectoEspectaculos.modelo.EspectaculosComprados"%>
<%@page import="ProyectoEspectaculos.modelo.ConsultaEspectaculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<%@page import="ProyectoEspectaculos.modelo.Espectaculo"%> 
<%@page import="java.util.LinkedList"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/login.css">
        <title>Espectaculos</title>
    </head>
    <body>
        <header id="main-header">
            <%
                HttpSession sesion = request.getSession();
                String usuario = new String();
                if (sesion.getAttribute("usuario") != null && sesion.getAttribute("usuario").toString().length() > 0) {
                    usuario = sesion.getAttribute("usuario").toString();
                    out.println("<a id=\"logo-header\" href=\"menu.jsp\"><span class=\"site-name\"> Bienvenido " + usuario + "</span></href>");
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>

            <nav>
                <ul>
                    <li> <a href="salir" type="submit" name="salir" id="Boton" value="salir">Salir</a><li>
                </ul>
            </nav>
        </header>

        <section id="main-content">
            <article>
                <div class="table-title">
                    <h3>Espectaculos comprados</h3>
                </div>
                <table class="table-fill">
                    <thead>
                        <tr>
                            <th class="text-left">id</th>
                            <th class="text-left">nombre</th>
                            <th class="text-left">descripcion</th>
                            <th class="text-left">fecha</th>
                            <th class="text-left">tipo</th>
                            <th class="text-left">precio</th>
                            <th class="text-left">sillas compradas</th> 
                            <th class="text-left">detalle</th>                          
                        </tr>
                    </thead>
                    <tbody class="table-hover">
                        <%
                            ConsultaEspectaculo cons = new ConsultaEspectaculo();
                            LinkedList<EspectaculosComprados> lista = cons.getEspectaculosComprados(usuario);
                            for (int i = 0; i < lista.size(); i++) {
                                out.println("<form  action =\"#\" method=\"post\">");
                                out.println("<tr>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getIdEspectaculo() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getNombre() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getDescripcion() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getFecha() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getTipo() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getPrecio() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getNumsillas() + "</td>");
                                out.println("<td class=\"text-left\"><button  type=\"submit\" name=\"Detalle\" id=\"Boton\" value=\"Detalle\"> Ver destalles</button></td>");
                                out.println("</tr>");
                                out.println("</form>");
                            }
                        %>
                    </tbody>
                </table>
            </article>
        </section>>
    </body>
</html>
