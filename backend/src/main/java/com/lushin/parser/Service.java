package com.lushin.parser;


public class Service {

    private Integer id;
    private String serviceInfo;
    private String price;

    public Service() {}

    public Service(Integer id, String serviceInfo, String price) {
        this.id = id;
        this.serviceInfo = serviceInfo;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}