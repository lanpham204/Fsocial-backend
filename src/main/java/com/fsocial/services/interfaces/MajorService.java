package com.fsocial.services.interfaces;

import com.fsocial.dtos.MajorDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Major;

import java.util.List;

public interface MajorService {
  Major create(MajorDTO majorDTO);

  List<Major> getAll();

  Major update(MajorDTO majorDTO, String id) throws DataNotFoundException;

  Major getById(String id) throws DataNotFoundException;

  void delete(String id) throws DataNotFoundException;
}
