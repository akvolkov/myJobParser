package com.mycompany.akvolkov;

import com.mycompany.akvolkov.Services.ServiceFactory;
import com.mycompany.akvolkov.domain.Job;
import com.mycompany.akvolkov.repos.JobsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MyController {
    @Autowired
    private JobsRepo jobsRepo;

    private Integer numberPageForLoadJobs = 0;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "greeting";
    }

    //фильтр по имени
    @PostMapping("filter")
    public String filter (
            @RequestParam String filter, Model model
    ) {
        if (filter.equals("")) {
            Pageable pageable = PageRequest.of(numberPageForLoadJobs, 10);
            Page<Job> page = jobsRepo.findAll(pageable);
            model.addAttribute("listJobs", page);
            pagesMethod(model);
        }
        else {
            List<Job> byTitleJobs = jobsRepo.findByTitleJobs(filter);
            model.addAttribute("listJobs", byTitleJobs);
        }

        return "main.html";
    }

    @GetMapping("pagination")
    public String pagination (
            @RequestParam Integer numberPage, Model model
    ) {
        System.out.println(numberPage);
        Pageable pageable = PageRequest.of(numberPage - 1, 10);
        Page<Job> page = jobsRepo.findAll(pageable);
        model.addAttribute("listJobs", page); //
        numberPageForLoadJobs = numberPage;
        pagesMethod(model);
        return "main.html";
    }

    public void pagesMethod(Model model) {
        Integer numberPage = ((int)jobsRepo.count()-1) / 10 + 1;

        Integer[] pages = new Integer[(numberPage)];
        for (int i = 0; i < numberPage; i ++) {
            pages[i] = i + 1;
        }
        model.addAttribute("pages", pages);
    }

    @GetMapping
    public String main (Model model) {
        Pageable pageable = PageRequest.of(numberPageForLoadJobs, 10);
        Page<Job> page = jobsRepo.findAll(pageable);
        model.addAttribute("listJobs", page);
        pagesMethod(model);
        return "main";
    }

    @GetMapping("/description")
    public String description(
            @RequestParam Integer id, Model model
    ) {

        Optional<Job> byId = jobsRepo.findById(id);
        model.addAttribute("description", byId.get().getDescription());
        return "description";
    }




    @PostMapping("loadjobs")
    public String loadjobs (
            @RequestParam String url, Model model
    ) {
        //https://rabota.yandex.ru/search?job_industry=275&page_num=3
        final int COUNT_JOBS = 50;
        int count = COUNT_JOBS / 15;
        int ost = COUNT_JOBS % 15;
        List<Job> jobs = new ArrayList<>();
        String urlWithPage = url;
        for (int i = 1; i <= count; i++) {
            urlWithPage = url + "&page_num=" + i;
            try {
                jobs.addAll(new ServiceFactory(urlWithPage).getService().getJobs());
            }
            catch (NullPointerException e) {
                System.out.println("url для парсинга не соответствует шаблону (https://rabota.yandex.ru/)");
                System.out.println(e.toString());
            }

        }
        urlWithPage = url + "&page_num=" + (count + 1);
        List<Job> listForOst = new ServiceFactory(urlWithPage).getService().getJobs();
        for (int i = 1; i <= ost; i++) {
            try {
                jobs.add(listForOst.get(i));
            }
            catch (Exception e) {
                System.out.println("blocked Yandex");
                System.out.println(e.toString());
            }

        }
        for (Job job:jobs
             ) {
            jobsRepo.save(job);
        }


        Pageable pageable = PageRequest.of(numberPageForLoadJobs, 10);
        Page<Job> page = jobsRepo.findAll(pageable);
        model.addAttribute("listJobs", page);
        pagesMethod(model);
        return "main";
    }
}
