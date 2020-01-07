package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="kendaraan")
public class KendaraanModel implements Serializable{
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
    @Column(name="hargaPasar", nullable = false)
    private int hargaPasar;

    @OneToMany(mappedBy = "kendaraan", fetch = FetchType.LAZY)
    private List<JaminanModel> jaminan;
    
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
    public List<JaminanModel> getJaminan() {
        return this.jaminan;
    }
    public void setJaminan(List<JaminanModel> jaminan) {
        this.jaminan = jaminan;
    }
    public int getHargaPasar() {
        return this.hargaPasar;
    }
    public void setHarga(int hargaPasar) {
        this.hargaPasar = hargaPasar;
    }
}