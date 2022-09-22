package com.example.sl_utilities_provider.entities;
import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="cnumber")
    private String cnumber;
    @Column(name="holdername")
    private String holdername;
    @Column(name="mm")
    private String mm;
    @Column(name="yy")
    private String yy;
    @Column(name="cvv")
    private String cvv;
    public Payment() {
        super();
    }
    public Payment (long id,String cnumber,String holdername,String mm,String yy,String cvv){
        this.id = id;
        this.cnumber=cnumber;
        this.holdername=holdername;
        this.mm=mm;
        this.yy=yy;
        this.cvv=cvv;
    }

    public long getId(){ return id; }

    public void setId(long id) { this.id = id;}

    public String getCnumber () { return cnumber;}

    public void setCnumber(String cnumber) { this.cnumber = cnumber ;}

    public String getHoldername () { return holdername;}

    public void setHoldername(String holdername){ this.holdername = holdername;}

    public String getMm () { return mm;}

    public void setMm (String mm) { this.mm = mm ;}

    public String getYy () { return yy;}

    public void setYy(String yy){ this.yy = yy ;}

    public String getCvv () { return cvv ;}

    public void setCvv (String cvv) { this.cvv = cvv ;}





}
