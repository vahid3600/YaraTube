package com.yaratech.yaratube.ui.product_detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.ui.dialog.comment.CommentDialog;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract.View,
        View.OnClickListener {

    private ProgressBar progressBar;
    private Button sendComment;
    private VideoView videoView;
    private ImageView playButton;
    private TextView productTitle, productAbout;
    private String productImage;
    private String videoUri;
    private ProductDetailContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        presenter = new ProductDetailPresenter(getApplicationContext(), this);

        progressBar = findViewById(R.id.progressbar);
        sendComment = findViewById(R.id.send_comment);
        progressBar.setVisibility(View.GONE);
        videoView = findViewById(R.id.product_video);
        productTitle = findViewById(R.id.product_title);
        productAbout = findViewById(R.id.product_about);
        playButton = findViewById(R.id.play);
        Intent intent = getIntent();
        presenter.fetchProductDetailFromRemote(intent.getIntExtra("PRODUCT_ID", 0));
        playButton.setOnClickListener(this);
    }

    @Override
    public void showProductDetail(ProductDetail productDetail) {

        videoUri = productDetail.getFiles().get(0).getFile();
        productImage = productDetail.getFeatureAvatar().getXxxdpi();
        productTitle.setText(productDetail.getName());
        productAbout.setText(productDetail.getDescription());
        sendComment.setOnClickListener(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {

        if (v.getId() == R.id.play) {
            progressDialog = new ProgressDialog(ProductDetailActivity.this);
            progressDialog.setMessage("Please wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            try {
                if (videoView.isPlaying()) {
                    videoView.setVideoURI(Uri.parse(videoUri));
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                        }
                    });
                } else
                    videoView.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    mp.setLooping(true);
                    videoView.start();
                }
            });
        } else {
            CommentDialog commentDialog = new CommentDialog();
            FragmentManager fragmentManager = getSupportFragmentManager();
            commentDialog.show(fragmentManager, "comment dialog");
        }
    }
}
