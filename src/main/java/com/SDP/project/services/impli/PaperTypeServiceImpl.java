package com.SDP.project.services.impli;

import com.SDP.project.DTOs.PaperTypeDto;
import com.SDP.project.Repository.DepartmentRepository;
import com.SDP.project.Repository.PaperTypeRepository;
import com.SDP.project.models.Department;
import com.SDP.project.models.PaperTypes;
import com.SDP.project.services.PaperTypeService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaperTypeServiceImpl implements PaperTypeService {
    private static final Logger log = LoggerFactory.getLogger(PaperTypeServiceImpl.class);
    @Autowired
    PaperTypeRepository paperTypeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional
    public String savePaperType(PaperTypeDto paperTypeDto){
        isPaperTypeExists(paperTypeDto);

        String paperType = paperTypeDto.getPaperType();
        Department department = departmentRepository.getDepartmentByName(paperTypeDto.getDepartmentName());
//        log.info("Department " + department + " has been created");
        PaperTypes paperTypes = new PaperTypes(paperType);
        paperTypes.setDepartment(department);

        paperTypeRepository.save(paperTypes);
        return ("Saved a paper type successfully.");
    }

    private void isPaperTypeExists(PaperTypeDto paperTypeDto) {
        boolean paperTypeExists = paperTypeRepository.existsByPaperType(paperTypeDto.getPaperType());

        log.info("Paper type Exists: " + paperTypeExists); // Debugging log

        if (paperTypeExists) {
            throw new RecordAlreadyExistException("Model paper already exists.");
        }
    }
}
