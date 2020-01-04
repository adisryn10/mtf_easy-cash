package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="userIntegrity")
public class UserIntegrityModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name="riwayatPajak", nullable = false)
    private int riwayatPajak;

    @NotNull
    @Column(name="riwayatKredit", nullable = false)
    private int riwayatKredit;

    @OneToOne(mappedBy = "userIntegrity")
    private UserModel user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getRiwayatPajak(){
        return riwayatPajak;
    }
    public void setRiwayatPajak(int riwayatPajak){
        this.riwayatPajak = riwayatPajak;
    }
    public int getRiwayatKredit(){
        return riwayatPajak;
    }
    public void setRiwayatKredit(int riwayatKredit){
        this.riwayatKredit = riwayatKredit;
    }
    public UserModel getUser() {
        return this.user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
}