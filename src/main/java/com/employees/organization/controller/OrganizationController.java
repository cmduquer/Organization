package com.employees.organization.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employees.organization.client.DepartmentClient;
import com.employees.organization.client.EmployeeClient;
import com.employees.organization.model.Organization;
import com.employees.organization.repository.OrganizarionCRUDRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrganizationController {

	
	@Autowired
	OrganizarionCRUDRepository repository;
	@Autowired
	DepartmentClient departmentClient;
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping("/")
	public Organization add(@RequestBody Organization organization) {
		log.info("Organization add: {}", organization);
		return repository.save(organization);
	}
	
	@GetMapping("/")
	public Iterable<Organization> findAll() {
		log.info("Organization find");
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") String id) {
		log.info("Organization find: id={}", id);
		return repository.findById(id).get();
	}

	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@PathVariable("id") String id) {
		log.info("Organization find: id={}", id);
		Optional<Organization> organization = repository.findById(id);
		if (organization.isPresent()) {
			Organization o = organization.get();
			o.setDepartments(departmentClient.findByOrganization(o.getId()));
			return o;
		} else {
			return null;
		}
	}
	
	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") String id) {
		log.info("Organization find: id={}", id);
		Optional<Organization> organization = repository.findById(id);
		if (organization.isPresent()) {
			Organization o = organization.get();
			o.setDepartments(departmentClient.findByOrganizationWithEmployees(o.getId()));
			return o;
		} else {
			return null;
		}
	}
	
	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") String id) {
		log.info("Organization find: id={}", id);
		Optional<Organization> organization = repository.findById(id);
		if (organization.isPresent()) {
			Organization o = organization.get();
			o.setEmployees(employeeClient.findByOrganization(o.getId()));
			return o;
		} else {
			return null;
		}
	}
	
}
