package com.rishabh.roposo.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rishabh.roposo.R;
import com.rishabh.roposo.adapters.StoriesAdapter;
import com.rishabh.roposo.models.Story;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class StoriesFragment extends Fragment {

    private SuperRecyclerView recyclerView;

    public static StoriesFragment newInstance() {
        return new StoriesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (SuperRecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        Gson gson = new Gson();
        JsonReader reader = loadData();
        if(reader != null) {
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(reader).getAsJsonArray();

            Type listType = new TypeToken<List<Story>>(){}.getType();
            List<Story> stories = (List<Story>) gson.fromJson(jArray.toString(), listType);

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StoriesAdapter adapter = new StoriesAdapter(getActivity(),stories);

            recyclerView.setAdapter(adapter);
        }else {
            Log.d("rick","null reader");
        }
    }

    private JsonReader loadData() {
        JsonReader reader = null;
        try {
            InputStream inStream = getResources().openRawResource(R.raw.json_temp);

            BufferedInputStream bufferedStream = new BufferedInputStream(
                    inStream);
            InputStreamReader streamReader = new InputStreamReader(
                    bufferedStream);

            reader = new JsonReader(streamReader);

        } catch (Exception e) {
            Log.e("rick", e.getLocalizedMessage(), e);
        }
        return reader;
    }
}
