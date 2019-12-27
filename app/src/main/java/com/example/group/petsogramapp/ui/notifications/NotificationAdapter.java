package com.example.group.petsogramapp.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.group.petsogramapp.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context mContext;
    private List<Notification> mNotification;

    public NotificationAdapter(Context mContext, List<Notification> mNotification) {
        this.mContext = mContext;
        this.mNotification = mNotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_notification_item,parent,false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = mNotification.get(position);
        holder.text.setText(notification.getText());
        getUserInfo(holder.profilePicture,holder.name,notification.getUserId());

        //video 17
    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView profilePicture,postImage;
        public TextView name,text;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.profilePicture);
            postImage = itemView.findViewById(R.id.postImage);
            name = itemView.findViewById(R.id.petName);
            text= itemView.findViewById(R.id.comment);

        }

    }
    private void getUserInfo(ImageView imageView, TextView Name,String publisherId){

    }

    private void getPostImage(ImageView imageView,String postId){}
}
