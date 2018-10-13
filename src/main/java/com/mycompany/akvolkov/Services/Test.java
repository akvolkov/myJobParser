package com.mycompany.akvolkov.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String url = "https://rabota.yandex.ru/search?job_industry=275";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(document.html());

    }
}
