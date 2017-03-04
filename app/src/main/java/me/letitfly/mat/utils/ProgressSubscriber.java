package me.letitfly.mat.utils;

import rx.Subscriber;

public class ProgressSubscriber<T> extends Subscriber<T> {
    private static final String TAG = "ProgressSubscriber";
    public interface SubscriberOnNextListener<T> {
        void onNext(T t);
        void onError (Throwable e);
    }
    private SubscriberOnNextListener<T> mSubscriberOnNextListener;

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
        Logger.i(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(TAG, "onError", e);
        mSubscriberOnNextListener.onError(e);
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }
}