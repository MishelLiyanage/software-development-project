package com.SDP.project.services.impli;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.School;
import com.SDP.project.services.SchoolService;
import com.SDP.project.shared.ApplicationConstants;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SchoolServiceImpli implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String saveSchool(SchoolDto schoolDto) {
        try{
            log.info("In the SchoolServiceImpli saveSchool");

            validateSchool(schoolDto);

            String username = schoolDto.getUsername();
            String password = passwordEncoder.encode(schoolDto.getPassword());
            String role = ApplicationConstants.ROLES.ROLE_SCHOOL.name();

            Account account = new Account(username, password, role);

            Account savedAccount = accountRepository.save(account);

            String name = schoolDto.getName();
            String address = schoolDto.getAddress();
            String contact_no = schoolDto.getContactNo();
            String email = schoolDto.getEmail();
            String principle_name = schoolDto.getPrincipleName();
            String principle_signature = schoolDto.getPrincipleSignature();
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

    @Transactional
    public ResponseEntity<Map<String, String>> updateSchoolProfile(SchoolDto schoolDto) {
        try {
            log.info("In the SchoolServiceImpli updateSchoolProfile");

            Optional<Object> optionalSchool = schoolRepository.findByEmail(schoolDto.getEmail());
            if (optionalSchool.isEmpty()) {
                throw new RuntimeException("School not found with ID: " + schoolDto.getEmail());
            }

            School school = (School) optionalSchool.get();

            // Update school details
            school.setName(schoolDto.getName());
            school.setAddress(schoolDto.getAddress());
            school.setContactNo(schoolDto.getContactNo());
            school.setEmail(schoolDto.getEmail());
            school.setPrincipleName(schoolDto.getPrincipleName());
            school.setPrincipleSignature(schoolDto.getPrincipleSignature());

            // Update account details (username, password, role)
            Account account = school.getAccount();
            if (account == null) {
                throw new RuntimeException("Account not found for school ID: " + school.getId());
            }

            account.setUsername(schoolDto.getUsername());

            if (schoolDto.getPassword() != null && !schoolDto.getPassword().isBlank()) {
                account.setPassword(passwordEncoder.encode(schoolDto.getPassword()));
            }

            accountRepository.save(account);
            schoolRepository.save(school);

            log.info("Updated school profile successfully");
            Map<String, String> response = new HashMap<>();
            response.put("message", "Updated school profile successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("An error occurred while updating school: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while updating the school. Please try again later.");
        }
    }

}
