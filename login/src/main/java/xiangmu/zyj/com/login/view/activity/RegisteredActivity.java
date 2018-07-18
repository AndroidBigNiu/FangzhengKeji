package xiangmu.zyj.com.login.view.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import xiangmu.zyj.com.login.R;
import xiangmu.zyj.com.login.moudle.bean.RegisterBean;
import xiangmu.zyj.com.login.moudle.utils.PassUtil;
import xiangmu.zyj.com.login.persenter.MainPersenter;
import xiangmu.zyj.com.login.view.intfaces.MainView;

public class RegisteredActivity extends SwipeBackActivity implements View.OnClickListener,MainView{
  @BindView(R.id.return_req_text)
   ImageView Return_imager;
    @BindView(R.id.have_login)
    TextView have_login;
    @BindView(R.id.req_moblie)
    EditText req_m;
    @BindView(R.id.req_password)
    EditText req_p;
    @BindView(R.id.but_req)
    Button but_r;
    private MainPersenter persenter;
    private String req_mm;
    private String req_pp;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        immersion();
        initView();
        initData();
    }
    public void initView() {
        ButterKnife.bind(this);
        persenter = new MainPersenter();
        initsetView();
    }
    public void initData() {
        intent = new Intent(RegisteredActivity.this, LoginActivity.class);

    }
    public void immersion() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void initsetView(){
        Return_imager.setOnClickListener(this);
        have_login.setOnClickListener(this);
      /*  req_m.setOnClickListener(this);
        req_p.setOnClickListener(this);*/
      but_r.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_req_text:
                finish();
                break;
            case R.id.have_login:
                finish();
                break;
            case R.id.but_req:
                //点击注册按钮
                req_mm = req_m.getText().toString();
                req_pp = req_p.getText().toString();
                if(req_mm.equals("")|| req_pp.equals("")){
                    TastyToast.makeText(getApplicationContext(), "账号密码不能为空", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                }if(!PassUtil.isMobile(req_mm)){
                TastyToast.makeText(getApplicationContext(), "手机格式不正确", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
            }else{
            persenter.attchView(this);
            persenter.getregister(req_mm, req_pp);
            }
                break;
        }
    }

    @Override
    public void access(Object object) {

    }
//回调的数据
    @Override
    public void registeraccess(Object object) {
        RegisterBean registerBean= (RegisterBean) object;
        String msg = registerBean.getMsg();
        if("注册成功".equals(msg)){
            TastyToast.makeText(getApplicationContext(), "注册成功", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
            intent.putExtra("zhanghao",req_mm);
            intent.putExtra("password",req_pp);
            startActivity(intent);
        }else{
            TastyToast.makeText(getApplicationContext(), msg+"", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        }
    }

    @Override
    public void guanzhu(Object object) {

    }
}
