<%-- 
    Document   : espectaculos
    Created on : 20-mar-2017, 11:46:10
    Author     : Pedro
--%>


<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
                if (sesion.getAttribute("usuario") != null && sesion.getAttribute("usuario").toString().length() > 0) {
                    String usuario = sesion.getAttribute("usuario").toString();
                    out.println("<a id=\"logo-header\" href=\"#\"><span class=\"site-name\"> Bienvenido " + usuario + "</span></a>");
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>

            <nav>
                <ul>
                    <li> <a href="comprado.jsp" >Mis entradas</a><li>
                    <li> <a href="salir" >Salir</a><li>
                </ul>
            </nav>
       </header>
            <%
                if (sesion.getAttribute("error") != null && sesion.getAttribute("error").toString().length() > 0) {
                    String alerta = sesion.getAttribute("error").toString();
            %>


            <script type="text/javascript">MensajeAlerta("Error al comprar la entrada");</script>
            <div class="error">Error: <%=alerta%> </div></p>
            <% }%>
    

    <section id="main-content">
        <article>
            
               <%           
                    ConsultaEspectaculo cons = new ConsultaEspectaculo();
                    LinkedList<Espectaculo> lista = cons.getEspectaculos();
                    LinkedList<Espectaculo> listaFechas = cons.getFechasDisponibles();
                    if(lista.size() > 0){
                %>
            
            <div class="table-title">
                <h3>Espectaculos disponibles</h3>
                <form  action ="menu.jsp" method="post" class="filtroFecha">
                <p> Filtrar por fecha: <select name="filtroFecha">
                    <option value=""> </option>    
                    <%
                        for (int i = 0; i < listaFechas.size(); i++)
                            out.println("<option value=\""+ listaFechas.get(i).getFecha() +"\">" + listaFechas.get(i).getFecha() + "</option>");
                    %>    
                    </select><button  type="submit" name="Detalle" id="Fecha" value="Detalle">Filtrar</button></p>
                 </form>
            </div>
            <table class="table-fill">
                <thead>
                    <tr>
                        <th class="text-left">id</th>
                        <th class="text-left">nombre</th>
                        <th class="text-left">descripcion</th>
                        <th class="text-left">fecha</th>
                        <th class="text-left">detalle</th>
                    </tr>
                </thead>
                <tbody class="table-hover">
                    <%
                        Date fecha = null;
                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");

                        if (request.getParameter("filtroFecha") != null && request.getParameter("filtroFecha").length() > 0) {
                            fecha = inputFormat.parse(request.getParameter("filtroFecha").toString());
                        }
                        
                        for (int i = 0; i < lista.size(); i++) {
                            if(fecha == null || fecha.compareTo(inputFormat.parse(lista.get(i).getFecha().toString())) == 0) {
                                out.println("<form  action =\"DetalleEspectaculo.jsp\" method=\"post\">");

                                out.println("<input type=\"hidden\" name=\"idespectaculo\" value= \"" + lista.get(i).getIdEspectaculo() + "\">");
                                out.println("<input type=\"hidden\" name=\"nombreespectaculo\" value=\"" + lista.get(i).getNombre() + "\">");
                                out.println("<input type=\"hidden\" name=\"descripcionespectaculo\" value=\"" + lista.get(i).getDescripcion() + "\">");
                                out.println("<input type=\"hidden\" name=\"fechaespectaculo\" value=\"" + lista.get(i).getFecha() + "\">");

                                out.println("<tr>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getIdEspectaculo() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getNombre() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getDescripcion() + "</td>");
                                out.println("<td class=\"text-left\">" + lista.get(i).getFecha() + "</td>");
                                out.println("<td class=\"text-left\"><button  type=\"submit\" name=\"Detalle\" id=\"Boton\" value=\"Detalle\"> Ver detalles</button></td>");
                                out.println("</tr>");
                                out.println("</form>");
                            }
                        }
                    %>
                </tbody>
            </table>
            <%
               }else{
                   out.println("<div class=\"table-title\"><h3>No quedan espectaculos disponibles en este momento.</h3></div>");
               }
           %>               
                
        </article>
    </section>
</body>
</html>
