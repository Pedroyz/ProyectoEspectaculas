/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoEspectaculos.servlet;

import ProyectoEspectaculos.modelo.ConsultaEspectaculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pedro
 */
@WebServlet(name = "borrarEntradas", urlPatterns = {"/borrarEntradas"})
public class borrarEntradas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession sesion = request.getSession();
            
            if (sesion.getAttribute("usuario") == null || sesion.getAttribute("usuario").toString().length() <= 0) {
                response.sendRedirect("index.jsp");
            }

            if (request.getParameter("idespectaculo") == null || request.getParameter("tipoSilla") == null) {
                sesion.setAttribute("error", "Error al enviar parametros");
                response.sendRedirect("menu.jsp");
            }

            String user = sesion.getAttribute("usuario").toString();
            int idespectaculo = Integer.parseInt(request.getParameter("idespectaculo"));
            String tipoSilla = request.getParameter("tipoSilla");

            ConsultaEspectaculo con = new ConsultaEspectaculo();

            if (con.borrarSillas(idespectaculo, tipoSilla, user)) {
                sesion.removeAttribute("error");
            } else {
                 sesion.setAttribute("error", "Error al comprar las entradas");
            }           

            response.sendRedirect("comprado.jsp");
  
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(borrarEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(borrarEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}