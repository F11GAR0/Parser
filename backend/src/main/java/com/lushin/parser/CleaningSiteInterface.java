package com.lushin.parser;

import java.util.List;

public interface CleaningSiteInterface {
    String getUrl();
    void setUrl(String url);
    List<Service> getServices();
    void setServices(List<Service> services);
    List<Service> parseContent();
} 