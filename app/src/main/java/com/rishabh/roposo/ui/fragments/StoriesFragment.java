package com.rishabh.roposo.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
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

    private RecyclerView recyclerView;
    private StoriesAdapter adapter;
    private List<Story> stories;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(recyclerView,"Voila!",Snackbar.LENGTH_SHORT).show();
            }
        });


        Gson gson = new Gson();
        JsonReader reader = loadData();
        if(reader != null) {
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(reader).getAsJsonArray();

            Type listType = new TypeToken<List<Story>>(){}.getType();
            stories = (List<Story>) gson.fromJson(jArray.toString(), listType);

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            adapter = new StoriesAdapter(this,stories);

            recyclerView.setAdapter(adapter);
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

        } catch (Exception e) { }
        return reader;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode !=  Activity.RESULT_OK) return;

        String author = data.getStringExtra("author");
        Boolean isFollowing = data.getBooleanExtra("isFollowing",false);

        adapter.refreshSpecificAuthorOnly(author,isFollowing);
    }
}
