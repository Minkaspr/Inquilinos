
package test;

import dao.DaoInquilinos;
import dao.impl.DaoInquilinosImpl;
import dto.Inquilinos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDaoInquilinos {

    public static void main(String[] args) {

        Scanner scanner;
        // Creamos un objeto DaoInquilinos
        DaoInquilinos daoInquilinos = new DaoInquilinosImpl();
        Inquilinos inquilino;
        Integer opt;
        do {
            scanner = new Scanner(System.in);
            inicio();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    mostrarInquilinos(daoInquilinos);
                    break;
                case 2:
                    mostrarInquilino(daoInquilinos, 1);
                    break;
                case 3:
                    insertarInquilino(daoInquilinos);
                    break;
                case 4:
                    actualizarInquilino(daoInquilinos);
                    break;
                case 5:
                    eliminarInquilinos(daoInquilinos);
                    break;
                case 6:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opt != 0);
    }

    private static void inicio() {
        System.out.println("--------Claves de las prueba-----");
        System.out.println("1: Lista de usuarios UsuariosSel");
        System.out.println("2: Obtener usuario UsuariosGet");
        System.out.println("3: Insertar usuario UsuariosUpd");
        System.out.println("4: Actualizar usuario UsuariosIns");
        System.out.println("5: Eliminar usuario UsuariosDel");
        System.out.println("6: Salir");
        System.out.println("-----------------------------------");
        System.out.print("\nIngrese la clave: ");
    }

    /**
     * Este método muestra la información de todos los inquilinos.
     *
     * @param daoInquilinos Un objeto DaoInquilinos para acceder a los datos de
     * los inquilinos.
     */
    public static void mostrarInquilinos(DaoInquilinos daoInquilinos) {
        // Creamos una lista para almacenar los resultados
        // Llamamos al método inquilinosSel() para obtener la lista de inquilinos
        List<Inquilinos> list = daoInquilinos.inquilinosSel();
        if (list != null) {
            try {
                // Iteramos sobre cada elemento de la lista y mostramos los datos de cada inquilino
                list.forEach(inqui -> {
                    System.out.println(inqui.getIdInquilino().toString() + " "
                            + inqui.getDni() + " "
                            + inqui.getNombres() + " "
                            + inqui.getPaterno() + " "
                            + inqui.getMaterno() + " "
                            + inqui.getTelefono() + " "
                            + inqui.getCorreo() + " "
                            + inqui.getDeuda().toString() + " "
                            + inqui.getFecha_ingreso().toString());
                });
                System.out.println("");
            } catch (Exception e) {
                // Obtener el mensaje de error almacenado en el objeto dao
                System.out.println(daoInquilinos.getMessage());
                // Si ocurre una excepción, mostramos el mensaje de error
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Lista vacía");
        }
    }

    /**
     * Este método muestra la información de un inquilino específico.
     *
     * @param daoInquilinos Un objeto DaoInquilinos para acceder a los datos de
     * los inquilinos.
     * @param id El id del inquilino que se quiere mostrar.
     */
    public static void mostrarInquilino(DaoInquilinos daoInquilinos, int id) {
        // Llamamos al método inquilinosGet() para obtener el inquilino con el id especificado
        Inquilinos inquilino = daoInquilinos.inquilinosGet(id);
        if (inquilino != null) {
            try {
                // Mostramos los datos del inquilino
                System.out.println(inquilino.getIdInquilino().toString() + " "
                        + inquilino.getDni() + " "
                        + inquilino.getNombres() + " "
                        + inquilino.getPaterno() + " "
                        + inquilino.getMaterno() + " "
                        + inquilino.getTelefono() + " "
                        + inquilino.getCorreo() + " "
                        + inquilino.getDeuda().toString() + " "
                        + inquilino.getFecha_ingreso().toString());
                System.out.println("");
            } catch (Exception e) {
                // Obtener el mensaje de error almacenado en el objeto dao
                System.out.println(daoInquilinos.getMessage());
                // Si ocurre una excepción, mostramos el mensaje de error
                System.out.println(e.getMessage());
            }
        } else {
            // Si no se encontró el inquilino, mostramos un mensaje indicándolo
            System.out.println("No se encontró el inquilino con id: " + id);
        }
    }

    /**
     * Este método inserta un nuevo inquilino en la base de datos.
     *
     * @param daoInquilinos Un objeto DaoInquilinos para acceder a los datos de
     * los inquilinos.
     */
    public static void insertarInquilino(DaoInquilinos daoInquilinos) {
        // Creamos un nuevo objeto Inquilinos y establecemos sus atributos con los valores que queremos insertar
        Inquilinos inquilino = new Inquilinos();
        inquilino.setDni("70000000");
        inquilino.setNombres("ROBERT");
        inquilino.setPaterno("JONES");
        inquilino.setMaterno("áéóíúñ");
        inquilino.setTelefono("997777822");
        inquilino.setCorreo("robert@mail.com");
        inquilino.setDeuda(0d);
        inquilino.setFecha_ingreso(LocalDate.parse("2010-12-18"));
        try {
            // Llamamos al método inquilinosIns() para insertar el nuevo inquilino en la base de datos
            //daoInquilinos.inquilinosIns(inquilino);
            if (daoInquilinos.inquilinosIns(inquilino) == null) {
                System.out.println("Inquilino insertado con éxito");
            } else {
                System.out.println(daoInquilinos.inquilinosIns(inquilino));
            }
            System.out.println("");
        } catch (Exception e) {
            // Si ocurre una excepción, mostramos el mensaje de error
            System.out.println(daoInquilinos.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método actualiza la información de un inquilino en la base de datos.
     *
     * @param daoInquilinos Un objeto DaoInquilinos para acceder a los datos de
     * los inquilinos.
     */
    public static void actualizarInquilino(DaoInquilinos daoInquilinos) {
        // Creamos un nuevo objeto Inquilinos y establecemos sus atributos con los valores que queremos actualizar
        Inquilinos inquilino = new Inquilinos();
        inquilino.setIdInquilino(9);
        inquilino.setDni("79123123");
        inquilino.setNombres("PAUL");
        inquilino.setPaterno("SAMU");
        inquilino.setMaterno("DIAZ");
        inquilino.setTelefono("998484822");
        inquilino.setCorreo("paul@mail.com");
        inquilino.setDeuda(0d);
        inquilino.setFecha_ingreso(LocalDate.parse("2022-12-24"));
        try {
            // Llamamos al método inquilinosUpd() para actualizar la información del inquilino en la base de datos
            if (daoInquilinos.inquilinosUpd(inquilino) == null) {
                System.out.println("Inquilino actualizado con éxito");
            } else {
                System.out.println(daoInquilinos.inquilinosUpd(inquilino));
            }
            System.out.println("");
        } catch (Exception e) {
            // Si ocurre una excepción, mostramos el mensaje de error
            System.out.println(daoInquilinos.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método elimina uno o más inquilinos de la base de datos.
     *
     * @param daoInquilinos Un objeto DaoInquilinos para acceder a los datos de
     * los inquilinos.
     */
    public static void eliminarInquilinos(DaoInquilinos daoInquilinos) {
        // Creamos una lista para almacenar los ids de los inquilinos que queremos eliminar
        List<Integer> eliminar = new ArrayList<>();
        // Agregamos los ids de los inquilinos que queremos eliminar a la lista
        eliminar.add(3);
        //eliminar.add(4);
        try {
            // Llamamos al método inquilinosDel() para eliminar los inquilinos con los ids especificados
            if (daoInquilinos.inquilinosDel(eliminar) == null) {
                System.out.println("Inquilino eliminado con éxito");
            } else {
                System.out.println(daoInquilinos.inquilinosDel(eliminar));
            }
            System.out.println("");
        } catch (Exception e) {
            // Si ocurre una excepción, mostramos el mensaje de error
            System.out.println(daoInquilinos.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
