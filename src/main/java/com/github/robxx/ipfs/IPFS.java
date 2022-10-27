package com.github.robxx.ipfs;

import java.io.IOException;

import com.github.robxx.ipfs.api.Cid;
import com.github.robxx.ipfs.api.Files;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IPFS {

	// req
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	final OkHttpClient client = new OkHttpClient();
	String url;

	// opt

	private IPFS(Builder builder) {
		this.url = builder.url;
	}

	public static class Builder {
		String url = "http://127.0.0.1:5001/api/v0/";
		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}
		public IPFS build() {
			return new IPFS(this);
		}
	}

	Response post(String command, String json) throws IOException {
		String url = this.url + command;
		RequestBody body = RequestBody.create(json, JSON);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			// TODO
			System.out.println(response);
			return response;
		}
	}	
	Response post(String command) throws IOException {
		return post(command, "");
	}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		IPFS ipfs = new IPFS.Builder().build();
		ipfs.post("commands");		

		/*
		 * Cid cid = new Cid.Builder().build(); ipfs.post(cid.bases());
		 * ipfs.post(cid.codecs()); ipfs.post(cid.hashes());
		 */
		
		//TODO
		Files chcid = new Files.Chcid().setHash("sha2-256").build();		
		ipfs.post(chcid.call());		
		
		ipfs.post(new Files.Cp().build("/thing", "/thing2").call());
			
		ipfs.post(new Files.Ls().setArg("/").build().call());		

	}
}
