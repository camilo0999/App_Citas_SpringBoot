package com.electiva.citas.modelos;

public class CitasJson {

    private Long idCita;

    private String title;
    
    private String start;
    
    private String end;
    
    public CitasJson(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public CitasJson() {
    }


    public Long getIdCita() {
        return this.idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
}
