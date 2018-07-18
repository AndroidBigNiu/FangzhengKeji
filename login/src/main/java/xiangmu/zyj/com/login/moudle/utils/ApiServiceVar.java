package xiangmu.zyj.com.login.moudle.utils;

public class ApiServiceVar {
  //基本接口
  public static final String BASE_URL="https://www.zhaoapi.cn/";
    //1.登录 login
    public static final String LOGIN="user/login";
    //2.注册 register
    public static final String REGISTER="quarter/register";
    //3.忘记密码 resetPass
    public static final String RESETPASS="quarter/resetPass";
    //4.上传头像upload
    public static final String UPLOAD="file/upload";
    //5.获取用户信息getUserInfo
    public static final String GETUSERINFO="user/getUserInfo";
    //6.修改密码 updateNickName
    public static final String UPDATANICKNAME = "user/updateNickName";
    //7.发布段子 publishJoke
    public static final String PUBLISHJOKE ="quarter/publishJoke";
    //8.获取段子列表 getJokes
    public static final String GETJOKES ="quarter/getJokes";
    //9.发布视频作品 publishVideo
    public static final String PUBLISHVIDEO ="quarter/publishVideo";
    //10.获取视频作品列表getVideos
    public static final String GETVIDEOS = "quarter/getVideos";
    //11.获取某个用户的视频作品集 getUserVideos
    public static final String GETUSERVIDEOS = "quarter/getUserVideos";
    //12.关注 follow
    public static final String FOLLOW = "quarter/follow";
    //13.获取关注用户列表getFollowUsers
    public static final String GETFOLLOWUSERS = "quarter/getFollowUsers";
    //14.作品点赞praise
    public static final String PRAISE = "quarter/praise";
    //15.收藏作品 addFavorite
    public static final String ADDFAVORITE="quarter/addFavorite";
    //16.取消收藏 cancelFavorite
    public static final String CANCELFAVORITE ="quarter/cancelFavorite";
    //17.作品评论comment
    public static final String COMMENT = "quarter/comment";

}
