package com.Upgrade.utility;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.Upgrade.pojo.LoansInReview;
import com.Upgrade.pojo.LoginSuccessResponse;
import com.Upgrade.utility.ExtentLogger;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ApiHelper {
	String url;
	String randomUUIDString;
	String jsonRequestsFolder;
	String headerkeyone = Helper.readInput.getProperty("headerKey1");
	String headerValueone = Helper.readInput.getProperty("headerValue1");
	String headerKeytwo = Helper.readInput.getProperty("headerKey2");

	/*
	 * This method returns JSON Object from the JSON file after reading. This method
	 * is used to send a request body in object form to http
	 * methods(get,post,put,delete,patch,optons)
	 */
	/**
	 * @param path is the path for Json file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONObject ReadJson(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		jsonRequestsFolder = System.getProperty("user.dir") + path;
		Object obj = parser.parse(new FileReader(jsonRequestsFolder));
		JSONObject jsonobject = (JSONObject) obj;
		return jsonobject;
	}

	// Setter for URL which is read from urlData JSON files
	public void setUrl(String key, String path) {
		try {
			JSONObject urlDataJSON = this.ReadJson(path);
			JSONObject urlJSON = (JSONObject) urlDataJSON.get("url");
			this.url = (String) urlJSON.get(key);
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception in setUrl " + e);
		}
	}

	// Getter for URL which gets the value stored in url class variable
	public String getUrl() {
		return this.url;
	}

	// This method returns multiple header values
	public Headers getHeader() {
		Header h1 = new Header(headerkeyone, headerValueone);
		Header h2 = new Header(headerKeytwo, generateUUID());
		List<Header> list = new ArrayList<Header>();
		list.add(h1);
		list.add(h2);

		Headers header = new Headers(list);
		return header;

	}

	// This method generates UUID
	public String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return randomUUIDString = uuid.toString();
	}

	/* This method Post the request to the API and asserts the status code to 200 */

	public void postJsonRequest(String path, String key, String UrlPath) {
		try {
			setUrl(key, UrlPath);
			JSONObject requestBody = this.ReadJson(path);
			given().headers(getHeader()).body(requestBody).contentType("application/json").when().post(getUrl()).then()
					.assertThat().statusCode(200);
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception in postJsonRequest " + e);
		}

	}

	/*
	 * This method Post the request to the API and asserts any staus code
	 * (1xx,2xx,3xx,4xx,5xx)
	 */
	public void postErrorMessageResponse(String path, String key, String UrlPath, int statusCode) {
		try {
			setUrl(key, UrlPath);
			JSONObject requestBody = this.ReadJson(path);
			given().headers(getHeader()).body(requestBody).contentType("application/json").when().post(getUrl()).then()
					.assertThat().statusCode(statusCode);
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception in postErrorMessageResponse " + e);
		}

	}

}
