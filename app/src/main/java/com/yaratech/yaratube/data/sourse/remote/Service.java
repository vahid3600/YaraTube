package com.yaratech.yaratube.data.sourse.remote;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.LoginGoogle;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Service {
    @GET("store/" + Utils.STORE_ID)
    Call<Store> getStore();

    @GET("category/" + Utils.STORE_ID + "/463")
    Call<List<CategoryList>> getCategory();

    @GET("listproducts/{category_id}")
    Call<List<Product>> getProductList(@Path("category_id") int categoryId);

    @GET("product/{product_id}")
    Call<ProductDetail> getProductDetail(@Path("product_id") int productId);

    @GET("comment/{product_id}")
    Call<List<Comment>> getComment(@Path("product_id") int productId);

    @FormUrlEncoded
    @POST("profile")
    Call<List<CategoryList>> sendProfile();

    @FormUrlEncoded
    @POST("login_google/" + Utils.STORE_ID)
    Call<LoginGoogle> sendGoogleLogin(
            @Field("token_id") String tokenId,
            @Field("device_id") String deviceId,
            @Field("device_os") String deviceOs,
            @Field("device_model") String deviceModel);

    @FormUrlEncoded
    @POST("mobile_login_step1/" + Utils.STORE_ID)
    Call<MobileLoginStep1> sendMobileLoginStep1(
            @Field("mobile") String mobile,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs,
            @Field("gcm") String gcm);

    @FormUrlEncoded
    @POST("mobile_login_step2/" + Utils.STORE_ID)
    Call<LoginGoogle> sendMobileLoginStep2(
            @Field("mobile") String mobile,
            @Field("device_id") String deviceId,
            @Field("verification_code") String verificationCode,
            @Field("nickname") String nickname);
}