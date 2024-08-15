package com.example.projectakhirvsga.model;

public class User {
    private long id;
    private String name;
    private String domisili;
    private String nim;
    private String tmpt;
    private String tgl;
    private String msk;
    private String lls;
    private String job;
    private String jbtn;
    private String tlp;

    public User(long id, String name, String domisili, String nim, String tmpt, String tgl, String msk, String lls, String job, String jbtn, String tlp) {
        this.id = id;
        this.name = name;
        this.domisili = domisili;
        this.nim = nim;
        this.tmpt = tmpt;
        this.tgl = tgl;
        this.msk = msk;
        this.lls = lls;
        this.job = job;
        this.jbtn = jbtn;
        this.tlp = tlp;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDomisili() {
        return domisili;
    }
    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getNim() {
        return nim;
    }
    public void setNim(String nim){ this.nim = nim;}

    public String getTmpt() {
        return tmpt;
    }
    public void setTmpt(String tmpt){ this.tmpt = tmpt;}

    public String getTgl() {
        return tgl;
    }
    public void setTgl(String tgl){ this.tgl = tgl;}

    public String getMsk() {
        return msk;
    }
    public void setMsk(String msk){ this.msk = msk;}

    public String getLls() {
        return lls;
    }
    public void setLls(String lls){ this.lls = lls;}

    public String getJob() {
        return job;
    }
    public void setJob(String job){ this.job = job;}

    public String getJbtn() {
        return jbtn;
    }
    public void setJbtn(String jbtn){ this.jbtn = jbtn;}

    public String getTlp(){ return  tlp;}
    public void setTlp(String tlp){ this.tlp = tlp;}

}
