
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Hacemos uso de la libreria JSTL--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inquilinos</title>
        <link rel="icon" href="assets/Clase.png">
        <script src="js/inquilinos.js" type="text/javascript"></script>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>
                        <a href="#" onclick="inquilinosIns();">
                            Agregar                            
                        </a>
                    </th>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>NOMBRES</th>
                    <th>PATERNO</th>
                    <th>MATERNO</th>
                    <th>TELEFONO</th>
                    <th>FECHA DE INGRESO</th>
                    <th>CORREO</th>
                    <th>DEUDAS</th>
                    <th>
                        <a href="#" onclick="inquilinosUpd();">
                            Actualizar                            
                        </a>
                    </th>
                    <th>
                        <a href="#" onclick="inquilinosDel();">
                            Eliminar                         
                        </a>
                    </th>
                </tr>
            </thead>
            <tbody>
                <%-- El 'list' es el mismo que se utiliza en InquilinosValidator --%>
                <c:forEach var="l" items="${list}">
                    <tr>
                        <td colspan="2">${l.idInquilino}</td>
                        <td>${l.dni}</td>
                        <td>${l.nombres}</td>
                        <td>${l.paterno}</td>
                        <td>${l.materno}</td>
                        <td>${l.telefono}</td>
                        <td>${l.fecha_ingreso}</td>
                        <td>${l.correo}</td>
                        <td>${l.deuda}</td>
                        <th>
                            <input type="radio" name="id_upd"
                                   value="${l.idInquilino}"/>
                        </th>
                        <th>
                            <input type="checkbox" name="id_del" 
                                   value="${l.idInquilino}"/>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
