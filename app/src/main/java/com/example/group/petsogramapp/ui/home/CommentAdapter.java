package com.example.group.petsogramapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.notifications.NotificationAdapter;

import org.w3c.dom.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context mContext;
    private List<Comment> Comments;

    public CommentAdapter(Context mContext, List<Comment> comments) {
        this.mContext = mContext;
        Comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_comment_item,parent,false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = Comments.get(position);
//        holder.comment.setText(comment.get);
//        getUserInfo(holder.profilePicture,holder.name,notification.getUserId());

        //video 10
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView  profilePicture;
        public TextView Name,comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.profilePicture);
            Name = itemView.findViewById(R.id.petName);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
