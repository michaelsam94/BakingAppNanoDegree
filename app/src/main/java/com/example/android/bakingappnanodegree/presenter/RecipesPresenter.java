package com.example.android.bakingappnanodegree.presenter;

import com.example.android.bakingappnanodegree.data.backend.RecipeSerivce;
import com.example.android.bakingappnanodegree.data.local.RecipesLocal;
import com.example.android.bakingappnanodegree.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by micky on 07-May-17.
 */

public class RecipesPresenter implements RecipesContract.Presenter {

    private final RecipeSerivce recipeService;
    private final RecipesContract.View mRecipiesView;
    private final RecipesLocal mRecipesLocal;




    public RecipesPresenter(RecipesContract.View recipiesView){
        this.mRecipiesView = recipiesView;
        this.recipeService = new RecipeSerivce();
        this.mRecipesLocal = RecipesLocal.getInstance();
    }


    private void getRecipes(){
        recipeService.getRecipesAPI()
                .getRecipies()
                .enqueue(new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        List<Recipe> result = response.body();

                        if(result != null){
                            mRecipesLocal.setRecipes(result);
                            mRecipiesView.showRecipies(result);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {
                        try {
                            throw  new InterruptedException("Erro na comunicação com o servidor!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void getRecipiesFromApi() {
        getRecipes();
    }
}
