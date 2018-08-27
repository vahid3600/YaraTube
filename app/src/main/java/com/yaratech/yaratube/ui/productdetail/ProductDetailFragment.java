package com.yaratech.yaratube.ui.productdetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.ui.dialog.comment.CommentDialog;
import com.yaratech.yaratube.ui.player.PlayerActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yaratech.yaratube.ui.player.PlayerActivity.PLAYER_ACTIVITY_KEY;

public class ProductDetailFragment extends Fragment implements
        ProductDetailContract.View {

    private Product product;
    private static final String PRODUCT_KEY = "product_id";
    private CommentRecyclerViewAdapter commentRecyclerViewAdapter;
    private String videoUri;
    private ProductDetailContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private ProductDetail productDetail;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.product_title)
    TextView productTitle;
    @BindView(R.id.product_about)
    TextView productAbout;
    @BindView(R.id.product_image)
    ImageView productImage;
    @BindView(R.id.product_comments)
    RecyclerView recyclerView;

    @OnClick(R.id.play)
    public void playVideo() {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra(PLAYER_ACTIVITY_KEY, productDetail.getFiles().get(0).getFile());
        startActivity(intent);
    }

    @OnClick(R.id.send_comment)
    public void sendComment() {
        CommentDialog commentDialog = new CommentDialog();
        FragmentManager fragmentManager = getFragmentManager();
        commentDialog.show(fragmentManager, "comment dialog");
    }

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(Product product) {

        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_KEY, product);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter = new ProductDetailPresenter(getContext(), this);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        product = getArguments().getParcelable(PRODUCT_KEY);
        presenter.fetchProductDetailFromRemote(product.getId());
        Glide.with(getContext()).load(product.getFeatureAvatar().getXxxdpi()).into(productImage);
        productTitle.setText(product.getName());
        initRecycleview();
    }

    private void initRecycleview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter();
        recyclerView.setAdapter(commentRecyclerViewAdapter);
    }


    @Override
    public void showProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
        videoUri = productDetail.getFiles().get(0).getFile();
        productAbout.setText(productDetail.getDescription());
        presenter.fetchCommentFromRemote(product.getId());
    }

    @Override
    public void showComment(List<Comment> comments) {
        commentRecyclerViewAdapter.setComments(comments);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
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
    public void onDestroyView() {
        presenter.cancelProductDetailRequest();
        presenter.cancelCommentRequest();
        super.onDestroyView();
    }
}
