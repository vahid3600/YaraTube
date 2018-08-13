package com.yaratech.yaratube.ui.imagePicker;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

/**
 * Created by Vah on 8/12/2018.
 */

public class ImagePickerDialog extends DialogFragment implements View.OnClickListener {

    Button camera, galery;
    ProfileFragment profileFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_picker, container, false);
        profileFragment = new ProfileFragment();
        camera = view.findViewById(R.id.camera);
        galery = view.findViewById(R.id.galery);
        camera.setOnClickListener(this);
        galery.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                Log.e("tas","sad");
                profileFragment.getFromCamera();
                break;
            case R.id.galery:
                Log.e("tas","sad");
                profileFragment.getFromGalery();
                break;
        }
    }
}
