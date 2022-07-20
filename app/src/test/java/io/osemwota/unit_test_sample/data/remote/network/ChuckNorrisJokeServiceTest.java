package io.osemwota.unit_test_sample.data.remote.network;

import static com.google.common.truth.Truth.assertThat;

import androidx.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import io.osemwota.unit_test_sample.util.ParseJsonHelper;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckNorrisJokeServiceTest {

    private ChuckNorrisJokeService service;
    private MockWebServer mockWebServer;

    @Before
    public void initialise() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url(JokesApiFactory.CHUCK_NORRIS_JOKE_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChuckNorrisJokeService.class);
    }

    @After
    public void reset() throws IOException {
//        mockWebServer.close();
        mockWebServer.shutdown();
    }

    @Test
    public void requestJokeSuccess() throws InterruptedException {
        final String expectedUrl = "/" + ChuckNorrisJokeService.RANDOM_JOKE_ENDPOINT;
        String HTTP_REQUEST = "GET";
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(ParseJsonHelper.randomChuckNorrisSuccessResponse());
        mockResponse.setResponseCode(HttpURLConnection.HTTP_OK);
        Dispatcher dispatcher = new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest recordedRequest) throws InterruptedException {
                try {
                    switch (Objects.requireNonNull(recordedRequest.getPath())) {
                        case expectedUrl:
                            return new MockResponse()
                                    .setResponseCode(HttpURLConnection.HTTP_OK)
                                    .setBody(ParseJsonHelper.randomChuckNorrisSuccessResponse());
                    }

                } catch (Exception ignored) {

                }
                return new MockResponse().setResponseCode(404);
            }
        };
//        mockWebServer.setDispatcher(dispatcher);
        mockWebServer.enqueue(mockResponse);

        Call<ChuckNorrisJokeItem> responseBody = service.getRandomJoke();
        RecordedRequest request = mockWebServer.takeRequest();

        assertThat(responseBody).isNotNull();
        assertThat(request.getPath()).isEqualTo(expectedUrl);
        assertThat(request.getMethod()).isEqualTo(HTTP_REQUEST);
    }
}
