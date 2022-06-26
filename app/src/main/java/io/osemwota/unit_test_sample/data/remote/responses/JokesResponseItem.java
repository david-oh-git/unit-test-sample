package io.osemwota.unit_test_sample.data.remote.responses;

public class JokesResponseItem {

	private final String punchline;
	private final String setup;
	private final int id;
	private final String type;

	public JokesResponseItem(String punchline, String setup, int id, String type) {
		this.punchline = punchline;
		this.setup = setup;
		this.id = id;
		this.type = type;
	}

	public String getPunchline(){
		return punchline;
	}

	public String getSetup(){
		return setup;
	}

	public int getId(){
		return id;
	}

	public String getType(){
		return type;
	}
}
