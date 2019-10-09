package com.senasoft.appdoman.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senasoft.appdoman.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.Holder> {


    private Context context;
    private ArrayList<Boy> list;

    public AdapterUsers(Context context, ArrayList<Boy> list) {
        this.context = context;
        this.list = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        Holder holder = new Holder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.findUser(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvName;

        public Holder(@NonNull View itemView) {
            super(itemView);

            ivAvatar = (CircleImageView) itemView.findViewById(R.id.ivAvatarList);
            tvName = itemView.findViewById(R.id.tvNameUserList);

        }

        public void findUser(Boy boy) {

            try {
                ivAvatar.setImageBitmap(BitmapFactory.decodeFile(boy.getUrl_avatar()));
                tvName.setText(boy.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
