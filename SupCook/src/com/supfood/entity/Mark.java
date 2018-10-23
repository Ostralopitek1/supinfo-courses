package com.supfood.entity;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    public static transient String API_SUFFIX = "marks";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int amount;
    @ManyToOne
    private User user;
    @ManyToOne(optional = false)
    private Recipe recipe;

    public Mark() {
    }

    public Mark(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
