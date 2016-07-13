package cn.liusiqian.fastdevfwlib.model;

/**
 * Créé par liusiqian 16/7/13.
 */

import com.google.gson.annotations.SerializedName;

/**
 * 我们这里需要让服务器对于每个Api返回统一的格式。
 * 比如error_code表示返回码(0表示成功),
 * error_msg表示错误信息
 * data表示返回的数据结构
 */
public abstract class BaseModel
{

    /**
     * error_code : 0
     * error_msg : success
     */

    @SerializedName("error_code")
    public int errorCode;
    @SerializedName("error_msg")
    public String errorMsg;
}
