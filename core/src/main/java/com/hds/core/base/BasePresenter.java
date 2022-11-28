package com.hds.core.base;

import java.lang.ref.WeakReference;

public class BasePresenter<View> {
    private WeakReference<View> mViews;

    public BasePresenter(View view){
        attachView(view);
    }

    public void attachView(View view){
        mViews = new WeakReference<>(view);
    }

    protected View getView(){
        return isViewAttached() ? mViews.get() : null;
    }

    //如果mView.get()为空 则说明mView所指向的对象已经被回收
    private boolean isViewAttached(){
        return null != mViews && null != mViews.get();
    }

    public void detachView(){
        if (null != mViews){
            mViews.clear();
            mViews = null;
        }
    }
}
