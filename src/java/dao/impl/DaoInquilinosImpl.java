package dao.impl;

import dao.DaoInquilinos;
import dto.Inquilinos;
import java.sql.Connection;
import java.util.List;
import util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DaoInquilinosImpl implements DaoInquilinos {

    private final ConexionBD conexion;
    private String mensaje;

    // Constructor
    public DaoInquilinosImpl() {
        // Inicializamos la conexión
        this.conexion = new ConexionBD();
    }

    /**
     * Este método selecciona todos los inquilinos de la base de datos.
     *
     * @return Una lista de objetos Inquilinos que contiene información sobre
     * cada inquilino.
     */
    @Override
    public List<Inquilinos> inquilinosSel() {
        // Inicializamos la lista como nula
        List<Inquilinos> lista = null;
        // Creamos un objeto StringBuilder para construir la consulta SQL
        StringBuilder query = new StringBuilder();
        // Agregamos la cláusula SELECT a la consulta
        query.append("SELECT ")
                // Agregamos las columnas que queremos seleccionar
                .append("idInquilino, ")
                .append("dni, ")
                .append("nombres, ")
                .append("paterno, ")
                .append("materno, ")
                .append("telefono, ")
                .append("correo, ")
                .append("deuda, ")
                .append("fecha_ingreso ")
                // Agregamos la cláusula FROM y el nombre de la tabla
                .append("FROM inquilino");
        // Obtenemos una conexión a la base de datos
        try (Connection cn = conexion.conexionBD()) {
            // Preparamos la consulta SQL
            PreparedStatement ps = cn.prepareStatement(query.toString());
            // Ejecutamos la consulta y obtenemos el 'resultado'
            ResultSet rs = ps.executeQuery();
            // Creamos una nueva lista para almacenar los resultados
            lista = new ArrayList<>();
            // Iteramos sobre cada fila del 'resultado'
            while (rs.next()) {
                // Creamos un nuevo objeto Inquilinos
                Inquilinos inquilino = new Inquilinos();
                // Establecemos el valor de cada atributo del objeto Inquilinos con el valor correspondiente de la fila del resultado
                inquilino.setIdInquilino(rs.getInt(1));
                inquilino.setDni(rs.getString(2));
                inquilino.setNombres(rs.getString(3));
                inquilino.setPaterno(rs.getString(4));
                inquilino.setMaterno(rs.getString(5));
                inquilino.setTelefono(rs.getString(6));
                inquilino.setCorreo(rs.getString(7));
                inquilino.setDeuda(rs.getDouble(8));
                inquilino.setFecha_ingreso(LocalDate.parse(rs.getString(9)));
                // Agregamos el objeto Inquilino a la lista
                lista.add(inquilino);
            }
        } catch (SQLException e) {
            // Si ocurre una excepción SQL, almacenamos el mensaje en una variable
            mensaje = e.getMessage();
        }
        // Devolvemos la lista de objetos Inquilinos
        return lista;
    }

    /**
     * Este método devuelve la información de un inquilino específico.
     *
     * @param id El id del inquilino que se quiere obtener.
     * @return Un objeto Inquilinos que contiene la información del inquilino
     * con el id especificado.
     */
    @Override
    public Inquilinos inquilinosGet(Integer id) {
        // Creamos un nuevo objeto Inquilinos para almacenar la información del inquilino
        Inquilinos inquilino = new Inquilinos();
        // Creamos un objeto StringBuilder para construir la consulta SQL
        StringBuilder sql = new StringBuilder();
        // Agregamos la cláusula SELECT a la consulta
        sql.append("SELECT ")
                // Agregamos las columnas que queremos seleccionar
                .append("idInquilino,")
                .append("dni,")
                .append("nombres,")
                .append("paterno,")
                .append("materno,")
                .append("telefono,")
                .append("correo,")
                .append("deuda,")
                .append("fecha_ingreso ")
                // Agregamos la cláusula FROM y el nombre de la tabla
                .append("FROM inquilino ")
                // Agregamos la cláusula WHERE para filtrar por el id del inquilino
                .append("WHERE idInquilino = ?");
        // Obtenemos una conexión a la base de datos
        try (Connection cn = conexion.conexionBD()) {
            // Preparamos la consulta SQL
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            // Establecemos el valor del parámetro en la consulta
            ps.setInt(1, id);
            // Ejecutamos la consulta y obtenemos el resultado
            try (ResultSet rs = ps.executeQuery()) {
                // Si hay una fila en el resultado, significa que se encontró el inquilino con el id especificado
                if (rs.next()) {
                    // Establecemos el valor de cada atributo del objeto Inquilinos con el valor correspondiente de la fila del resultado
                    inquilino.setIdInquilino(rs.getInt(1));
                    inquilino.setDni(rs.getString(2));
                    inquilino.setNombres(rs.getString(3));
                    inquilino.setPaterno(rs.getString(4));
                    inquilino.setMaterno(rs.getString(5));
                    inquilino.setTelefono(rs.getString(6));
                    inquilino.setCorreo(rs.getString(7));
                    inquilino.setDeuda(rs.getDouble(8));
                    inquilino.setFecha_ingreso(LocalDate.parse(rs.getString(9)));
                } else {
                    // Si no hay ninguna fila en el resultado, significa que no se encontró el inquilino con el id especificado
                    inquilino = null;
                }
            } catch (SQLException e) {
                // Si ocurre una excepción SQL, almacenamos el mensaje en una variable
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            // Si ocurre una excepción SQL al obtener la conexión a la base de datos, almacenamos el mensaje en una variable
            mensaje = e.getMessage();
        }
        // Devolvemos el objeto Inquilinos con la información del inquilino
        return inquilino;
    }

    /**
     * Este método inserta un nuevo inquilino en la base de datos.
     *
     * @param inquilino Un objeto Inquilinos que contiene la información del
     * inquilino que se quiere insertar.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
    @Override
    public String inquilinosIns(Inquilinos inquilino) {
        // Creamos un objeto StringBuilder para construir la consulta SQL
        StringBuilder sql = new StringBuilder();
        // Agregamos la cláusula INSERT a la consulta
        sql.append("INSERT INTO inquilino( ")
                // Agregamos las columnas en las que queremos insertar valores
                .append("dni,")
                .append("nombres,")
                .append("paterno,")
                .append("materno,")
                .append("telefono,")
                .append("correo,")
                .append("deuda,")
                .append("fecha_ingreso")
                // Agregamos la cláusula VALUES y los marcadores de posición para los valores
                .append(") VALUES (?,?,?,?,?,?,?,?) ");
        // Obtenemos una conexión a la base de datos
        try (Connection cn = conexion.conexionBD()) {
            // Preparamos la consulta SQL
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            // Establecemos el valor de cada parámetro en la consulta con el valor correspondiente del objeto Inquilinos
            ps.setString(1, inquilino.getDni());
            ps.setString(2, inquilino.getNombres());
            ps.setString(3, inquilino.getPaterno());
            ps.setString(4, inquilino.getMaterno());
            ps.setString(5, inquilino.getTelefono());
            ps.setString(6, inquilino.getCorreo());
            ps.setDouble(7, inquilino.getDeuda());
            ps.setString(8, inquilino.getFecha_ingreso().toString());
            // Ejecutamos la consulta y obtenemos el número de filas afectadas
            int ctos = ps.executeUpdate();
            // Si no se insertó ninguna fila, mostramos un mensaje indicándolo
            if (ctos == 0) {
                mensaje = "cero filas insertadas";
            }
        } catch (SQLException e) {
            // Si ocurre una excepción SQL, almacenamos el mensaje en una variable
            mensaje = e.getMessage();
        }
        // Devolvemos el mensaje indicando si la operación fue exitosa o no
        return mensaje;
    }

    /**
     * Este método actualiza la información de un inquilino en la base de datos.
     *
     * @param inquilino Un objeto Inquilinos que contiene la información del
     * inquilino que se quiere actualizar.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
    @Override
    public String inquilinosUpd(Inquilinos inquilino) {
        // Creamos un objeto StringBuilder para construir la consulta SQL
        StringBuilder sql = new StringBuilder();
        // Agregamos la cláusula UPDATE a la consulta
        sql.append("UPDATE inquilino SET ")
                // Agregamos las columnas que queremos actualizar y los marcadores de posición para los valores
                .append("dni = ?,")
                .append("nombres = ?,")
                .append("paterno = ?,")
                .append("materno = ?,")
                .append("telefono = ?,")
                .append("correo = ?,")
                .append("deuda = ?,")
                .append("fecha_ingreso = ? ")
                // Agregamos la cláusula WHERE para filtrar por el id del inquilino
                .append("WHERE idInquilino = ? ");
        // Obtenemos una conexión a la base de datos
        try (Connection cn = conexion.conexionBD()) {
            // Preparamos la consulta SQL
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            // Establecemos el valor de cada parámetro en la consulta con el valor correspondiente del objeto Inquilinos
            ps.setString(1, inquilino.getDni());
            ps.setString(2, inquilino.getNombres());
            ps.setString(3, inquilino.getPaterno());
            ps.setString(4, inquilino.getMaterno());
            ps.setString(5, inquilino.getTelefono());
            ps.setString(6, inquilino.getCorreo());
            ps.setDouble(7, inquilino.getDeuda());
            ps.setString(8, inquilino.getFecha_ingreso().toString());
            ps.setInt(9, inquilino.getIdInquilino());
            // Ejecutamos la consulta y obtenemos el número de filas afectadas
            int ctos = ps.executeUpdate();
            // Si no se actualizó ninguna fila, mostramos un mensaje indicándolo
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            // Si ocurre una excepción SQL, almacenamos el mensaje en una variable
            mensaje = e.getMessage();
        }
        // Devolvemos el mensaje indicando si la operación fue exitosa o no
        return mensaje;
    }

    /**
     * Este método elimina uno o más inquilinos de la base de datos.
     *
     * @param ids Una lista de enteros que contiene los ids de los inquilinos
     * que se quieren eliminar.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
    @Override
    public String inquilinosDel(List<Integer> ids) {
        // Creamos un objeto StringBuilder para construir la consulta SQL
        StringBuilder sql = new StringBuilder();
        // Agregamos la cláusula DELETE a la consulta
        sql.append("DELETE FROM inquilino WHERE ")
                // Agregamos la cláusula WHERE para filtrar por el id del inquilino
                .append("idInquilino = ? ");
        // Obtenemos una conexión a la base de datos
        try (Connection cn = conexion.conexionBD()) {
            // Preparamos la consulta SQL
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            // Deshabilitamos el modo de confirmación automática para poder hacer una transacción
            cn.setAutoCommit(false);
            // Creamos una variable para llevar un registro de si todas las operaciones fueron exitosas
            boolean ok = true;
            // Iteramos sobre cada id en la lista de ids
            for (int id = 0; id < ids.size(); id++) {
                // Establecemos el valor del parámetro en la consulta con el id del inquilino que se quiere eliminar
                ps.setInt(1, ids.get(id));
                // Ejecutamos la consulta y obtenemos el número de filas afectadas
                int ctos = ps.executeUpdate();
                // Si no se eliminó ninguna fila, significa que no se encontró ningún inquilino con el id especificado
                if (ctos == 0) {
                    // Marcamos que hubo un error y almacenamos un mensaje indicándolo
                    ok = false;
                    mensaje = "ID: " + ids.get(id) + " no existe";
                }
            }
            // Si todas las operaciones fueron exitosas, confirmamos la transacción
            if (ok) {
                cn.commit();
            } else {
                // Si hubo algún error, deshacemos la transacción
                cn.rollback();
            }
            // Volvemos a habilitar el modo de confirmación automática
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            // Si ocurre una excepción SQL, almacenamos el mensaje en una variable
            mensaje = e.getMessage();
        }
        // Devolvemos el mensaje indicando si la operación fue exitosa o no
        return mensaje;
    }

    /**
     * Este método devuelve el valor de la variable mensaje.
     *
     * @return El valor de la variable mensaje.
     */
    @Override
    public String getMessage() {
        return mensaje;
    }
}
