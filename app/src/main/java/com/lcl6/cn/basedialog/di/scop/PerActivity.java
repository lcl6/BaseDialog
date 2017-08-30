package com.lcl6.cn.basedialog.di.scop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by liancl on 2017/8/30.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerActivity {
}
