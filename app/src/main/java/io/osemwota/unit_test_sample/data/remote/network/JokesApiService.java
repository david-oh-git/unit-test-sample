package io.osemwota.unit_test_sample.data.remote.network;


import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesApiService {

    String RANDOM_JOKE_ENDPOINT = "random/jokes";

    @GET(RANDOM_JOKE_ENDPOINT)
    Call<JokesResponseItem> getRandomJoke();
}
