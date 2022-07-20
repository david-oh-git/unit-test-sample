package io.osemwota.unit_test_sample.data;

import io.osemwota.unit_test_sample.data.JokeAppRepository;
import io.osemwota.unit_test_sample.data.JokeDataSource;
import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import retrofit2.Call;

public class JokeAppRepositoryImpl implements JokeAppRepository {

    private final JokeDataSource remoteSource;

    public JokeAppRepositoryImpl(JokeDataSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    @Override
    public Call<JokesResponseItem> getRandomJoke() {
        return remoteSource.getRandomJoke();
    }

    @Override
    public Call<ChuckNorrisJokeItem> getRandomChuckNorrisJoke() {
        return remoteSource.getRandomChuckNorrisJoke();
    }
}
