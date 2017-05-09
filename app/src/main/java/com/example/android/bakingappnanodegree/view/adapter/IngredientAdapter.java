package com.example.android.bakingappnanodegree.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingappnanodegree.R;
import com.example.android.bakingappnanodegree.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by micky on 09-May-17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<Ingredient> mIngredients;
    private Context mContext;

    public IngredientAdapter(List<Ingredient> ingredients, Context context) {
        this.mIngredients = ingredients;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ingredient,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        holder.tvIngredientTitle.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return (mIngredients != null) ? mIngredients.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_ingredient_title)
        TextView tvIngredientTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
