package com.ebac.modulo59.dto;

public class Familiar {
    private int idFamilia;
    private int idEmpleado;
    private String nombre;
    private String parentesco;

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return "Familiares{" +
                "idFamilia=" + idFamilia +
                ", idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", parentesco='" + parentesco + '\'' +
                '}';
    }
}
