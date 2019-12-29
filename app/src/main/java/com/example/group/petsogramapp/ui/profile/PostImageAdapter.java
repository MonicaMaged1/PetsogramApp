package com.example.group.petsogramapp.ui.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group.petsogramapp.Backend.StorageManager;
import com.example.group.petsogramapp.R;

import java.util.ArrayList;
import java.util.List;

//public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.ViewHolder>{
//   a5leeh recyclerview then continue video 7
//    public Context mContext;
//    public List<Post> mPost;
//
//    public PostImageAdapter(Context mContext, List<Post> mPost){
//        this.mContext=mContext;
//        this.mPost=mPost;
//    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_post,parent,false);
//        return new PostImageAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Post post =mPost.get(position);
//        Glide.with(mContext).load(post.getPostImage()).into(holder.postImage);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mPost.size();
//    }
public class PostImageAdapter extends BaseAdapter {
    Context Context;
    ArrayList<Bitmap> myPosts;

    LayoutInflater Inflater;
    public PostImageAdapter(Context applicationContext, ArrayList<Bitmap> myPosts) {
        this.Context = applicationContext;
        this.myPosts = myPosts;
        Inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View profileImagesView, ViewGroup viewGroup) {
        profileImagesView = Inflater.inflate(R.layout.layout_gridview_profile, null);
        ImageView myPost = (ImageView) profileImagesView.findViewById(R.id.myPostId);
        myPost.setImageBitmap(myPosts.get(i));
        return profileImagesView;
    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public ImageView profilePicture, postImage, Like;
//        public TextView Name,Likes,Description,Comments;
//
//        public ViewHolder(@NonNull View itemView){
//            super(itemView);
//            profilePicture = itemView.findViewById(R.id.profilePicture);
//            postImage = itemView.findViewById(R.id.postImage);
//            Like = itemView.findViewById(R.id.likeButton);
//            Name = itemView.findViewById(R.id.petName);
//
//            Likes = itemView.findViewById(R.id.Likes);
//            Description = itemView.findViewById(R.id.biography);
//            Comments = itemView.findViewById(R.id.Comments);
//        }
//        private void publisherInfo(ImageView profilePicture, TextView Name,String userId)
//        {
//
//        }
//    }
}
