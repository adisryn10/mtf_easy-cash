package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="kredit")
public class KreditModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nominal", nullable = false)
    private int nominal;

    @ManyToOne
    @JoinColumn(name = "tenor_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AprModel apr;

    @ManyToOne
    @JoinColumn(name = "id_pembayaran", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    PembayaranModel pembayaran;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_angsuran")
    private AngsuranModel angsuran;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserRoleModel user;

    @NotNull
    @Size(max = 200)
    @Column(name="status", nullable = false)
    private String status;

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNominal() {
        return this.nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public AprModel getApr() {
        return this.apr;
    }
    public void setApr(AprModel apr) {
        this.apr = apr;
    }
    public PembayaranModel getPembayaran() {
        return this.pembayaran;
    }

    public void setPembayaran(PembayaranModel pembayaran) {
        this.pembayaran = pembayaran;
    }
    public AngsuranModel getAngsuran() {
        return this.angsuran;
    }

    public void setAngsuran(AngsuranModel angsuran) {
        this.angsuran = angsuran;
    }
    public UserRoleModel getUser() {
        return this.user;
    }

    public void setUser(UserRoleModel user) {
        this.user = user;
    }
}