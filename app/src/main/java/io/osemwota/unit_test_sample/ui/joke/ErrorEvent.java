package io.osemwota.unit_test_sample.ui.joke;

import io.osemwota.unit_test_sample.base.BaseEvent;

public class ErrorEvent extends BaseEvent<String> {

    public ErrorEvent(String event) {
        super(event);
    }
}
