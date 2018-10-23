package com.supfood.servlets.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.client.ClientApi;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.PictureDao;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.api.server.dao.UserDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Product;
import com.supfood.entity.Recipe;
import com.supfood.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;


@WebServlet("/TestClientApiRecipe")
public class TestClientApiRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public TestClientApiRecipe() {
         }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();

		User user = new User();
		user.setUsername("titi");
		user.setFirstName("Toto");
		user.setLastName("Tata");
		user.setEmail("toto@tete.com");
		user.setPassword("supinfo!");
		user.setPostalCode("75000");
		ApiResponse resAddUser = ClientApi.createObject(user, User.API_SUFFIX);
		writer.write("addUser: " + resAddUser.getContent() + "\n");

		ApiResponse resRemUser = ClientApi.removeObject(user.getId(),User.API_SUFFIX);
		writer.write("addUser: " + resRemUser.getContent() + "\n");

		// Create
		Recipe recipe1 = new Recipe();
		recipe1.setName("Recipe 1");
		recipe1.setDescription("This is the recipe 1");
		recipe1.setPreparationTime(36000);
		recipe1.setCookingTime(3600);
		recipe1.setDifficulty(1);
		ClientApi.createObject(recipe1, Recipe.API_SUFFIX);

		Recipe recipe2 = new Recipe();
		recipe2.setName("Recipe 2");
		recipe2.setDescription("This is the recipe 2");
		recipe2.setPreparationTime(36002);
		recipe2.setCookingTime(3602);
		recipe2.setDifficulty(2);
		ApiResponse resCreateRecipe = ClientApi.createObject(recipe2, Recipe.API_SUFFIX);

		writer.write("addRecipe: " + resCreateRecipe.getContent() + "\n");

		// Update
		String newName = "Recipe 1 renamed";
		recipe1.setName(newName);
		ApiResponse resUpdateRecipe = ClientApi.updateObject(recipe1, Recipe.API_SUFFIX);
		writer.write("updateRecipe: " + resUpdateRecipe.getContent() + "\n");

		// Get all
		ApiResponse resGetAllRecipes = ClientApi.getAllObject(Recipe.API_SUFFIX);
		Type recipesTypeList = new TypeToken<List<Recipe>>(){}.getType();
		List<Recipe> recipes = gson.fromJson(resGetAllRecipes.getContent(), recipesTypeList);
		writer.write("listRecipes: " + resGetAllRecipes.isSuccess() + "\n");

		// Remove
		for (Recipe recipe : recipes) {
			if (recipe.getName().equals(recipe1.getName()) ||
				recipe.getName().equals(recipe2.getName())) {
				// Add products
				for (int i=0; i<=5; i++) {
					Product product = new Product("Product " + i, recipe);
					ClientApi.createObject(product, Product.API_SUFFIX);
				}
				ApiResponse resRemRecipe = ClientApi.removeObject(recipe.getId(), Recipe.API_SUFFIX);
				writer.write("removing " + recipe.getName() + ": " + resRemRecipe.getContent() + "\n");
			}
		}

		// Search
		ApiResponse resSearchRecipes = ClientApi.getAllObject(Recipe.API_SUFFIX);
		Type searchRecTypeList = new TypeToken<List<Recipe>>(){}.getType();
		List<Recipe> searchedRecipes = gson.fromJson(resGetAllRecipes.getContent(), searchRecTypeList);
		writer.write("searchRecipe: " + resSearchRecipes.isSuccess() + "\n");
	}
}
