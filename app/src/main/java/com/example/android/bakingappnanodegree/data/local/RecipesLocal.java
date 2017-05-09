package com.example.android.bakingappnanodegree.data.local;

import com.example.android.bakingappnanodegree.model.Ingredient;
import com.example.android.bakingappnanodegree.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micky on 09-May-17.
 */

public class RecipesLocal {
    private static RecipesLocal instance;
    private List<Recipe> mRecipes;


    private RecipesLocal() {
        mRecipes = new ArrayList<>();
    }

    public static RecipesLocal getInstance() {
        if(instance == null) instance = new RecipesLocal();
        return instance;
    }


    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    public void setRecipes(List<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
    }


    public List<Ingredient> getIngredientsByRecipeId(int recipeId){
        for (Recipe recipe : mRecipes){
            if(recipe.getId() == recipeId) return recipe.getIngredients();
        }
        return null;
    }

}
