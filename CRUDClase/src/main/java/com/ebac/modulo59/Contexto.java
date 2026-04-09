package com.ebac.modulo59;


import com.ebac.modulo59.conexion.MySQLConnection;
import com.ebac.modulo59.dto.Empleado;
import com.ebac.modulo59.dto.Estudio;
import com.ebac.modulo59.dto.Familiar;
import com.ebac.modulo59.model.EmpleadosModel;
import com.ebac.modulo59.model.EstudiosModel;
import com.ebac.modulo59.model.FamiliaresModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Contexto {

    static Connection connection;

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3307/modulo59";
        String user = "root";
        String password = "root";

        //Conexion
        MySQLConnection mySqlConnection = new MySQLConnection();
        connection = mySqlConnection.getConnection(url  , user , password);
        System.out.println(connection);

        //Operaciones
        operacionesBasesDeDatos();

        //Cierra conexion
        connection.close();
    }

    public static void operacionesBasesDeDatos() throws SQLException {
        //Operaciones con Empleados
        operacionConEmpleados();

        //Operacion con familiares
        operacionConFamilares();

        //Operacion con estudios
        operacionConEstudios();
    }

    public static void operacionConEmpleados() throws SQLException {
        System.out.println("-------------------- OPERACION CON EMPLEADOS -------------------->");

        EmpleadosModel empleadosModel = new EmpleadosModel(connection);

        //INSERTAR
        insertarEmpleados(empleadosModel);

        //SELECT POR ID
        obtenerEmpleadosPorId(empleadosModel);

        //SELECT
        obtenerEmpleados(empleadosModel);

        //ACTUALIZAR
        actualizaEmpleadoPorId(empleadosModel);

        //ELIMINAR
        eliminarEmpleado(empleadosModel);
    }

    public static void operacionConFamilares() throws SQLException {
        System.out.println("-------------------- OPERACION CON FAMILIARES -------------------->");

        FamiliaresModel familiaresModel = new FamiliaresModel(connection);
        EmpleadosModel empleadosModel = new EmpleadosModel(connection);

        //INSERTAR
        insertarFamilia(familiaresModel , empleadosModel);

        //SELECT POR ID
        obtenerFamiliarPorId(familiaresModel);

        //SELECT
        obtenerFamiliarTodosId(familiaresModel , 1);

        //ACTUALIZAR
        actualizaFamiliarPorId(familiaresModel);

        //ELIMINAR
        eliminarFamilia(familiaresModel);
    }

    public static void operacionConEstudios() throws SQLException {
        System.out.println("-------------------- OPERACION CON ESTUDIOS -------------------->");

        EstudiosModel estudiosModel = new EstudiosModel(connection);
        EmpleadosModel empleadosModel = new EmpleadosModel(connection);

        //INSERTAR
        insertarEstudio(estudiosModel , empleadosModel);

        //SELECT POR ID
        obtenerEstudiosPorId(estudiosModel);

        //SELECT
        obtenerEstudiosTodosId(estudiosModel , 1);

        //ACTUALIZAR
        actualizaEstudioPorId(estudiosModel);

        //ELIMINAR
        eliminarEstudio(estudiosModel);
    }

    private static void insertarEmpleados(EmpleadosModel empleadosModel) throws SQLException {
        System.out.println("------------------> Insertando empleados");

        Empleado empleadoAlta1 = altaEmpleado("Angel" , 40 , "CDMX" , "Empleado" , "Desarrollador");
        Empleado empleadoAlta2 = altaEmpleado("Karina" , 39 , "Puebla" , "Empleado" , "Logistica");
        Empleado empleadoAlta3 = altaEmpleado("Manuel" , 44 , "Estado de México" , "Operario" , "Transportista");

        Empleado empleado1 = empleadosModel.insertarDatos(empleadoAlta1);
        System.out.println(empleado1);

        Empleado empleado2 = empleadosModel.insertarDatos(empleadoAlta2);
        System.out.println(empleado2);

        Empleado empleado3 = empleadosModel.insertarDatos(empleadoAlta3);
        System.out.println(empleado3);
    }

    private static void obtenerEmpleadosPorId(EmpleadosModel empleadosModel) throws SQLException {
        Empleado empleadoTabla1 = empleadosModel.obtenerDatosPorId(1);
        System.out.println("------------------> Buscando empleados por Id (1)");
        System.out.println(empleadoTabla1);

        Empleado empleadoTabla2 = empleadosModel.obtenerDatosPorId(2);
        System.out.println("------------------> Buscando empleados por Id (2)");
        System.out.println(empleadoTabla2);

        Empleado empleadoTabla3 = empleadosModel.obtenerDatosPorId(3);
        System.out.println("------------------> Buscando empleados por Id (3)");
        System.out.println(empleadoTabla3);

        Empleado empleadoTablaError = empleadosModel.obtenerDatosPorId(10);
        System.out.println("------------------> Buscando empleados por Id (10)");
        System.out.println(empleadoTablaError);
    }

    private static void obtenerEmpleados(EmpleadosModel empleadosModel) throws SQLException {
        List<Empleado> empleados = empleadosModel.obtenerDatos();
        System.out.println("\n------------------> Listado de empleados");
        empleados.forEach(System.out::println);
    }

    private static void actualizaEmpleadoPorId(EmpleadosModel empleadosModel) throws SQLException {
        Empleado empleadoTabla = empleadosModel.obtenerDatosPorId(3);
        System.out.println("\n------------------> Actualizando empleados");

        if (!empleadoTabla.getNombre().isEmpty()){
            empleadoTabla.setPuesto("Almacenista");
            empleadoTabla.setEdad(28);

            //Actualiza
            Empleado empleadosActualizados = empleadosModel.actualizarDatos(empleadoTabla);

            empleadoTabla = empleadosModel.obtenerDatosPorId(3);
            System.out.println(empleadoTabla);

        }else{
            throw new SQLException("No se encontro el empleado a actualizar");
        }
    }

    private static void eliminarEmpleado(EmpleadosModel empleadosModel) throws SQLException {
        //Actualiza
        int empleadosEliminados = empleadosModel.eliminarDatos(2);

         if (empleadosEliminados != 1)
         {
            throw new SQLException("No se encontro el empleado a actualizar");
         }else {
             obtenerEmpleados(empleadosModel);
         }
    }

    private static Empleado altaEmpleado(String nombre , int edad , String direccion , String tipo , String puesto){
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(nombre);
        nuevoEmpleado.setEdad(edad);
        nuevoEmpleado.setDireccion(direccion);
        nuevoEmpleado.setTipo(tipo);
        nuevoEmpleado.setPuesto(puesto);

        return nuevoEmpleado;
    }

    private static void insertarFamilia(FamiliaresModel familiaresModel , EmpleadosModel empleadosModel) throws SQLException {
        System.out.println("------------------> Insertando familiares");

        Empleado empleado1 = empleadosModel.obtenerDatosPorId(1);
        System.out.println("------------------> Buscando empleados por Id (1)");
        System.out.println(empleado1);

        Empleado empleado3 = empleadosModel.obtenerDatosPorId(3);
        System.out.println("------------------> Buscando empleados por Id (3)");
        System.out.println(empleado3);

        if (!empleado1.getNombre().isEmpty()){
            Familiar familiarAlta1 = altaFamiliar(empleado1.getIdEmpleado() , "José" , "papá");
            Familiar familiarAlta2 = altaFamiliar(empleado1.getIdEmpleado() , "Gloria" , "mamá");
            Familiar familiarAlta3 = altaFamiliar(empleado1.getIdEmpleado() , "Otro" , "hermano");

            Familiar familiarAlta4 = altaFamiliar(empleado3.getIdEmpleado() , "Manuel" , "papá");
            Familiar familiarAlta5 = altaFamiliar(empleado3.getIdEmpleado() , "Ester" , "mamá");
            Familiar familiarAlta6 = altaFamiliar(empleado3.getIdEmpleado() , "Lidia" , "hermana");

            Familiar familiar1 = familiaresModel.insertarDatos(familiarAlta1);
            System.out.println(familiar1);

            Familiar familiar2 = familiaresModel.insertarDatos(familiarAlta2);
            System.out.println(familiar2);

            Familiar familiar3 = familiaresModel.insertarDatos(familiarAlta3);
            System.out.println(familiar3);

            Familiar familiar4 = familiaresModel.insertarDatos(familiarAlta4);
            System.out.println(familiar4);

            Familiar familiar5 = familiaresModel.insertarDatos(familiarAlta5);
            System.out.println(familiar5);

            Familiar familiar6 = familiaresModel.insertarDatos(familiarAlta6);
            System.out.println(familiar6);

        }else {
            throw new SQLException("No se encontro el empleado");
        }
    }

    private static Familiar altaFamiliar(int idEmpleado , String nombre , String parentesco){
        Familiar nuevoFamiliar = new Familiar();
        nuevoFamiliar.setIdEmpleado(idEmpleado);
        nuevoFamiliar.setNombre(nombre);
        nuevoFamiliar.setParentesco(parentesco);

        return nuevoFamiliar;

    }

    private static void obtenerFamiliarPorId(FamiliaresModel familiaresModel) throws SQLException {
        Familiar familiarTabla1 = familiaresModel.obtenerDatosPorId(1);
        System.out.println("------------------> Buscando familiar por Id (1)");
        System.out.println(familiarTabla1);

        Familiar familiarTabla2 = familiaresModel.obtenerDatosPorId(2);
        System.out.println("------------------> Buscando familiar por Id (2)");
        System.out.println(familiarTabla2);

        Familiar familiarTabla3 = familiaresModel.obtenerDatosPorId(3);
        System.out.println("------------------> Buscando familiar por Id (3)");
        System.out.println(familiarTabla3);

        Familiar familiarTablaError = familiaresModel.obtenerDatosPorId(10);
        System.out.println("------------------> Buscando familiar por Id (10)");
        System.out.println(familiarTablaError);
    }

    private static void obtenerFamiliarTodosId(FamiliaresModel familiaresModel , int idEmpleado) throws SQLException {
        List<Familiar> familiares = familiaresModel.obtenerDatosTodosId(idEmpleado);
        System.out.println("\n------------------> Listado de familiares empleado " + idEmpleado);
        familiares.forEach(System.out::println);
    }

    private static void actualizaFamiliarPorId(FamiliaresModel familiaresModel) throws SQLException {
        Familiar familiarTabla = familiaresModel.obtenerDatosPorId(3);

        System.out.println("\n------------------> Actualizando familia");
        System.out.println(familiarTabla);

        if (!familiarTabla.getNombre().isEmpty()){
            familiarTabla.setNombre("Karina");
            familiarTabla.setParentesco("Esposa");

            //Actualiza
            Familiar familiarActualizado = familiaresModel.actualizarDatos(familiarTabla);

            familiarTabla = familiaresModel.obtenerDatosPorId(3);
            System.out.println(familiarTabla);

        }else {
            throw new SQLException("No se encontro el familiar a actualizar");
        }
    }

    private static void eliminarFamilia(FamiliaresModel familiaresModel) throws SQLException {

        int familiaIdEliminar = 2;

        Familiar familiarTabla1 = familiaresModel.obtenerDatosPorId(familiaIdEliminar);
        System.out.println("------------------> Buscando familiar por Id (" + familiaIdEliminar + ")");
        System.out.println(familiarTabla1);

        int familiarEliminado = familiaresModel.eliminarDatos(familiaIdEliminar);

        if (familiarEliminado != 1)
        {
            throw new SQLException("No se encontro el familiar a actualizar");
        }else {
            obtenerFamiliarTodosId(familiaresModel , familiarTabla1.getIdEmpleado());
        }

    }

    private static void insertarEstudio(EstudiosModel estudiosModel , EmpleadosModel empleadosModel) throws SQLException {
        System.out.println("------------------> Insertando estudios");

        Empleado empleado1 = empleadosModel.obtenerDatosPorId(1);
        System.out.println("------------------> Buscando empleados por Id (1)");
        System.out.println(empleado1);

        Empleado empleado3 = empleadosModel.obtenerDatosPorId(3);
        System.out.println("------------------> Buscando empleados por Id (3)");
        System.out.println(empleado3);

        if (!empleado1.getNombre().isEmpty()){
            Estudio estudioAlta1 = altaEstudio(empleado1.getIdEmpleado() , "EBAC" , "En-Proceso");
            Estudio estudioAlta2 = altaEstudio(empleado1.getIdEmpleado() , "IPN" , "Ingeniero");
            Estudio estudioAlta3 = altaEstudio(empleado1.getIdEmpleado() , "CONALEP" , "Técnico");

            Estudio estudioAlta4 = altaEstudio(empleado3.getIdEmpleado() , "Villa de los niños" , "Técnico");
            Estudio estudioAlta5 = altaEstudio(empleado3.getIdEmpleado() , "Primaria rutal" , "Certificado");

            Estudio estudio1 = estudiosModel.insertarDatos(estudioAlta1);
            System.out.println(estudio1);

            Estudio estudio2 = estudiosModel.insertarDatos(estudioAlta2);
            System.out.println(estudio2);

            Estudio estudio3 = estudiosModel.insertarDatos(estudioAlta3);
            System.out.println(estudio3);

            Estudio estudio4 = estudiosModel.insertarDatos(estudioAlta4);
            System.out.println(estudio4);

            Estudio estudio5 = estudiosModel.insertarDatos(estudioAlta5);
            System.out.println(estudio5);

        }else {
            throw new SQLException("No se encontro el empleado");
        }
    }

    private static Estudio altaEstudio(int idEmpleado , String institucion , String documento){
        Estudio nuevoEstudio = new Estudio();
        nuevoEstudio.setIdEmpleado(idEmpleado);
        nuevoEstudio.setInstitucion(institucion);
        nuevoEstudio.setDocumento(documento);

        return nuevoEstudio;
    }

    private static void obtenerEstudiosPorId(EstudiosModel estudiosModel) throws SQLException {
        Estudio estudioTabla1 = estudiosModel.obtenerDatosPorId(1);
        System.out.println("------------------> Buscando estudio por Id (1)");
        System.out.println(estudioTabla1);

        Estudio estudioTabla2 = estudiosModel.obtenerDatosPorId(2);
        System.out.println("------------------> Buscando estudio por Id (2)");
        System.out.println(estudioTabla2);

        Estudio estudioTabla3 = estudiosModel.obtenerDatosPorId(3);
        System.out.println("------------------> Buscando estudio por Id (3)");
        System.out.println(estudioTabla3);

        Estudio estudioTablaError = estudiosModel.obtenerDatosPorId(10);
        System.out.println("------------------> Buscando estudio por Id (10)");
        System.out.println(estudioTablaError);
    }

    private static void obtenerEstudiosTodosId(EstudiosModel estudiosModel , int idEmpleado) throws SQLException {
        List<Estudio> estudios = estudiosModel.obtenerDatosTodosId(idEmpleado);
        System.out.println("\n------------------> Listado de estudios empleado " + idEmpleado);
        estudios.forEach(System.out::println);
    }

    private static void actualizaEstudioPorId(EstudiosModel estudiosModel) throws SQLException {
        Estudio estudioTabla = estudiosModel.obtenerDatosPorId(1);

        System.out.println("\n------------------> Actualizando estudio");
        System.out.println(estudioTabla);

        if (!estudioTabla.getInstitucion().isEmpty()){
            estudioTabla.setInstitucion("EBAC");
            estudioTabla.setDocumento("FULLSTACK");

            System.out.println(estudioTabla);
            //Actualiza
            Estudio estudioActualizado = estudiosModel.actualizarDatos(estudioTabla);

            //estudioActualizado = estudiosModel.obtenerDatosPorId(1);
            //System.out.println(estudioTabla);

        }else {
            throw new SQLException("No se encontro el estudio a actualizar");
        }
    }

    private static void eliminarEstudio(EstudiosModel estudiosModel) throws SQLException {

        int estudioIdEliminar = 2;

        Estudio estudioTabla1 = estudiosModel.obtenerDatosPorId(estudioIdEliminar);
        System.out.println("------------------> Buscando estudio por Id (" + estudioIdEliminar + ")");
        System.out.println(estudioTabla1);

        int familiarEliminado = estudiosModel.eliminarDatos(estudioIdEliminar);

        if (familiarEliminado != 1)
        {
            throw new SQLException("No se encontro el estudio a actualizar");
        }else {
            obtenerEstudiosTodosId(estudiosModel , estudioTabla1.getIdEmpleado());
        }

    }

}
