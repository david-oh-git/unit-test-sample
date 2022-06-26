package io.osemwota.unit_test_sample.data.remote;


import io.osemwota.unit_test_sample.BuildConfig;
import io.osemwota.unit_test_sample.data.remote.network.JokesApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesApiFactory {

    public static String BASE_URL = "https://us-central1-dadsofunny.cloudfunctions.net/DadJokes/";
    private static JokesApiFactory instance = null;

    /**
     *  Provider method for [HttpLoggingInterceptor] for HTTP client
     *
     *  @return Instance of http interceptor
     */
    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     *  Provides instance of {@link OkHttpClient}
     * @param interceptor For logging.
     *  @return Instance of http client
     */
    private static OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    /**
     * Provider method for [Retrofit]
     *
     * @return Instance of retrofit
     */
    private static Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(
                provideHttpClient( provideHttpLoggingInterceptor())
        );

        return builder.build();
    }

    /**
     * Provide an instance of {@link JokesApiService}
     * @return Retrofit service for Jokes API
     */
    public static JokesApiService provideDadJokesApiService() {
        return provideRetrofit().create(JokesApiService.class);
    }

}
