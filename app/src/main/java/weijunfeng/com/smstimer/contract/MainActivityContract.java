package weijunfeng.com.smstimer.contract;

import weijunfeng.com.smstimer.base.BasePresenter;
import weijunfeng.com.smstimer.base.BaseView;

/**
 * Created by hexin on 2016/11/18.
 */

public class MainActivityContract {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter<View> {

    }
}
