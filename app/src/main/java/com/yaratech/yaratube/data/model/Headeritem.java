
package com.yaratech.yaratube.data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Headeritem implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_english")
    @Expose
    private String nameEnglish;
    @SerializedName("product_type")
    @Expose
    private int productType;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("payment_type")
    @Expose
    private List<Object> paymentType = null;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("feature_avatar")
    @Expose
    private FeatureAvatar featureAvatar;
    @SerializedName("rank")
    @Expose
    private double rank;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("is_purchased")
    @Expose
    private boolean isPurchased;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("is_bookmarked")
    @Expose
    private boolean isBookmarked;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("total_view")
    @Expose
    private int totalView;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("invest_goal")
    @Expose
    private Object investGoal;
    @SerializedName("product_staff")
    @Expose
    private List<Object> productStaff = null;
    @SerializedName("support")
    @Expose
    private Support support;
    @SerializedName("is_special")
    @Expose
    private boolean isSpecial;
    @SerializedName("additional_attributes")
    @Expose
    private List<Object> additionalAttributes = null;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("customjson")
    @Expose
    private Object customjson;

    protected Headeritem(Parcel source) {
        id = source.readInt();
        name = source.readString();
        nameEnglish = source.readString();
        productType = source.readInt();
        producerName = source.readString();
        price = source.readInt();
        rank = source.readInt();
        shortDescription = source.readString();
        isPurchased = source.readByte() != 0;
        comments = source.readInt();
        isBookmarked = source.readByte() != 0;
        sku = source.readString();
        priceUnit = source.readString();
        totalView = source.readInt();
        dateAdded = source.readString();
        isSpecial = source.readByte() != 0;
        datePublished = source.readString();
    }

    public static final Creator<Headeritem> CREATOR = new Creator<Headeritem>() {

        @Override
        public Headeritem createFromParcel(Parcel source) {
            return new Headeritem(source);

        }

        @Override
        public Headeritem[] newArray(int size) {
            return new Headeritem[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public int getProductType() {
        return productType;
    }

    public String getProducerName() {
        return producerName;
    }

    public List<Object> getPaymentType() {
        return paymentType;
    }

    public int getPrice() {
        return price;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public FeatureAvatar getFeatureAvatar() {
        return featureAvatar;
    }

    public double getRank() {
        return rank;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isIsPurchased() {
        return isPurchased;
    }

    public int getComments() {
        return comments;
    }

    public boolean isIsBookmarked() {
        return isBookmarked;
    }

    public String getSku() {
        return sku;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public int getTotalView() {
        return totalView;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public Object getInvestGoal() {
        return investGoal;
    }

    public List<Object> getProductStaff() {
        return productStaff;
    }

    public Support getSupport() {
        return support;
    }

    public boolean isIsSpecial() {
        return isSpecial;
    }

    public List<Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public Object getCustomjson() {
        return customjson;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(nameEnglish);
        dest.writeInt(productType);
        dest.writeString(producerName);
        dest.writeInt(price);
        dest.writeDouble(rank);
        dest.writeString(shortDescription);
        dest.writeByte((byte) (isPurchased ? 1 : 0));
        dest.writeInt(comments);
        dest.writeByte((byte) (isBookmarked ? 1 : 0));
        dest.writeString(sku);
        dest.writeString(priceUnit);
        dest.writeInt(totalView);
        dest.writeString(dateAdded);
        dest.writeByte((byte) (isSpecial ? 1 : 0));
        dest.writeString(datePublished);
    }
}
