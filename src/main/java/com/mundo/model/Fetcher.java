package com.mundo.model;

/**
 * @author chubby
 * @date 2020/3/24
 */
public class Fetcher {

    private String website;

    private String startUrls;

    private String ipXpath;

    private String portXpath;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStartUrls() {
        return startUrls;
    }

    public void setStartUrls(String startUrls) {
        this.startUrls = startUrls;
    }

    public String getIpXpath() {
        return ipXpath;
    }

    public void setIpXpath(String ipXpath) {
        this.ipXpath = ipXpath;
    }

    public String getPortXpath() {
        return portXpath;
    }

    public void setPortXpath(String portXpath) {
        this.portXpath = portXpath;
    }

    @Override
    public String toString() {
        return "Fetcher{" +
                "website='" + website + '\'' +
                ", startUrls='" + startUrls + '\'' +
                ", ipXpath='" + ipXpath + '\'' +
                ", portXpath='" + portXpath + '\'' +
                '}';
    }
}
