package com.example.eventbus.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable implements  IObservable {
    List<IObserver> list=new ArrayList<>();
    @Override
    public void registerObserver(IObserver iObserver) {
        if (iObserver!=null){
            if (!list.contains(iObserver))
                list.add(iObserver);
        }
    }

    @Override
    public void removeObserver(IObserver iObserver) {
        if (iObserver!=null){
            list.remove(iObserver);
        }
    }
    @Override
    public void notifyMsg() {
        if (list!=null&&list.size()>0){
            for (IObserver iObserver : list) {
                iObserver.receiveMsg("我是更新的消息");
            }
        }
    }
}
