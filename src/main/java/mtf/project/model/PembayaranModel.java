package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="pembayaran")
public class PembayaranModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="konstanta", nullable = false)
    private int konstanta;

    @OneToMany(mappedBy = "pembayaran", fetch = FetchType.LAZY)
    private List<KreditModel> kredit;

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
    public int getKonstanta(){
        return konstanta;
    }
    public void setKonstanta(int konstanta){
        this.konstanta = konstanta;
    }
    public List<KreditModel> getKredit() {
        return this.kredit;
    }
    public void setKredit(List<KreditModel> kredit) {
        this.kredit = kredit;
    }
}