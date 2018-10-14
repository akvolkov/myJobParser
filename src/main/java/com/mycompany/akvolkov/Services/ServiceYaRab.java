package com.mycompany.akvolkov.Services;

import com.mycompany.akvolkov.domain.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceYaRab implements Service {
    private String url;

    public String getUrl() {
        return url;
    }

    public ServiceYaRab(String url) {
        this.url = url;
    }

    @Override
    public List<Job> getJobs() {
        String titleJobs = "";
        String salary = "";
        String company = "";
        String contact = "";
        String phone = "";
        String typeEmployment = "";
        String description = "";
        List<Job> jobs = new ArrayList<Job>();

        /**
         * создание doc из File("C:/myProject/myJobParser/abc.txt")
         */
        File htmlFile = new File("C:/myProject/myJobParser/abc.txt");
        Document doc = null;
        try {
            doc = Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * создание doc из url
         * <h2 class="why__title" style="">Почему так случилось?</h2>
         *

        Document doc = null;
        try {

            doc = Jsoup.connect(url).get();
            //System.out.println(doc.html());

        } catch (IOException e) {
            e.printStackTrace();
        }
         **/


        int count = 0;
        Elements elements = doc.getElementsByAttributeValue("class", "serp-vacancy stat i-bem stat_gate_yes stat_goal_yes stat_js_inited");
        for (Element element:elements
        ) {
            count++;
            Elements allElement = element.getAllElements();
            for (Element elem:allElement
            ) {
                //titleJobs
                if (elem.attr("class").equals("link stat__click") ||
                        elem.attr("class").equals("link link_upped_yes stat__click")) {
                    titleJobs = elem.text();
                    System.out.println(count + " " + elem.text());
                }
                //salary
                if (elem.attr("class").equals("serp-vacancy__salary")) {
                    salary = elem.text();
                    System.out.println(count + " " + elem.text());
                }

                //company
                //не все ищет
                if (elem.attr("class").equals("link link_nav_yes link_minor_yes i-bem")) {
                    company = elem.text();
                    System.out.println(count + " " + elem.text());
                }

                //contact
                //отсутствует

                //phone
                //отсутствует

                //typeEmployment
                if (elem.attr("class").equals("serp-vacancy__schedule")) {
                    typeEmployment = elem.text();
                    System.out.println(count + " " + elem.text());
                }

                //description
                if (elem.attr("class").equals("serp-vacancy__requirements")) {
                    description = elem.text();
                    System.out.println(count + " " + elem.text());
                }
            }
            Job job = new Job(titleJobs, salary, company, contact, phone, typeEmployment, description);
            jobs.add(job);
        }

        for (Job j :jobs
             ) {
            System.out.println(j);
        }
        return jobs;
    }
}
