package com.fsocial.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fsocial.models.Report;

import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, String> {
    Optional<Report> findByUserId(String userId);
}
