package io.osemwota.unit_test_sample.base;

public class BaseEvent<T> {

    private boolean isHandled = false;
    public T event;

    public BaseEvent(T event) {
        this.event = event;
    }

    public T getEventIfNotHandled() {
        if (isHandled) {
            return null;
        }else {
            isHandled = true;
            return event;
        }
    }
}
