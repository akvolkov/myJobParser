package com.mycompany.akvolkov.repos;

import com.mycompany.akvolkov.domain.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobsRepo extends PagingAndSortingRepository<Job, Integer> {

}
