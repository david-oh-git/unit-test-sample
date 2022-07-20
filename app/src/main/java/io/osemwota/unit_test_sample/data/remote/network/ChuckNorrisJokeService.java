package io.osemwota.unit_test_sample.data.remote.network;

import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChuckNorrisJokeService {

    String RANDOM_JOKE_ENDPOINT = "jokes/random";

    @GET(RANDOM_JOKE_ENDPOINT)
    Call<ChuckNorrisJokeItem> getRandomJoke();
}
