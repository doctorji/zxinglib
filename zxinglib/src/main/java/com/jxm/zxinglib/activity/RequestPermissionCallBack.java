package com.jxm.zxinglib.activity;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public interface RequestPermissionCallBack {
    /**
     * 同意授权
     */
    void granted();

    /**
     * 取消授权
     */
    void denied();
}
