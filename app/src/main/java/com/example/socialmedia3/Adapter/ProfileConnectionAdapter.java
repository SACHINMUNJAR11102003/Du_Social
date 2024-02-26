package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.ConnectionsModel;
import com.example.socialmedia3.Model.UserModel;
import com.example.socialmedia3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileConnectionAdapter extends RecyclerView.Adapter<ProfileConnectionAdapter.Viewholder>{
    Context context;

    ArrayList<ConnectionsModel> connectionsModelArrayList;

    public ProfileConnectionAdapter(Context context, ArrayList<ConnectionsModel> connectionsModelArrayList) {
        this.context = context;
        this.connectionsModelArrayList = connectionsModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.connections_view,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ConnectionsModel connectionsModel=connectionsModelArrayList.get(position);
        holder.userName.setText(connectionsModel.getName());
        Picasso.get()
                .load(connectionsModel.getProfilePhoto())
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(holder.profilePhoto);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return connectionsModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView profilePhoto,more;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.userName_connectionView);
            profilePhoto=itemView.findViewById(R.id.profileImage_connectionView);
            more=itemView.findViewById(R.id.more_with_connectionView);
        }
    }
}
