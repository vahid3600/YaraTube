package com.yaratech.yaratube.ui.productdetail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.utils.PresenterInteractions;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface ProductDetailContract {
    interface View {

        void showProductDetail(ProductDetail productDetail);

        void showComment(List<Comment> comments);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends PresenterInteractions {

        void fetchProductDetailFromRemote(int id);

        void cancelProductDetailRequest();

        void fetchCommentFromRemote(int id);

        void cancelCommentRequest();

        boolean getUserLoginStatus();
    }
}
