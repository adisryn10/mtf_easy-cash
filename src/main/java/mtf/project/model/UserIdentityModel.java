package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import mtf.project.model.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="userIdentity")
public class UserIdentityModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name="kk", nullable = false)
    private String kk;

    @NotNull
    @Size(max = 200)
    @Column(name="npwp", nullable = false)
    private String npwp;

    @NotNull
    @Size(max = 200)
    @Column(name="bpkb", nullable = false)
    private String bpkb;

    @NotNull
    @Size(max = 200)
    @Column(name="bkr", nullable = false)
    private String bkr;

    @NotNull
    @Size(max = 200)
    @Column(name="ktp", nullable = false)
    private String ktp;

    @NotNull
    @Size(max = 200)
    @Column(name="stnk", nullable = false)
    private String stnk;

    @NotNull
    @Size(max = 200)
    @Column(name="tujuan", nullable = false)
    private String tujuan;

    @NotNull
    @Size(max = 200)
    @Column(name="fraktur", nullable = false)
    private String fraktur;

    @NotNull
    @Size(max = 200)
    @Column(name="rancanganAnggaran", nullable = false)
    private String rancanganAnggaran;

    @NotNull
    @Size(max = 200)
    @Column(name="rekeningKoran", nullable = false)
    private String rekeningKoran;

    @NotNull
    @Size(max = 200)
    @Column(name="rekeningMandiri", nullable = false)
    private String rekeningMandiri;
    
    @OneToOne(mappedBy = "userIdentity")
    private UserRoleModel user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKk() {
        return this.kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getNpwp() {
        return this.npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getBpkb() {
        return this.bpkb;
    }

    public void setBpkb(String bpkb) {
        this.bpkb = bpkb;
    }

    public String getBkr() {
        return this.bkr;
    }

    public void setBkr(String bkr) {
        this.bkr = bkr;
    }

    public String getKtp() {
        return this.ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getStnk() {
        return this.stnk;
    }

    public void setStnk(String stnk) {
        this.stnk = stnk;
    }
    public String getTujuan() {
        return this.tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
    public String getRancanganAnggaran() {
        return this.rancanganAnggaran;
    }
    public String getFraktur() {
        return this.fraktur;
    }

    public void setFraktur(String fraktur) {
        this.fraktur = fraktur;
    }

    public void setRancanganAnggaran(String rancanganAnggaran) {
        this.rancanganAnggaran = rancanganAnggaran;
    }
    public String getRekeningKoran() {
        return this.rekeningKoran;
    }

    public void setRekeningKoran(String rekeningKoran) {
        this.rekeningKoran = rekeningKoran;
    }
    public String getRekeningMandiri() {
        return this.rekeningMandiri;
    }

    public void setRekeningMandiri(String rekeningMandiri) {
        this.rekeningMandiri = rekeningMandiri;
    }

    public UserRoleModel getUser() {
        return this.user;
    }
    public void setUser(UserRoleModel user) {
        this.user = user;
    }
}

