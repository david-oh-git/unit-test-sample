package io.osemwota.unit_test_sample.util;

import androidx.annotation.NonNull;

import java.io.IOException;

import io.osemwota.unit_test_sample.BuildConfig;
import io.osemwota.unit_test_sample.data.remote.network.JokesApiService;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (BuildConfig.DEBUG) {
            final String uri = chain.request().url().uri().toString();
            String responseString = "";
            if (uri.contains(JokesApiService.RANDOM_JOKE_ENDPOINT)) {
                responseString = ParseJsonHelper.SUCCESSFUL_RESPONSE;
                return chain.proceed(chain.request())
                        .newBuilder()
                        .code(200)
                        .protocol(Protocol.HTTP_2)
                        .message(responseString)
                        .body(
                                ResponseBody.create(
                                        MediaType.parse("application/json"),
                                        responseString.getBytes()
                                )
                        )
                        .addHeader("content-type", "application/json")
                        .build();
            }else {
                return chain.proceed(chain.request())
                        .newBuilder()
                        .code(404)
                        .protocol(Protocol.HTTP_2)
                        .message(responseString)
                        .body(
                                ResponseBody.create(
                                        MediaType.parse("application/json"),
                                        responseString.getBytes()
                                )
                        )
                        .addHeader("content-type", "application/json")
                        .build();
            }

        }else {
            throw new IllegalAccessError("MockInterceptor is only meant for testing purposes.");
        }
    }
}
