
package dao;

import dto.Inquilinos;
import java.util.List;

public interface DaoInquilinos {
    // Muestra todos los registros de la tabla inquilino
    List<Inquilinos> inquilinosSel();
    // Muestra un solo registro de la tabla inquilino
    Inquilinos inquilinosGet(Integer id);
    // Inserta un solo registro a la tabla inquilino
    String inquilinosIns(Inquilinos inquilino);
    // Inserta un solo registro a la tabla inquilino
    String inquilinosUpd(Inquilinos inquilino);
    // Inserta un solo registro a la tabla inquilino
    String inquilinosDel(List<Integer> ids);
    String getMessage();
}
