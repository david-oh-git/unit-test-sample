package io.osemwota.unit_test_sample.data;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.osemwota.unit_test_sample.data.remote.RemoteDataSource;
import io.osemwota.unit_test_sample.data.remote.network.ChuckNorrisJokeService;
import io.osemwota.unit_test_sample.data.remote.network.JokesApiService;

public class RemoteDataSourceTest {

    @Mock()
    private JokesApiService jokesApiService;
    @Mock
    private ChuckNorrisJokeService chuckNorrisJokeService;
    private JokeDataSource remoteSource;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        remoteSource = new RemoteDataSource(jokesApiService, chuckNorrisJokeService);
    }

    /**
     * Verify when {@link RemoteDataSource#getRandomJoke()} is called,
     * it calls {@link JokesApiService#getRandomJoke()} is also called.
     */
    @Test
    public void confirm_JokeService_getRandom_isCalled() {
        remoteSource = new RemoteDataSource(jokesApiService, chuckNorrisJokeService);

        remoteSource.getRandomJoke();
        verify(jokesApiService).getRandomJoke();
    }

    /**
     * Verify when {@link RemoteDataSource} is created,
     * it calls {@link JokesApiService#getRandomJoke()} is not called.
     */
    @Test
    public void confirm_JokeService_getRandom_isNotCalled_onInitialisation() {
        remoteSource = new RemoteDataSource(jokesApiService, chuckNorrisJokeService);

        verify(jokesApiService, never()).getRandomJoke();
    }

    /**
     * Verify when {@link RemoteDataSource#getRandomJoke()} is called,
     * it calls {@link JokesApiService#getRandomJoke()} is also called.
     */
    @Test
    public void confirm_ChuckNorrisJokeService_getRandom_isCalled() {
        remoteSource = new RemoteDataSource(jokesApiService, chuckNorrisJokeService);

        remoteSource.getRandomChuckNorrisJoke();
        verify(chuckNorrisJokeService).getRandomJoke();
    }

}