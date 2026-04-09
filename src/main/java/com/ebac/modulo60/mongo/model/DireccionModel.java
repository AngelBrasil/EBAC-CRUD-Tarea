package com.ebac.modulo60.mongo.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Objects;
import java.util.Optional;

public class DireccionModel {
    private final MongoCollection<Document> collection;

    public DireccionModel(MongoDatabase database){
        collection = database.getCollection("direcciones");
    }

    public void guardarDireccion(Document document){
        collection.insertOne(document);
    }

    public void obtenerDirecciones(){
        FindIterable<Document> direcciones = collection.find();

        for (Document direccion : direcciones){
            System.out.println(direccion);
        }
    }

    public Optional<Document> obtenerDireccionPorId(Document document){
        Document direccion =collection.find(document).first();

        if (!Objects.isNull(direccion)){
            System.out.println(direccion);
            return Optional.of(direccion);
        }else {
            return Optional.empty();
        }
    }

    public void actualizarDireccion(Document direccionActual , Document direccionModificada){
        UpdateResult updateResult = collection.updateOne(direccionActual , direccionModificada);

        if (updateResult.getModifiedCount() > 0){
            System.out.println("Direccion actualizada");
        }else {
            System.out.println("No se encontro la direccion a actualizar");
        }
    }

    public void eliminarDireccion(Document document){
        DeleteResult deleteResult = collection.deleteOne(document);

        if (deleteResult.getDeletedCount() > 0){
            System.out.println("Direccion eliminada");
        }else {
            System.out.println("No se encontro la direccion a eliminar");
        }
    }

}
