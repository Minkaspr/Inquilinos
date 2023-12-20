package web.validator;

import dao.DaoInquilinos;
import dao.impl.DaoInquilinosImpl;
import dto.Inquilinos;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import util.DeString;

public class InquilinosValidator {
    // Propiedades de la clase

    // Objeto que representa la solicitud HTTP
    private final HttpServletRequest request;
    // Objeto para acceder a los datos de los inquilinos
    private final DaoInquilinos daoInquilinos;

    public InquilinosValidator(HttpServletRequest request) {
        // Asignamos el objeto de solicitud HTTP a la propiedad correspondiente
        this.request = request;
        // Creamos un nuevo objeto para acceder a los datos de los inquilinos
        this.daoInquilinos = new DaoInquilinosImpl();
    }

    /**
     * Obtiene una lista de inquilinos y la asigna como un atributo de la
     * solicitud HTTP.
     *
     * @return null si la operación fue exitosa, o un mensaje de error en caso
     * contrario
     */
    public String inquilinosSel() {
        // Variable para almacenar el resultado
        String result = null;
        // Obtenemos la lista de inquilinos
        List<Inquilinos> list = daoInquilinos.inquilinosSel();
        // Si la lista no es nula
        if (list != null) {
            // Asignamos la lista como un atributo de la solicitud HTTP
            request.setAttribute("list", list);
        } else { // Si la lista es nula
            // Obtenemos el mensaje de error del objeto daoInquilinos
            result = daoInquilinos.getMessage();
        }
        return result; // Devolvemos el resultado
    }

    /**
     * Obtiene un inquilino por su ID y lo asigna como un atributo de la
     * solicitud HTTP.
     *
     * @return null si la operación fue exitosa, o un mensaje de error en caso
     * contrario
     */
    public String inquilinosGet() {
        // Variable para almacenar el resultado
        String result = null;
        // Obtenemos el ID del inquilino de la solicitud HTTP y lo convertimos en un entero
        Integer idInquilino = DeString.aInteger(request.getParameter("idInquilino"));
        // Obtenemos el inquilino con el ID especificado
        Inquilinos inquilino = daoInquilinos.inquilinosGet(idInquilino);
        if (inquilino != null) { // Si el inquilino no es nulo
            // Asignamos el inquilino como un atributo de la solicitud HTTP
            request.setAttribute("inquilino", inquilino);
        } else {
            // Obtenemos el mensaje de error del objeto daoInquilinos
            result = daoInquilinos.getMessage();
        }
        return result; // Devolvemos el resultado
    }

    /**
     * Inserta o actualiza los datos de un inquilino en la base de datos.
     *
     * @param upd indica si se debe realizar una actualización (true) o una
     * inserción (false)
     * @return null si la operación fue exitosa, o una lista de mensajes de
     * error en caso contrario
     */
    public String inquilinosInsUpd(boolean upd) {
        // Variable para almacenar el resultado
        StringBuilder result = new StringBuilder("<ul>"); // (1)

        // Obtenemos el ID del inquilino de la solicitud HTTP y lo convertimos en un entero
        Integer idInquilino = DeString.aInteger(request.getParameter("idInquilino"));
        // Obtenemos el DNI del inquilino de la solicitud HTTP
        String dni = request.getParameter("dni");
        // Obtenemos los nombres del inquilino de la solicitud HTTP
        String nombres = request.getParameter("nombres");
        // Obtenemos el apellido paterno del inquilino de la solicitud HTTP
        String paterno = request.getParameter("paterno");
        // Obtenemos el apellido materno del inquilino de la solicitud HTTP
        String materno = request.getParameter("materno");
        // Obtenemos el teléfono del inquilino de la solicitud HTTP
        String telefono = request.getParameter("telefono");
        // Obtenemos la fecha de ingreso del inquilino de la solicitud HTTP y la convertimos en un objeto LocalDate
        /*LocalDate fecha_ingreso = LocalDate.parse(request.getParameter("fecha_ingreso"));*/
        String fecha_ingreso_str = request.getParameter("fecha_ingreso");
        LocalDate fecha_ingreso = null;
        // Obtenemos el correo electrónico del inquilino de la solicitud HTTP
        String correo = request.getParameter("correo");
        // Obtenemos la deuda del inquilino de la solicitud HTTP y la convertimos en un doble
        Double deuda = DeString.aDouble(request.getParameter("deuda"));

        // Realizamos varias validaciones para verificar que los valores ingresados sean válidos
        // Si se está realizando una actualización y el ID del inquilino es nulo
        if (upd && idInquilino == null) {
            result.append("<li>Id requerido</li>"); // Agregamos un mensaje de error al resultado
        }
        // Si los nombres están vacíos
        if (nombres == null || nombres.trim().length() == 0) {
            result.append("<li>Nombre requerido</li>"); // Agregamos un mensaje de error al resultado
            // Si los nombres tienen una longitud incorrecta
        } else if (nombres.trim().length() < 3 || nombres.trim().length() > 50) {
            result.append("<li>La dimensión del nombre debe estar entre")
                    .append("3 a 50 caracteres</li>"); // Agregamos un mensaje de error al resultado
        }
        // Si el apellido paterno está vacío
        if (paterno == null || paterno.trim().length() == 0) {
            result.append("<li>Apellido paterno requerido</li>"); // Agregamos un mensaje de error al resultado
            // Si el apellido paterno tiene una longitud incorrecta
        } else if (paterno.trim().length() < 3 || paterno.trim().length() > 50) {
            result.append("<li>La dimensión del apellido paterno debe estar entre")
                    .append("3 a 50 caracteres</li>"); // Agregamos un mensaje de error al resultado
        }
        // Si el apellido materno está vacío
        if (materno == null || materno.trim().length() == 0) {
            result.append("<li>Apellido materno requerido</li>"); // Agregamos un mensaje de error al resultado
            // Si el apellido materno tiene una longitud incorrecta
        } else if (materno.trim().length() < 3 || materno.trim().length() > 50) {
            result.append("<li>La dimensión del apellido materno debe estar entre")
                    .append("3 a 50 caracteres</li>"); // Agregamos un mensaje de error al resultado
        }
        // Si la fecha de ingreso es nula
        /*if (fecha_ingreso == null ) {
            result.append("<li>Fecha requerida</li>"); // Agregamos un mensaje de error al resultado
        }*/
        if (fecha_ingreso_str == null || fecha_ingreso_str.isEmpty()) {
            result.append("<li>Fecha requerida</li>");
        } else {
            fecha_ingreso = LocalDate.parse(fecha_ingreso_str);
            // ...
        }
        // Si la deuda es negativa
        if (deuda == null) {
            result.append("<li>Deuda requerida</li>");
        } else if (deuda < 0d) {
            result.append("<li>La deuda no puede ser negativa</li>"); // Agregamos un mensaje de error al resultado
        }

        // Creamos un nuevo objeto Inquilinos y le asignamos los valores obtenidos de la solicitud HTTP
        Inquilinos inquilino = new Inquilinos();
        inquilino.setIdInquilino(idInquilino);
        inquilino.setDni(dni);
        inquilino.setNombres(nombres);
        inquilino.setPaterno(paterno);
        inquilino.setMaterno(materno);
        inquilino.setFecha_ingreso(fecha_ingreso);
        inquilino.setTelefono(telefono);
        inquilino.setDeuda(deuda);
        inquilino.setCorreo(correo);

        // El valor 4 ↓ hace referente a la cantidad de caracteres de la etiqueta '<ul>' - en el (1)
        // Si no se han agregado mensajes de error al resultado
        if (result.length() == 4) {
            String msg = upd
                    // Si se está realizando una actualización, llamamos al método inquilinosUpd del objeto daoInquilinos
                    ? daoInquilinos.inquilinosUpd(inquilino)
                    // Si se está realizando una inserción, llamamos al método inquilinosIns del objeto daoInquilinos
                    : daoInquilinos.inquilinosIns(inquilino);
            if (msg != null) { // Si el método devolvió un mensaje de error
                result.append("<li>").append(msg).append("</li>"); // Agregamos el mensaje de error al resultado
            }
        }

        // Si se han agregado mensajes de error al resultado
        if (result.length() > 4) {
            // Asignamos el objeto inquilino como un atributo de la solicitud HTTP
            request.setAttribute("inquilino", inquilino);
        }
        // Devolvemos el resultado, que puede ser null si la operación fue exitosa o una lista de mensajes de error en caso contrario
        return result.length() == 4 ? null : result.append("</ul>").toString();
    }

    /**
     * Elimina una lista de inquilinos especificada por sus IDs.
     *
     * @return null si la operación fue exitosa, o un mensaje de error en caso
     * contrario
     */
    public String inquilinosDel() {
        // Obtenemos la lista de IDs de la solicitud HTTP y la convertimos en una lista de enteros
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        String result = (ids != null)
                // Si la lista de IDs no es nula, eliminamos los inquilinos con esos IDs
                ? daoInquilinos.inquilinosDel(ids)
                // Si la lista de IDs es nula, devolvemos un mensaje de error
                : "IDs incorrectos";
        return result; // Devolvemos el resultado
    }
}
