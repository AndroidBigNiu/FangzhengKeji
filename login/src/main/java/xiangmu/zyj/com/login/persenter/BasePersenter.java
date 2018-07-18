package xiangmu.zyj.com.login.persenter;

import xiangmu.zyj.com.login.view.intfaces.MainView;

public class BasePersenter {
    private MainView mainView;
    public void attchView(MainView mainView){
        this.mainView = mainView;

    }
    public MainView gettchView(){
        return mainView;
    }
}

