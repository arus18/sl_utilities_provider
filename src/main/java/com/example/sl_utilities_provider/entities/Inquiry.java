package com.example.sl_utilities_provider.entities;

import javax.persistence.*;

@Entity
@Table(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String inic;
    private String iname;

    private String itype;

    private String idescription;
    private String icontact;
    private String iemail;


    public Inquiry() {
    }


    public Inquiry(long id, String inic, String iname, String itype, String idescription, String icontact, String iemail) {
        this.id = id;
        this.inic = inic;
        this.iname = iname;
        this.itype = itype;
        this.idescription = idescription;
        this.icontact = icontact;
        this.iemail = iemail;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInic() {
        return inic;
    }

    public void setInic(String inic) {
        this.inic = inic;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public String getIdescription() {
        return idescription;
    }

    public void setIdescription(String idescription) {
        this.idescription = idescription;
    }

    public String getIcontact() {
        return icontact;
    }

    public void setIcontact(String icontact) {
        this.icontact = icontact;
    }
    public String getIemail() {
        return iemail;
    }

    public void setIemail(String iemail) {
        this.iemail = iemail;
    }

}
