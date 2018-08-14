package com.yaratech.yaratube.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.image_picker.ImagePickerDialog;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    ProfileContract.Presenter presenter;
    private static final int IMAGE_PICKER_SELECT = 999;
    ImageView profile_picture;
    Button cancel_button, send_button;
    EditText name_family, birth_date;
    Spinner gender;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile_picture = view.findViewById(R.id.profile_picture);
        cancel_button = view.findViewById(R.id.cancel);
        send_button = view.findViewById(R.id.save);
        name_family = view.findViewById(R.id.name_family);
        birth_date = view.findViewById(R.id.birth_date);
        gender = view.findViewById(R.id.gender);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        presenter = new ProfilePresenter(getContext(), this);
        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePickerDialog imagePickerDialog = new ImagePickerDialog();
                FragmentManager fragmentManager = getFragmentManager();
                imagePickerDialog.show(fragmentManager, "image picker");
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public void getFromGalery() {
        presenter = new ProfilePresenter(getContext(), this);
        presenter.fetchProfileGalery();
    }

    public void getFromCamera() {
        presenter = new ProfilePresenter(getContext(), this);
        presenter.fetchProfileCamera();
    }


    @Override
    public void updateImage(Uri uri) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
