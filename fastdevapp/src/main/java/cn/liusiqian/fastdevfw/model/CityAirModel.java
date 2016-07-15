package cn.liusiqian.fastdevfw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.liusiqian.fastdevfw.model.base.BaseModel;

/**
 * Créé par liusiqian 16/7/14.
 */
public class CityAirModel extends BaseModel
{
    @SerializedName("result")
    public List<ResultEntity> result;

    public static class ResultEntity
    {
        @SerializedName("citynow")
        public CitynowEntity citynow;

        public static class CitynowEntity
        {
            @SerializedName("city")
            public String city;
            @SerializedName("AQI")
            public String AQI;
            @SerializedName("quality")
            public String quality;
            @SerializedName("date")
            public String date;
        }
    }
}
