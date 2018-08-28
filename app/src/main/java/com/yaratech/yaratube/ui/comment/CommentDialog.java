package com.yaratech.yaratube.ui.comment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vah on 8/12/2018.
 */

public class CommentDialog extends DialogFragment implements CommentContract.View {

    private int productId;
    private CommentContract.Presenter presenter;
    private static String COMMENT_KEY = "product_id";
    @BindView(R.id.rate)
    RatingBar ratingBar;

    @BindView(R.id.comment_text)
    EditText editText;

    @OnClick(R.id.save)
    void sendComment() {
        presenter.sendComment(
                productId,
                presenter.getUserAuthorization(),
                ratingBar.getRating(),
                editText.getText().toString());
        Log.e("rag",productId+" "+presenter.getUserAuthorization()+ratingBar.getRating()+editText.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productId = getArguments().getInt(COMMENT_KEY);
        presenter = new CommentPresenter(getContext(), this);
    }

    public static CommentDialog newInstance(int productId) {

        Bundle args = new Bundle();
        args.putInt(COMMENT_KEY, productId);
        CommentDialog fragment = new CommentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
