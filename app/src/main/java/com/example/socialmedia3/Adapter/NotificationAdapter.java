package com.example.socialmedia3.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia3.Model.NotificationModel;
import com.example.socialmedia3.R;


import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder>{

    ArrayList<NotificationModel> notificationModelArrayList;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> notificationModelArrayList, Context context) {
        this.notificationModelArrayList = notificationModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_notification_view,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        NotificationModel notificationModel=notificationModelArrayList.get(position);
        holder.profileImage.setImageResource(notificationModel.getProfileImage());
        holder.notification.setText(Html.fromHtml(notificationModel.getNotification()));
        holder.datetime.setText(notificationModel.getdatetime());
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView notification,datetime;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileImage=itemView.findViewById(R.id.profileImage_notification_notification_Fragment);
            notification=itemView.findViewById(R.id.textview_notification_notification_tobeshow);
            datetime=itemView.findViewById(R.id.textview_date_notification_notification);
        }
    }
}
