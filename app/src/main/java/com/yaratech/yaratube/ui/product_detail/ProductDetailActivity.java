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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract.View {

    private Button sendComment;
    private ImageView playButton;
    private String productImage;
    private String videoUri;
    private ProductDetailContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.product_title)
    TextView productTitle;
    @BindView(R.id.product_about)
    TextView productAbout;
    @BindView(R.id.product_video)
    VideoView videoView;

    @OnClick(R.id.play)
    public void playVideo() {
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
    }

    @OnClick(R.id.send_comment)
    public void sendComment() {
        CommentDialog commentDialog = new CommentDialog();
        FragmentManager fragmentManager = getSupportFragmentManager();
        commentDialog.show(fragmentManager, "comment dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        presenter = new ProductDetailPresenter(getApplicationContext(), this);
        sendComment = findViewById(R.id.send_comment);
        progressBar.setVisibility(View.GONE);
        Intent intent = getIntent();
        presenter.fetchProductDetailFromRemote(intent.getIntExtra("PRODUCT_ID", 0));
    }

    @Override
    public void showProductDetail(ProductDetail productDetail) {

        videoUri = productDetail.getFiles().get(0).getFile();
        productImage = productDetail.getFeatureAvatar().getXxxdpi();
        productTitle.setText(productDetail.getName());
        productAbout.setText(productDetail.getDescription());
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
}
