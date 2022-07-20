package io.osemwota.unit_test_sample.data;

import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import retrofit2.Call;

public interface JokeAppRepository {

    Call<JokesResponseItem> getRandomJoke();

    Call<ChuckNorrisJokeItem> getRandomChuckNorrisJoke();

}
