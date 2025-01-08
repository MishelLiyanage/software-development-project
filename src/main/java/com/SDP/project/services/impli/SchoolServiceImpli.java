package com.SDP.project.services.impli;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.School;
import com.SDP.project.services.SchoolService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchoolServiceImpli implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    private AccountRepository accountRepository;


    public String saveSchool(SchoolDto schoolDto) {
        try{
            log.info("In the SchoolServiceImpli saveSchool");

            validateSchool(schoolDto);

            String username = schoolDto.getUsername();
            String password = schoolDto.getPassword();
            String role = "School";

            Account account = new Account(username, password, role);

            Account savedAccount = accountRepository.save(account);

            String name = schoolDto.getName();
            String address = schoolDto.getAddress();
            String contact_no = schoolDto.getContact_no();
            String email = schoolDto.getEmail();
            String principle_name = schoolDto.getPrinciple_name();
            String principle_signature = schoolDto.getPrinciple_signature();
            School school = new School(name, address, contact_no, email, principle_name, principle_signature);

            // Set the Account object instead of account ID
            school.setAccount(savedAccount);
            school.setId(savedAccount.getId());

            schoolRepository.save(school);

            log.info("Registered a school Successfully");
            return "Registered a school Successfully";

        } catch (RecordAlreadyExistException e) {
            log.error("RecordAlreadyExistException: {}", e.getMessage());
            throw e;
        } catch  (Exception e) {
            log.error("An error occurred while saving school: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while saving the school. Please try again later.");
        }
    }

    public void validateSchool(SchoolDto schoolDto) throws RecordAlreadyExistException {
        if (accountRepository.findByUsernameIgnoreCase(schoolDto.getUsername()).isPresent()) {
            log.warn("Schhol with username '{}' already exists", schoolDto.getUsername());
            throw new RecordAlreadyExistException("An school with the username '" + schoolDto.getUsername() + "' already exists.");
        }

        if (schoolRepository.findByEmail(schoolDto.getEmail()).isPresent()) {
            log.warn("School with email '{}' already exists", schoolDto.getEmail());
            throw new RecordAlreadyExistException("A school with the email '" + schoolDto.getEmail() + "' already exists.");
        }
    }
}
