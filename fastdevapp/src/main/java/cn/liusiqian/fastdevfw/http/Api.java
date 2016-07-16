package cn.liusiqian.fastdevfw.http;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.http.base.HttpMethod;
import cn.liusiqian.fastdevfw.model.AccessTokenModel;
import cn.liusiqian.fastdevfw.model.CityAirModel;
import cn.liusiqian.fastdevfw.model.CityListModel;
import cn.liusiqian.fastdevfw.model.UploadModel;
import cn.liusiqian.fastdevfw.model.base.BaseModel;

/**
 * Créé par liusiqian 16/7/13.
 */

public enum Api
{
    // 下面是接口示例
    // Api Examples shown below
    // TODO: define your own api here

    @ExampleTag
    CITY_AIR("air/cityair", CityAirModel.class),
    @ExampleTag
    GET_CITIES("air/airCities", HttpMethod.GET, CityListModel.class),
    //access_token is like a cookie that represent your current id on your device
    // api below is a fake one
    @ExampleTag
    ACCESS_TOKEN("access/token",AccessTokenModel.class),
    @ExampleTag
    UPLOAD_FILE("file/upload",HttpMethod.POST, UploadModel.class);

    //Constructor with no parameter usually used for downloading file
    Api()
    {
        this.apiName = "";
        this.modelClass = BaseModel.class;
        this.method = HttpMethod.GET;
    }

    Api(String apiName)
    {
        this.apiName = apiName;
        this.modelClass = BaseModel.class;
        this.method = HttpMethod.GET;
    }

    Api(String apiName, Class<? extends BaseModel> modelClass)
    {
        this.apiName = apiName;
        this.modelClass = modelClass;
        this.method = HttpMethod.GET;
    }

    Api(String apiName, HttpMethod method, Class<? extends BaseModel> modelClass)
    {
        this.apiName = apiName;
        this.method = method;
        this.modelClass = modelClass;
    }

    public final String apiName;
    public final HttpMethod method;
    public final Class modelClass;
}
