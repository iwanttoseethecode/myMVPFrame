package com.example.isolation_processor.retrofitPg.Rxretrofit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by luoling on 2019/9/25.
 * description:
 */
public class RxRestClientBuilder {

    private Map<String,Object> PARAMS;
    private String URL;
    private RequestBody BODY;
    private File FILE;

    private String DOWNDIR_DIR;
    private String EXTENSION;
    private String FILENAME;

    public RxRestClientBuilder(){}

    public RxRestClientBuilder setParams(Map<String,Object> params){
        this.PARAMS = params;
        return this;
    }

    public RxRestClientBuilder setUrl(String url){
        this.URL = url;
        return this;
    }

    public RxRestClientBuilder setDir(String dir){
        this.DOWNDIR_DIR = dir;
        return this;
    }

    public RxRestClientBuilder setExtension(String extension){
        this.EXTENSION = extension;
        return this;
    }

    public RxRestClientBuilder setFileName(String fileName){
        this.FILENAME = fileName;
        return this;
    }

    public RxRestClientBuilder setFile(String path){
        FILE = new File(path);
        return this;
    }

    public RxRestClientBuilder setFile(File file){
        FILE = file;
        return this;
    }

    public RxRestClientBuilder raw(String raw){
        this.BODY = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public RxRestClient build(){
        return new RxRestClient(PARAMS,URL,BODY,FILE,DOWNDIR_DIR,EXTENSION,FILENAME);
    }

}
