// Función para insertar inquilinos
function inquilinosIns() {
    // Redirige a la página de inserción de inquilinos
    window.location = "inquilinosIns.jsp";
}

// Función para actualizar inquilinos
function inquilinosUpd() {    
    // Obtiene el valor del radio button seleccionado
    var radio = document.querySelector("input[name='id_upd']:checked");
    // Si no se ha seleccionado ningún radio button
    if (radio === null) {
        // Muestra un mensaje de alerta
        alert("Seleccione Fila para Actualizar Datos");
    } else {
        // Obtiene el valor del radio button seleccionado
        var id = radio.value;
        // Redirige a la página de actualización de inquilinos con el id del inquilino seleccionado
        window.location = "Inquilinos?accion=GET&idInquilino=" + id;
    }
}

// Función para eliminar inquilinos
function inquilinosDel() {
    // Crea un arreglo vacío para almacenar los ids de los inquilinos a eliminar
    var ids = [];
    // Obtiene todos los checkboxes seleccionados y los agrega al arreglo de ids
    document.querySelectorAll("input[name='id_del']:checked").forEach(function (element) {
        ids.push(element.value);
    });
    // Si no se ha seleccionado ningún checkbox
    if (ids.length === 0) {
        // Muestra un mensaje de alerta
        alert("Advertencia\n\nSeleccione la(s) fila(s) a Retirar");
    } else {
        // Pregunta al usuario si está seguro de eliminar los registros seleccionados
        var exito = confirm('¿Seguro que deseas borrar los registros?');
        // Si el usuario confirma la eliminación
        if (exito) {
            // Redirige a la página de eliminación de inquilinos con los ids de los inquilinos a eliminar
            window.location = "Inquilinos?accion=DEL&ids=" + ids.toString();
        } else {
            // Muestra un mensaje indicando que la operación fue cancelada
            alert("Operación cancelada");
        }
    }
}
