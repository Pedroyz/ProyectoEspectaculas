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
        <script src="js/popUp.js"></script>
    </head>
    <body>




        <form action ="registrar" method="post">
            <header>Registrarse</header>
            <label>Usuario <span>*</span></label>
            <input type="text" name="user" id="user"/>
            <%
                HttpSession sesion = request.getSession();
                String alerta = sesion.getAttribute("error").toString();
                if (alerta != null && alerta.length() > 0) {

            %>


            <script type="text/javascript">MensajeAlerta("Error al insertar el usuario");</script>
            <div class="error">Error: <%=alerta%> </div></p>
            <% }%>

        <label>Contraseña <span>*</span></label>
        <input type="password" name="pass" id="pass"/>
        <div class="help">La contraseña diferencia mayusculas de minusculas</div>
        <button type="submit" name="Entrar" id="Entrar" value="Entrar">Registrarse</button>
    </form>
</body>
</html>
