package com.yaratech.yaratube.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yaratech.yaratube.BuildConfig;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog;
import com.yaratech.yaratube.utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog.IMAGE_PICKER_TAG;

public class ProfileActivity extends AppCompatActivity implements ImagePickerDialog.ImagePickerListener {

    @BindView(R.id.cancel)
    Button cancel_button;
    @BindView(R.id.save)
    Button send_button;
    @BindView(R.id.name_family)
    EditText name_family;
    @BindView(R.id.birth_date)
    EditText birth_date;
    @BindView(R.id.gender)
    Spinner gender;
    @BindView(R.id.profile_picture)
    ImageView profilePicture;

    @OnClick(R.id.profile_picture)
    public void onImageClick() {
        ImagePickerDialog imagePickerDialog = ImagePickerDialog.newInstance(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        imagePickerDialog.show(fragmentManager, IMAGE_PICKER_TAG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ButterKnife.bind(this);
    }

    @Override
    public void onCamera() {
        Log.e("camera", "camera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onGalery() {
        Log.e("galery", "camera");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Log.e("Tag", "ok");
            Bitmap bitmap = null;
            if (requestCode == 0) {
                Log.e("Tag", "camera");
                bitmap = (Bitmap) data.getExtras().get("data");
                Glide.with(getApplicationContext()).load(bitmap).into(profilePicture);
            } else if (requestCode == 1) {
                Log.e("Tag", "galery");
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(getApplicationContext()).load(bitmap).into(profilePicture);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fileOutputStream = null;
            try {
                destination.createNewFile();
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                fileOutputStream.close();
                Log.e("tag",destination.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
