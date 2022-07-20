package io.osemwota.unit_test_sample.ui.joke;

import static io.osemwota.unit_test_sample.ui.joke.JokeViewState.LOADED;
import static io.osemwota.unit_test_sample.ui.joke.JokeViewState.LOADING;
import static io.osemwota.unit_test_sample.ui.joke.JokeViewState.START;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.osemwota.unit_test_sample.base.BaseEvent;
import io.osemwota.unit_test_sample.base.SnackBarEvent;
import io.osemwota.unit_test_sample.data.JokeAppRepository;
import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeViewModel extends ViewModel {

    private final JokeAppRepository repository;

    private final MutableLiveData<JokeState> _state = new MutableLiveData<>(
            new JokeState(START, null)
    );
    LiveData<JokeState> state = _state;

    private final MutableLiveData<BaseEvent> _event = new MutableLiveData<>();
    LiveData<BaseEvent> event = _event;

    public JokeViewModel(JokeAppRepository repository) {
        this.repository = repository;
    }

    public void getRandomJoke() {
        _state.setValue(JokeState.create(LOADING, null));

        repository.getRandomChuckNorrisJoke()
                .enqueue(
                        new Callback<>() {
                            @Override
                            public void onResponse(@NonNull Call<ChuckNorrisJokeItem> call, @NonNull Response<ChuckNorrisJokeItem> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    _state.postValue(JokeState.create(LOADED, response.body().getValue()));
                                } else {
                                    _state.postValue(JokeState.create(START, null));
                                    ErrorEvent event = new ErrorEvent("");
                                    _event.postValue(event);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<ChuckNorrisJokeItem> call, @NonNull Throwable t) {
                                _state.postValue(JokeState.create(START, null));
                                SnackBarEvent event = new SnackBarEvent(t.getMessage());
                                _event.postValue(event);
                            }
                        }
                );
    }

}
