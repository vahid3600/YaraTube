
package com.yaratech.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yaratech.yaratube.utils.Utils;

public class CategoryList implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("is_default")
    @Expose
    private boolean isDefault;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("is_enable")
    @Expose
    private boolean isEnable;
    @SerializedName("is_visible")
    @Expose
    private boolean isVisible;
    @SerializedName("parent")
    @Expose
    private int parent;
    @SerializedName("childs")
    @Expose
    private List<Object> childs = null;

    protected CategoryList(Parcel in) {
        id = in.readInt();
        isDefault = in.readByte() != 0;
        title = in.readString();
        position = in.readInt();
        isEnable = in.readByte() != 0;
        isVisible = in.readByte() != 0;
        parent = in.readInt();
    }

    public static final Creator<CategoryList> CREATOR = new Creator<CategoryList>() {
        @Override
        public CategoryList createFromParcel(Parcel in) {
            return new CategoryList(in);
        }

        @Override
        public CategoryList[] newArray(int size) {
            return new CategoryList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = Utils.BASE_URL+avatar;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<Object> getChilds() {
        return childs;
    }

    public void setChilds(List<Object> childs) {
        this.childs = childs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (isDefault ? 1 : 0));
        dest.writeString(title);
        dest.writeInt(position);
        dest.writeByte((byte) (isEnable ? 1 : 0));
        dest.writeByte((byte) (isVisible ? 1 : 0));
        dest.writeInt(parent);
    }
}
