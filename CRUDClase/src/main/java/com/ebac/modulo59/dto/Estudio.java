package com.ebac.modulo59.dto;

public class Estudio {
    private int idEstudio;
    private int idEmpleado;
    private String institucion;
    private String documento;

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Estudios{" +
                "idEstudio=" + idEstudio +
                ", idEmpleado=" + idEmpleado +
                ", institucion='" + institucion + '\'' +
                ", documento='" + documento + '\'' +
                '}';
    }
}