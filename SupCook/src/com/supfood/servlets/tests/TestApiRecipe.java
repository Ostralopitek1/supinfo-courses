package com.supfood.servlets.tests;

import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.PictureDao;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.api.server.dao.UserDao;
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


@WebServlet("/TestApiRecipe")
public class TestApiRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public TestApiRecipe() {
         }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		UserDao userDao = DaoFactory.getInstance().getUserDao();

		User user = new User();
		user.setEmail("test7@test.com");
		user.setUsername("toto");
		user.setFirstName("Toto");
		user.setLastName("Tata");
		user.setPassword("supinfo!");
		user.setPostalCode("75000");
		userDao.addUser(user);
		writer.write("addUser: success\n");

		userDao.removeUser(user);
		writer.write("removeUser: success\n\n");

		RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();

		// Create
		Recipe recipe1 = new Recipe();
		recipe1.setName("Recipe 1");
		recipe1.setDescription("This is the recipe 1");
		recipe1.setPreparationTime(36000);
		recipe1.setCookingTime(3600);
		recipe1.setDifficulty(1);
		recipeDao.addRecipe(recipe1);

		Recipe recipe2 = new Recipe();
		recipe2.setName("Recipe 2");
		recipe2.setDescription("This is the recipe 2");
		recipe2.setPreparationTime(36002);
		recipe2.setCookingTime(3602);
		recipe2.setDifficulty(2);
		recipeDao.addRecipe(recipe2);

		writer.write("addRecipe: success\n");

		// Update
		String newName = "Recipe 1 renamed";
		recipe1.setName(newName);
		recipeDao.updateRecipe(recipe1);
		if (recipe1.getName().equals(newName)) {
			writer.write("updateRecipe : success\n");
		}

		// Get all
		recipeDao.getAllRecipes();
		writer.write("listRecipes: success\n");

		//Remove
		recipeDao.removeRecipe(recipe1);
		recipeDao.removeRecipeById(recipe2.getId());
		writer.write("removeRecipe: success\n");

		// Search
		recipeDao.searchRecipes("1");
		writer.write("searchRecipe: success\n");

		PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
		for(Product p : recipe1.getProducts()) {
			writer.write(p.getName() + "\n");
		}
		writer.write("pictureRecipe: success");
	}
}
