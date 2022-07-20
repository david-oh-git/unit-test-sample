package io.osemwota.unit_test_sample;

import io.osemwota.unit_test_sample.data.JokeAppRepository;
import io.osemwota.unit_test_sample.data.JokeAppRepositoryImpl;
import io.osemwota.unit_test_sample.data.remote.RemoteDataSource;
import io.osemwota.unit_test_sample.data.remote.network.JokesApiFactory;

public class ServiceLocator {

    private static JokeAppRepository repository = null;

    synchronized public static JokeAppRepository provideRepository() {
        if(repository == null) {
            repository = new JokeAppRepositoryImpl(
                    new RemoteDataSource(
                            JokesApiFactory.provideDadJokesApiService(),
                            JokesApiFactory.provideChuckNorrisJokesApiService()
                    )
            );
        }
        return repository;
    }
}
