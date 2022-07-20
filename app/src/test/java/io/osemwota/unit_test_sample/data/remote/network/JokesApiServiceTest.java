package io.osemwota.unit_test_sample.data.remote.network;

import static com.google.common.truth.Truth.assertThat;

import androidx.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import io.osemwota.unit_test_sample.util.ParseJsonHelper;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesApiServiceTest {

    private final String JOKES_RESPONSE_SUCCESS = "mock-responses/get-joke-status200.json";
    private JokesApiService service;
    private MockWebServer mockWebServer;

    @Before
    public void initialise() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url(JokesApiFactory.DAD_JOKES_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JokesApiService.class);
    }

    @After
    public void reset() throws IOException {
        mockWebServer.close();
    }

    @Test
    public void requestJokeSuccess() throws InterruptedException {
        final String expectedUrl = "/" + JokesApiService.RANDOM_JOKE_ENDPOINT;
        String HTTP_REQUEST = "GET";
        Dispatcher dispatcher = new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest recordedRequest) throws InterruptedException {
                try {
                    switch (Objects.requireNonNull(recordedRequest.getPath())) {
                        case expectedUrl:
                            return new MockResponse()
                                    .setResponseCode(HttpURLConnection.HTTP_OK)
                                    .setBody(ParseJsonHelper.SUCCESSFUL_RESPONSE);
                    }

                } catch (Exception ignored) {

                }
                return new MockResponse().setResponseCode(404);
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        service.getRandomJoke();
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath()).isEqualTo(expectedUrl);
        assertThat(request.getMethod()).isEqualTo(HTTP_REQUEST);
    }

    @Test
    public void requestJokeSuccessII() throws IOException, InterruptedException, RuntimeException{

        String expectedResponseData = ParseJsonHelper.SUCCESSFUL_RESPONSE;
        MockResponse successResponse = new MockResponse();
        successResponse
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(expectedResponseData);

        Response<JokesResponseItem> apiResponse = service.getRandomJoke().execute();

        mockWebServer.enqueue(successResponse);
        mockWebServer.takeRequest();

        assertThat(apiResponse.body()).isEqualTo(expectedResponseData);
    }

}