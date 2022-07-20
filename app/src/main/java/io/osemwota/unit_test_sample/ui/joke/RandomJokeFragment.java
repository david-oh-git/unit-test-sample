package io.osemwota.unit_test_sample.ui.joke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import io.osemwota.unit_test_sample.R;
import io.osemwota.unit_test_sample.ServiceLocator;
import io.osemwota.unit_test_sample.base.BaseEvent;
import io.osemwota.unit_test_sample.base.SnackBarEvent;
import io.osemwota.unit_test_sample.databinding.FragmentRandomJokeBinding;
import io.osemwota.unit_test_sample.util.ViewModelFactory;

public class RandomJokeFragment extends Fragment {

    private FragmentRandomJokeBinding viewBinding;
    private JokeViewModel viewModel;

    public RandomJokeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelFactory.getViewModel(
                this,
                JokeViewModel.class,
                JokeViewModelFactory.create(ServiceLocator.provideRepository())
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentRandomJokeBinding.inflate(inflater);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.state.observe(getViewLifecycleOwner(), this::onStateChange);
        viewModel.event.observe(getViewLifecycleOwner(), this::onEvent);
        viewBinding.jokebtn.setOnClickListener(view1 -> {
            viewModel.getRandomJoke();
        });
    }

    private void onEvent(BaseEvent eventWrapper) {
        if (eventWrapper instanceof SnackBarEvent) {
            String message = ((SnackBarEvent) eventWrapper).getEventIfNotHandled();
            if (message != null)
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
        }else if (eventWrapper instanceof ErrorEvent) {
            if ( ((ErrorEvent) eventWrapper).getEventIfNotHandled() != null )
                Snackbar.make(requireView(), R.string.default_remote_api_error, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void onStateChange(JokeState state) {
        viewBinding.punchline.setText(state.getPunchline());
        onViewStateChange(state.getViewState());
    }

    private void onViewStateChange(JokeViewState viewState) {
        switch (viewState) {
            case START:
                startState();
                break;
            case LOADED:
            case ERROR:
                loadedState();
                break;
            case LOADING:
                loadingState();
                break;
        }
    }

    private void loadingState() {
        viewBinding.cardView.setVisibility(View.INVISIBLE);
        viewBinding.punchline.setVisibility(View.INVISIBLE);
        viewBinding.progress.setVisibility(View.VISIBLE);
        viewBinding.jokebtn.setVisibility(View.INVISIBLE);
    }

    private void startState() {
        viewBinding.cardView.setVisibility(View.INVISIBLE);
        viewBinding.punchline.setVisibility(View.INVISIBLE);
        viewBinding.progress.setVisibility(View.INVISIBLE);
        viewBinding.jokebtn.setVisibility(View.VISIBLE);
    }

    private void loadedState() {
        viewBinding.cardView.setVisibility(View.VISIBLE);
        viewBinding.punchline.setVisibility(View.VISIBLE);
        viewBinding.progress.setVisibility(View.INVISIBLE);
        viewBinding.jokebtn.setVisibility(View.VISIBLE);
    }

}