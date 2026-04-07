package com.ebac.modulo59.model;

import com.ebac.modulo59.dto.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosModel implements OperacionesCRUD<Empleado> {

    private final Connection connection;
    public EmpleadosModel(Connection connection){
        this.connection = connection;
    }

    @Override
    public Empleado insertarDatos(Empleado empleados) throws SQLException {
        String sqlAgregar = "INSERT INTO empleados(nombre,edad,direccion,tipo,puesto) VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sqlAgregar);

        statement.setString(1, empleados.getNombre());
        statement.setInt(2 , empleados.getEdad());
        statement.setString(3, empleados.getDireccion());
        statement.setString(4, empleados.getTipo());
        statement.setString(5,empleados.getPuesto());

        int elementoAgregados = statement.executeUpdate();

        if (elementoAgregados == 1){
            return empleados;
        }else {
            throw new SQLException("Error al insertar registros!");

        }
    }

    @Override
    public Empleado actualizarDatos(Empleado empleados) throws SQLException {
        String sqlActualizar = "UPDATE empleados SET nombre=? , edad=?, direccion=?, tipo=?, puesto=? WHERE idEmpleado =? ";
        PreparedStatement statement = connection.prepareStatement(sqlActualizar);

        statement.setString(1 , empleados.getNombre());
        statement.setInt(2 , empleados.getEdad());
        statement.setString(3 , empleados.getDireccion());
        statement.setString(4 , empleados.getTipo());
        statement.setString(5 , empleados.getPuesto());
        statement.setInt(6 , empleados.getIdEmpleado());

        int elementoActualizados = statement.executeUpdate();

        if (elementoActualizados == 1){
            return empleados;
        }else {
            throw new SQLException("Error al actualizar el empleado");
        }
    }

    @Override
    public int eliminarDatos(int id) throws SQLException {
        String sqlEliminar = "DELETE FROM empleados WHERE idEmpleado = ?";
        PreparedStatement statement = connection.prepareStatement(sqlEliminar);

        statement.setInt(1 , id);

        return statement.executeUpdate();
    }

    @Override
    public Empleado obtenerDatosPorId(int id) throws SQLException {
        String sqlSelecionar = "SELECT * FROM empleados WHERE idEmpleado = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSelecionar);

        statement.setInt(1 , id);

        ResultSet resultSet = statement.executeQuery();

        Empleado empleadoSelect = new Empleado();
        while (resultSet.next()){
            empleadoSelect.setIdEmpleado(resultSet.getInt("idEmpleado"));
            empleadoSelect.setNombre(resultSet.getString("nombre"));
            empleadoSelect.setEdad(resultSet.getInt("edad"));
            empleadoSelect.setDireccion(resultSet.getString("direccion"));
            empleadoSelect.setTipo(resultSet.getString("tipo"));
            empleadoSelect.setPuesto(resultSet.getString("puesto"));
        }

        return empleadoSelect;
    }

    @Override
    public List<Empleado> obtenerDatos() throws SQLException {
        String sqlSelecionar = "SELECT * FROM empleados";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlSelecionar);

        List<Empleado> empleadosSelect = new ArrayList<>();

        while (resultSet.next()){
            Empleado empleado = new Empleado();

            empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
            empleado.setNombre(resultSet.getString("nombre"));
            empleado.setEdad(resultSet.getInt("edad"));
            empleado.setDireccion(resultSet.getString("direccion"));
            empleado.setTipo(resultSet.getString("tipo"));
            empleado.setPuesto(resultSet.getString("puesto"));

            empleadosSelect.add(empleado);
        }
        return empleadosSelect;
    }
}
