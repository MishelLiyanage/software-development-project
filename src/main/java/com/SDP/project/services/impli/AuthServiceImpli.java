package com.SDP.project.services.impli;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.DTOs.response.EmployeeResponseDto;
import com.SDP.project.DTOs.response.SchoolResponseDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.Repository.EmployeeRepository;
import com.SDP.project.Repository.SchoolRepository;
import com.SDP.project.models.Account;
import com.SDP.project.models.Employee;
import com.SDP.project.models.School;
import com.SDP.project.services.AuthService;
import com.SDP.project.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpli implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthServiceImpli( AccountRepository accountRepository, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
    }

    public Account authenticate(LoginUserDto input) {
        System.out.println(input.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return accountRepository.findByUsernameIgnoreCase(input.getUsername())
                .orElseThrow();
    }

    @Override
    public Optional<?> getCurrentUser() {
        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new NotFoundException("No authenticated user found."); // No authenticated user
        }

        // Get the username of the current user
        String username = authentication.getName();
        Account account = accountRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Check if the user is an Employee
        Optional<Employee> employee = employeeRepository.findByAccountId(account.getId());
        if (employee.isPresent()) {
            Employee emp = employee.get();
            // Return the EmployeeResponseDto
            return Optional.of(new EmployeeResponseDto(
                    account.getId(),
                    account.getUsername(),
                    account.getRole(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getAddress(),
                    emp.getContactNo(),
                    emp.getStatus(),
                    emp.getProfilePic()
            ));
        }

        // Check if the user is a School
        Optional<School> school = schoolRepository.findByAccountId(account.getId());
        if (school.isPresent()) {
            School sch = school.get();
            // Return the SchoolResponseDto
            return Optional.of(new SchoolResponseDto(
                    account.getId(),
                    account.getUsername(),
                    account.getRole(),
                    sch.getName(),
                    sch.getAddress(),
                    sch.getContactNo(),
                    sch.getEmail(),
                    sch.getPrincipleName(),
                    sch.getPrincipleSignature()
            ));
        }

        // If no match is found, return an empty Optional
        throw new NotFoundException("No matching user found for the current authentication. Please ensure your credentials are correct and try again.");
    }
}
