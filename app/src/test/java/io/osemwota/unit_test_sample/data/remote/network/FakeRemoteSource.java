package io.osemwota.unit_test_sample.data.remote.network;

import io.osemwota.unit_test_sample.data.JokeDataSource;
import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import retrofit2.Call;

public class FakeRemoteSource implements JokeDataSource {

    @Override
    public Call<JokesResponseItem> getRandomJoke() {
        return null;
    }

    @Override
    public Call<ChuckNorrisJokeItem> getRandomChuckNorrisJoke() {
        return null;
    }
}
