package com.yaratech.yaratube.ui.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.soundcloud.android.crop.Crop;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.GetProfileResponse;
import com.yaratech.yaratube.data.model.ProfileResponse;
import com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog;
import com.yaratech.yaratube.utils.Permissions;
import com.yaratech.yaratube.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.yaratech.yaratube.ui.imagepicker.ImagePickerDialog.IMAGE_PICKER_TAG;

public class ProfileFragment extends Fragment
        implements ImagePickerDialog.ImagePickerListener,
        DatePickerDialog.OnDateSetListener,
        ProfileContract.View {

    public static String PROFILE_FRAGMENT_TAG = "profile_fragment";
    ProfileContract.Presenter presenter;
    String birthDateString;
    File destination;
    Uri imagePath;
    String imageFilePath;
    final int CAMERA = 0;
    final int GALLERY = 1;
    private static final int CAMERA_PERMISSION = 2;
    private static final int GALERY_PERMISSION = 3;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
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
        if (imagePath != null)
            presenter.sendImage(
                    presenter.getUserAuthorization(),
                    destination.getPath());
        else
            presenter.sendProfileData(
                    presenter.getUserAuthorization(),
                    name_family.getText().toString(),
                    getGender(gender),
                    birthDateString);
    }

    @OnClick(R.id.sign_out)
    public void signOut() {
        presenter.signOut();
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        getFragmentManager().popBackStack();
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
        progressBar.setVisibility(View.GONE);
        presenter = new ProfilePresenter(getContext(), this);
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        presenter.getProfileData(presenter.getUserAuthorization());
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

    private String getGender(Spinner gender) {
        switch (gender.getSelectedItemPosition()) {
            case 0:
                return "male";

            case 1:
                return "female";

            default:
                return "";
        }
    }

    @Override
    public void onCamera() {
        if (!Permissions.checkCameraPermissions(getContext())) {
            requestCameraPermission();
        } else {
            getImageFromCamera();
        }
    }

    @Override
    public void onGalery() {
        if (!Permissions.checkGaleryPermissions(getContext())) {
            requestGaleryPermission();
        } else {
            getImageFromGalery();
        }
    }

    private void getImageFromCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                openCameraIntent(cameraIntent);
//            else
            startActivityForResult(cameraIntent, CAMERA);
        }
    }

    private void getImageFromGalery() {
        Crop.pickImage(getContext(), this);
    }

    private void openCameraIntent(Intent cameraIntent) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(
                        getContext(),
                        getContext().getPackageName() + ".provider",
                        photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                Log.d("TAG", "openCameraIntent: " + photoURI.getPath());
                startActivityForResult(cameraIntent, CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("YYYYMMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", " granted");
                    getImageFromCamera();

                } else {
                    Log.e("permission", " denied");
                }
                return;
            case GALERY_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", " granted");
                    Crop.pickImage(getContext(), this);

                } else {
                    Log.e("permission", " denied");
                }
                return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_PICK) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_CROP) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                    imagePath = Crop.getOutput(data);
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imagePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    destination = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".jpg");
                    FileOutputStream fileOutputStream = null;

                    try {
                        destination.createNewFile();
                        fileOutputStream = new FileOutputStream(destination);
                        fileOutputStream.write(byteArrayOutputStream.toByteArray());
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide
                            .with(getContext())
                            .load(destination)
                            .apply(RequestOptions.circleCropTransform())
                            .into(profilePicture);
                } else {
                    presenter.sendImage(
                            presenter.getUserAuthorization(),
                            Crop.getOutput(data).getPath());

                    Glide
                            .with(getContext())
                            .load(Crop.getOutput(data))
                            .apply(RequestOptions.circleCropTransform())
                            .into(profilePicture);

//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if (photoFile != null) {
//                        Uri photoURI = FileProvider.getUriForFile(
//                                getContext(),
//                                getContext().getPackageName() + ".provider",
//                                photoFile);
//
//                        Bitmap bitmap = null;
//                        try {
//                            bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Crop.getOutput(data));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//
//                        FileOutputStream fileOutputStream = null;
//                        try {
//                            photoFile.createNewFile();
//                            fileOutputStream = new FileOutputStream(photoFile);
//                            fileOutputStream.write(byteArrayOutputStream.toByteArray());
//                            fileOutputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Glide
//                                .with(getContext())
//                                .load(destination)
//                                .apply(RequestOptions.circleCropTransform())
//                                .into(profilePicture);;
//                    Log.e("Taginegi", Crop.getOutput(data).getPath()+" "+photoURI.getPath()+" "+photoFile.getPath());
////                    presenter.sendImage(presenter.getUserAuthorization(), createFilePath(Crop.getOutput(data)));
//                }
                }

//            if (requestCode == GALLERY) {
//
//                Uri selectedImage = data.getData();
//                beginCrop(selectedImage);
//            } else if (requestCode == CAMERA) {
//
//                if (data != null && data.getExtras() != null) {
//                    Uri selectedImage = data.getData();
//                    beginCrop(selectedImage);
//                }
            }
        }
    }


    private String createFilePath(Uri selectedImage) {

        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        android.database.Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    private void beginCrop(Uri source) {

        Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getContext(), this);
    }

    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                CAMERA_PERMISSION);
    }

    private void requestGaleryPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                GALERY_PERMISSION);
    }

    @Override
    public void updateImage(ProfileResponse profileResponse) {
        presenter.sendProfileData(
                presenter.getUserAuthorization(),
                name_family.getText().toString(),
                getGender(gender),
                birthDate.getText().toString());
    }

    @Override
    public void onDataLoad(GetProfileResponse getProfileResponse) {
        name_family.setText(getProfileResponse.getNickname());
        if (getProfileResponse.getDateOfBirth() != null) {
            char[] birthDay = getProfileResponse.getDateOfBirth().toCharArray();
            birthDay[4] = '/';
            birthDay[7] = '/';
            birthDate.setText(String.valueOf(birthDay));
        }
        gender.setVerticalScrollbarPosition(1);
        if (getProfileResponse.getAvatar() != null)
            Glide
                    .with(getContext())
                    .load(Utils.BASE_URL + getProfileResponse.getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilePicture);
        else
            Glide
                    .with(getContext())
                    .load(R.drawable.ic_launcher_background)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilePicture);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tag", "detach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        Log.e("Tag", "destroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tag", "destroy");
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
        String monthOfYearString;
        String dayOfMonthString;

        if (monthOfYear < 10)
            monthOfYearString = "0" + monthOfYear;
        else
            monthOfYearString = "" + (monthOfYear + 1);

        if (dayOfMonth < 10)
            dayOfMonthString = "0" + dayOfMonth;
        else
            dayOfMonthString = "" + dayOfMonth
                    ;
        birthDateString = year + "-" + monthOfYearString + "-" + dayOfMonthString;
        birthDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        Log.e("tag", birthDateString);
    }

}
