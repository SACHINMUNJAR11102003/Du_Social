package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.SendRequestModel;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GetRequestAdapter extends RecyclerView.Adapter<GetRequestAdapter.Viewholder>{


    Context context;
    ArrayList<SendRequestModel> getRequestModelArrayList;

    public GetRequestAdapter(Context context, ArrayList<SendRequestModel> getRequestModelArrayList) {
        this.context = context;
        this.getRequestModelArrayList = getRequestModelArrayList;
    }




    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.getrequest_connection_veiw,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SendRequestModel sendRequestModel=getRequestModelArrayList.get(position);
        holder.profession.setText(sendRequestModel.getProfession());
        holder.institute.setText(sendRequestModel.getInstitution());
        holder.date_time.setText(sendRequestModel.getDate_time());
        holder.username.setText(sendRequestModel.getName());
        Picasso.get()
                .load(sendRequestModel.getProfilePhoto())
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return getRequestModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView username,profession,institute,date_time;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileImage=itemView.findViewById(R.id.profileImage_getRequest_makeNewConnection);
            username=itemView.findViewById(R.id.userName_getRequest_makeNewConnection);
            profession=itemView.findViewById(R.id.profession_getRequest_makeNewConnection);
            institute=itemView.findViewById(R.id.institution_getRequest_makeNewConnection);
            date_time=itemView.findViewById(R.id.dateAndTime_getRequest_makeNewConnection);
        }
    }
}
