package xiangmu.zyj.com.login.moudle.utils;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xiangmu.zyj.com.login.moudle.bean.LoginBean;
import xiangmu.zyj.com.login.moudle.bean.RegisterBean;

public interface Apiservice {
    /*@GET(ApiServiceVar.JIEKOU+"?type=3&page=1")
    Flowable<MyDataBean> getHomeAd();
       @Query("type") String type, @Query("page") int page*/
//login 登录
    @GET(ApiServiceVar.LOGIN)
    Flowable<LoginBean> getHomeAd(@Query("mobile") String mobile, @Query("password") String password);
    @GET(ApiServiceVar.REGISTER)
    Flowable<RegisterBean> getREGISTER(@Query("regType") String regType,@Query("mobile") String mobile, @Query("password") String password);
}
