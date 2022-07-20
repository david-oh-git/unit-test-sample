package io.osemwota.unit_test_sample.data.remote.network.responses;


import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import io.osemwota.unit_test_sample.data.remote.responses.ChuckNorrisJokeItem;

public class ChuckNorrisJokeItemTest {

    @Test
    public void confirm_Properties() {
        final String iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png";
        final String id = "v5bjnMaRTk2dW3lCGAfkVQ";
        final String url = "http://chucknorris.net/23333";
        final String value = "Ok im back. Chuck Norris was secretly included for Mortal Kombat X. The developers then changed their minds and removed him because 1 hit from Chuck Norris= a fatality.";

        final ChuckNorrisJokeItem chuckNorrisJokeItem = new ChuckNorrisJokeItem(
                iconUrl,
                id,
                url,
                value
        );

        assertThat(chuckNorrisJokeItem.getIconUrl()).isEqualTo(iconUrl);
        assertThat(chuckNorrisJokeItem.getId()).isEqualTo(id);
        assertThat(chuckNorrisJokeItem.getUrl()).isEqualTo(url);
        assertThat(chuckNorrisJokeItem.getValue()).isEqualTo(value);
    }

}