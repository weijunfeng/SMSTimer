package weijunfeng.com.smstimer.base;

/**
 * Created by hexin on 2016/11/18.
 */

public interface BaseContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
