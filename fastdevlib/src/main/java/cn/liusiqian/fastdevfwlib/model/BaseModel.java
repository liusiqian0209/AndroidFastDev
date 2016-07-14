package cn.liusiqian.fastdevfwlib.model;

/**
 * Créé par liusiqian 16/7/13.
 */

import com.google.gson.annotations.SerializedName;

/**
 * 我们这里存放服务器对于每个Api统一返回的格式。
 * 无论参数有无以及为何值，返回的json中总存在这些字段
 * 比如error_code表示返回码(0表示成功),
 * reason表示错误信息
 *
 * Here we stored json fields that part of the server response,
 * no matter what your params are or whether they exists
 */
public abstract class BaseModel
{
    // TODO:Customize your own response json here

    @SerializedName("resultcode")
    public String resultcode;
    @SerializedName("reason")
    public String reason;
    @SerializedName("error_code")
    public int errorCode;
}
