package com.yaratech.yaratube.ui.dialog.imagepicker;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Vah on 8/12/2018.
 */

public class ImagePickerDialog extends DialogFragment {

    @OnClick(R.id.camera)
    public void getImageFromCamera() {
        profileFragment.getFromCamera();
    }

    @OnClick(R.id.galery)
    public void getImageFromGalery() {
        profileFragment.getFromGalery();
    }

    ProfileFragment profileFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_picker, container, false);
        profileFragment = new ProfileFragment();
        return view;
    }
}
