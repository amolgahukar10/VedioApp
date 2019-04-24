package practice.iitms.com.vedioapp;


import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Aman Shahu on 1/23/2017.
 */

class ApiClient {
    //    private static final String BASE_URL = "http://192.168.0.69/JSSREST/api/v2/"; //common code
    //private static final String BASE_URL = "http://192.168.0.54/JSSREST/api/v2/"; //common code disha machine
    //private static final String BASE_URL = "http://103.203.87.18/jssturest/api/v2/"; // msg static url
    private static final String BASE_URL = "https://api.vimeo.com/"; // production www
//    private static final String BASE_URL = "http://androidjsstu.mastersofterp.in/api/v2/"; // production www
    static Retrofit retrofit = null;


    static Retrofit getClient() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(interceptor);
        }
        builder.client(httpClient.build());

        retrofit = builder.build();
        return retrofit;
    }


    @NonNull
    private static OkHttpClient getHeader(final Map<String, String> header) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder();
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }

                builder.method(original.method(), original.body());
                return chain.proceed(builder.build());
            }
        });
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(interceptor);
        }
        return httpClient.build();
    }
}