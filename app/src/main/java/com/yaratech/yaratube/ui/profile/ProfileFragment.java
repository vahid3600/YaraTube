package com.yaratech.yaratube.ui.profile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.soundcloud.android.crop.Crop;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog;
import com.yaratech.yaratube.utils.Permissions;

import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog.IMAGE_PICKER_TAG;

public class ProfileFragment extends Fragment
        implements ImagePickerDialog.ImagePickerListener,
        DatePickerDialog.OnDateSetListener,
        ProfileContract.View {
    public static String PROFILE_FRAGMENT_TAG = "profile_fragment";
    ProfileContract.Presenter presenter;
    File destination;
    final int CAMERA = 0;

    @BindView(R.id.name_family)
    EditText name_family;
    @BindView(R.id.gender)
    Spinner gender;
    @BindView(R.id.profile_picture)
    ImageView profilePicture;
    @BindView(R.id.birth_date)
    TextView birthDate;

    @OnClick(R.id.save)
    public void saveProfile() {
        Log.e("salar", presenter.getUserAuthorization());
        presenter.sendImage(presenter.getUserAuthorization(), destination.toString());
//        Date date = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        try {
//            date = format.parse(birth_date.getText().toString());
//            Log.e("Tag", date + " " + img);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Log.e("Tag", date + " " + img);
//        presenter.sendProfileData(
//                img,
//                name_family.getText().toString(),
//                gender.getSelectedItem().toString(),
//                date);
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        getChildFragmentManager().popBackStack();
    }


    @OnClick(R.id.profile_picture)
    public void onImageClick() {
        ImagePickerDialog imagePickerDialog = ImagePickerDialog.newInstance(this);
        FragmentManager fragmentManager = getFragmentManager();
        imagePickerDialog.show(fragmentManager, IMAGE_PICKER_TAG);
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new ProfilePresenter(getContext(), this);
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
    }

    public void setDate() {
        PersianCalendar now = new PersianCalendar();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.getPersianYear(),
                now.getPersianMonth(),
                now.getPersianDay()
        );
        dpd.setThemeDark(false);
        dpd.show(getActivity().getFragmentManager(), "tag");
    }

    private ProfileFragment switchToProfileFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        ProfileFragment fragment = (ProfileFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = ProfileFragment.newInstance();
            fragmentTransaction.add(R.id.fragment_container, fragment, PROFILE_FRAGMENT_TAG).commit();
            fragmentTransaction.attach(fragment);
        }
        return fragment;
    }


    @Override
    public void onCamera() {
        if (!Permissions.checkCameraPermissions(getContext())) {
            requestCameraPermission(MEDIA_TYPE_IMAGE);
        } else {
            Log.e("TASA",this+"");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            this.startActivityForResult(intent, CAMERA);
        }
    }

    @Override
    public void onGalery() {
        Crop.pickImage(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Log.e("Tag ", "Ok");
            if (requestCode == CAMERA) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_PICK) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_CROP) {

                Log.e("Cropeer", Crop.getOutput(data).getPath());
                handleCrop(resultCode, data);
//                Glide.with(getContext()).load(Crop.getOutput(data)).into(profilePicture);
            }


//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            destination = new File(Environment.getExternalStorageDirectory(),
//                    System.currentTimeMillis() + ".jpg");
//            FileOutputStream fileOutputStream = null;
//
//            try {
//                destination.createNewFile();
//                fileOutputStream = new FileOutputStream(destination);
//                fileOutputStream.write(byteArrayOutputStream.toByteArray());
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            Log.e("tag", destination.toString());
        }
    }

    private void handleCrop(int code, Intent data) {
        if (code == RESULT_OK) {
            Glide.with(getContext()).load(Crop.getOutput(data)).into(profilePicture);
        } else if (code == Crop.RESULT_ERROR) {
            Log.e("Tag", " Error");
        }
    }

    private void beginCrop(Uri source) {
        Log.e("camera",source.getPath());
        Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getContext(), this);
    }

    private void requestCameraPermission(final int type) {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                onCamera();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            Log.e("Tag ", "ajab!");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
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

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tag", "detach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Tag", "destroy");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tag", "very");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Tag", "pause");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tag", "start");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Tag", "stop");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        birthDate.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
    }
}
