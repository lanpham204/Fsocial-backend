package com.fsocial.services;

import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Report;
import com.fsocial.respositories.ReportRepository;
import com.fsocial.services.interfaces.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepostServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    @Override
    public Report create(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void delete(String id) {
        reportRepository.deleteById(id);
    }

    @Override
    public Report findByUserId(String id) throws DataNotFoundException {
        return reportRepository.findByUserId(id).orElseThrow(()->new DataNotFoundException("Cannot find report with user id: "+id));
    }
}
