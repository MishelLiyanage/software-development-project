package com.SDP.project.services.impli;

import com.SDP.project.DTOs.EmployeeDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.EmployeeRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.Employee;
import com.SDP.project.services.EmployeeService;
import com.SDP.project.shared.exceptions.RecordAlreadyExistException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpli implements EmployeeService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Transactional
    public String saveEmployee(EmployeeDto employeeDto) {
        try {
            log.info("in save employee method");

            validateEmployee(employeeDto);

            // Create and save account
            String username = employeeDto.getUsername();
            String password = passwordEncoder.encode(employeeDto.getPassword()); // Encode the password
            String role = "Employee";
            Account account = new Account(username, password, role);

            Account savedAccount = accountRepository.save(account);
            int accountId = savedAccount.getId();

            // Create and save employee
            String firstName = employeeDto.getFirstName();
            String lastName = employeeDto.getLastName();
            String address = employeeDto.getAddress();
            String contactNo = employeeDto.getContactNo();
            String status = "active";
            String profilePic = employeeDto.getProfilePic();
            Employee employee = new Employee(firstName, lastName, address, contactNo, status, profilePic);
            employee.setAccount(savedAccount);
            employee.setId(accountId);

            employeeRepository.save(employee);

            log.info("Successfully registered employee with username '{}'", username);
            return "Registered an employee successfully";

        } catch (RecordAlreadyExistException e) {
            log.error("RecordAlreadyExistException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while saving employee: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while saving the employee. Please try again later.");
        }
    }

    public void validateEmployee(EmployeeDto employeeDto) throws RecordAlreadyExistException {
        // Check for existing username
        if (accountRepository.findByUsernameIgnoreCase(employeeDto.getUsername()).isPresent()) {
            log.warn("Employee with username '{}' already exists", employeeDto.getUsername());
            throw new RecordAlreadyExistException("An employee with the username '" + employeeDto.getUsername() + "' already exists.");
        }
    }
}
