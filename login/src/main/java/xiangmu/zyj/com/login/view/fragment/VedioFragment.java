package xiangmu.zyj.com.login.view.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;
/*import com.like.LikeButton;
import com.like.OnLikeListener;*/

import xiangmu.zyj.com.login.R;

public  class VedioFragment extends Fragment {
    private static final String TAG = "ViewPagerActivity";
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private View inflate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity_view_pager_layout_manager, container, false);
        initView();

        initListener();
        return inflate;
    }
    private void initView() {
        mRecyclerView = inflate.findViewById(R.id.recycler);
        mLayoutManager = new ViewPagerLayoutManager(getActivity(), OrientationHelper.VERTICAL);
        mAdapter = new MyAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    private void initListener(){
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG,"onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                Log.e(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                playVideo(0);
            }


        });
    }
    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2};
        private int[] videos = {R.raw.video_1,R.raw.video_2};
        private VideoView videoView;
        public MyAdapter(){

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            videoView = holder.videoView;
            holder.img_thumb.setImageResource(imgs[position%2]);
            holder.videoView.setVideoURI(Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ videos[position%2]));
            if(position%2==1){
                holder.aa.setImageResource(R.drawable.harder);
            }else if(position%2==2){
                holder.aa.setImageResource(R.mipmap.header_icon_2);
            }
           /* holder.lovebut.setOnLikeListener(new OnLikeListener() {
                //点击事件
                @Override
                public void liked(LikeButton likeButton) {

                }

                @Override
                public void unLiked(LikeButton likeButton) {

                }
            });*/
        }
        public void OnstopVideoView(){
            if(videoView.isPlaying()){
                videoView.pause();
            }else{
                return;
            }

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public  class ViewHolder extends RecyclerView.ViewHolder{
              ImageView img_thumb;
             VideoView videoView;
             ImageView img_play;
             RelativeLayout rootView;
             ImageView aa;
          /*  LikeButton lovebut;*/
            public ViewHolder(View itemView) {
                super(itemView);
                img_thumb = itemView.findViewById(R.id.img_thumb);
                videoView = itemView.findViewById(R.id.video_view);
                img_play = itemView.findViewById(R.id.img_play);
                rootView = itemView.findViewById(R.id.root_view);
                aa = itemView.findViewById(R.id.aa);
                 /*lovebut = itemView.findViewById(R.id.star_button);*/
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //要判断mAdapter不为空 还有videoView不为空的时候 才可以关闭  onPause onstop是用户按home建会执行的方法
        if(mAdapter!=null){
            if(mAdapter.videoView!=null){
                mAdapter.OnstopVideoView();
            }
        }
    }
}