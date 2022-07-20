package io.osemwota.unit_test_sample.ui.joke;

public class JokeState {

    private final JokeViewState viewState;
    private final String punchline;

    public JokeState(JokeViewState viewState, String punchline) {
        this.viewState = viewState;
        this.punchline = punchline;
    }

    public JokeViewState getViewState() {
        return viewState;
    }

    public String getPunchline() {
        return punchline;
    }

    public static JokeState create(JokeViewState viewState, String punchline) {
        return new JokeState(viewState,punchline);
    }
}
