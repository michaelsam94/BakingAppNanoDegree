package com.example.android.bakingappnanodegree.presenter;

import com.example.android.bakingappnanodegree.model.Recipe;

import java.util.List;

/**
 * Created by micky on 09-May-17.
 */

public class RecipesContract {

    public interface View {
        void showRecipieingredints(int recpieId);
        void showRecipies(List<Recipe> recipies);
    }


    public interface Presenter {
        void getRecipiesFromApi();
    }


}
