//package com.example.group.petsogramapp.ui.profile;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.group.petsogramapp.R;
//
//import java.util.ArrayList;
//
//public class OtherUserProfile extends AppCompatActivity {
//    private ProfileViewModel profileViewModel;
//    private Context mContext;
//    private Activity mActivity;
//
//    private LinearLayout mLinearLayout;
//    private Button Follow;
//    private Button moreInfoButton;
//    private GridView profileGridView;
//    private ImageView profilePicture;
//    private TextView numberOfPosts, numberOfFollowers, numberOfFollowing,petName,biography;
//    private String profileID;
//
//    ArrayList<Bitmap> imageId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_other_user_profile);
//        imageId = new ArrayList<>();
//
//
//        profileGridView = (GridView) findViewById(R.id.postGridView);
//        PostImageAdapter adapter = new PostImageAdapter(this,imageId);
//        profileGridView.setAdapter(adapter);
//
//        profileGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View View, int Position, long Id) {
//                Intent Intent = new Intent(getApplicationContext(), PostDetails.class);
//                Intent.putExtra("image", imageId.get(Position));
//                startActivity(Intent);
//            }
//        });
//
//
//        mLinearLayout = (LinearLayout) findViewById(R.id.profileLinearLayout);
//        moreInfoButton = (Button) findViewById(R.id.moreInfoButton);
//        Follow =(Button) findViewById(R.id.Follow);
//        profilePicture =(ImageView) findViewById(R.id.profilePicture);
//        numberOfPosts = (TextView) findViewById(R.id.numberOfPosts);
//        numberOfFollowers = (TextView) findViewById(R.id.numberOfFollowers);
//        numberOfFollowing = (TextView) findViewById(R.id.numberOfFollowing);
//        biography = (TextView) findViewById(R.id.biography);
//        petName= (TextView) findViewById(R.id.petName);
//
//
//        moreInfoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent Intent = new Intent(getApplicationContext(), MoreInformationActivity.class);
//                startActivity(Intent);
//
//            }
//        });
//        Follow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//                View customView = inflater.inflate(R.layout.popup_window,null);
//
//                Intent Intent = new Intent(getApplicationContext(), EditProfileActivity.class);
//                startActivity(Intent);
//
//
//            }
//        });
//    }
//
//}
