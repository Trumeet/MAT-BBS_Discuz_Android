package me.letitfly.mat.api;

import me.letitfly.mat.api.model.HttpResult;
import me.letitfly.mat.model.ForumDisplay;
import me.letitfly.mat.model.ForumNav;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Trumeet on 2017/3/2.
 * Mat Bbs Api
 * @author Trumeet
 */

interface APIInterface {
    @GET("mobile/index.php?version=4&module=forumnav")
    Observable<HttpResult<ForumNav>> nav (@Query("page") int page);
    @GET("mobile/index.php?version=4&module=forumdisplay")
    Observable<HttpResult<ForumDisplay>> display (@Query("fid") int fid, @Query("page") int page);
}
