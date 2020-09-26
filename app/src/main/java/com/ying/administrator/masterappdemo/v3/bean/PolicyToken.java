package com.ying.administrator.masterappdemo.v3.bean;

public class PolicyToken {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"accessid":"LTAI4G4kcRFjMF4xARFULpSD","policy":"eyJleHBpcmF0aW9uIjoiMjAyMC0wOS0yNFQwOToyNzo1Mi4zMDBaIiwiY29uZGl0aW9ucyI6W1siY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjAwMF0sWyJzdGFydHMtd2l0aCIsIiRrZXkiLCJ1c2VyLWRpci1wcmVmaXgvIl1dfQ==","signature":"MSBNW2M272QnY0K5LLu46+2TK+c=","dir":"user-dir-prefix/","host":"http://xigyubuckettest.oss-cn-hangzhou.aliyuncs.com","expire":"1600939672","callback":"eyJjYWxsYmFja1VybCI6Imh0dHA6Ly84OC44OC44OC44ODo4ODg4IiwiY2FsbGJhY2tCb2R5IjoiZmlsZW5hbWU9JHtvYmplY3R9JnNpemU9JHtzaXplfSZtaW1lVHlwZT0ke21pbWVUeXBlfSZoZWlnaHQ9JHtpbWFnZUluZm8uaGVpZ2h0fSZ3aWR0aD0ke2ltYWdlSW5mby53aWR0aH0iLCJjYWxsYmFja0JvZHlUeXBlIjoiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkIn0="}
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private DataBean Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * accessid : LTAI4G4kcRFjMF4xARFULpSD
         * policy : eyJleHBpcmF0aW9uIjoiMjAyMC0wOS0yNFQwOToyNzo1Mi4zMDBaIiwiY29uZGl0aW9ucyI6W1siY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjAwMF0sWyJzdGFydHMtd2l0aCIsIiRrZXkiLCJ1c2VyLWRpci1wcmVmaXgvIl1dfQ==
         * signature : MSBNW2M272QnY0K5LLu46+2TK+c=
         * dir : user-dir-prefix/
         * host : http://xigyubuckettest.oss-cn-hangzhou.aliyuncs.com
         * expire : 1600939672
         * callback : eyJjYWxsYmFja1VybCI6Imh0dHA6Ly84OC44OC44OC44ODo4ODg4IiwiY2FsbGJhY2tCb2R5IjoiZmlsZW5hbWU9JHtvYmplY3R9JnNpemU9JHtzaXplfSZtaW1lVHlwZT0ke21pbWVUeXBlfSZoZWlnaHQ9JHtpbWFnZUluZm8uaGVpZ2h0fSZ3aWR0aD0ke2ltYWdlSW5mby53aWR0aH0iLCJjYWxsYmFja0JvZHlUeXBlIjoiYXBwbGljYXRpb24veC13d3ctZm9ybS11cmxlbmNvZGVkIn0=
         */

        private String accessid;
        private String policy;
        private String signature;
        private String dir;
        private String host;
        private String expire;
        private String callback;
        private String fileName;

        public String getAccessid() {
            return accessid;
        }

        public void setAccessid(String accessid) {
            this.accessid = accessid;
        }

        public String getPolicy() {
            return policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getCallback() {
            return callback;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
