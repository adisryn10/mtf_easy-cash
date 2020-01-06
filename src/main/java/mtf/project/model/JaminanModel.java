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
    @Size(max = 255)
    @Column(name="asuransi", nullable = false)
    private String asuransi;

    @NotNull
    @Column(name="tahun", nullable = false)
    private int tahun;

    @NotNull
    @Column(name="harga", nullable = false)
    private int harga;

    @OneToOne(mappedBy = "jaminan")
    private KreditModel kredit;

    @NotNull
    @Size(max = 255)
    @Column(name="area", nullable = false)
    private String area;
    
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
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getAsuransi() {
        return asuransi;
    }
    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }
    public int getTahun(){
        return tahun;
    }
    public void setTahun(int tahun){
        this.tahun = tahun;
    }
    public KreditModel getKredit() {
        return this.kredit;
    }
    public void setKredit(KreditModel kredit) {
        this.kredit = kredit;
    }
    public int getHarga() {
        return this.harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
}