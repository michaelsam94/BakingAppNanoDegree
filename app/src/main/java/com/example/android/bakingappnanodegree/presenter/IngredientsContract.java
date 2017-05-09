package com.example.android.bakingappnanodegree.presenter;

import com.example.android.bakingappnanodegree.model.Ingredient;

import java.util.List;

/**
 * Created by micky on 09-May-17.
 */

public class IngredientsContract {

    public interface View {
        void showIngredints(List<Ingredient> ingredients);
    }

    public interface Presenter {
        void getIngredints(int recipeId);
    }
}
