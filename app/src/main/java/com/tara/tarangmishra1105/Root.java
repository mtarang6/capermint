package com.tara.tarangmishra1105;

import java.util.List;

public class Root {


    public int status;

    public Root(int status){
     this.status = status;
    }
    public List<Datum> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
