package com.mycompany.akvolkov.repos;

import com.mycompany.akvolkov.domain.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobsRepo extends CrudRepository<Job, Integer> {
    //List<Job>findByName(String name);
}
