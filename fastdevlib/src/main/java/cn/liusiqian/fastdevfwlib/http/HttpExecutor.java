package cn.liusiqian.fastdevfwlib.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Map;

import cn.liusiqian.fastdevfwlib.http.callback.ProgressCallback;
import cn.liusiqian.fastdevfwlib.model.BaseModel;
import okio.BufferedSink;

/**
 * Créé par liusiqian 16/7/14.
 */
public class HttpExecutor
{
    private static final String TAG = HttpExecutor.class.getSimpleName();
    private static final String CHARSET_NAME = "utf-8";
    protected OkHttpClient mClient;
    private static final int BUFFER_SIZE = 4 * 1024;
    protected Gson mGson = new Gson();
    protected Handler mHandler;

    public HttpExecutor()
    {
        mClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }


    protected void doRequestAsync(String url, Map<String, String> params, HttpMethod method, Object tag, Callback callback)
    {
        Request request = null;
        try
        {
            request = buildRequest(url, params, method, tag);
            mClient.newCall(request).enqueue(callback);
        }
        catch (Exception e)
        {
            callback.onFailure(new Request.Builder().tag(tag).build(), new IOException(e));
        }
    }

    public Response doRequestSync(String url, Map<String, String> params, HttpMethod method, Object tag) throws IOException, InvalidParameterException
    {
        return mClient.newCall(buildRequest(url, params, method, tag)).execute();
    }

    public void download(final String url, final String dir, final String fileName, final ProgressCallback callback)
    {
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onStart();
            }
        });
        mClient.newCall(buildRequest(url, null, HttpMethod.GET, null)).enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, final IOException e)
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        callback.onFailure(e);
                        callback.onEnd();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                try
                {
                    if (response.code() == 200)
                    {
                        String tempName = fileName + ".temp";
                        File tempFile = new File(dir, tempName);
                        final File file = new File(dir, fileName);
                        ResponseBody body = response.body();
                        long total = body.contentLength();
                        long readed = 0;
                        int progress = 0;
                        InputStream in = body.byteStream();
                        OutputStream out = new FileOutputStream(tempFile);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int read;
                        while ((read = in.read(buffer)) != -1)
                        {
                            out.write(buffer, 0, read);
                            out.flush();
                            readed += read;
                            int cp = (int) (readed * 100 / total);
                            if (cp != progress)
                            {
                                progress = cp;
                                final int fp = progress;
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        callback.onProgress(fp);
                                    }
                                });
                            }
                        }
                        out.close();
                        in.close();
                        tempFile.renameTo(file);
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                callback.onSuccess(file);
                            }
                        });
                    }
                    else
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                callback.onFailure(null);
                            }
                        });
                    }
                }
                catch (final IOException e)
                {
                    e.printStackTrace();
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            callback.onFailure(e);
                        }
                    });
                }
                finally
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            callback.onEnd();
                        }
                    });
                }
            }
        });
    }

    protected void uploadWithForm(final String url, Map<String, String> params, final FileUploadEntity entity, final ProgressCallback callback)
    {
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onStart();
            }
        });
        MultipartBuilder bodyBuilder = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart(entity.key, entity.fileName, new RequestBody()
                {
                    @Override
                    public long contentLength() throws IOException
                    {
                        return entity.file.length();
                    }

                    public MediaType contentType()
                    {
                        MediaType type = null;
                        if (TextUtils.isEmpty(entity.contentType))
                        {
                            type = MediaType.parse("application/octet-stream; charset=utf-8");
                        }
                        else
                        {
                            try
                            {
                                type = MediaType.parse(entity.contentType);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        return type;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException
                    {
                        long total = entity.file.length();
                        long writed = 0;
                        int progress = 0;
                        InputStream in = new FileInputStream(entity.file);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int write;
                        while ((write = in.read(buffer)) != -1)
                        {
                            sink.write(buffer, 0, write);
                            sink.flush();
                            writed += write;
                            int cp = (int) (writed * 100 / total);
                            if (cp != progress)
                            {
                                progress = cp;
                                final int fp = progress;
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        callback.onProgress(fp);
                                    }
                                });
                            }
                        }
                        in.close();
                    }
                });
        if (params != null && params.size() > 0)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = bodyBuilder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback()
        {

            @Override
            public void onFailure(Request request, final IOException e)
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        callback.onFailure(e);
                        callback.onEnd();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                try
                {
                    if (response.code() == 200)
                    {
                        final String json = response.body().string();
                        Type type = callback.getClass().getGenericSuperclass();
                        if (type instanceof ParameterizedType)
                        {
                            ParameterizedType pt = (ParameterizedType) type;
                            final Class clazz = (Class) pt.getActualTypeArguments()[0];
                            if (!BaseModel.class.equals(clazz))
                            {
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        callback.onSuccess(mGson.fromJson(json,clazz));
                                    }
                                });
                            }
                            else
                            {
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        callback.onSuccess(mGson.fromJson(json,BaseModel.class));
                                    }
                                });
                            }
                        }
                        else
                        {
                            mHandler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    callback.onSuccess(json);
                                }
                            });
                        }
                    }
                    else
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                callback.onFailure(null);
                            }
                        });
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (JsonSyntaxException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            callback.onEnd();
                        }
                    });
                }
            }
        });

    }

    protected Request buildRequest(String url, Map<String, String> params, HttpMethod method, Object tag) throws InvalidParameterException
    {
        try
        {
            Request.Builder requestBuilder = new Request.Builder();
            //为Get请求拼凑参数，对于Post来说仅用于打印日志
            StringBuilder paramsBuilder = new StringBuilder();
            if (params != null && params.size() > 0)
            {
                paramsBuilder.append("?");
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue()))
                    {
                        paramsBuilder.append(URLEncoder.encode(entry.getKey(), CHARSET_NAME))
                                .append("=")
                                .append(URLEncoder.encode(entry.getValue(), CHARSET_NAME))
                                .append("&");
                    }
                }
                if (paramsBuilder.length() > 1)
                {
                    paramsBuilder.deleteCharAt(paramsBuilder.length() - 1);
                }
            }

            if (HttpMethod.GET == method)
            {

                Logger.d("request-GET::" + url + paramsBuilder.toString());
                requestBuilder.url(url + paramsBuilder.toString());
                requestBuilder.get();
            }
            else
            {
                requestBuilder.url(url);
                FormEncodingBuilder bodyBuilder = new FormEncodingBuilder();
                if (params != null && params.size() > 0)
                {
                    for (Map.Entry<String, String> entry : params.entrySet())
                    {
                        if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue()))
                        {
                            bodyBuilder.add(entry.getKey(), entry.getValue());
                        }
                    }
                    requestBuilder.post(bodyBuilder.build());
                }
                else
                {
                    requestBuilder.get();
                }
                Logger.d("request-POST::" + url+ paramsBuilder.toString());
            }
            requestBuilder.tag(tag);

            return requestBuilder.build();
        }
        catch (Exception e)
        {
            throw new InvalidParameterException(e.getMessage());
        }
    }

}
