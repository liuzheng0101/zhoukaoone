package com.example.day3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.day3.adapter.MyAdapter;
import com.example.day3.bean.UserInfo;
import com.example.day3.contract.UserContract;
import com.example.day3.presenter.UserPresenter;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserContract.IUserView {
    private RecyclerView rv;
    private UserPresenter presenter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        initData();
    }
    private void initData() {
        presenter=new UserPresenter(this);
        HashMap<String,String> params=new HashMap<>();
        presenter.getLists(params);
    }
    @Override
    public void success(List<UserInfo.Databean> list) {
        adapter=new MyAdapter(this,list);
        rv.setAdapter(adapter);
    }

    @Override
    public void failure(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
