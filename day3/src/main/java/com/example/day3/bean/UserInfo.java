package com.example.day3.bean;

import java.util.List;

public class UserInfo {
    public String msg;
    public int code;
    public List<Databean> data;
    public class Databean{
        public String bookname;
        public String book_cover;
        public String book_info;
    }
}
