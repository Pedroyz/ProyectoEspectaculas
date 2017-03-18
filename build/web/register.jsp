<%-- 
    Document   : index
    Created on : 16-mar-2017, 17:50:39
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        

        
        
        <form action ="validacion" method="post">
            <header>Login</header>
            <label>Usuario <span>*</span></label>
            <input type="text" name="user" id="user"/>

            <% 
                HttpSession sesion = request.getSession();
                if(sesion.getAttribute("intento") != null && Integer.parseInt(sesion.getAttribute("intento").toString()) > 0){
                   int intento = Integer.parseInt(sesion.getAttribute("intento").toString());
            %>
            <div class="help">Contraseña errónea, intento numero <%=intento%> </div>
            <% } %>
            
            <label>Contraseña <span>*</span></label>
            <input type="password" name="pass" id="pass"/>
            <div class="help">La contraseña diferencia mayusculas de minusculas</div>
            <button type="submit" name="Entrar" id="Entrar" value="Entrar">Login</button>

        </form>
    </body>
</html>
