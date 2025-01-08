package com.SDP.project.services.impli;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.EmployeeRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.Employee;
import com.SDP.project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpli implements EmployeeService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public String saveEmployee(EmployeeDto employeeDto) {
        System.out.println("in save employee method");

        String username = employeeDto.getUsername();
        String password = employeeDto.getPassword();
        String role = employeeDto.getRole();
        Account account = new Account(username, password, role);

        Account savedAccount = accountRepository.save(account);

        int accountId = savedAccount.getId();

        String first_name = employeeDto.getFirst_name();
        String last_name = employeeDto.getLast_name();
        String address = employeeDto.getAddress();
        String contact_no = employeeDto.getContact_no();
        String status = employeeDto.getStatus();
        String profile_pic = employeeDto.getProfile_pic();
        Employee employee = new Employee(first_name, last_name, address, contact_no, status, profile_pic);
        employee.setAccount(savedAccount);
        employee.setId(accountId);

        employeeRepository.save(employee);

        return ("Registered a employee Successfully");
    }
}
