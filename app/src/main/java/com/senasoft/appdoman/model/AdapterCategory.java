package com.senasoft.appdoman.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.senasoft.appdoman.R;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.Holder>
        implements View.OnClickListener {

    String[] titles = {"Familia", "Nombre compañeros", "Partes del cuerpo", "Dias semana", "Materias"};
    int[] images = {R.drawable.car_familia, R.drawable.cat_amigos, R.drawable.cat_cuerpo, R.drawable.cat_calendario, R.drawable.cat_materias};

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

        holder.initViews(titles[position], images[position]);

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);

            Holder holder = new Holder(view);

            holder.cardView.setOnClickListener(view1 -> {

                if (clicCard){
                    holder.cardView.setBackgroundResource(R.drawable.bg_carditem_normal);
                    clicCard = false;
                }else {
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

        public void initViews(String title, int imgResource) {
            tvTitle.setText(title);
            ivImage.setImageResource(imgResource);

        }

    }

}
