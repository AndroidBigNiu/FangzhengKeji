package xiangmu.zyj.com.login.view.activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
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
import xiangmu.zyj.com.login.moudle.bean.LoginBean;
import xiangmu.zyj.com.login.moudle.utils.UserManage;
import xiangmu.zyj.com.login.persenter.MainPersenter;
import xiangmu.zyj.com.login.view.intfaces.MainView;

public class LoginActivity extends SwipeBackActivity implements View.OnClickListener,MainView {
@BindView(R.id.req_login)
    TextView regist;

@BindView(R.id.ese_img)
    ImageView returnImg;
    //登录按钮
    @BindView(R.id.text_login)
    Button text_linear;
  //账号
    @BindView(R.id.mine_moblie)
    EditText mine_moblie;
    //密码
    @BindView(R.id.mine_password)
    EditText mine_password;

    @BindView(R.id.forget_pass)
    TextView tourist;
    //游客登录
    @BindView(R.id.no_login)
    TextView no_login;

    private Intent intent;
    private Intent mainintent;
    private MainPersenter mainPersenter;
    private String mine_m;
    private String mine_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        immersion();
        ButterKnife.bind(this);
        initView();
        initData();

    }
    public void initView() {
       regist.setOnClickListener(this);
       returnImg.setOnClickListener(this);
          mine_moblie.setOnClickListener(this);
        text_linear.setOnClickListener(this);
        mine_password.setOnClickListener(this);
        tourist.setOnClickListener(this);
        intent = new Intent(LoginActivity.this,RegisteredActivity.class);
        mainintent = new Intent(LoginActivity.this,MainActivity.class);
        mainPersenter = new MainPersenter();
        Intent intent = getIntent();
        String num1 = intent.getStringExtra("zhanghao");
        String num2 = intent.getStringExtra("password");
        mine_moblie.setText(num1);
        mine_password.setText(num2);

    }
    public void initData() {

    }
    @Override
    public void onClick(View v) {
      switch (v.getId()){
            case R.id.req_login:
                //调到注册页面
                startActivity(intent);
                break;
             case R.id.ese_img:
                //返回上一层
                finish();
                break;
             case R.id.text_login:
               //这里做的是登录 对账号密码的验证
                 mine_m = mine_moblie.getText().toString();
                 mine_p = mine_password.getText().toString();
                 if("".equals(mine_m)&&"".equals(mine_p)){
                     TastyToast.makeText(getApplicationContext(), "账号密码不能为空", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                 }else{
                     mainPersenter.attchView(this);
                     mainPersenter.acttview(mine_m,mine_p);
                 }
                 break;
            case R.id.forget_pass:
                //忘记密码
                break;
          case R.id.no_login:
              //游客登录
             /* startActivity(mainintent);*/
              break;
        }

    }
    public void immersion() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
//回调的方法
    @Override
    public void access(Object object) {
    LoginBean loginBean= (LoginBean) object;
        String msg = loginBean.getMsg();
        if(msg.equals("登录成功")){
            TastyToast.makeText(getApplicationContext(), "登录成功", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
            String token = loginBean.getData().getToken();
            int uid = loginBean.getData().getUid();
            String username = loginBean.getData().getUsername();
            UserManage instance = UserManage.getInstance();
            instance.saveUserInfo(LoginActivity.this,username,"",token,"","",uid,"http://img1.touxiang.cn/uploads/20121226/26-025517_373.jpg","女");
            startActivity(mainintent);
        }else{
            TastyToast.makeText(getApplicationContext(), msg+"登录失败,账号或密码格式不正确", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        }
    }

    @Override
    public void registeraccess(Object object) {

    }

    @Override
    public void guanzhu(Object object) {

    }
}
