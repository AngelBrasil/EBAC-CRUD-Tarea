package com.ebac.modulo60.mongo;

import com.ebac.modulo60.jpa.dto.Telefono;
import com.ebac.modulo60.mongo.model.DireccionModel;
import com.ebac.modulo60.mongo.model.TelefonoModel;
import com.ebac.modulo60.mongo.model.UsuarioModel;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.ObjectIdCodec;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.Optional;

public class Contexto {
    public static void main(String[] args) {
        String connectionString = "mongodb://root:toor@localhost:27017";

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("modulo60");

        UsuarioModel usuarioModel = new UsuarioModel(database);
        TelefonoModel telefonoModel = new TelefonoModel(database);
        DireccionModel direccionModel = new DireccionModel(database);

        //Operaciones usuario
        operacionesUsuario(usuarioModel);

        //Operaciones direcciones
        operacionesDireccion(direccionModel);

        //Operaciones telefonos
        operacionesTelefono(telefonoModel);
    }

    public static void operacionesUsuario(UsuarioModel usuarioModel){
        //Alta usuarios
        altaUsuarios(usuarioModel);

        //Listar usuarios
        listaUsuarios(usuarioModel);

        //Lista usuarios por Id
        listaUsuarioPorId(usuarioModel);

        //Actualizar
        actualizarUsuario(usuarioModel);

        //Eliminar
        eliminarUsuario(usuarioModel);

    }

    public static void altaUsuarios(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ALTA USUARIOS <---------------------");

        Document usuario1 =new Document("nombre","Angel")
                .append("edad",40);
        System.out.println(usuario1);
        usuarioModel.guardarUsuario(usuario1);

        Document usuario2 = new Document("nombre","Alejandro")
                .append("edad",48)
                .append("profesion","Desarrollador");
        System.out.println(usuario2);
        usuarioModel.guardarUsuario(usuario2);

        Document usuario3 = new Document("nombre","Karla")
                .append("edad", 32)
                .append("profesion","Asistente")
                .append("estado civil","casada");
        System.out.println(usuario3);
        usuarioModel.guardarUsuario(usuario3);
    }

    public static void listaUsuarios(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> LISTA USUARIOS <---------------------");
        usuarioModel.obtenerUsuarios();
    }

    public static void listaUsuarioPorId(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> LISTA USUARIOS POR ID <---------------------");

        ObjectId objectId = new ObjectId("69d73d5853cf675fa4b93753");
        Document usuarioABuscar = new Document("_id" , objectId);
        usuarioModel.obtenerUsuarioPorId(usuarioABuscar);
    }

    public static void actualizarUsuario(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ACTUALIZA USUARIOS <---------------------");

        ObjectId objectId =new ObjectId("69d73d5853cf675fa4b93753");
        Document usuarioABuscar = new Document("_id" , objectId);
        Optional<Document> usuarioEncontrado = usuarioModel.obtenerUsuarioPorId(usuarioABuscar);

        usuarioEncontrado.ifPresent(usuarioActual -> {
            Document usuario =new Document("edad",46)
                    .append("profesion","Vendedor");
            Document usuarioModificado =new Document("$set" , usuario);

            usuarioModel.actualizarUsuario(usuarioActual , usuarioModificado);
        });
        listaUsuarios(usuarioModel);
    }

    public static void eliminarUsuario(UsuarioModel usuarioModel){
        System.out.println("\n---------------------> ELIMINAR USUARIOS <---------------------");

        ObjectId objectId = new ObjectId("69d73d5853cf675fa4b93752");
        Document usuarioABuscar = new Document("_id" , objectId);
        Optional<Document> usuarioEncontrado =usuarioModel.obtenerUsuarioPorId(usuarioABuscar);

        usuarioEncontrado.ifPresent(usuarioModel::eliminarUsuario);

        listaUsuarios(usuarioModel);
    }

    public static void operacionesDireccion(DireccionModel direccionModel){
        //Alta direcciones
        altaDirecciones(direccionModel);

        //Lista direcciones
        listaDirecciones(direccionModel);

        //Listar direcciones por Id
        listarDireccionesPorId(direccionModel);

        //Actualizar direccion
        actualizarDireccion(direccionModel);

        //Elimiinar direccion
        eliminarDireccion(direccionModel);
    }

    public static void altaDirecciones(DireccionModel direccionModel){
        System.out.println("\n---------------------> ALTA DIRECCIONES <---------------------");

        Document direccion1 = new Document("idUsuario","69d73d5853cf675fa4b93752")
                .append("calle","Aluminio")
                .append("estado", "Nuevo Leon");
        System.out.println(direccion1);
        direccionModel.guardarDireccion(direccion1);

        Document direccion2 = new Document("idUsuario","69d73d5853cf675fa4b93753")
                .append("calle", "Calzada del hueso")
                .append("numero" , 808)
                .append("estado" , "CDMX");
        System.out.println(direccion2);
        direccionModel.guardarDireccion(direccion2);

        Document direccion3 = new Document("idUsuario" , "69d73d5853cf675fa4b93754")
                .append("calle","Centro")
                .append("estado","Tabasco")
                .append("ciudad" , "Villa Hermosa");
        System.out.println(direccion3);
        direccionModel.guardarDireccion(direccion3);
    }

    public static void listaDirecciones(DireccionModel direccionModel){
        System.out.println("\n---------------------> LISTA DIRECCIONES <---------------------");
        direccionModel.obtenerDirecciones();
    }

    public static void listarDireccionesPorId(DireccionModel direccionModel){
        System.out.println("\n---------------------> LISTA DIRECCIONES POR ID <---------------------");
        ObjectId objectId = new ObjectId("69d7443a6a318c3e8eac5370");
        Document direccionABuscar = new Document("_id" , objectId);
        direccionModel.obtenerDireccionPorId(direccionABuscar);
    }

    public static void actualizarDireccion(DireccionModel direccionModel){
        System.out.println("\n---------------------> ACTUALIZAR DIRECCIONES <---------------------");

        ObjectId objectId = new ObjectId("69d7443a6a318c3e8eac536f");
        Document direccionABuscar = new Document("_id" , objectId);
        Optional<Document> direccionEncontrada = direccionModel.obtenerDireccionPorId(direccionABuscar);

        direccionEncontrada.ifPresent(direccionActual ->{
            Document direccion = new Document("calle","Monte Alban")
                    .append("numero",800);
            Document direccionModificada = new Document("$set" , direccion);

            direccionModel.actualizarDireccion(direccionActual , direccionModificada);
        });
        listaDirecciones(direccionModel);
    }

    public static void eliminarDireccion(DireccionModel direccionModel){
        System.out.println("\n---------------------> ELIMINAR DIRECCIONES <---------------------");

        ObjectId objectId = new ObjectId("69d7443a6a318c3e8eac536e");
        Document direccionABuscar = new Document("_id" , objectId);
        Optional<Document> direccionEncontrada = direccionModel.obtenerDireccionPorId(direccionABuscar);

        direccionEncontrada.ifPresent(direccionModel::eliminarDireccion);

        listaDirecciones(direccionModel);
    }

    public static void operacionesTelefono(TelefonoModel telefonoModel){
        //Alta telefonos
        altaTelefonos(telefonoModel);

        //Listado de telefonos
        listaTelefonos(telefonoModel);

        //Listado de telefonos por Id
        listaTelefonosPorId(telefonoModel);

        //Actualiza telefono
        actualizarTelefono(telefonoModel);

        //Eliminar telefono
        eliminarTelefono(telefonoModel);
    }

    public static void altaTelefonos(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ALTA TELEFONOS <---------------------");

        Document telefono1 = new Document("idUsuario","69d73d5853cf675fa4b93752")
                .append("numero" , "5566557777")
                .append("tipo","casa");
        System.out.println(telefono1);
        telefonoModel.guardarTelefono(telefono1);

        Document telefono2 = new Document("idUsuario", "69d73d5853cf675fa4b93753")
                .append("numero", "0445567891")
                .append("telefonia","Telcel");
        System.out.println(telefono2);
        telefonoModel.guardarTelefono(telefono2);

        Document telefono3 = new Document("idUsuario","69d73d5853cf675fa4b93754")
                .append("numero","2334599807")
                .append("tipo","Oficina")
                .append("pais","México");
        System.out.println(telefono3);
        telefonoModel.guardarTelefono(telefono3);

    }

    public static void listaTelefonos(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> LISTA TELEFONOS <---------------------");
        telefonoModel.obtenerTelefonos();
    }

    public static void listaTelefonosPorId(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> LISTA TELEFONOS POR ID <---------------------");

        ObjectId objectId = new ObjectId("69d74cdc58e92b290b012214");
        Document telefonoABuscar = new Document("_id" , objectId);
        telefonoModel.obtenerTelefonoPorId(telefonoABuscar);
    }

    public static void actualizarTelefono(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ACTUALIZAR TELEFONOS <---------------------");

        ObjectId objectId = new ObjectId("69d74cdc58e92b290b012213");
        Document telefonoABuscar = new Document("_id" , objectId);
        Optional<Document> telefonoEncontrado = telefonoModel.obtenerTelefonoPorId(telefonoABuscar);

        telefonoEncontrado.ifPresent(telefonoActual ->{
            Document telefono = new Document("telefonia","Movistar")
                    .append("tipo","celular");
            Document telefonoModificado = new Document("$set" , telefono);

            telefonoModel.actualizarTelefono(telefonoActual , telefonoModificado);
        });
        listaTelefonos(telefonoModel);
    }

    public static void eliminarTelefono(TelefonoModel telefonoModel){
        System.out.println("\n---------------------> ELIMINAR TELEFONOS <---------------------");

        ObjectId objectId = new ObjectId("69d74cdc58e92b290b012214");
        Document telefonoABuscar = new Document("_id" , objectId);
        Optional<Document> telefonoEncontrado = telefonoModel.obtenerTelefonoPorId(telefonoABuscar);

        telefonoEncontrado.ifPresent(telefonoModel::eliminarTelefono);

        listaTelefonos(telefonoModel);
    }
}
