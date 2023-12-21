package com.example.WajahaAppTest.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.WajahaAppTest.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
    {
        private static final String TAG = "RetrofitClient";
        private static final String BASE_URL = "https://wajaha-api.beetronix.net/";
        public static final String HEADER_CACHE_CONTROL = "Cache-Control";
        public static final String HEADER_PRAGMA = "Pragma";
        public static final String AUTHORIZATION = "Authorization";

        private static final long cacheSize = 5 * 1024 * 1024; // 5 MB

        public static boolean forceNetworking = false;

        public static Retrofit getRetrofitCaching(boolean forceNetwork)
        {
            forceNetworking = forceNetwork;
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static Retrofit getRetrofit(boolean isCache, boolean isForceNetwork, boolean tokenCheck)
        {
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

            forceNetworking = isForceNetwork;

//            if(tokenCheck)
//            {
//                //set auth token check for requesting new auth token if current one is expired
//                okBuilder.authenticator(tokenAuthenticator());
//            }

            if(isCache)
            {
                //add cache for current request
                okBuilder.cache(cache())
                        .addNetworkInterceptor(networkInterceptor())
                        .addInterceptor(offlineInterceptor());
            }

            return getRetrofit().newBuilder()
                    .client(okBuilder.build())
                    .build();

        }



        public static Retrofit getRetrofit()
        {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        private static OkHttpClient okHttpClient()
        {
            return new OkHttpClient.Builder()
                    .cache(cache())
                   // .authenticator(tokenAuthenticator()) //used when the response is 401 'unauthorised'
//                    .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
//                    .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                    .addInterceptor(offlineInterceptor())
                    .build();
        }

        private static Cache cache()
        {
            return
            new Cache(new File(MyApplication.getInstance().getCacheDir(),"someIdentifier"), cacheSize);
        }

        /**
         * This interceptor will be called both if the network is available and if the network is not available
         * @return
         */
        private static Interceptor offlineInterceptor()
        {
            return new Interceptor()
            {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException
                {
                    Log.e(TAG, "offline interceptor: called. "+ forceNetworking);
                    Request request = chain.request();

                    // prevent caching when network is on. For that we use the "networkInterceptor"
                    if (!MyApplication.hasNetwork() && !forceNetworking)
                    {
                        CacheControl cacheControl = new CacheControl.Builder()
                                .maxStale(7, TimeUnit.DAYS)
                                .build();

                        request = request.newBuilder()
                                .removeHeader(HEADER_PRAGMA)
                                .removeHeader(HEADER_CACHE_CONTROL)
                                .cacheControl(cacheControl)
                                .build();
                    }

                    return chain.proceed(request);
                }
            };
        }

        /**
         * This interceptor will be called ONLY if the network is available
         * @return
         */
        private static Interceptor networkInterceptor()
        {
            return new Interceptor()
            {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException
                {
                    Log.d(TAG, "network interceptor: called.");

                    Response response = chain.proceed(chain.request());

                    CacheControl cacheControl = forceNetworking ? new CacheControl.Builder()
                            .maxAge(0, TimeUnit.SECONDS)
                            .build():
                            new CacheControl.Builder()
                                    .maxAge(5, TimeUnit.SECONDS)
                                    .build();

                    return response.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                            .build();
                }
            };
        }

        private static HttpLoggingInterceptor httpLoggingInterceptor ()
        {
            HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
                    {
                        @Override
                        public void log (String message)
                        {
                            //Log.d(TAG, "log: http log: " + message);
                        }
                    } );
            httpLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY);
            return httpLoggingInterceptor;
        }

//        private static Authenticator tokenAuthenticator()
//        {
//            return new Authenticator()
//            {
//                @Nullable
//                @Override
//                public Request authenticate(@Nullable Route route, Response response) throws IOException
//                {
//                    // Refresh your access_token using a synchronous api request
//                    boolean refreshToken = SessionManger.refreshToken();
//
//                    // Add new header to rejected request and retry it
//                    if(refreshToken)
//                    {
//                        return response.request().newBuilder()
//                                .header(AUTHORIZATION, SessionManger.getUserAuthToken())
//                                .build();
//                    }
//                    //return null when the refresh token is false so we do not get stuck in loop
//                    return null;
//                }
//            };
//        }
    }