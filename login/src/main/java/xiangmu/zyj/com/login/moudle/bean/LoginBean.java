package xiangmu.zyj.com.login.moudle.bean;

public class LoginBean {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":"a3d80a5d32391b04","appsecret":"67416844A33BBCA08C2622C145A9DA40","createtime":"2018-06-08T20:23:40","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"17600960805","money":null,"nickname":null,"password":"96D4B05BC40E9CA4386CFE31668FB848","praiseNum":null,"token":"B6308BDFA5951C0ABE5B1DA02769D193","uid":15451,"userId":null,"username":"17600960805"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
        
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : a3d80a5d32391b04
         * appsecret : 67416844A33BBCA08C2622C145A9DA40
         * createtime : 2018-06-08T20:23:40
         * email : null
         * fans : null
         * follow : null
         * gender : null
         * icon : null
         * latitude : null
         * longitude : null
         * mobile : 17600960805
         * money : null
         * nickname : null
         * password : 96D4B05BC40E9CA4386CFE31668FB848
         * praiseNum : null
         * token : B6308BDFA5951C0ABE5B1DA02769D193
         * uid : 15451
         * userId : null
         * username : 17600960805
         */

        private int age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private String email;
        private String fans;
        private String follow;
        private String gender;
        private String icon;
        private String latitude;
        private String longitude;
        private String mobile;
        private Double money;
        private String nickname;
        private String password;
        private String praiseNum;
        private String token;
        private int uid;
        private int userId;
        private String username;

        public int getAge() {
            return age;
        }

        public String getAppkey() {
            return appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public String getEmail() {
            return email;
        }

        public String getFans() {
            return fans;
        }

        public String getFollow() {
            return follow;
        }

        public String getGender() {
            return gender;
        }

        public String getIcon() {
            return icon;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public Double getMoney() {
            return money;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPassword() {
            return password;
        }

        public String getPraiseNum() {
            return praiseNum;
        }

        public String getToken() {
            return token;
        }

        public int getUid() {
            return uid;
        }

        public int getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }


        public void setAge(int age) {
            this.age = age;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
