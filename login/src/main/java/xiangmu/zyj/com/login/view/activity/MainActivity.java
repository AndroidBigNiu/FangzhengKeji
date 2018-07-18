package xiangmu.zyj.com.login.view.activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.hjm.bottomtabbar.BottomTabBar;
import com.sdsmdg.tastytoast.TastyToast;
import com.suke.widget.SwitchButton;


import java.io.File;
import java.security.PublicKey;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import xiangmu.zyj.com.login.R;
import xiangmu.zyj.com.login.moudle.utils.ThemeManager;
import xiangmu.zyj.com.login.moudle.utils.UserInfo;
import xiangmu.zyj.com.login.moudle.utils.UserManage;
import xiangmu.zyj.com.login.view.fragment.DuanziFragment;
import xiangmu.zyj.com.login.view.fragment.QutuFragment;
import xiangmu.zyj.com.login.view.fragment.TuijianFragment;
import xiangmu.zyj.com.login.view.fragment.VedioFragment;

import static xiangmu.zyj.com.login.R.drawable.man;

public class  MainActivity extends BaseTwoActivity implements View.OnClickListener,ThemeManager.OnThemeChangeListener   {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CircleImageView menu;
    private SwitchButton switchButton;
    private ImageView night;
    private Intent crossintent;
    private ImageView scrollview;
    private LinearLayout linear;
    private BottomTabBar bottomTabBar;
    private TextView tx1;
    private TextView tx2;
    private View headerView;
    private LinearLayout linearLayout;
    private SharedPreferences sp;
    private int o=0;
    private CircleImageView person;
    private Intent login_intent;
    private boolean status =true;
    //性别
    private ImageView user_gender;
    private UserManage instance;
    private UserInfo userInfo;
    private boolean headstatus =true;
    private UserManage instance1;
    //关于拍照
    public  static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "headPhoto.jpg";
    private File tempFile;


    @Override
    public int getView() {
        return R.layout.activity_main;
    }
    @Override
    public void initView() {
        bottomTabBar = findViewById(R.id.bottom_tab_bar);
        linear = findViewById(R.id.main_linear);
        switchButton = findViewById(R.id.switch_button);
        night = findViewById(R.id.imageview_night);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu = (CircleImageView) findViewById(R.id.main_menu);
        scrollview = findViewById(R.id.search);
        headerView = navigationView.getHeaderView(0);
        crossintent = new Intent(MainActivity.this, CrossActivity.class);
        login_intent = new Intent(MainActivity.this, CheckLoginActivity.class);
        menu.setOnClickListener(this);
        scrollview.setOnClickListener(this);
        person = headerView.findViewById(R.id.person);
        linearLayout = headerView.findViewById(R.id.this_linerlayout);
        tx1 = headerView.findViewById(R.id.textview01);
        tx2 = headerView.findViewById(R.id.textview02);
        user_gender = headerView.findViewById(R.id.userdata_gender);
        InitUserData();
        instance1 = UserManage.getInstance();


        //头像的点击事件
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(headstatus){
                    startActivity(login_intent);
                }else if (headstatus==false&&status==false){
                    //这里做一个弹框
                    new AlertView.Builder().setContext(MainActivity.this)
                            .setStyle(AlertView.Style.ActionSheet)
                            .setTitle("选择操作")
                            .setMessage(null)
                            .setCancelText("取消")
                            .setDestructive("拍照", "从相册中选择")
                            .setOthers(null)
                            .setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(Object o, int position) {
                                    //无论是拍照还是从相册选择 如果都要直接上传头像
                                   if (position==0){
                                       //拍照操作 person menu  这两个赋值
                                       // 激活相机
                                       Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                       // 判断存储卡是否可以用，可用进行存储
                                       if (hasSdcard()) {
                                           tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                                           // 从文件中创建uri
                                           Uri uri = Uri.fromFile(tempFile);
                                           intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                                   /*        Glide.with(MainActivity.this).load(uri).into(person);
                                           Glide.with(MainActivity.this).load(uri).into(menu);*/
                                       }
                                       // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                                       startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

                                   }else{
                                       //从相册中选择
                                        // 激活系统图库，
                                        // 选择一张图片
                                       Intent intent1 = new Intent(Intent.ACTION_PICK);
                                       intent1.setType("image/*");
                                       // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                                       startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                                   }
                                }
                            })
                            .build()
                            .show();
                }

            }
        });

        //抽屉布局里面的主体设置点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString()){
                     case "    会员                        >":
             /*  Toast.makeText(MainActivity.this,item.getTitle().toString(), Toast.LENGTH_SHORT).show();*/
                    break;
                    case "    查看                        >":
                        break;
                    case "    相册                        >":
                        break;
                    case "    装扮                        >":
                        break;
                    case "    设置                        >":
                        if(!headstatus){
                            new PanterDialog(MainActivity.this)
                                    .setHeaderBackground(R.drawable.pppp)
                                    .setHeaderLogo(R.drawable.harder)
                                    .setPositive("退出登陆", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //退出登陆做的事情
                                            instance1.cleanSp(MainActivity.this);
                                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                                        }
                                    })
                                    .setNegative("取消")
                                    .setMessage("是否退出登陆")
                                    .isCancelable(false)
                                    .show();
                        }else{
                            new PanterDialog(MainActivity.this)
                                    .setHeaderBackground(R.drawable.pppp)
                                    .setHeaderLogo(R.drawable.harder)
                                    .setPositive("登陆", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        startActivity(login_intent);
                                        }
                                    })
                                    .setNegative("取消")
                                    .setMessage("您还没有登陆")
                                    .isCancelable(false)
                                    .show();
                        }
                        break;
                }
                //item.setChecked(true);
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
        //调用按钮的方法
        initSwch();
        ThemeManager.registerThemeChangeListener(this);
        sp = getSharedPreferences("info", MODE_PRIVATE);
    }
    @Override
    public void initVData() {
        bottomTabBar.init(getSupportFragmentManager(), 720, 1280)
                .addTabItem("推荐", R.mipmap.raw_1500085367, R.mipmap.raw_1500083878, TuijianFragment.class)
                .addTabItem("段子", R.mipmap.raw_1500085899, R.mipmap.raw_1500085327, DuanziFragment.class)
                .addTabItem("视频", R.mipmap.raw_1500086067, R.mipmap.raw_1500083686, VedioFragment.class)
                .addTabItem("趣图", R.mipmap.qutulan, R.mipmap.qutu, QutuFragment.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            bottomTabBar.setSpot(1, false);
                        if (position == 0)
                            bottomTabBar.setSpot(0, false);
                        if (position == 2)
                            bottomTabBar.setSpot(2, false);
                        if (position == 3)
                            bottomTabBar.setSpot(3, false);
                    }
                })
                .setSpot(1, true)
                .setSpot(2, true)
                .setSpot(3, true);
        readAccount();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            case R.id.search://点击跳转
                startActivity(crossintent);
        }
    }
 public void InitUserData(){
     instance = UserManage.getInstance();
     userInfo = instance.getUserInfo(MainActivity.this);
     if(!userInfo.getToken().equals("")){
         //用户名赋值
             tx1.setText(userInfo.getUserName());

             //给属性赋值
         headstatus=false;
             //用户头像赋值操作
         if(!(userInfo.getUid() ==0)){
            status=false;
         }
        if(!userInfo.getProfile_image_url().equals("")){
            Uri url = Uri.parse(userInfo.getProfile_image_url());
            Glide.with(MainActivity.this).load(url).into(person);
            Glide.with(MainActivity.this).load(url).into(menu);
        }
         if(userInfo.getGender().equals("男")){
             user_gender.setImageResource(R.drawable.man);
         }if (userInfo.getGender().equals("女")){
             user_gender.setImageResource(R.drawable.woman);
         }
     }
 }
  //读取保存在本地的用户名和密码
  public void readAccount() {
      //创建SharedPreferences对象
      bottomTabBar = findViewById(R.id.bottom_tab_bar);
      SharedPreferences sp1 = getSharedPreferences("info", MODE_PRIVATE);
      boolean b = sp1.getBoolean("zyj", false);
      o=5;
      if(b){
          switchButton.setChecked(true);
          setnight();
      }else if(b==false){
          switchButton.setChecked(false);
          daytime();
      }
  }
    public void initSwch(){
      switchButton.setChecked(false);
      switchButton.isChecked();
      switchButton.toggle();     //switch state
      switchButton.toggle(true);//switch without animation
      switchButton.setShadowEffect(true);//disable shadow effect
      switchButton.setEnabled(true);//disable button
      switchButton.setEnableEffect(true);//disable the switch animation
      switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(SwitchButton view, boolean isChecked) {
              linearLayout = headerView.findViewById(R.id.this_linerlayout);
              tx1 = headerView.findViewById(R.id.textview01);
              tx2 = headerView.findViewById(R.id.textview02);
                o++;
                if(isChecked){
                    //调用夜间模式的方法
                    setnight();
                    //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
                    //获得sp的编辑器
                    SharedPreferences.Editor ed = sp.edit();
                    //以键值对的显示将用户名和密码保存到sp中
                    ed.putBoolean("zyj",true);
                    //提交用户名和密码
                    ed.commit();
                }else{
                  daytime();
                  /*  judgment=false;
                    SharedPreferencesUtils.setParam(MainActivity.this, "boolean", judgment);*/
                    SharedPreferences.Editor ed = sp.edit();
                    //以键值对的显示将用户名和密码保存到sp中
                    ed.putBoolean("zyj",false);
                    //提交用户名和密码
                    ed.commit();
                }
          }
      });
  }

//夜间模式
    @Override
    public void onThemeChanged() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
    }
    //抽两个方法
    //晚上
    public void setnight(){
        night.setImageResource(R.drawable.yueliang2);
        ThemeManager.setThemeMode(ThemeManager.ThemeMode.NIGHT);
        if(o<4){
            TastyToast.makeText(getApplicationContext(), "夜间模式已开启!", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        }
        linear.setBackgroundResource(R.color.hhhh);
        navigationView.setBackgroundResource(R.color.hhhh);
        navigationView.setItemBackgroundResource(R.color.hhhh);
        linearLayout.setBackgroundResource(R.color.hhhh);
        drawerLayout.setBackgroundColor(Color.parseColor("#333444"));
        tx1.setTextColor(Color.WHITE);
        tx2.setTextColor(Color.WHITE);
        navigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        linear.setBackgroundResource(R.color.pppp);
        bottomTabBar.setTabBarBackgroundColor(Color.parseColor("#333444"));
    }
    //白天
    public void daytime(){
        if(o<4){
            TastyToast.makeText(getApplicationContext(), "夜间模式已关闭!", TastyToast.LENGTH_LONG, TastyToast.WARNING);
        }
        night.setImageResource(R.drawable.yueliang1);
        ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY);
        linear.setBackgroundResource(R.color.colorPrimary);
        navigationView.setBackgroundResource(R.color.textColor_night);
        navigationView.setItemBackgroundResource(R.color.textColor_night);
        linearLayout.setBackgroundResource(R.color.textColor_night);
        drawerLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tx1.setTextColor(Color.BLACK);
        tx2.setTextColor(Color.BLACK);
        navigationView.setItemIconTintList(ColorStateList.valueOf(Color.BLACK));
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#333444")));
        bottomTabBar.setTabBarBackgroundColor(Color.parseColor("#FFFFFF"));

    }
    //判断sdcard是否被挂载
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
        }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                //给图片赋值的操作
                person.setImageBitmap(bitmap);
                menu.setImageBitmap(bitmap);
                //给图片赋值的操作

              /*  //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);*/
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}