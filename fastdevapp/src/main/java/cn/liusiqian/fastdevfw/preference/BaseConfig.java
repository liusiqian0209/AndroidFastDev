package cn.liusiqian.fastdevfw.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Créé par liusiqian 16/7/12.
 */
class BaseConfig
{
    private SharedPreferences preferences;

    public BaseConfig(String name, Context context)
    {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor edit()
    {
        return preferences.edit();
    }

    protected void commitBoolean(String key, boolean value)
    {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putBoolean(key, value);
        ed.commit();
    }

    protected boolean getBoolean(String key, boolean defValue)
    {
        return preferences.getBoolean(key, defValue);
    }

    protected void commitInt(String key, int value)
    {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putInt(key, value);
        ed.commit();
    }

    protected int getInt(String key, int defValue)
    {
        return preferences.getInt(key, defValue);
    }

    protected void commitLong(String key, long value)
    {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putLong(key, value);
        ed.commit();
    }


    protected long getLong(String key, long defValue)
    {
        return preferences.getLong(key, defValue);
    }

    protected void commitString(String key, String value)
    {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString(key, value);
        ed.commit();
    }

    protected String getString(String key, String defValue)
    {
        return preferences.getString(key, defValue);
    }

    protected void commitFloat(String key, float value)
    {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putFloat(key, value);
        ed.commit();
    }

    protected float getFloat(String key, float defValue)
    {
        return preferences.getFloat(key, defValue);
    }

    protected void commitStringSet(String key, Set<String> values)
    {
        SharedPreferences.Editor ed = preferences.edit().clear();
        ed.putStringSet(key, values);
        ed.commit();
    }

    protected Set<String> getStringSet(String key, HashSet defValues)
    {
        return preferences.getStringSet(key, defValues);
    }
}
