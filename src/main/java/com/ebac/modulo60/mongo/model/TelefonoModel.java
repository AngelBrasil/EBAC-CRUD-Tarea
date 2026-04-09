package com.ebac.modulo60.mongo.model;

import com.ebac.modulo60.jpa.dto.Telefono;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Objects;
import java.util.Optional;

public class TelefonoModel {
    private final MongoCollection<Document> collection;

    public TelefonoModel(MongoDatabase database){
        collection = database.getCollection("telefonos");
    }

    public void guardarTelefono(Document document){
        collection.insertOne(document);
    }

    public void obtenerTelefonos(){
        FindIterable<Document> telefonos = collection.find();

        for (Document telefono:telefonos){
            System.out.println(telefono);
        }
    }

    public Optional<Document> obtenerTelefonoPorId(Document document){
        Document telefono = collection.find(document).first();

        if (!Objects.isNull(telefono)){
            System.out.println(telefono);
            return Optional.of(telefono);
        }else {
            return Optional.empty();
        }
    }

    public void actualizarTelefono(Document telefonoActual , Document telefonoModificado){
        UpdateResult updateResult = collection.updateOne(telefonoActual , telefonoModificado);

        if (updateResult.getModifiedCount() > 0){
            System.out.println("Telefono actualizado");
        }else {
            System.out.println("No se encontro el telefono a actualizar");
        }
    }

    public void eliminarTelefono(Document document){
        DeleteResult deleteResult = collection.deleteOne(document);

        if (deleteResult.getDeletedCount() > 0){
            System.out.println("Telefono eliminado");
        }else {
            System.out.println("No se encontro el telefono a actualizar");
        }
    }
}
