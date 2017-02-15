package com.revature.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("/home")
public class LoginController {
	User user = new User();
	Employee employee = new Employee();
	UserService userService = new UserService();

	@PostMapping("/register")
	public String register(@RequestBody User user) throws ServiceException {
		try {
			userService.registerForUser(user);
			return "success";
		} catch (ServiceException e) {
			return "failure";
		}
	}

	@PostMapping("/userlogin")
	public String userLogin(@RequestBody User user) throws ServiceException {
		try {
			userService.loginForUser(user.getEmailId(), user.getPassword());
			return "success";
		} catch (ServiceException e) {

			return "failure";
		}

	}

	@PostMapping("/employeelogin")
	public String employeeLogin(@RequestBody Employee employee) throws ServiceException {
		try {
			userService.loginForEmployee(employee.getEmailId(), employee.getPassword());
			return "success";
		} catch (ServiceException e) {
			return "failure";
		}
	}
}