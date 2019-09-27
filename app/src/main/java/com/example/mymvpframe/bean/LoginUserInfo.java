package com.example.mymvpframe.bean;

/**
 * Created by Administrator on 2018/6/6 0006.
 */



public class LoginUserInfo {


    /**
     * status : 200
     * success : true
     * message : 操作成功
     * out : {"accessToken":"AccessToken402883855bec28e8015bec28e9de0001","clientKey":"clientKey402883855bec28e8015bec28e8d10000"}
     * duration : 接口调用总计789毫秒
     */

    private int status;
    private boolean success;
    private String message;
    private OutBean out;
    private String duration;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OutBean getOut() {
        return out;
    }

    public void setOut(OutBean out) {
        this.out = out;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static class OutBean {
        /**
         * accessToken : AccessToken402883855bec28e8015bec28e9de0001
         * clientKey : clientKey402883855bec28e8015bec28e8d10000
         */

        private String accessToken;
        private String clientKey;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getClientKey() {
            return clientKey;
        }

        public void setClientKey(String clientKey) {
            this.clientKey = clientKey;
        }
    }
}
