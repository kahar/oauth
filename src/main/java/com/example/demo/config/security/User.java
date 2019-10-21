package com.example.demo.config.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class User {
    @Id
    @Column(name = "google_email")
    private String googleMeil;

    private boolean confirmed;

    @Column(name = "capgemini_email")
    private String capgeminiMeil;

    User() {
    }

    public User(String googleMeil, boolean confirmed, String capgeminiMeil) {
        this.googleMeil = googleMeil;
        this.confirmed = confirmed;
        this.capgeminiMeil = capgeminiMeil;
    }

    public String getGoogleMeil() {
        return googleMeil;
    }

    void setGoogleMeil(String googleMeil) {
        this.googleMeil = googleMeil;
    }

    boolean isConfirmed() {
        return confirmed;
    }

    void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getCapgeminiMeil() {
        return capgeminiMeil;
    }
}
