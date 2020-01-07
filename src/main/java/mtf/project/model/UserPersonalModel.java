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
@Table(name="userPersonal")
public class UserPersonalModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
    @Column(name="namaLengkap", nullable = true)
    private String namaLengkap;

    public String getNamaLengkap() {
        return this.namaLengkap;
    }
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

   
    @Column(name="alamat", nullable = true)
    private String alamat;

    public String getAlamat() {
        return this.alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    
    @Column(name="tempatLahir", nullable = true)
    private String tempatLahir;

    public String getTempatLahir() {
        return this.tempatLahir;
    }
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }


  
    @Column(name="tanggalLahir", nullable = true)
    private String tanggalLahir;

    public String getTanggalLahir() {
        return this.tanggalLahir;
    }
    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }


    @Column(name="pendapatan", nullable = true)
    private String pendapatan;

    public String getPendapatan() {
        return this.pendapatan;
    }
    public void setPendapatan(String pendapatan) {
        this.pendapatan = pendapatan;
    }

    @OneToOne(mappedBy = "userPersonal")
    private UserRoleModel userRole;

    public UserRoleModel getUserRole() {
    	return this.userRole;
    }
    public void setUserRole(UserRoleModel userRole) {
    	this.userRole = userRole;
    }


    
}