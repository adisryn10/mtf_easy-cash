package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="jaminan")
public class JaminanModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="merk", nullable = false)
    private String merk;

    @NotNull
    @Size(max = 255)
    @Column(name="jenis", nullable = false)
    private String jenis;

    @NotNull
    @Column(name="tahun", nullable = false)
    private int tahun;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMerk() {
        return merk;
    }
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public int getTahun(){
        return tahun;
    }
    public void setTahun(int tahun){
        this.tahun = tahun;
    }
}