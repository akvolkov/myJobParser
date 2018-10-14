package com.mycompany.akvolkov.repos;

import com.mycompany.akvolkov.domain.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JobsRepo extends PagingAndSortingRepository<Job, Integer> {
    List<Job> findByTitleJobs(String titleJobs);
}
