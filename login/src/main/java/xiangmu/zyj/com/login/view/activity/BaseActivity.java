package xiangmu.zyj.com.login.view.activity;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity{
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        initVData();
    }
    //判断有无网络的方法
    public static boolean isConn(Context context){
        //1.得到系统服务
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2.得到网络信息类对象-需要添加权限
        NetworkInfo info = manager.getActiveNetworkInfo();
        //3.进行判断
        if(info!=null && info.isAvailable()){//已经连接网络
            return true;
        }else{
            return false;
        }
    }
    //如果没有网络的情况下，弹出一个对话框，打开设置页面
    public static void openNetDialog(final Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("没有网络，是否进行设置？");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //跳转到系统设置页面-隐士跳转
                Intent intent = null;
                // 先判断当前系统版本
                if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消",null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
           /* if(y1 - y2 > 50) {
                Toast.makeText(CheckLoginActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(CheckLoginActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                Toast.makeText(CheckLoginActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else */if(x2 - x1 > 500) {
                /*Toast.makeText(CheckLoginActivity.this, "向右滑", Toast.LENGTH_SHORT).show();*/
                finish();
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){

            }
        }
        return super.onTouchEvent(event);
    }
    public abstract int getView();
    public abstract void initView();
    public abstract void initVData();
}
