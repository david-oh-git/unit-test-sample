package io.osemwota.unit_test_sample.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ViewModelFactory {

    private ViewModelFactory(){}

    public static <T extends ViewModel> T getViewModel(@NonNull ViewModelStoreOwner owner, Class<T> viewModel) {
        return new ViewModelProvider(owner).get(viewModel);
    }

    public static <T extends ViewModel> T getViewModel(
            @NonNull ViewModelStoreOwner owner,
            Class<T> viewModel,
            ViewModelProvider.Factory factory
    ) {
        return new ViewModelProvider(owner, factory).get(viewModel);
    }
}
