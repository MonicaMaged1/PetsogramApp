package com.example.group.petsogramapp.add;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.home.HomeFragment;
import com.example.group.petsogramapp.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.Inflater;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {

    private AddViewModel mViewModel;
    private ImageView selectedFromGallery;
    View Root;
    private Button btnNext;
    private Bitmap bitmap;
    private boolean isGallery = false;
    Uri contentURI = null;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Root = inflater.inflate(R.layout.fragment_add, container, false);

        return inflater.inflate(R.layout.fragment_add, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);

        selectedFromGallery = (ImageView) getActivity().findViewById(R.id.selectedFromGallery);
        btnNext = (Button) getActivity().findViewById(R.id.btnNext);

        selectImage(Root.getContext());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGallery) {
                    Intent intent = new Intent(getActivity(), UploadPhoto.class);
                    intent.putExtra("imageUri", contentURI.toString());
                    intent.putExtra("bool", isGallery);
                    startActivity(intent);

                } else {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent in1 = new Intent(getActivity(), UploadPhoto.class);
                    in1.putExtra("image", byteArray);
                    in1.putExtra("bool", isGallery);
                    startActivity(in1);
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        isGallery = false;
                        bitmap = (Bitmap) data.getExtras().get("data");
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
//                        mImageView.setImageDrawable(d);
                        if (bitmap != null) {
//                            selectedFromGallery.setImageBitmap(bitmap);
                            selectedFromGallery.setImageDrawable(d);
                        }
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        if (data != null) {
                            contentURI = data.getData();
                            isGallery = true;
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                                selectedFromGallery.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();

                            }
                        }
                    }
                    break;

            }

        }
        btnNext.setVisibility(View.VISIBLE);
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your image");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {

                    BottomNavigationView navigation = (BottomNavigationView) getActivity().findViewById(R.id.nav_view);
                    navigation.setSelectedItemId(R.id.navigation_home);
                }
            }
        });
        builder.show();
    }

    public void SaveImage(Bitmap showedImgae, Intent imageIntent) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/DCIM/myCapturedImages");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "FILENAME-" + n + ".jpg";
        File file = new File(myDir, fname);
        Uri uriSavedImage = Uri.fromFile(file);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
//        if (file.exists ()) file.delete ();
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//            showedImgae.compress(Bitmap.CompressFormat.JPEG, 100, out);
////            Toast.makeText(activityname.this, "Image Saved", Toast.LENGTH_SHORT).show();
//            out.flush();
//            out.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri contentUri = Uri.fromFile(file);
//        mediaScanIntent.setData(contentUri);
//        getApplicationContext().sendBroadcast(mediaScanIntent);
    }

}
