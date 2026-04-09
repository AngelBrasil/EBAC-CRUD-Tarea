package com.ebac.modulo60.jpa.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;

    @Column(name = "calle")
    private String calle;

    @Column (name = "numero")
    private int numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "idUsuario")
    private int idUsuario;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "idDireccion=" + idDireccion +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", estado='" + estado + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
