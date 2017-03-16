package me.letitfly.mat.api;

import me.letitfly.mat.BuildConfig;
import me.letitfly.mat.api.exception.ApiException;
import me.letitfly.mat.api.model.HttpResult;
import me.letitfly.mat.model.ForumDisplay;
import me.letitfly.mat.model.ForumNav;
import me.letitfly.mat.model.ThreadView;
import me.letitfly.mat.utils.Logger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static me.letitfly.mat.BuildConfig.DEBUG;

/**
 * Created by Trumeet on 2017/3/2.
 * API Interface manager
 * @see APIInterface
 * @author Trumeet
 */

public class APIManager {
    private static final String TAG = "API";
    private static Retrofit getBaseRetrofit () {
        Logger.i(TAG, "-> getBaseRetrofit");
        Retrofit.Builder builder =  new Retrofit.Builder()
                .baseUrl(BuildConfig.BBS_URL + "api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        if (DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            builder.client(client);
        }
        return builder.build();
    }

    private static APIInterface getInterface () {
        return getBaseRetrofit().create(APIInterface.class);
    }

    public static void getForumDisplay (int fid, int page, Subscriber<ForumDisplay> subscriber) {
        Logger.i(TAG, "-> getForumDisplay");
        toSubscribe(getInterface().display(fid, page)
        .map(new HttpResultFunc<ForumDisplay>()), subscriber);
    }

    public static void getForums(int page, Subscriber<ForumNav> subscriber) {
        Logger.i(TAG, "-> getForums");
        toSubscribe(getInterface().nav(page)
                .map(new HttpResultFunc<ForumNav>()), subscriber);
    }

    public static void getThreadView (int tid, int page, Subscriber<ThreadView> subscriber) {
        Logger.i(TAG, "-> getThreadView");
        toSubscribe(getInterface().threadView(tid, page)
                .map(new HttpResultFunc<ThreadView>()), subscriber);
    }

    //添加线程管理并订阅
    @SuppressWarnings("unchecked")
    private static void toSubscribe(Observable o, Subscriber s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private static class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) throws ApiException{
            if (!httpResult.isSuccess()) {
                throw new ApiException(httpResult.getError());
            }
            return httpResult.getData();
        }
    }

}
