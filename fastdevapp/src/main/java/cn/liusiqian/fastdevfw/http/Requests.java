package cn.liusiqian.fastdevfw.http;

/**
 * Créé par liusiqian 16/7/15.
 */
public class Requests extends ExecutorWrapper
{
    private static Requests instance = null;
    private Requests(){}
    public static Requests getInstance()
    {
        if(instance == null)
        {
            synchronized (Requests.class)
            {
                if (instance == null)
                {
                    instance = new Requests();
                }
            }
        }
        return instance;
    }
}
