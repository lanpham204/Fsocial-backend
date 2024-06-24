package com.fsocial.services.interfaces;

import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Report;

import java.util.Optional;

public interface ReportService {
  Report create(Report report);

  void delete(String id);
  Report findByUserId(String id) throws DataNotFoundException;
}
