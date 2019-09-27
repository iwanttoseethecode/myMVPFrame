package com.example.isolation_processor.retrofitPg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IFailure;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IRequest;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.ISuccess;
import com.example.isolation_processor.utils.FileUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by luoling on 2019/9/24.
 * description:
 */
public class SaveFileTask extends AsyncTask<Object,Void, File> {

    IRequest REQUEST;
    ISuccess SUCCESS;

    public SaveFileTask(IRequest request,ISuccess success){
        this.REQUEST = request;
        this.SUCCESS = success;
    }


    @Override
    protected File doInBackground(Object... objects) {
        String downLoadDir = (String) objects[0];
        String extension = (String) objects[1];
        String name = (String) objects[2];
        ResponseBody body = (ResponseBody) objects[3];
        InputStream is = body.byteStream();
        if (TextUtils.isEmpty(downLoadDir)){
            downLoadDir = "downloads";
        }
        if (TextUtils.isEmpty(extension)){
            extension = "";
        }
        if (TextUtils.isEmpty(name)){
            return FileUtils.writeToDiskByTime(is,downLoadDir,"unName",extension);
        }else{
            return FileUtils.writeToDisk(is,downLoadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }

        //如果下载了apk文件，就直接安装
        autoInstallAPK(file);
    }

    private void autoInstallAPK(File file){
        if (FileUtils.getExtension(file.getPath()).equals("apk")){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Context context = Configurator.getInstance().getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
            context.startActivity(intent);
        }
    }

}
