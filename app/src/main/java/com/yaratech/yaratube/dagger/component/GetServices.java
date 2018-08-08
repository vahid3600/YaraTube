package com.yaratech.yaratube.dagger.component;

import com.yaratech.yaratube.data.sourse.remote.Service;
import com.yaratech.yaratube.dagger.module.RetrofitModule;
import com.yaratech.yaratube.dagger.scope.MainScope;

import dagger.Component;

/**
 * Created by Vah on 7/11/2018.
 */

@MainScope
@Component(modules = RetrofitModule.class)
public interface GetServices {
    Service getStoreService();
}


