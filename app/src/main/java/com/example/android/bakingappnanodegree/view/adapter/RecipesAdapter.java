package com.example.android.bakingappnanodegree.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.bakingappnanodegree.R;
import com.example.android.bakingappnanodegree.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by micky on 07-May-17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> mRecipes;
    private Context mContext;


    public RecipesAdapter(List<Recipe> recipes, Context context) {
        mRecipes = recipes;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        Glide.with(mContext).load(recipe.getImage()).into(holder.ivRecipeThumbnail);
        holder.tvRecipeTitle.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return (mRecipes != null) ? mRecipes.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recipe)
        ImageView ivRecipeThumbnail;
        @BindView(R.id.tv_recipe_title)
        TextView tvRecipeTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
