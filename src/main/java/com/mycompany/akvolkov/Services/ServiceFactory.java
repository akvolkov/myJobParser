package com.mycompany.akvolkov.Services;

import com.mycompany.akvolkov.MyController;

public class ServiceFactory {
    private String url;

    public ServiceFactory(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Можно добавить создание Servise для других сайтов с вакансиями     *
     *
     */
    public Service getService() {
        Service service = null;
        if (getUrl().contains("rabota.yandex.ru")) {
            service = new ServiceYaRab(getUrl());
        }
        return service;
    }
}
