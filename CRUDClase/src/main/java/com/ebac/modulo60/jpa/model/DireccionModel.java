package com.ebac.modulo60.jpa.model;

import com.ebac.modulo60.jpa.dto.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class DireccionModel {
    private final EntityManager entityManager;

    public DireccionModel(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardarDireccion(Direccion direccion){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(direccion);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public void actualizarDireccion(Direccion direccion){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.merge(direccion);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public Direccion obtenerDireccionPorId(int id){
        return entityManager.find(Direccion.class , id);
    }

    public void eliminarDireccion(Direccion direccion){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.remove(direccion);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public List<Direccion> obtenerDirecciones(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Direccion> cq = cb.createQuery(Direccion.class);

        Root<Direccion> rootEntry = cq.from(Direccion.class);
        CriteriaQuery<Direccion> select =cq.select(rootEntry);

        TypedQuery<Direccion> query =entityManager.createQuery(select);
        return query.getResultList();
    }

    public List<Direccion> obtenerDireccionesSQL(){
        String sqlSelect = "SELECT * FROM direcciones";
        return entityManager.createNativeQuery(sqlSelect , Direccion.class)
                .getResultList();
    }

}
