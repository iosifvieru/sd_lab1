package beans;

import java.util.Date;

public class StudentBean implements java.io.Serializable {
    private int id = 0;
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;
    private String facultate = null;

    // master, licenta, doctorat
    private String specializare = null;
    private int an_universitar = 0;

    public StudentBean() {}
    public int getId(){return id;}
    public void setId(int id){this.id = id; }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public int getVarsta() {
        return varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    public void setFacultate(String facultate){
        this.facultate = facultate;
    }
    public void setSpecializare(String specializare){
        this.specializare = specializare;
    }
    public void setAn_universitar(int an){
        this.an_universitar = an;
    }
    public String getFacultate() {
        return facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public int getAn_universitar() {
        return an_universitar;
    }

}