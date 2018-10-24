package com.supfood.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.client.ClientApi;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.MarkDao;
import com.supfood.api.server.dao.PictureDao;
import com.supfood.api.server.dao.ProductDao;
import com.supfood.api.server.dao.jpa.JpaProductDao;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {
    public static transient String API_SUFFIX = "recipes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Type(type="text")
    private String description;
    private long preparationTime;
    private long cookingTime;
    private int difficulty;
    private boolean active;

    @ManyToOne
    private User owner;
    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "recipe")
    private transient List<Product> products;
    @OneToMany(mappedBy = "recipe")
    private transient List<Picture> pictures;
    @OneToMany(mappedBy = "recipe")
    private transient List<Mark> marks;

    public Recipe() {
    }

    public Recipe(String name, String description, long preparationTime, long cookingTime, int difficulty, User owner, Category category) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.owner = owner;
        this.category = category;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(long preparationTime) {
        this.preparationTime = preparationTime;
    }

    public long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void removePicture(Picture picture) {
        this.pictures.remove(picture);
    }

    public String getFirstUrlPicture() {
        String requestUrl = Picture.API_SUFFIX + "/recipe/" + this.id;
        ApiResponse resPicture = ClientApi.getRequest(requestUrl);
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Picture>>(){}.getType();
        List<Picture> pictures = gson.fromJson(resPicture.getContent(), type);
        if (pictures.size() == 0) {
            return null;
        }
        return pictures.get(0).getUrl();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
