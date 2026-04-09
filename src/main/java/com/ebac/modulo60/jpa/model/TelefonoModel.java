package com.ebac.modulo60.jpa.model;

import com.ebac.modulo60.jpa.dto.Telefono;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TelefonoModel {

    private final EntityManager entityManager;

    public TelefonoModel(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardarTelefono(Telefono telefono){
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(telefono);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public void actualizarTelefono(Telefono telefono){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.merge(telefono);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public Telefono obtenerTelefonoPorId(int id){
        return entityManager.find(Telefono.class , id);
    }

    public void eliminarTelefono(Telefono telefono){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.remove(telefono);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public List<Telefono> obtenerTelefonos(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Telefono> cq = cb.createQuery(Telefono.class);

        Root<Telefono> rootEntry =cq.from(Telefono.class);
        CriteriaQuery<Telefono> select =cq.select(rootEntry);

        TypedQuery<Telefono> query =entityManager.createQuery(select);
        return query.getResultList();
    }

    public List<Telefono> obtenerTelefonosSQL(){
        String sqlSelect = "SELECT * FROM telefonos";
        return entityManager.createNativeQuery(sqlSelect , Telefono.class)
                .getResultList();
    }

}
