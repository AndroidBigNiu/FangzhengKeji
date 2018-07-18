package xiangmu.zyj.com.login.persenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import xiangmu.zyj.com.login.moudle.bean.LoginBean;
import xiangmu.zyj.com.login.moudle.bean.RegisterBean;
import xiangmu.zyj.com.login.moudle.utils.HttpUtils;

public class MainPersenter extends BasePersenter {
    public void acttview(String mine_m,String mine_p){
        Flowable<LoginBean> homeAd = HttpUtils.getInstance().getApiService().getHomeAd(mine_m,mine_p);
        homeAd.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        //接口回调的方法
                        gettchView().access(loginBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getregister(String user_register,String user_password){
        Flowable<RegisterBean> register = HttpUtils.getInstance().getApiService().getREGISTER("0",user_register,user_password);
        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        //接口回调的方法
                        gettchView().registeraccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
