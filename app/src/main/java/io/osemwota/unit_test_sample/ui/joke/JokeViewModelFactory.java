package io.osemwota.unit_test_sample.ui.joke;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.osemwota.unit_test_sample.data.JokeAppRepository;

public class JokeViewModelFactory implements ViewModelProvider.Factory {

    private final JokeAppRepository repository;

    public JokeViewModelFactory(JokeAppRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new JokeViewModel(repository);
    }

    public static JokeViewModelFactory create(@NonNull JokeAppRepository repository) {
        return new JokeViewModelFactory(repository);
    }
}
