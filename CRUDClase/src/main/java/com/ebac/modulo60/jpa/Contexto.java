package com.ebac.modulo60.jpa;

import com.ebac.modulo60.jpa.dto.Direccion;
import com.ebac.modulo60.jpa.dto.Telefono;
import com.ebac.modulo60.jpa.dto.Usuario;
import com.ebac.modulo60.jpa.model.DireccionModel;
import com.ebac.modulo60.jpa.model.TelefonoModel;
import com.ebac.modulo60.jpa.model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Contexto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connectionLocalMySQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Modelos
        UsuarioModel usuarioModel = new UsuarioModel(entityManager);
        DireccionModel direccionModel = new DireccionModel(entityManager);
        TelefonoModel telefonoModel = new TelefonoModel(entityManager);

        //Operaciones Usuarios
        operacionesUsuarios(usuarioModel);

        //Operaciones Direcciones
        operacionesDirecciones(direccionModel);

        //Operaciones Telefonos
        operacionesTelefonos(telefonoModel);

        //Cerrar conexion
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void operacionesUsuarios(UsuarioModel usuarioModel) {
        //Alta usuarios
        altaUsuarios(usuarioModel);

        //Obtener lista de usuarios
        obtenerListadoUsuarios(usuarioModel);

        //Obtener lista de usuarios sql
        obtenerListadoUsuariosSQL(usuarioModel);

        //Obtener usuarios por Id
        obtenerListadoUsuariosPorId(usuarioModel);

        //Actualizar
        actualizarUsuario(usuarioModel);

        //Eliminat
        eliminaUsuario(usuarioModel);
    }

    private static void altaUsuarios(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ALTA DE USUARIOS <---------------------");
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Angel");
        usuario1.setEdad(40);
        System.out.println(usuario1);
        usuarioModel.guardarUsuario(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Alejandro");
        usuario2.setEdad(46);
        System.out.println(usuario2);
        usuarioModel.guardarUsuario(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setNombre("Karla");
        usuario3.setEdad(36);
        System.out.println(usuario3);
        usuarioModel.guardarUsuario(usuario3);
    }

    private static void obtenerListadoUsuarios(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> LISTADO DE USUARIOS <---------------------");
        usuarioModel.obtenerUsuarios().forEach(System.out::println);
    }

    private static void obtenerListadoUsuariosSQL(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> LISTADO DE USUARIOS SQL <---------------------");
        usuarioModel.obtenerUsuariosSQL().forEach(System.out::println);
    }

    private static void obtenerListadoUsuariosPorId(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ALTA DE USUARIOS POR ID <---------------------");
        Usuario usuario = usuarioModel.obtenerUsuarioPorId(1);
        System.out.println(usuario);
    }

    private static void actualizarUsuario(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ACTUALIZA USUARIO <---------------------");
        Usuario usuario = usuarioModel.obtenerUsuarioPorId(1);
        usuario.setNombre("Jose Angel");
        usuarioModel.actualizarUsuario(usuario);
        System.out.println(usuario);

        //Lista de usuarios despues del cambio
        obtenerListadoUsuarios(usuarioModel);

    }

    private static void eliminaUsuario(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ELIMINA USUARIO <---------------------");
        Usuario usuario = usuarioModel.obtenerUsuarioPorId(1);
        if (!usuario.getNombre().isEmpty()){
            System.out.println(usuario);
            usuarioModel.eliminarUsuario(usuario);
        }
        //Lista de usuarios despues del cambio
        obtenerListadoUsuarios(usuarioModel);
    }

    private static void operacionesDirecciones(DireccionModel direccionModel){
        //Alta Direcciones
        altaDirecciones(direccionModel);

        //Obtener listado de direcciones
        obtenerListadoDirecciones(direccionModel);

        //Obtener listado direcciones SQL
        obtenerListadoDireccionesSQL(direccionModel);

        //Actualizar direcciones
        actualizarDireccion(direccionModel);

        //Elimina direcciones
        eliminarDireccion(direccionModel);
    }

    private static void altaDirecciones(DireccionModel direccionModel){
        System.out.println("\n---------------------> ALTA DE DIRECCIONES <---------------------");
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("ALuminio");
        direccion1.setIdUsuario(1);
        direccion1.setNumero(20);
        direccion1.setEstado("Nuevo Leon");
        System.out.println(direccion1);
        direccionModel.guardarDireccion(direccion1);

        Direccion direccion2 = new Direccion();
        direccion2.setCalle("Calzada del hueso");
        direccion2.setIdUsuario(2);
        direccion2.setNumero(808);
        direccion2.setEstado("CDMX");
        System.out.println(direccion2);
        direccionModel.guardarDireccion(direccion2);

        Direccion direccion3 = new Direccion();
        direccion3.setCalle("Centro");
        direccion3.setIdUsuario(3);
        direccion3.setNumero(151);
        direccion3.setEstado("Tabasco");
        System.out.println(direccion3);
        direccionModel.guardarDireccion(direccion3);
    }

    private static void obtenerListadoDirecciones(DireccionModel direccionModel){
        System.out.println("\n---------------------> LISTADO DE DIRECCIONES <---------------------");
        direccionModel.obtenerDirecciones().forEach(System.out::println);
    }

    private static void obtenerListadoDireccionesSQL(DireccionModel direccionModel){
        System.out.println("\n---------------------> LISTADO DE DIRECCIONES SQL <---------------------");
        direccionModel.obtenerDireccionesSQL().forEach(System.out::println);
    }

    private static void actualizarDireccion(DireccionModel direccionModel){
        System.out.println("\n---------------------> ACTUALIZA DIRECCION <---------------------");
        Direccion direccion = direccionModel.obtenerDireccionPorId(2);
        direccion.setCalle("Circuito azteca");
        direccion.setNumero(8);
        direccionModel.actualizarDireccion(direccion);
        System.out.println(direccion);

        //listado de direcciones despues del cambio
        obtenerListadoDirecciones(direccionModel);
    }

    private static void eliminarDireccion(DireccionModel direccionModel){
        System.out.println("\n---------------------> ELIMINA DIRECCION <---------------------");
        Direccion direccion = direccionModel.obtenerDireccionPorId(1);
        if (!direccion.getCalle().isEmpty()){
            System.out.println(direccion);
            direccionModel.eliminarDireccion(direccion);
        }
        //listado de direcciones despues del cambio
        obtenerListadoDirecciones(direccionModel);
    }

    private static void operacionesTelefonos(TelefonoModel telefonoModel){
        //Alta telefono
        altaTelefonos(telefonoModel);

        //Obtener listado de Telefonos
        obtenerListadoTelefonos(telefonoModel);

        //Obtener listado de Telefonos SQL
        obtenerListadoTelefonosSQL(telefonoModel);

        //Actualiza telefono
        actualizarTelefono(telefonoModel);

        //Elimina telefono
        eliminarTelefono(telefonoModel);
    }

    private static void altaTelefonos(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ALTA DE TELEFONOS <---------------------");
        Telefono telefono1 = new Telefono();
        telefono1.setNumero("5566775566");
        telefono1.setTipo("Casa");
        telefono1.setIdUsuario(1);
        System.out.println(telefono1);
        telefonoModel.guardarTelefono(telefono1);

        Telefono telefono2 = new Telefono();
        telefono2.setNumero("0445567655");
        telefono2.setTipo("Oficina");
        telefono2.setIdUsuario(2);
        System.out.println(telefono2);
        telefonoModel.guardarTelefono(telefono2);

        Telefono telefono3 = new Telefono();
        telefono3.setNumero("6634233455");
        telefono3.setTipo("Celular");
        telefono3.setIdUsuario(3);
        System.out.println(telefono3);
        telefonoModel.guardarTelefono(telefono3);
    }

    private static void obtenerListadoTelefonos(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> LISTADO DE TELEFONOS <---------------------");
        telefonoModel.obtenerTelefonos().forEach(System.out::println);
    }

    private static void obtenerListadoTelefonosSQL(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> LISTADO DE TELEFONOS SQL <---------------------");
        telefonoModel.obtenerTelefonosSQL().forEach(System.out::println);
    }

    private static void actualizarTelefono(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ACTUALIZA TELEFONO <---------------------");
        Telefono telefono = telefonoModel.obtenerTelefonoPorId(3);
        telefono.setTipo("Casa");
        telefonoModel.actualizarTelefono(telefono);
        System.out.println(telefono);

        //listado de telefonos despues del cambio
        obtenerListadoTelefonos(telefonoModel);

    }

    private static void eliminarTelefono(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ELIMINA TELEFONO <---------------------");
        Telefono telefono = telefonoModel.obtenerTelefonoPorId(2);
        if (!telefono.getNumero().isEmpty()){
            System.out.println(telefono);
            telefonoModel.eliminarTelefono(telefono);
        }
        //listado de telefonos despues del cambio
        obtenerListadoTelefonos(telefonoModel);
    }

}
