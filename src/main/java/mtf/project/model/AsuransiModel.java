package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="asuransi")
public class AsuransiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="rate", nullable = false)
    private double rate;

    @NotNull
    @Column(name="bulan", nullable = false)
    private int bulan;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public double getRate(){
        return rate;
    }
    public void setRate(double rate){
        this.rate = rate;
    }
    public int getBulan(){
        return bulan;
    }
    public void setBulan(int bulan){
        this.bulan = bulan;
    }
}