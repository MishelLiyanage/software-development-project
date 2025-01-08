package com.SDP.project.services.impli;

import com.SDP.project.DTOs.SchoolDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.School;
import com.SDP.project.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpli implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    private AccountRepository accountRepository;


    public String saveSchool(SchoolDto schoolDto) {
        System.out.println("in save school method");

        String username = schoolDto.getUsername();
        String password = schoolDto.getPassword();
        String role = schoolDto.getRole();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

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

        return "Registered a school Successfully";
    }

}
