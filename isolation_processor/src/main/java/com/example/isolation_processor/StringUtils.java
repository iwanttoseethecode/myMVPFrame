package com.example.isolation_processor;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by luoling on 2019/9/20.
 * description:
 */
public class StringUtils {

    /**
     * 构建一个完整的url
     * @param url
     * @param paramsUrl
     * @return
     */
    public static String buildWholeUrl(String url,String paramsUrl){
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();

            String returnUrl = "";
            if (null == scheme && scheme.startsWith("http")){
                if (url.endsWith("/") || paramsUrl.startsWith("/")){
                    returnUrl = url + paramsUrl;
                }else{
                    returnUrl = url + "/" + paramsUrl;
                }
            }else{
                returnUrl = url + File.separator + paramsUrl;
            }
            return returnUrl;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
