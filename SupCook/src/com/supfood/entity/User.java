package com.supfood.entity;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.RecipeDao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    public static transient String API_SUFFIX = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String postalCode;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Recipe> recipes;
    @OneToMany(mappedBy = "user")
    private List<Mark> marks;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String postalCode, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postalCode = postalCode;
        this.password = User.encodePassword(password);
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.postalCode = user.getPostalCode();
        this.password = user.getPassword();
        this.recipes = user.getRecipes();
        this.marks = user.getMarks();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public static String encodePassword(String password) {
        byte[] encoded = Base64.encode(password.getBytes());
        return new String(encoded);
    }

    public static String decodePassword(String password) {
        return Base64.base64Decode(password);
    }
}
