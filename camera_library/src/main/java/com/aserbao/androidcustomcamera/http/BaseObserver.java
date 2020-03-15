package com.aserbao.androidcustomcamera.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**Oberser类的封装,解决内存泄漏
 * Created by ivan on 2018/5/29.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onError(Throwable e) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
