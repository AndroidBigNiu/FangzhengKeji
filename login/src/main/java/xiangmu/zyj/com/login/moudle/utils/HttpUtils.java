package xiangmu.zyj.com.login.moudle.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static volatile HttpUtils instance;
    private final Retrofit retrofit;

    private HttpUtils(){

        //拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceVar.BASE_URL)
                .client(build)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static HttpUtils getInstance() {

        if(instance == null){
            synchronized (HttpUtils.class){
                if(null == instance){
                    instance = new HttpUtils();
                }
            }
        }

        return instance;
    }

    public Apiservice getApiService(){
        return retrofit.create(Apiservice.class);
    }
}
