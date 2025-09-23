package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.mappers.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.mappers.EmployeeMapper;
import com.udacity.jdnd.course3.critter.user.services.CustomerService;
import com.udacity.jdnd.course3.critter.user.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public UserController(
            CustomerService customerService,
            CustomerMapper customerMapper,
            EmployeeService employeeService,
            EmployeeMapper employeeMapper
    ){
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        var customer = customerService.create(customerDTO);
        return customerMapper.dtoFromEntity(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        var customers = customerService.getAll();
        return customers.stream().map(
                customerMapper::dtoFromEntity).toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        var customer = customerService.getCustomerByPetId(petId);
        return customer.map(customerMapper::dtoFromEntity).orElse(null);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        var employee = employeeService.saveEmployee(employeeDTO);
        return employeeMapper.dtoFromEntity(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        var employee = employeeService.getEmployeeById(employeeId);
        return employee.map(employeeMapper::dtoFromEntity).orElse(null);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        var employees = employeeService.findForDateAndActivities(employeeDTO);

        return employees.stream().map(
                employeeMapper::dtoFromEntity).toList();
    }

}
