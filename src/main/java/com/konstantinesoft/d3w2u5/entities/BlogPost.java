package com.konstantinesoft.d3w2u5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Articoli")
public class BlogPost {

    @Id
    private int postID;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoLettura;

    @ManyToOne
    private Author author;

    public BlogPost() {}

    public BlogPost(int id, String categoria, String titolo, String cover, String contenuto, int tempoLettura) {
        this.postID = id;
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
    }

    public int getId() {
        return postID;
    }

    public void setId(int id) {
        this.postID = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public int getTempoLettura() {
        return tempoLettura;
    }

    public void setTempoLettura(int tempoLettura) {
        this.tempoLettura = tempoLettura;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "postID=" + postID +
                ", categoria='" + categoria + '\'' +
                ", titolo='" + titolo + '\'' +
                ", cover='" + cover + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", tempoLettura=" + tempoLettura +
                '}';
    }
}
