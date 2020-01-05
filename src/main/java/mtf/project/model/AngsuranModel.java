package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="angsuran")
public class AngsuranModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nominal", nullable = false)
    private int nominal;

    @OneToOne(mappedBy = "angsuran")
    private KreditModel kredit;

    @ManyToOne
    @JoinColumn(name = "id_asuransi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AsuransiModel asuransi;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserRoleModel user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNominal(){
        return nominal;
    }
    public void setNomial(int nominal){
        this.nominal = nominal;
    }
    public KreditModel getKredit() {
        return this.kredit;
    }

    public void setKredit(KreditModel kredit) {
        this.kredit = kredit;
    }
    public AsuransiModel getAsuransi() {
        return this.asuransi;
    }
    public void setAsuransi(AsuransiModel asuransi) {
        this.asuransi = asuransi;
    }
    public UserRoleModel getUser() {
        return this.user;
    }
    public void setUser(UserRoleModel user) {
        this.user = user;
    }
}