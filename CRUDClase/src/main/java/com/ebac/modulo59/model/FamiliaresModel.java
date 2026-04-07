package com.ebac.modulo59.model;

import com.ebac.modulo59.dto.Empleado;
import com.ebac.modulo59.dto.Familiar;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class FamiliaresModel implements OperacionesCRUD<Familiar> {

    private final Connection connection;
    public FamiliaresModel(Connection connection){
        this.connection = connection;
    }


    @Override
    public Familiar insertarDatos(Familiar familiares) throws SQLException {
        String sqlAgregar = "INSERT INTO familiares(idEmpleado, nombre, parentesco) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sqlAgregar);

        statement.setInt(1 , familiares.getIdEmpleado());
        statement.setString(2 , familiares.getNombre());
        statement.setString(3 , familiares.getParentesco());

        int elementoAgregaro = statement.executeUpdate();

        if (elementoAgregaro == 1){
            return familiares;
        }else {
            throw new SQLException("Error al agregar familiar");
        }
    }

    @Override
    public Familiar actualizarDatos(Familiar familiares) throws SQLException {
        String sqlActualizar = "UPDATE familiares set idEmpleado = ? , nombre = ? , parentesco = ? WHERE idFamilia = ?";
        PreparedStatement statement = connection.prepareStatement(sqlActualizar);

        statement.setInt(1 , familiares.getIdEmpleado());
        statement.setString(2 , familiares.getNombre());
        statement.setString(3 , familiares.getParentesco());
        statement.setInt(4,familiares.getIdFamilia());

        int elementoActualizado = statement.executeUpdate();

        if (elementoActualizado == 1){
            return familiares;
        }else {
            throw new SQLException("Error al actualizar familiar");
        }
    }

    @Override
    public int eliminarDatos(int id) throws SQLException {
        String sqlEliminar = "DELETE FROM familiares WHERE idFamilia = ?";
        PreparedStatement statement = connection.prepareStatement(sqlEliminar);

        statement.setInt(1 , id);

        return statement.executeUpdate();
    }

    @Override
    public Familiar obtenerDatosPorId(int id) throws SQLException {
        String sqlSeleccionar = "SELECT * FROM familiares WHERE idFamilia = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSeleccionar);

        statement.setInt(1 , id);

        ResultSet resultSet = statement.executeQuery();

        Familiar familiar = new Familiar();
        while (resultSet.next()){
            familiar.setIdFamilia(resultSet.getInt("idFamilia"));
            familiar.setIdEmpleado(resultSet.getInt("idEmpleado"));
            familiar.setNombre(resultSet.getString("nombre"));
            familiar.setParentesco(resultSet.getString("parentesco"));
        }
        return familiar;
    }


    @Override
    public List<Familiar> obtenerDatosTodosId(int id) throws SQLException {
        String sqlSelecionar = "SELECT * FROM familiares WHERE idEmpleado = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSelecionar);
        statement.setInt(1 , id);

        ResultSet resultSet = statement.executeQuery();
        List<Familiar> familiarSelect = new ArrayList<>();
        while (resultSet.next()){
            Familiar familiar = new Familiar();

            familiar.setIdFamilia(resultSet.getInt("idFamilia"));
            familiar.setIdEmpleado(resultSet.getInt("idEmpleado"));
            familiar.setNombre(resultSet.getString("nombre"));
            familiar.setParentesco(resultSet.getString("parentesco"));

            familiarSelect.add(familiar);
        }
        return familiarSelect;
    }
}
