package com.ebac.modulo59.model;

import java.sql.SQLException;
import java.util.List;

public interface OperacionesCRUD<T> {
    T insertarDatos(T t) throws SQLException;
    T actualizarDatos(T t) throws SQLException;
    int eliminarDatos(int id) throws SQLException;
    T obtenerDatosPorId(int id) throws SQLException;

    default List<T> obtenerDatos() throws SQLException {
        return null;
    }

    default List<T> obtenerDatosTodosId(int id) throws SQLException {
        return null;
    }
}
