package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.SendRequestModel;
import com.example.socialmedia3.Model.UserModel;
import com.example.socialmedia3.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.internal.cache.DiskLruCache;

public class SendRequestAdapter extends RecyclerView.Adapter<SendRequestAdapter.Viewholder>{

    Context context;
    ArrayList<SendRequestModel> sendRequestModelArrayList;

    public SendRequestAdapter() {
    }

    public SendRequestAdapter(Context context, ArrayList<SendRequestModel> sendRequestModelArrayList) {
        this.context = context;
        this.sendRequestModelArrayList = sendRequestModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.sendrequest_connection_view,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SendRequestModel sendRequestModel=sendRequestModelArrayList.get(position);
        Picasso.get()
                .load(sendRequestModel.getProfilePhoto())
                        .placeholder(R.drawable.baseline_account_circle_24)
                                .into(holder.profileImage);
        holder.username.setText(sendRequestModel.getName());
        holder.institution.setText(sendRequestModel.getInstitution());
        holder.profession.setText(sendRequestModel.getProfession());


        holder.sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    SendRequestModel sendRequestModel1=snapshot.getValue(SendRequestModel.class);
                                    String name= sendRequestModel1.getName();
                                    String profession= sendRequestModel1.getProfession();
                                    String institution= sendRequestModel1.getInstitution();
                                    String profilePhoto= sendRequestModel1.getProfilePhoto();



                                    LocalDateTime currentDateTime = null;
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        currentDateTime = LocalDateTime.now();
                                    }

                                    // Define a format for the date and time
                                    DateTimeFormatter formatter = null;
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    }

                                    // Format the current date and time using the specified format
                                    String formattedDateTime = null;
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        formattedDateTime= currentDateTime.format(formatter);
                                    }

                                    SendRequestModel sendRequestModel2=new SendRequestModel(profilePhoto,name,profession,institution,formattedDateTime);
                                    FirebaseDatabase.getInstance().getReference().child("Users")
                                            .child(sendRequestModel.getUserID()).child("connections")
                                            .child(FirebaseAuth.getInstance().getUid()).setValue(sendRequestModel2)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    int no_of_connection=sendRequestModel1.getConnection_counter();

                                                    FirebaseDatabase.getInstance().getReference().child("Users")
                                                            .child(sendRequestModel.getUserID()).child("connection_counter")
                                                            .setValue(no_of_connection+1)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(context, "You get connected with "+sendRequestModel.getName(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            });



                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


            }
        });


    }

    @Override
    public int getItemCount() {
        return sendRequestModelArrayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        public  ImageView profileImage;
        TextView username,profession,institution;
        Button sendRequest;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileImage=itemView.findViewById(R.id.profileImage_sendRequest_makeNewConnection);
            username=itemView.findViewById(R.id.userName_sendRequest_makeNewConnection);
            profession=itemView.findViewById(R.id.profession_sendRequest_makeNewConnection);
            institution=itemView.findViewById(R.id.institution_sendRequest_makeNewConnection);
            sendRequest=itemView.findViewById(R.id.sendRequest_button_makeNewconnection);
        }
    }
}
