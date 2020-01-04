package mtf.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="apr")
public class AprModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="rate", nullable = false)
    private double rate;

    @NotNull
    @Column(name="tenor", nullable = false)
    private int tenor;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getRate(){
        return rate;
    }
    public void setRate(double rate){
        this.rate = rate;
    }
    public int getTenor(){
        return tenor;
    }
    public void setTenor(int tenor){
        this.tenor = tenor;
    }
}