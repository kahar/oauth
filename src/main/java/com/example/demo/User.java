package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @Column(name="google_email")
    private String googleMeil;

    private boolean confirmed;

    @Column(name="capgemini_email")
    private String capgeminiMeil;

    public User() {
    }

    public User(String googleMeil, boolean confirmed, String capgeminiMeil) {
        this.googleMeil = googleMeil;
        this.confirmed = confirmed;
        this.capgeminiMeil = capgeminiMeil;
    }

    public String getGoogleMeil() {
        return googleMeil;
    }

    public void setGoogleMeil(String googleMeil) {
        this.googleMeil = googleMeil;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getCapgeminiMeil() {
        return capgeminiMeil;
    }

    public void setCapgeminiMeil(String capgeminiMeil) {
        this.capgeminiMeil = capgeminiMeil;
    }
}
