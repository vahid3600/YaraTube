package com.yaratech.yaratube.data.sourse.remote;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.CommentResponse;
import com.yaratech.yaratube.data.model.LoginGoogle;
import com.yaratech.yaratube.data.model.LoginResponse;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.model.ProfileResponse;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Service {
    @GET("store/" + Utils.STORE_ID)
    Call<Store> getStore();

    @GET("category/" + Utils.STORE_ID + "/463")
    Call<List<CategoryList>> getCategory();

    @GET("listproducts/{category_id}")
    Call<List<Product>> getProductList(
            @Path("category_id") int categoryId,
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("product/{product_id}?device_os=ios")
    Call<ProductDetail> getProductDetail(@Path("product_id") int productId);

    @GET("comment/{product_id}")
    Call<List<Comment>> getComment(@Path("product_id") int productId);

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
    Call<LoginResponse> sendMobileLoginStep2(
            @Field("mobile") String mobile,
            @Field("device_id") String deviceId,
            @Field("verification_code") String verificationCode,
            @Field("nickname") String nickname);

    @FormUrlEncoded
    @POST("/comment/{product_id}")
    Call<CommentResponse> sendComment(
            @Path("product_id") int productId,
            @Header("Authorization") String authorization,
            @Field("title") String title,
            @Field("score") int score,
            @Field("comment_text") String commentText);


    @FormUrlEncoded
    @POST("profile")
    Call<ProfileResponse> sendProfile(
            @Field("nickname") String nickname,
            @Field("date_of_birth") DateFormat dateOfBirth,
            @Field("gender") String gender,
            @Field("avatar") String avatar,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs,
            @Field("password") String password
    );

}
