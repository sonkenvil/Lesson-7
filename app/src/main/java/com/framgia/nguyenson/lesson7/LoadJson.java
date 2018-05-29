package com.framgia.nguyenson.lesson7;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class LoadJson extends AsyncTask<String, Void, ArrayList<Repos>> {
    private CallBack mCallBack;

    @Override
    protected ArrayList<Repos> doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        try {
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.d("TAG",forecastJsonStr);
            return parseJson(forecastJsonStr);
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
    }

    public void onListener(CallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected void onPostExecute(ArrayList<Repos> repos) {
        super.onPostExecute(repos);
        if (mCallBack == null) return;
        if (repos == null) {
            mCallBack.callBackError("");
        } else {
            mCallBack.callBack(repos);
        }
    }

    public ArrayList<Repos> parseJson(String s) {
        ArrayList<Repos> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                Repos repos = new Repos();
                License license = new License();
                Owner owner = new Owner();
                JSONObject object = (JSONObject) jsonArray.get(i);
                repos.setId(object.getInt("id"));
                repos.setName(object.getString("name"));
                repos.setFullName(object.getString("full_name"));
                owner.setId(object.getJSONObject("owner").getInt("id"));
                owner.setLogin(object.getJSONObject("owner").getString("login"));
                owner.setAvatarURL(object.getJSONObject("owner").getString("avatar_url"));
                license.setKey(object.getJSONObject("license").getString("key"));
                license.setName(object.getJSONObject("license").getString("name"));
                license.setSpdxId(object.getJSONObject("license").getString("spdx_id"));
                license.setUrl(object.getJSONObject("license").getString("url"));
                repos.setLicense(license);
                repos.setOwner(owner);
                arrayList.add(repos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    interface CallBack {
        void callBack(ArrayList<Repos> list);

        void callBackError(String message);
    }

}
