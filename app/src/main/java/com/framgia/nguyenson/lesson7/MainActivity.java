package com.framgia.nguyenson.lesson7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoadJson.CallBack,SearchView.OnQueryTextListener{
    private RecyclerView mRecyclerView;
    private ReposAdapter mReposAdapter;
    private LoadJson mLoadJson;
    private SearchView mSearchView;
    private ArrayList<Repos> mRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mLoadJson = new LoadJson();
        mLoadJson.onListener(this);
        mLoadJson.execute("https://api.github.com/users/google/repos");
    }

    @Override
    public void callBack(ArrayList<Repos> list) {
        mRepos = list;
        mReposAdapter = new ReposAdapter(list, this);
        mRecyclerView.setAdapter(mReposAdapter);
    }

    @Override
    public void callBackError(String message) {
        System.out.println("Error");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // thêm search vào vào action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_view);
        mSearchView = (SearchView) itemSearch.getActionView();
        //set OnQueryTextListener cho search view để thực hiện search theo text
        mSearchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Repos> list = new ArrayList<>();
         if(!newText.isEmpty()){
             for(Repos i : mRepos){
                 String change = String.valueOf(i.getId());
                 if(change.contains(newText)) list.add(i);
             }
             mReposAdapter = new ReposAdapter(list, this);
         }
         else {
             mReposAdapter = new ReposAdapter(mRepos, this);
         }
        mRecyclerView.setAdapter(mReposAdapter);
         return true;
    }


}
