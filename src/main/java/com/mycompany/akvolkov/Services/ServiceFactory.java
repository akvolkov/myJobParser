package com.mycompany.akvolkov.Services;

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
     * @return Service
     */
    public Service getService() {
        Service service = null;
        //System.out.println("getService() - " + getUrl());
        if (getUrl().contains("rabota.yandex.ru")) {
            //System.out.println("1");
            service = new ServiceYaRab(getUrl());
        }
        return service;
    }
}
