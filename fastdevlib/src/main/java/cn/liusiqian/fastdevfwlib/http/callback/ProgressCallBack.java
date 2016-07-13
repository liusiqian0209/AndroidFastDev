package cn.liusiqian.fastdevfwlib.http.callback;

/**
 * Créé par liusiqian 16/7/13.
 */

public interface ProgressCallback<T> extends Callback<T>
{
    void onProgress(int progress);
}
