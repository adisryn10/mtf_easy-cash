package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="jaminan")
public class JaminanModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="tahun", nullable = false)
    private int tahun;

    @NotNull
    @Column(name="harga", nullable = false)
    private int harga;

    @OneToOne(mappedBy = "jaminan")
    private KreditModel kredit;

    @ManyToOne
    @JoinColumn(name = "id_kendaraan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    KendaraanModel kendaraan;

    @ManyToOne
    @JoinColumn(name = "id_asuransi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AsuransiModel asuransi;

    @NotNull
    @Size(max = 255)
    @Column(name="area", nullable = false)
    private String area;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserRoleModel user;

    public UserRoleModel getUser() {
        return this.user;
    }

    public void setUser(UserRoleModel user) {
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public AsuransiModel getAsuransi() {
        return asuransi;
    }
    public void setAsuransi(AsuransiModel asuransi) {
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
    public KendaraanModel getKendaraan() {
        return this.kendaraan;
    }
    public void setKendaraan(KendaraanModel kendaraan) {
        this.kendaraan = kendaraan;
    }
}