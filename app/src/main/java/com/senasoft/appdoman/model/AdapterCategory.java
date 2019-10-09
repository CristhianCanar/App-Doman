package com.senasoft.appdoman.model;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.senasoft.appdoman.R;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.Holder>
        implements View.OnClickListener {

    ArrayList<Category> list;

    public AdapterCategory(ArrayList<Category> list) {
        this.list = new ArrayList<>(list);
    }

    private View.OnClickListener listener;
    private boolean clicCard = false;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorias, parent, false);
        Holder holder = new Holder(layout);
        layout.setOnClickListener(this);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.initViews(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);

            Holder holder = new Holder(view);

            holder.cardView.setOnClickListener(view1 -> {

                if (clicCard) {
                    holder.cardView.setBackgroundResource(R.drawable.bg_carditem_normal);
                    clicCard = false;
                } else {
                    holder.cardView.setBackgroundResource(R.drawable.bg_card_item);
                    clicCard = true;
                }

            });


        }
    }


    public class Holder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivImage;
        private CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNameCat);
            ivImage = itemView.findViewById(R.id.ivImageCat);
            cardView = itemView.findViewById(R.id.cardItem);

        }

        public void initViews(Category category) {
            tvTitle.setText(category.getName());
            ivImage.setImageBitmap(BitmapFactory.decodeFile(category.getUrl_image()));
        }

    }

}
