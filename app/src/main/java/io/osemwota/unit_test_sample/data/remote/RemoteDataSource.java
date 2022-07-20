package io.osemwota.unit_test_sample.data.remote;

import io.osemwota.unit_test_sample.data.JokeDataSource;
import io.osemwota.unit_test_sample.data.remote.network.ChuckNorrisJokeService;
import io.osemwota.unit_test_sample.data.remote.network.JokesApiService;
import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import io.osemwota.unit_test_sample.data.remote.responses.JokesResponseItem;
import retrofit2.Call;

public class RemoteDataSource implements JokeDataSource {

    private final JokesApiService jokesApiService;
    private final ChuckNorrisJokeService chuckNorrisJokeService;

    public RemoteDataSource(
            JokesApiService jokesApiService,
            ChuckNorrisJokeService chuckNorrisJokeService
    ) {
        this.jokesApiService = jokesApiService;
        this.chuckNorrisJokeService = chuckNorrisJokeService;
    }

    @Override
    public Call<JokesResponseItem> getRandomJoke() {
        return jokesApiService.getRandomJoke();
    }

    @Override
    public Call<ChuckNorrisJokeItem> getRandomChuckNorrisJoke() {
        return chuckNorrisJokeService.getRandomJoke();
    }
}
