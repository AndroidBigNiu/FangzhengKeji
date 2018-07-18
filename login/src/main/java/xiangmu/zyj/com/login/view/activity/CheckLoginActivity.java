package xiangmu.zyj.com.login.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rubensousa.raiflatbutton.RaiflatButton;
import com.sdsmdg.tastytoast.TastyToast;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import xiangmu.zyj.com.login.R;
import xiangmu.zyj.com.login.moudle.utils.UserInfo;
import xiangmu.zyj.com.login.moudle.utils.UserManage;
import xiangmu.zyj.com.login.view.fragment.DuanziFragment;

public class CheckLoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.return_back)
    ImageView back;
    @BindView(R.id.normalButton)
    RaiflatButton normalButton;
    @BindView(R.id.QQButton)
    RaiflatButton qqButton;
    @BindView(R.id.elselogin)
    TextView elselogin;
    private Intent intent;
    private boolean conn;
    private Intent mainintent;
    @Override
    public int getView() {
        return R.layout.activity_check_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        back.setOnClickListener(this);
        normalButton.setOnClickListener(this);
        elselogin.setOnClickListener(this);
        qqButton.setOnClickListener(this);
        mainintent = new Intent(CheckLoginActivity.this,MainActivity.class);
        intent = new Intent(CheckLoginActivity.this,LoginActivity.class);
    }
    @Override
    public void initVData() {

    }
    @Override
    public void onClick(View v) {
        conn = isConn(CheckLoginActivity.this);
        switch (v.getId()){
            case R.id.return_back:
                finish();
                break;
            case R.id.normalButton:
                if(conn){
                    TastyToast.makeText(getApplicationContext(), "微信登录", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    MyWeixinLogin();
                }else{
                    openNetDialog(CheckLoginActivity.this);
                }
                break;
            case R.id.QQButton:
                if(conn){
                    TastyToast.makeText(getApplicationContext(), "微信登录", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                    }else{
                    openNetDialog(CheckLoginActivity.this);
                }
                TastyToast.makeText(getApplicationContext(), "QQ登录", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                MyQQLogin();
                break;
            case R.id.elselogin:
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    public void MyQQLogin(){
        UMAuthListener authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }
            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                //MainActivity需要的数据
                //用户名字
                String name = data.get("screen_name");
                //token值
                String token = data.get("accessToken");
                //性别
                String gender = data.get("gender");
                //图片路径
                String profile_image_url = data.get("profile_image_url");
                String uid = data.get("uid");
                UserManage instance = UserManage.getInstance();
                //这里写一个思路 uid第三方返回的是字符串类型 而一刻钟传回的是6位的int类型 这里就可以判断是第三方登录还是本地登陆的
                instance.saveUserInfo(CheckLoginActivity.this,name,"",token,"","",0,profile_image_url,gender);
                startActivity(mainintent);

            }
            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(CheckLoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
            }
            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(CheckLoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
            }
        };
        UMShareAPI umShareAPI = UMShareAPI.get(CheckLoginActivity.this);
        umShareAPI.getPlatformInfo(CheckLoginActivity.this, SHARE_MEDIA.QQ,authListener);
    }
    public void MyWeixinLogin(){
        UMAuthListener authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }
            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Toast.makeText(CheckLoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                Log.e("==",""+data.toString());
                //MainActivity需要的数据
                //用户名字
                String name = data.get("screen_name");
                //token值
                String token = data.get("accessToken");
                //性别
                String gender = data.get("gender");
                //图片路径
                String profile_image_url = data.get("profile_image_url");
                String uid = data.get("uid");
                UserManage instance = UserManage.getInstance();
                //这里写一个思路 uid第三方返回的是字符串类型 而一刻钟传回的是6位的int类型 这里就可以判断是第三方登录还是本地登陆的
                instance.saveUserInfo(CheckLoginActivity.this,name,"",token,"","",0,profile_image_url,gender);
                startActivity(mainintent);

            }
            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(CheckLoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
            }
            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(CheckLoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
            }
        };
        UMShareAPI umShareAPI = UMShareAPI.get(CheckLoginActivity.this);
        umShareAPI.getPlatformInfo(CheckLoginActivity.this, SHARE_MEDIA.WEIXIN,authListener);
    }

}
