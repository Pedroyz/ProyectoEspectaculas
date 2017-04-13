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
                    out.println("<a id=\"logo-header\" href=\"menu.jsp\"><span class=\"site-name\"> Bienvenido " + usuario + "</span></a>");
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
                
                <%           
                    ConsultaEspectaculo cons = new ConsultaEspectaculo();
                    LinkedList<EspectaculosComprados> lista = cons.getEspectaculosComprados(usuario);

                    if(lista.size() > 0){
                %>
                
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
                            for (int i = 0; i < lista.size(); i++) {
                                out.println("<form  action =\"borrarEntradas\" method=\"post\">");
                                out.println("<tr>");
                                out.println("<input type=\"hidden\" name=\"idespectaculo\" value=\"" + lista.get(i).getIdEspectaculo() + "\">");
                                out.println("<input type=\"hidden\" name=\"tipoSilla\" value=\"" + lista.get(i).getTipo() + "\">");
                                out.println("<td class=\"text-left\" >" + lista.get(i).getIdEspectaculo() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getNombre() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getDescripcion() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getFecha() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getTipo() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getPrecio() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getNumsillas() + "</td>");
                                out.println("<td class=\"text-left\"><button  type=\"submit\" name=\"Detalle\" id=\"Boton\" value=\"Detalle\"> Borrar</button></td>");
                                out.println("</tr>");
                                out.println("</form>");
                            }
                        %>
                    </tbody>
                </table>
                    
                <%
                    }else{
                        out.println("<div class=\"table-title\"><h3>No tiene ninguna entrada comprada.</h3></div>");
                    }
                %>
                    
            </article>
        </section>
    </body>
</html>
