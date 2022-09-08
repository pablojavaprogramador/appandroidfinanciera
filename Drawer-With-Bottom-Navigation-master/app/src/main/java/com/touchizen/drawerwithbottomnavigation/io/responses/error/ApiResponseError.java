package com.touchizen.drawerwithbottomnavigation.io.responses.error;

import java.util.List;

public class ApiResponseError {


        private Integer codigo;
        private String fecha;
        private String mensaje;
        private String descripcion;
        private String url;
     //  private List<FieldError> fieldErrors = null;


    @Override
    public String toString() {
        return "ApiResponseError{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public ApiResponseError(Integer codigo, String fecha, String mensaje, String descripcion, String url) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
        this.url = url;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



