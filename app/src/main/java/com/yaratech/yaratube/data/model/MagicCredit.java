package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vah on 9/15/2018.
 */

public class MagicCredit {

    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("cash")
    @Expose
    private int cash;
    @SerializedName("gem")
    @Expose
    private int gem;
    @SerializedName("coin")
    @Expose
    private int coin;

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getGem() {
        return gem;
    }

    public void setGem(int gem) {
        this.gem = gem;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

}
