package com.konstantinesoft.d3w2u5.entities;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "Autori")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String nome;
    @Setter
    private String cognome;
    @Setter
    private String email;
    @Setter
    private String dataDiNascita;
    @Setter
    private String avatar;

    public Author() {}

    public Author(String nome, String cognome, String email, String dataDiNascita, String avatar) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
