package web.servlet;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.validator.InquilinosValidator;

@WebServlet(name = "InquilinosServlet", urlPatterns = {"/Inquilinos"})
public class InquilinosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Establecemos el tipo de contenido de la respuesta como HTML con codificación UTF-8
        response.setContentType("text/html;charset=UTF-8");

        // Obtenemos el valor del parámetro "accion" de la solicitud HTTP
        String accion = request.getParameter("accion");
        // Si el parámetro "accion" no está presente en la solicitud, asignamos una cadena vacía a la variable accion
        accion = (accion == null) ? "" : accion;

        String result; // Variable para almacenar el resultado de la operación
        String target = "inquilinosSel.jsp"; // Variable para almacenar la página a la que se redirigirá al usuario

        // Creamos un nuevo objeto InquilinosValidator y lo utilizamos para realizar diferentes acciones dependiendo del valor de la variable accion
        InquilinosValidator validator = new InquilinosValidator(request);
        switch (accion) {
            case "SEL": // Si accion es "SEL"
                // Llamamos al método inquilinosSel del objeto validator
                result = validator.inquilinosSel();
                break;
            case "INS": // Si accion es "INS"
                // Llamamos al método inquilinosInsUpd del objeto validator con el parámetro false
                result = validator.inquilinosInsUpd(false);
                // Si el resultado es nulo, redirigimos al usuario a la página "inquilinos.jsp", de lo contrario a la página "inquilinosIns.jsp"
                target = result == null ? "inquilinos.jsp" : "inquilinosIns.jsp";
                break;
            case "DEL": // Si accion es "DEL"
                result = validator.inquilinosDel();
                // Llamamos al método inquilinosDel del objeto validator
                target = "inquilinos.jsp"; // Redirigimos al usuario a la página "inquilinos.jsp"
                break;
            case "GET": // Si accion es "GET"
                // Llamamos al método inquilinosGet del objeto validator
                result = validator.inquilinosGet();
                target = "inquilinosUpd.jsp"; // Redirigimos al usuario a la página "inquilinosUpd.jsp"
                break;
            case "UPD": // Si accion es "UPD"
                // Llamamos al método inquilinosInsUpd del objeto validator con el parámetro true
                result = validator.inquilinosInsUpd(true);
                // Si el resultado es nulo, redirigimos al usuario a la página "inquilinos.jsp", de lo contrario a la página "inquilinosUpd.jsp"
                target = result == null ? "inquilinos.jsp" : "inquilinosUpd.jsp";
                break;
            case "":
                // Si accion es una cadena vacía, devolvemos un mensaje de error
                result = "Solicitud requerida";
                break;
            default:
                // Si accion no es ninguno de los valores anteriores, devolvemos un mensaje de error
                result = "Solicitud no reconocida";
        }

        if (result != null) { // Si el resultado no es nulo
            // Asignamos el resultado como un atributo de la solicitud HTTP
            request.setAttribute("message", result);
        }

        // Obtenemos un objeto RequestDispatcher para redirigir al usuario a la página especificada por la variable target
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        // Redirigimos al usuario a la página especificada por la variable target
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
