package cn.liusiqian.fastdevfw.model;

import com.google.gson.annotations.SerializedName;

import cn.liusiqian.fastdevfwlib.model.BaseModel;

/**
 * Créé par liusiqian 16/7/14.
 */
public class AccessTokenModel extends BaseModel
{
    @SerializedName("result")
    public ResultEntity result;

    public static class ResultEntity
    {
        @SerializedName("access_token")
        public String accessToken;
        @SerializedName("expiration")
        public int expiration;
    }
}
