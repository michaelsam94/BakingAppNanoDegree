package com.example.android.bakingappnanodegree.presenter;

import com.example.android.bakingappnanodegree.data.local.RecipesLocal;

/**
 * Created by micky on 09-May-17.
 */

public class IngredientsPresenter implements IngredientsContract.Presenter {

    private final IngredientsContract.View mView;

    private RecipesLocal mRecipiesLocal;

    public IngredientsPresenter(IngredientsContract.View view) {
        mView = view;
        mRecipiesLocal = RecipesLocal.getInstance();
    }



    @Override
    public void getIngredints(int recipeId) {
        mView.showIngredints(mRecipiesLocal.getIngredientsByRecipeId(recipeId));
    }


}
