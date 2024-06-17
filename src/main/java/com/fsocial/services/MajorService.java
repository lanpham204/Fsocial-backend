package com.fsocial.services;

import com.fsocial.dtos.MajorDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Major;
import com.fsocial.respositories.MajorRepository;
import com.fsocial.services.interfaces.IMajorService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MajorService implements IMajorService {
    private final ModelMapper modelMapper;
    private final MajorRepository majorRepository;
    @Override
    public Major create(MajorDTO majorDTO) {
        return majorRepository.save(modelMapper.map(majorDTO,Major.class));
    }

    @Override
    public List<Major> getAll() {
        return majorRepository.findAll();
    }

    @Override
    public Major update(MajorDTO majorDTO, String id) throws DataNotFoundException {
        Major major = majorRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found major with id: " + id));
        Major map = modelMapper.map(majorDTO, Major.class);
        modelMapper.map(majorDTO,major);
        return majorRepository.save(major);
    }

    @Override
    public Major getById(String id) throws DataNotFoundException {
        return majorRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found major with id: " + id));
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Major major = majorRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found major with id: " + id));
        majorRepository.delete(major);
    }
}
