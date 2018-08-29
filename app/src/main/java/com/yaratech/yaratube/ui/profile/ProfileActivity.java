package com.yaratech.yaratube.ui.profile;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog.IMAGE_PICKER_TAG;

public class ProfileActivity extends AppCompatActivity {

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

    @OnClick(R.id.profile_picture)
    public void onImageClick() {
        ImagePickerDialog imagePickerDialog = new ImagePickerDialog();
        FragmentManager fragmentManager = getSupportFragmentManager();
        imagePickerDialog.show(fragmentManager, IMAGE_PICKER_TAG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
