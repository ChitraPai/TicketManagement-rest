package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	TicketService ticketService = new TicketService();
	User user = new User();
	Employee employee = new Employee();

		@PutMapping("/createtickets/{emailId}")
	public String ticketCreation(@PathVariable("emailId") String emailId,@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("departmentName") String departmentName, @RequestParam("priority") String priority)throws ServiceException{
				ticketService.ticketCreation(emailId, subject,description,departmentName, priority);
		return "Ticket created Successfully";
	}

		@PutMapping("/ticketupdation")
	public String ticketUpdation(@RequestBody TicketTransaction ticket)throws ServiceException {
		ticketService.ticketUpdation(ticket.getId(),ticket.getDescription());
		return "Ticket updated successfully";
	}

		@PutMapping("/closeTicket")
	public String closeTicket(@RequestBody TicketTransaction ticket) throws ServiceException {
		ticketService.closeTicket(ticket.getId());
		return "redirect:/index.jsp";
	}

	@GetMapping("/view/{emailId}")
	public List<TicketTransaction> viewTicket(@PathVariable("emailId") String emailId) throws ServiceException {
		System.out.println(emailId);
		return ticketService.viewTickets(emailId);
	}

		@PutMapping("/reassigntickets")
	public String reassignTicket(@RequestParam("ticketId") Integer ticketId,
			@RequestParam("employeeId") Integer employeeId) throws ServiceException {
		ticketService.ticketReassignment(ticketId, employeeId);
		return "success";
	}

    @PutMapping("/resolveticket")
	public String resolveTicket(@RequestParam("ticketId") Integer ticketId,
			@RequestParam("solution") String solution) throws ServiceException {
		ticketService.resolveTicket(ticketId, solution);
		return "success";
	}

	@GetMapping("/viewassignedtickets")
	public List<TicketTransaction> viewAssignedTickets(HttpSession session, ModelMap modelMap) throws ServiceException {
		employee = (Employee) session.getAttribute("LOGGED_IN_USER");
		return ticketService.viewAssignedTickets(employee.getEmailId());
	}

		@PutMapping("/deletetickets")
	public String deleteTicket(@RequestParam("ticketId") Integer ticketId)
			throws ServiceException {
		ticketService.deleteTicket(ticketId, employee.getEmailId());
		return "success";
	}
}
