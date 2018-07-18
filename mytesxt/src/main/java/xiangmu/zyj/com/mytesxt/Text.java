package xiangmu.zyj.com.mytesxt;

import android.util.Log;

public enum Text  {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");//记住要用分号结束

    private String desc;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private Text(String desc){
        this.desc=desc;
    }
    /**
     * 覆盖
     * @return
     */
    @Override
    public String toString() {
        return desc;
    }
    public static void main(String[] args){
        for (Text day:Text.values()) {
            Log.i("name,desc:"+day.name(),day
                   .toString());
        /*    System.out.println("name:"+day.name()+
                    ",desc:"+day.toString());*/
        }
    }

}
