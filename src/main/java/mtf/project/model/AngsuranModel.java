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
    private Double nominal;

    @OneToOne(mappedBy = "angsuran")
    private KreditModel kredit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserRoleModel user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getNominal(){
        return nominal;
    }
    public void setNominal(Double nominal){
        this.nominal = nominal;
    }
    public KreditModel getKredit() {
        return this.kredit;
    }

    public void setKredit(KreditModel kredit) {
        this.kredit = kredit;
    }
    public UserRoleModel getUser() {
        return this.user;
    }
    public void setUser(UserRoleModel user) {
        this.user = user;
    }
}