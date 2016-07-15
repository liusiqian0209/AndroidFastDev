package cn.liusiqian.fastdevfw.http;

import cn.liusiqian.fastdevfw.utils.AppConstants;

/**
 * Créé par liusiqian 16/7/14.
 */
public class DomainManager
{
    //这个类用于拼凑完整的域名,根据不同的服务器以及不同的环境获得对应的前缀
    //This class is used for getting your whole url, you may return corresponding domain prefix
    // according to your individual server and your Debug switch
    public static final String ONLINE = "http://web.juhe.cn/environment/";
    public static final String TEST = "https://192.168.12.23/environment/";
    public static final String BACKUP_SERVER = "https://192.168.12.28/environment/";


    public static String getUrl(Api api)
    {
        String prefix = BACKUP_SERVER;
        switch (api)
        {
            case CITY_AIR:
            case GET_CITIES:
                prefix = AppConstants.DEBUG_MODE ? TEST : ONLINE;
                break;
            case ACCESS_TOKEN:
                prefix = AppConstants.DEBUG_MODE ? TEST : BACKUP_SERVER;
                break;
        }
        return prefix + api.apiName;
    }
}
