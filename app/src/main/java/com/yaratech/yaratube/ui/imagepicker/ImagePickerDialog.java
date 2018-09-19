package com.yaratech.yaratube.ui.imagepicker;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vah on 8/12/2018.
 */

public class ImagePickerDialog extends DialogFragment {

    public static final String IMAGE_PICKER_TAG = "image_picker_dialog";
    private static ImagePickerListener imagePicker;

    @OnClick(R.id.camera)
    public void getImageFromCamera() {
        imagePicker.onCamera();
        dismiss();
    }

    @OnClick(R.id.gallery)
    public void getImageFromGallery() {
        imagePicker.onGallery();
        dismiss();
    }

    public static ImagePickerDialog newInstance(ImagePickerListener imagePickerListener) {

        Bundle args = new Bundle();
        imagePicker = imagePickerListener;
        ImagePickerDialog fragment = new ImagePickerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_picker, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface ImagePickerListener{
        void onCamera();
        void onGallery();
    }
}
