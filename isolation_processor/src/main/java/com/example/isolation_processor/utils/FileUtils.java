package com.example.isolation_processor.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UnknownFormatConversionException;

/**
 * Created by luoling on 2019/9/24.
 * description:
 */
public class FileUtils {
    //格式化的模板
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";

    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();

    //默认本地上传图片目录
    public static final String UPLOAD_PHOTO_DIR = Environment.getExternalStorageDirectory().getPath()+ File.separator + "upload_photo" + File.separator;

    //网页缓存地址
    public static final String WEB_CACHE_DIR = Environment.getExternalStorageDirectory().getPath() + File.separator + "app_web_cache" + File.separator;

    //系统相机目录
    public static final String CAMERA_PHOTO_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + File.separator + "Camera" + File.separator;

    private static String getTimeFormatName(String timeFormatHeader){
        final Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'" +timeFormatHeader+ "'" + TIME_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * @param timeFormatHeader 格式化的头(除去时间部分)
     * @param extension        后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader,String extension){
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File createDir(String sdcardDirName) {
        //拼接成SD卡中完整的dir
        final String dir = SDCARD_DIR + File.separator + sdcardDirName + File.separator;
        final File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static File createFile(String sdcardDirName,String fileName){
        return new File(createDir(sdcardDirName),fileName);
    }

    public static File createFileByTime(String sdcardDirName,String timeFormatHandler,String extension){
        final String fileName = getFileNameByTime(timeFormatHandler,extension);
        return createFile(sdcardDirName,fileName);
    }

    //获取文件的后缀名
    public static String getExtension(String filePath){
        String suffix = "";
        File file = new File(filePath);
        if (!file.exists()){
            return null;
        }
        String name = file.getName();
        final int index = name.lastIndexOf('.');
        if (index < 0 || index >= name.length()-1){
            return null;
        }
        suffix = name.substring(index+1);
        return suffix;
    }


    //获取文件的MIME
    public static String getMimeType(String filePath){
        final String extension = getExtension(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    public static File saveBitmap(Bitmap bitmap,String fileNamePrefix,String extension,String dir,int compress){
        final String sdStatus = Environment.getExternalStorageState();
        //检测sd卡是否存在
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        if (TextUtils.isEmpty(fileNamePrefix)){
            fileNamePrefix = "DOWNLOAD";
        }
        if (TextUtils.isEmpty(extension)){
            extension = "jpg";
        }
        if (TextUtils.isEmpty(dir)){
            dir = UPLOAD_PHOTO_DIR;
        }
        File fileName = createFileByTime(dir,fileNamePrefix,extension);
        try {
            if (!fileName.exists()){
                fileName.createNewFile();
            }
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            bitmap.compress(matchBitmapExtension(extension),compress,bos);// 把数据写入文件
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (fos != null){
                    fos.flush();
                    fos.close();
                }
                if (bos != null){
                    bos.flush();
                    bos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        refreshDCIM();

        return fileName;
    }

    public static File writeToDisk(InputStream is,String dir,String name){
        final File file = createFile(dir,name);

        BufferedInputStream fis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{
            fis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length=fis.read(bytes))!=-1){
                bos.write(bytes,0,length);
            }

            bos.flush();
            fos.flush();

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (fis != null){
                    fis.close();
                }
                if (fos != null){
                    fos.close();
                }
                if (bos != null){
                    bos.close();
                }
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File writeToDiskByTime(InputStream is,String dir,String namePrefix,String extension){
        final File file = createFileByTime(dir,namePrefix,extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = bis.read(bytes))!=-1){
                bos.write(bytes,0,length);
            }
            bos.flush();
            fos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (bos != null){
                    bos.close();
                }
                if (fos != null){
                    fos.close();
                }
                if (bis != null){
                    bis.close();
                }
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 读取raw目录中的文件,并返回为字符串
     */
    public static String readRawFile(Context context,int id){
        InputStream is = context.getResources().openRawResource(id);

        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis);
            br = new BufferedReader(isr);
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (bis != null){
                    bis.close();
                }
                if (isr != null){
                    isr.close();
                }
                if (br != null){
                    br.close();
                }
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static void setIconFont(Context context,String path, TextView textView){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),path);
        textView.setTypeface(typeface);
    }

    /**
     * 读取assets目录下的文件,并返回字符串
     */
    public static String readAssetsFile(Context context,String name){
        InputStream is = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
            is = context.getAssets().open(name);
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis);
            br = new BufferedReader(isr);

            while((line = br.readLine())!=null){
                sb.append(line).append("\n");
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (is != null){
                    is.close();
                }
                if (bis != null){
                    bis.close();
                }
                if (isr != null){
                    isr.close();
                }
                if (br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * @return 返回文件真实的路径，如果是图片，就会去系统的contentprovider查找路径
     */
    public static String getRealFilePath(final Context context,final Uri uri){
        if (uri == null) return null;
        String scheme = uri.getScheme();
        String path = null;
        if (ContentResolver.SCHEME_FILE.equals(scheme)){
            path = uri.getPath();
        }else if(ContentResolver.SCHEME_CONTENT.equals(scheme)){
            Cursor cursor = context.getContentResolver().query(uri,new String[]{MediaStore.Images.ImageColumns.DATA},null,null,null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        path = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return path;
    }

    /**
     * 通知系统刷新系统相册，使照片展现出来
     */
    private static void refreshDCIM(){
        Context context = Configurator.getInstance().getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
        if (Build.VERSION.SDK_INT > 19){
            //兼容android4.4版本，只扫描存放照片的目录
            MediaScannerConnection.scanFile(context,
                    new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()},null,null);
        }else{
            //扫描整个SD卡来更新系统图库，当文件很多时用户体验不佳，且不适合4.4以上版本
            context.sendBroadcast(new Intent(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()))));
        }
    }

    private static Bitmap.CompressFormat matchBitmapExtension(String extension){

        Bitmap.CompressFormat compressFormat = null;

        switch (extension){
            case "jpg":
                compressFormat = Bitmap.CompressFormat.JPEG;
                break;
            case "png":
                compressFormat = Bitmap.CompressFormat.PNG;
                break;
            case "webp":
                compressFormat = Bitmap.CompressFormat.WEBP;
                break;
                default:
                    throw new UnknownFormatConversionException("不支持"+extension+"图片格式");
        }
        return compressFormat;

    }

}
