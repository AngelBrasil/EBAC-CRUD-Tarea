package com.ebac.modulo60.jpa.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "telefonos")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTelefono;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "idUsuario")
    private int idUsuario;

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "idTelefono=" + idTelefono +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
