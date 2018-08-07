package com.yaratech.yaratube.presenter;

import android.content.Context;

import com.yaratech.yaratube.dataModels.Store;

/**
 * Created by Vah on 8/6/2018.
 */

public interface GetDataInterface {
Store getStoreData(Context context);

Store getCategoryListData(Context context);
}
