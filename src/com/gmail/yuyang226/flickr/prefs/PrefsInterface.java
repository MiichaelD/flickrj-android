/*
 * Copyright (c) 2007 Martin Goebel
 */
package com.gmail.yuyang226.flickr.prefs;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.gmail.yuyang226.flickr.FlickrException;
import com.gmail.yuyang226.flickr.Parameter;
import com.gmail.yuyang226.flickr.Response;
import com.gmail.yuyang226.flickr.Transport;
import com.gmail.yuyang226.flickr.oauth.OAuthUtils;
import com.gmail.yuyang226.flickr.org.json.JSONException;
import com.gmail.yuyang226.flickr.org.json.JSONObject;

/**
 * Requesting preferences for the current authenticated user.
 *
 * @author Martin Goebel
 * @version $Id: PrefsInterface.java,v 1.6 2008/06/28 22:30:04 x-mago Exp $
 */
public class PrefsInterface {
    public static final String METHOD_GET_CONTENT_TYPE = "flickr.prefs.getContentType";
    public static final String METHOD_GET_HIDDEN = "flickr.prefs.getHidden";
    public static final String METHOD_GET_SAFETY_LEVEL = "flickr.prefs.getSafetyLevel";
    public static final String METHOD_GET_PRIVACY = "flickr.prefs.getPrivacy";
    public static final String METHOD_GET_GEO_PERMS = "flickr.prefs.getGeoPerms";

    private String apiKey;
    private String sharedSecret;
    private Transport transportAPI;

    /**
     * Construct a PrefsInterface.
     *
     * @param apiKey The API key
     * @param transportAPI The Transport interface
     */
    public PrefsInterface(
        String apiKey,
        String sharedSecret,
        Transport transportAPI
    ) {
        this.apiKey = apiKey;
        this.sharedSecret = sharedSecret;
        this.transportAPI = transportAPI;
    }

    /**
     * Returns the default content type preference for the user.
     *
     * @see com.gmail.yuyang226.flickr.Flickr#CONTENTTYPE_OTHER
     * @see com.gmail.yuyang226.flickr.Flickr#CONTENTTYPE_PHOTO
     * @see com.gmail.yuyang226.flickr.Flickr#CONTENTTYPE_SCREENSHOT
     * @return The content-type
     * @throws IOException
     * @throws FlickrException
     * @throws JSONException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public String getContentType() throws IOException, FlickrException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_GET_CONTENT_TYPE));
//        parameters.add(new Parameter("api_key", apiKey));
        OAuthUtils.addOAuthToken(parameters);

        Response response = transportAPI.postJSON(apiKey, sharedSecret, parameters);
        if (response.isError()) {
            throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
        }

        JSONObject personElement = response.getData().getJSONObject("person");
        return personElement.getString("content_type");
    }

    /**
     * Returns the default privacy level for geographic information attached to the user's photos.
     *
     * @return privacy-level
     * @throws IOException
     * @throws FlickrException
     * @throws JSONException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_NO_FILTER
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_PUBLIC
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FRIENDS
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FAMILY
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FRIENDS_FAMILY
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_PRIVATE
     */
    public int getGeoPerms() throws IOException, FlickrException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_GET_GEO_PERMS));
//        parameters.add(new Parameter("api_key", apiKey));
        OAuthUtils.addOAuthToken(parameters);

        Response response = transportAPI.postJSON(apiKey, sharedSecret, parameters);
        if (response.isError()) {
            throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
        }

        JSONObject personElement = response.getData().getJSONObject("person");
        return personElement.getInt("geoperms");
    }

    /**
     * Returns the default hidden preference for the user.
     *
     * @return boolean hidden or not
     * @throws IOException
     * @throws FlickrException
     * @throws JSONException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public boolean getHidden() throws IOException, FlickrException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_GET_HIDDEN));
//        parameters.add(new Parameter("api_key", apiKey));
        OAuthUtils.addOAuthToken(parameters);

        Response response = transportAPI.postJSON(apiKey, sharedSecret, parameters);
        if (response.isError()) {
            throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
        }

        JSONObject personElement = response.getData().getJSONObject("person");
        return "1".equals(personElement.getString("hidden"));
    }

    /**
     * Returns the default safety level preference for the user.
     *
     * @see com.gmail.yuyang226.flickr.Flickr#SAFETYLEVEL_MODERATE
     * @see com.gmail.yuyang226.flickr.Flickr#SAFETYLEVEL_RESTRICTED
     * @see com.gmail.yuyang226.flickr.Flickr#SAFETYLEVEL_SAFE
     * @return The current users safety-level
     * @throws IOException
     * @throws FlickrException
     * @throws JSONException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public String getSafetyLevel() throws IOException, FlickrException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_GET_SAFETY_LEVEL));
//        parameters.add(new Parameter("api_key", apiKey));
        OAuthUtils.addOAuthToken(parameters);

        Response response = transportAPI.postJSON(apiKey, sharedSecret, parameters);
        if (response.isError()) {
            throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
        }

        JSONObject personElement = response.getData().getJSONObject("person");
        return personElement.getString("safety_level");
    }

    /**
     * Returns the default privacy level preference for the user.
     *
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_NO_FILTER
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_PUBLIC
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FRIENDS
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FRIENDS_FAMILY
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FAMILY
     * @see com.gmail.yuyang226.flickr.Flickr#PRIVACY_LEVEL_FRIENDS
     * @throws IOException
     * @throws FlickrException
     * @return privacyLevel
     * @throws JSONException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public int getPrivacy() throws IOException, FlickrException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_GET_PRIVACY));
//        parameters.add(new Parameter("api_key", apiKey));
        OAuthUtils.addOAuthToken(parameters);

        Response response = transportAPI.postJSON(apiKey, sharedSecret, parameters);
        if (response.isError()) {
            throw new FlickrException(response.getErrorCode(), response.getErrorMessage());
        }

        JSONObject personElement = response.getData().getJSONObject("person");
        return personElement.getInt("privacy");
    }
}