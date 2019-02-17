package com.example.zhoukao1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhoukao1.R;
import com.example.zhoukao1.activity.Main2Activity;
import com.example.zhoukao1.activity.MainActivity;
import com.example.zhoukao1.adapter.HomeAdapter;
import com.example.zhoukao1.bean.UserInfo;
import com.example.zhoukao1.contract.UserContract;
import com.example.zhoukao1.presenter.UserPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Home_Fragment extends Fragment implements UserContract.IUserView,XRecyclerView.LoadingListener,HomeAdapter.onClickitem{
    private Unbinder bind;
    private XRecyclerView rv;
    private Button but_name;
    private EditText et_name;

    private int page=1;
    private String count="5";
    private UserPresenter presenter;
    private HomeAdapter adapter;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind=ButterKnife.bind(getActivity());
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        rv=view.findViewById(R.id.rv);
        but_name=view.findViewById(R.id.but_name);
        et_name=view.findViewById(R.id.et_name);
        initData();
        return view;
    }
    private void initData() {
        presenter=new UserPresenter(this);
        rv.setLoadingListener(this);
        rv.setLoadingMoreEnabled(true);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        but_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paserResult();
            }
        });
    }
    @Override
    public void Success(UserInfo userInfo) {
        if (userInfo!=null){
            //刷新
            if (page==1){
                rv.refreshComplete();
                adapter=new HomeAdapter(getActivity(),userInfo.result);
                rv.setAdapter(adapter);
            }else{//加载
                if (adapter==null) {
                    adapter=new HomeAdapter(getActivity(),userInfo.result);
                    rv.setAdapter(adapter);
                }else{
                    adapter.addData(userInfo.result);
                }
                rv.loadMoreComplete();
            }
        }
    }

    @Override
    public void Failure(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onRefresh() {
        page=1;
        paserResult();
    }

    @Override
    public void onLoadMore() {
        page++;
        paserResult();
    }
    private void paserResult(){
        String name=et_name.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("keyword",name);
        params.put("page",page+"");
        params.put("count",count);
        presenter.getShop(params);
    }

    @Override
    public void getId(String id) {
        Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();
        this.id=id;
        initShop(id);
    }

    private void initShop(String id) {

    }
}
