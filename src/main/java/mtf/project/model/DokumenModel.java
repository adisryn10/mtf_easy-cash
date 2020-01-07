package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.File;
import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="dokumen")
public class DokumenModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="ktp", nullable = false)
    private File ktp;

    @NotNull
    @Column(name="stnk", nullable = false)
    private File stnk;

    public File getStnk() {
        return this.stnk;
    }

    public void setStnk(File stnk) {
        this.stnk = stnk;
    }

    public File getKtp() {
        return this.ktp;
    }

    public void setKtp(File ktp) {
        this.ktp = ktp;
    }

}




