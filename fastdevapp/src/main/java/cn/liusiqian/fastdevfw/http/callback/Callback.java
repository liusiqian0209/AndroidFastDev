package cn.liusiqian.fastdevfw.http.callback;

/**
 * Créé par liusiqian 16/7/13.
 */

public interface Callback<T>
{
    void onStart();

    void onFailure(Exception exception);

    void onSuccess(T t);

    void onEnd();
}
