package cn.liusiqian.fastdevfw.model.base;

/**
 * Créé par liusiqian 16/7/13.
 */

import com.google.gson.annotations.SerializedName;

import cn.liusiqian.fastdevfw.ExampleTag;

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

    public static final int ERROR_CODE_SUCCESS = 0;

    @ExampleTag
    @SerializedName("resultcode")
    public String resultcode;
    @ExampleTag
    @SerializedName("reason")
    public String reason;
    @ExampleTag
    @SerializedName("error_code")
    public int errorCode;
}
