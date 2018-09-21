package com.yaratech.yaratube.ui.productdetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.ui.comment.CommentDialog;
import com.yaratech.yaratube.ui.login.LoginDialogContainer;
import com.yaratech.yaratube.ui.player.PlayerActivity;
import com.yaratech.yaratube.ui.productlist.PageScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yaratech.yaratube.ui.comment.CommentDialog.COMMENT_DIALOG_TAG;
import static com.yaratech.yaratube.ui.player.PlayerActivity.PLAYER_ACTIVITY_KEY;

public class ProductDetailFragment extends Fragment implements
        ProductDetailContract.View {

    private Product product;
    private static final String PRODUCT_KEY = "product_id";
    private CommentRecyclerViewAdapter commentRecyclerViewAdapter;
    private ProductDetailContract.Presenter presenter;
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
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.send_comment)
    Button commentButton;

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
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
    }

    private void initRecycleview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter();
        commentRecyclerViewAdapter.setHasStableIds(true);
        recyclerView.setAdapter(commentRecyclerViewAdapter);
    }

    public void playVideo() {
        boolean userLogin = presenter.getUserLoginStatus();
        if (userLogin) {
            if (productDetail != null) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra(PLAYER_ACTIVITY_KEY, productDetail.getFiles().get(0).getFile());
                startActivity(intent);
            }else
                showMessage(getString(R.string.no_internet));
        } else {
            LoginDialogContainer.newInstance(getFragmentManager());
        }
    }

    public void sendComment() {
        boolean userLogin = presenter.getUserLoginStatus();
        if (userLogin) {
            CommentDialog commentDialog = CommentDialog.newInstance(product.getId());
            FragmentManager fragmentManager = getFragmentManager();
            commentDialog.show(fragmentManager, COMMENT_DIALOG_TAG);
        } else {
            LoginDialogContainer.newInstance(getFragmentManager());
        }
    }

    @Override
    public void showProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
        productAbout.setText(productDetail.getDescription());
        presenter.fetchCommentFromRemote(product.getId());
    }

    @Override
    public void showComment(List<Comment> comments) {
        commentRecyclerViewAdapter.updateComments(comments);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        play.setClickable(false);
        productImage.setClickable(false);
        commentButton.setClickable(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        play.setClickable(true);
        productImage.setClickable(true);
        commentButton.setClickable(true);
    }

    @Override
    public void onDestroyView() {
        presenter.cancelProductDetailRequest();
        presenter.cancelCommentRequest();
        presenter.detachView();
        super.onDestroyView();
    }
}
