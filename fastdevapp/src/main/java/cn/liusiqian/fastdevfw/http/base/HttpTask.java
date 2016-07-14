package cn.liusiqian.fastdevfw.http.base;

import java.util.HashMap;
import java.util.Map;

import cn.liusiqian.fastdevfw.http.Api;
import cn.liusiqian.fastdevfw.http.DomainManager;

/**
 * Créé par liusiqian 16/7/14.
 */
public class HttpTask
{
    public Api api;
    public String url;
    public String tag;
    public Map<String, String> params;

    public HttpTask(Api api)
    {
        this.api = api;
        url = DomainManager.getUrl(api);
    }

    public HttpTask addParam(String key, String value)
    {
        if (key != null && value != null)
        {
            if (params == null)
            {
                params = new HashMap<>();
            }
            params.put(key, value);
        }
        return this;
    }

    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }
}