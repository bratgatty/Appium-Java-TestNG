package com.sample.mobile.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author maheswaran.palanisamy
 *
 */
public class ServiceHandler {

	HttpResponse response = null;
	HttpGet request = null;
	HttpPost post = null;
	HttpClient client = null;
	String url = null;
	String accessKey = null;
	String domain = null;

	public String getDomain(String env) {
		if (env.equalsIgnoreCase("test")) {
			domain = "http://test.dom.com";
		} else if (env.equalsIgnoreCase("prod")) {
			domain = "http://prod.dom.com";
		}
		return domain;

	}

	public String getAccessToken(String env, String key, String username,
			String password) throws ParseException, IOException, JSONException {

		url = getDomain(env)
				+ "/groceryapi/restservice.aspx?COMMAND=TOKEN&partial=Y";

		client = HttpClientBuilder.create().build();
		post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("client_secret",
				"7BC2490MDJ73DMD7AB376"));
		urlParameters.add(new BasicNameValuePair("client_id",
				"MKS789SSKS9DB6A6B5"));
		urlParameters.add(new BasicNameValuePair("grant_type", "password"));
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("version", "2.0"));
		urlParameters.add(new BasicNameValuePair("IdentityToken", "Y"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		response = client.execute(post);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getString(key);

	}

	public String getResponse(String url, String key, String accessKey)
			throws ClientProtocolException, IOException, JSONException {
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getString(key);

	}

	public JSONArray getResponseArray(String url, String key, String accessKey)
			throws ClientProtocolException, IOException, JSONException {
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getJSONArray(key);

	}
}
