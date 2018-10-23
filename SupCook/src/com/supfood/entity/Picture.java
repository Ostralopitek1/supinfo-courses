package com.supfood.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pictures")
public class Picture implements Serializable {
    public static transient String API_SUFFIX = "pictures";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String url;
    private boolean active;

    @ManyToOne(optional = false)
    private Recipe recipe;

    public Picture() {
    }

    public Picture(String url, Recipe recipe) {
        this.url = url;
        this.recipe = recipe;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
