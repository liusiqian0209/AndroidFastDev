package cn.liusiqian.fastdevfw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.liusiqian.fastdevfwlib.model.BaseModel;

/**
 * Créé par liusiqian 16/7/13.
 */

public class ExampleModel extends BaseModel
{

    /**
     * id : 12
     * name : tom
     * age : 25
     * gender : 0
     */

    @SerializedName("data")
    public List<DataEntity> data;

    public static class DataEntity
    {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("age")
        public int age;
        @SerializedName("gender")
        public int gender;
    }
}
