package xiangmu.zyj.com.login.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import xiangmu.zyj.com.login.R;


public class CrossActivity extends BaseActivity implements View.OnClickListener {

    private Intent videointent;
    private Intent duanyouintent;
    private Button crossBut;
    private Button duanyouBut;

    @Override
    public int getView() {
        return R.layout.activity_cross;
    }

    @Override
    public void initView() {
        crossBut = findViewById(R.id.cross_video);
        duanyouBut = findViewById(R.id.cross_duanyou);
        crossBut.setOnClickListener(this);
        duanyouBut.setOnClickListener(this);
        videointent = new Intent(CrossActivity.this, VideoActivity.class);
        duanyouintent = new Intent(CrossActivity.this, DuanYouActivity.class);
    }
    @Override
    public void initVData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cross_video:
            startActivity(videointent);
                break;
            case R.id.cross_duanyou:
                startActivity(duanyouintent);
                break;
        }
    }
}
