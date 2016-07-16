package cn.liusiqian.fastdevfw.model;

import com.google.gson.annotations.SerializedName;

import cn.liusiqian.fastdevfw.model.base.BaseModel;

/**
 * Créé par liusiqian 16/7/16.
 */

public class UploadModel extends BaseModel
{
    @SerializedName("data")
    public DataEntity data;

    public static class DataEntity
    {
        @SerializedName("success")
        public int success;
    }
}
