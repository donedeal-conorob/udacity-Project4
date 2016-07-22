package com.udacity.gradle.jokeApp;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import conor.obrien.jokebackend.jokeApi.JokeApi;


/**
 * A class to handle the asynchronous task to getJokes from the Api.
 *
 * @author Conor O'Brien <conorob@donedeal.ie>
 */
public class EndpointsAsyncTask extends AsyncTask <Context, Void, String> {
    private static JokeApi myApiService = null;
    private String mResult;
    private GetJokeListener mListener = null;
    private Exception mError = null;

    public EndpointsAsyncTask setListener(GetJokeListener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.API.apiUrl);

            myApiService = builder.build();
        }

        try {
            mResult = myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            mError =  e;
        }
        return mResult;
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.mListener != null)
            this.mListener.onComplete(result, mError);
    }

    public interface GetJokeListener {
        void onComplete(String joke, Exception e);
    }
}