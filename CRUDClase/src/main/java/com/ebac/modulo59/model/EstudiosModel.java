package com.ebac.modulo59.model;

import com.ebac.modulo59.dto.Estudio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudiosModel implements OperacionesCRUD<Estudio> {
    private final Connection connection;
    public EstudiosModel(Connection connection){
        this.connection = connection;
    }

    @Override
    public Estudio insertarDatos(Estudio estudios) throws SQLException {
        String sqlAgregar = "INSERT INTO estudios(idEmpleado, institucion , documento) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sqlAgregar);

        statement.setInt(1 , estudios.getIdEmpleado());
        statement.setString(2 , estudios.getInstitucion());
        statement.setString(3 , estudios.getDocumento());

        int elementoAgregado = statement.executeUpdate();
        if (elementoAgregado == 1){
            return estudios;
        }else {
            throw new SQLException("Error al agregar estudios");
        }
    }

    @Override
    public Estudio actualizarDatos(Estudio estudios) throws SQLException {
        String sqlActualizar = "UPDATE estudios SET idEmpleado = ? , institucion = ? , documento = ? WHERE idEstudio = ?";

        PreparedStatement statement = connection.prepareStatement(sqlActualizar);

        statement.setInt(1 , estudios.getIdEmpleado());
        statement.setString(2 , estudios.getInstitucion());
        statement.setString(3 , estudios.getDocumento());
        statement.setInt(4 , estudios.getIdEstudio());

        int elementoActualizado = statement.executeUpdate();
        if (elementoActualizado == 1){
            return estudios;
        }else {
            throw new SQLException("Error al agregar estudio");
        }
    }

    @Override
    public int eliminarDatos(int id) throws SQLException {
        String sqlEliminar = "DELETE FROM estudios WHERE idEstudio = ?";
        PreparedStatement statement = connection.prepareStatement(sqlEliminar);

        statement.setInt(1 , id);

        return statement.executeUpdate();
    }

    @Override
    public Estudio obtenerDatosPorId(int id) throws SQLException {
        String sqlSelecionar = "SELECT * FROM estudios WHERE idEstudio = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSelecionar);

        statement.setInt(1  , id);
        ResultSet resultSet = statement.executeQuery();

        Estudio estudios = new Estudio();
         while (resultSet.next()){
             estudios.setIdEstudio(resultSet.getInt("idEstudio"));
             estudios.setIdEmpleado(resultSet.getInt("idEmpleado"));
            estudios.setInstitucion(resultSet.getString("institucion"));
            estudios.setDocumento(resultSet.getString("documento"));
        }
        return estudios;
    }

    @Override
    public List<Estudio> obtenerDatosTodosId(int id) throws SQLException {
        String sqlSelecionar = "SELECT * FROM estudios WHERE idEmpleado = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSelecionar);
        statement.setInt(1 , id);

        ResultSet resultSet = statement.executeQuery();
        List<Estudio> estudioSelect = new ArrayList<>();
        while (resultSet.next()){
            Estudio estudio = new Estudio();

            estudio.setIdEstudio(resultSet.getInt("idEstudio"));
            estudio.setIdEmpleado(resultSet.getInt("idEmpleado"));
            estudio.setInstitucion(resultSet.getString("institucion"));
            estudio.setDocumento(resultSet.getString("documento"));

            estudioSelect.add(estudio);
        }
        return estudioSelect;
    }
}
