package io.kimmking.rpcfx.api;

public class InterfaceDes {

    private String url;

    private String serviceClass;

    String version;

    String group;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public InterfaceDes(String url,String serviceClass, String version, String group) {
        this.url = url;
        this.serviceClass = serviceClass;
        this.version = version;
        this.group = group;
    }
}
