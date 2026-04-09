package com.ebac.modulo60.jpa.model;

import com.ebac.modulo60.jpa.dto.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UsuarioModel {
    private final EntityManager entityManager;

    public UsuarioModel(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardarUsuario(Usuario usuario){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(usuario);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public void actualizarUsuario(Usuario usuario){
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(usuario);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public Usuario obtenerUsuarioPorId(int id){
        return entityManager.find(Usuario.class , id);
    }

    public void eliminarUsuario(Usuario usuario){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.remove(usuario);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public List<Usuario> obtenerUsuarios(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq =cb.createQuery(Usuario.class);

        Root<Usuario> rootEntry =cq.from(Usuario.class);
        CriteriaQuery<Usuario> select =cq.select(rootEntry);

        TypedQuery<Usuario> query = entityManager.createQuery(select);
        return query.getResultList();
    }

    public List<Usuario> obtenerUsuariosSQL(){
        String sqlSelect = "SELECT * FROM usuarios";
        return entityManager.createNativeQuery(sqlSelect , Usuario.class)
                .getResultList();
    }


}
