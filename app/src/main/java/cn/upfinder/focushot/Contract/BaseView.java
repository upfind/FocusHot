package cn.upfinder.focushot.Contract;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public interface BaseView<T> {

    T getMvpPresenter();

    void showError(String errorMsg);
}
