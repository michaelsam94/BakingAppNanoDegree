package com.example.android.bakingappnanodegree.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingappnanodegree.R;
import com.example.android.bakingappnanodegree.model.Ingredient;
import com.example.android.bakingappnanodegree.presenter.IngredientsContract;
import com.example.android.bakingappnanodegree.presenter.IngredientsPresenter;
import com.example.android.bakingappnanodegree.view.BaseActivity;
import com.example.android.bakingappnanodegree.view.adapter.IngredientAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by micky on 09-May-17.
 */

public class IngredietsActivity extends AppCompatActivity implements IngredientsContract.View {

    public static final String RECIPIE_ID_KEY = "recipeId";

    @BindView(R.id.rv_ingredients)
    RecyclerView rvIngredints;

    private int mRecipeId;
    private List<Ingredient> mIngredients;
    private IngredientAdapter mIngredientAdapter;
    private IngredientsContract.Presenter mPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);
        mPresenter = new IngredientsPresenter(this);

        mRecipeId = getIntent().getIntExtra(RECIPIE_ID_KEY,-1);

        if(mRecipeId != -1){
            mPresenter.getIngredints(mRecipeId);
        }

    }



    private void setupRecyclerView(){
        mIngredientAdapter = new IngredientAdapter(mIngredients,this);
        rvIngredints.setLayoutManager(new LinearLayoutManager(this));
        rvIngredints.setAdapter(mIngredientAdapter);
    }

    @Override
    public void showIngredints(List<Ingredient> ingredients) {
        mIngredients = ingredients;
        setupRecyclerView();
    }
}
