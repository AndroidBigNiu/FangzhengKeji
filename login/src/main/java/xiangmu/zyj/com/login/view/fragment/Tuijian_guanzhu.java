package xiangmu.zyj.com.login.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import xiangmu.zyj.com.login.R;
import xiangmu.zyj.com.login.view.intfaces.MainView;

public class Tuijian_guanzhu extends Fragment implements MainView {
    private XBanner banner;
    private RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.guanzhu, container, false);
        banner = inflate.findViewById(R.id.banner);
        rcv = inflate.findViewById(R.id.rv);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        // 为XBanner绑定数据
        banner .setData(imgesUrl,null);//第二个参数为提示文字资源集合
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });/*
        banner.setPageTransformer(Transformer.ZoomFade); // 缩小本页，同时放大另一页*/
/*
        banner.setPageTransformer(Transformer.ZoomStack); // 本页和下页同事缩小和放大*/
        banner.setPageTransformer(Transformer.Accordion); //三角换页
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getActivity(), "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }
    /** 为了更好的体验效果建议在下面两个生命周期中调用下面的方法 **/
    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void access(Object object) {

    }

    @Override
    public void registeraccess(Object object) {

    }
//本页面回调数据的方法
    @Override
    public void guanzhu(Object object) {

    }
}
