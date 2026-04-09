package com.ebac.modulo60.mongo.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Objects;
import java.util.Optional;

public class UsuarioModel {
   private final MongoCollection<Document> collection;

   public UsuarioModel(MongoDatabase database){
       collection = database.getCollection("usuarios");
   }

   public void guardarUsuario(Document document){
       collection.insertOne(document);
   }

   public void obtenerUsuarios(){
       FindIterable<Document> usuarios = collection.find();

        for (Document usuario : usuarios){
            System.out.println(usuario);
        }
   }

   public Optional<Document> obtenerUsuarioPorId(Document document){
        Document usuario = collection.find(document).first();

        if (!Objects.isNull(usuario)){
            System.out.println(usuario);
            return Optional.of(usuario);
        }else {
            return Optional.empty();
        }
   }

   public void actualizarUsuario(Document usuarioActual , Document usuarioModificado){
       UpdateResult updateResult = collection.updateOne(usuarioActual , usuarioModificado);

       if (updateResult.getModifiedCount() > 0){
           System.out.println("Usuario actualizazo");
       }else {
           System.out.println("No se encontro el usuario a actualizar");
       }
   }

    public void eliminarUsuario(Document document){
        DeleteResult deleteResult = collection.deleteOne(document);

        if (deleteResult.getDeletedCount() > 0){
            System.out.println("Usuario eliminado");
        }else {
            System.out.println("No se encontro el usaurio a eliminar");
        }
    }

}
