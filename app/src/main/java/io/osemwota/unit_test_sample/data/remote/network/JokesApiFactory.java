package io.osemwota.unit_test_sample.data.remote.network;


import io.osemwota.unit_test_sample.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesApiFactory {

    public static String DAD_JOKES_BASE_URL = "https://us-central1-dadsofunny.cloudfunctions.net/DadJokes/";
    public static String CHUCK_NORRIS_JOKE_BASE_URL = "https://api.chucknorris.io/";
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
    private static Retrofit provideRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
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
        return provideRetrofit(DAD_JOKES_BASE_URL).create(JokesApiService.class);
    }

    /**
     * Provide an instance of {@link ChuckNorrisJokeService}
     * @return Retrofit service for Chuck Norris Jokes API
     */
    public static ChuckNorrisJokeService provideChuckNorrisJokesApiService() {
        return provideRetrofit(CHUCK_NORRIS_JOKE_BASE_URL).create(ChuckNorrisJokeService.class);
    }

}
