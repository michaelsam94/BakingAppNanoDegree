package com.example.android.bakingappnanodegree.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.bakingappnanodegree.R;
import com.example.android.bakingappnanodegree.helper.ItemClickSupport;
import com.example.android.bakingappnanodegree.model.Recipe;
import com.example.android.bakingappnanodegree.presenter.RecipesContract;
import com.example.android.bakingappnanodegree.presenter.RecipesPresenter;
import com.example.android.bakingappnanodegree.view.adapter.RecipesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by micky on 07-May-17.
 */

public class RecipesActivity extends AppCompatActivity implements RecipesContract.View {

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;

    private RecipesAdapter mRecipesAdapter;
    private RecipesPresenter mRecipesPresenter;
    private List<Recipe> mRecipes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);
        ButterKnife.bind(this);
        mRecipesPresenter = new RecipesPresenter(this);
        mRecipesPresenter.getRecipiesFromApi();

    }

    private void setupRecyclerView(){
        mRecipesAdapter = new RecipesAdapter(mRecipes,this);
        rvRecipes.setAdapter(mRecipesAdapter);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(rvRecipes).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent ingredintsIntent = new Intent(RecipesActivity.this,IngredietsActivity.class);
                ingredintsIntent.putExtra(IngredietsActivity.RECIPIE_ID_KEY,mRecipes.get(position).getId());
                startActivity(ingredintsIntent);
            }
        });
    }



    @Override
    public void showRecipieingredints(int recpieId) {
        Intent ingerdientIntent = new Intent(this,IngredietsActivity.class);
        ingerdientIntent.putExtra(IngredietsActivity.RECIPIE_ID_KEY,recpieId);
        startActivity(ingerdientIntent);
    }

    @Override
    public void showRecipies(List<Recipe> recipies) {
        mRecipes = recipies;
        setupRecyclerView();
    }
}
