package io.osemwota.unit_test_sample.data.remote.responses;

import java.util.List;

public class JokesResponse {
	private final List<JokesResponseItem> jokesResponse;

	public JokesResponse(List<JokesResponseItem> jokesResponse) {
		this.jokesResponse = jokesResponse;
	}

	public List<JokesResponseItem> getJokesResponse(){
		return jokesResponse;
	}
}