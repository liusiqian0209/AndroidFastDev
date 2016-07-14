package cn.liusiqian.fastdevfw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.liusiqian.fastdevfwlib.model.BaseModel;

/**
 * Créé par liusiqian 16/7/14.
 */
public class CityListModel extends BaseModel
{
    @SerializedName("result")
    public List<ResultEntity> result;

    public static class ResultEntity
    {
        @SerializedName("name")
        public String name;
        @SerializedName("pinyin")
        public String pinyin;
    }
}
