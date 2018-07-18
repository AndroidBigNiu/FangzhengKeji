package xiangmu.zyj.com.login.moudle.utils;

import java.io.Serializable;

public class UserInfo implements Serializable{
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * Token
     * @return
     */
     private String Token;
    private String nickname;
    private String icon;
    private String praiseNum;
    private int uid;
    //图片路径
    private String profile_image_url;
    //用户性别
    private String gender;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {

        return gender;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {

        this.profile_image_url = profile_image_url;
    }
}
