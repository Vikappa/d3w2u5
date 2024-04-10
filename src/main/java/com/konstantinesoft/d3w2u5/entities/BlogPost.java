package com.konstantinesoft.d3w2u5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Articoli")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;
    @Getter
    @Setter
    private String categoria;
    @Getter
    @Setter
    private String titolo;
    @Getter
    @Setter
    private String cover;
    @Getter
    @Setter
    private String contenuto;
    @Getter
    @Setter
    private int tempoLettura;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost() {}

    public BlogPost(Author author, String categoria, String titolo, String cover, String contenuto, int tempoLettura) {
        this.author = author;
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
    }

    public BlogPost(int authorID, BlogPost blogPost) {
    }

    public int getId() {
        return postID;
    }

    public void setId(int id) {
        this.postID = id;
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
