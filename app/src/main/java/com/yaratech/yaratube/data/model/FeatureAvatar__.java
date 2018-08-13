
package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeatureAvatar__ {

    @SerializedName("xxhdpi")
    @Expose
    private String xxhdpi;
    @SerializedName("xxxdpi")
    @Expose
    private String xxxdpi;
    @SerializedName("xhdpi")
    @Expose
    private String xhdpi;
    @SerializedName("hdpi")
    @Expose
    private String hdpi;
    @SerializedName("mdpi")
    @Expose
    private String mdpi;

    public String getXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(String xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public String getXxxdpi() {
        return xxxdpi;
    }

    public void setXxxdpi(String xxxdpi) {
        this.xxxdpi = xxxdpi;
    }

    public String getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(String xhdpi) {
        this.xhdpi = xhdpi;
    }

    public String getHdpi() {
        return hdpi;
    }

    public void setHdpi(String hdpi) {
        this.hdpi = hdpi;
    }

    public String getMdpi() {
        return mdpi;
    }

    public void setMdpi(String mdpi) {
        this.mdpi = mdpi;
    }

}
