package com.vinnotech.portal.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.service.EmployeeService;

@RestController
@CrossOrigin()
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getEmployees() {
        return "Welcome!";
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return empService.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee emp) {
        return empService.saveEmployee(emp);
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee emp) {
        empService.saveEmployee(emp);
    }

    @DeleteMapping("/username")
    public void deleteEmployee(@PathVariable("username") String username) {
    }
    /*
	 * @GetMapping("/sortAcs/{field}") private List<Employee>
	 * getAllEmpWithSorting(@PathVariable String field) { return
	 * empService.getAllEmployeeswitSorting(field); }
	 * 
	 * @GetMapping("/sortDes/{field}") public List<Employee>
	 * getAllEmpWithSortingdesc(@PathVariable String field){ return
	 * empService.getAllEmployeeswitSortingDesc(field); }
	 */
    @GetMapping("/sortandpagedesc/{offset}/{pageSize}/{field}")
    private Page<Employee> getAllEmployeeswithSortAndPagiDesc(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        return empService.getAllEmployeeswithSortAndPagiDesc(offset, pageSize,field);
    }
    @GetMapping("/sortandpageasc/{offset}/{pageSize}/{field}")
    private Page<Employee> getAllEmployeeswithSortAndPagiASC(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        return empService.getAllEmployeeswithSortAndPagiASC(offset, pageSize,field);
    }
    @GetMapping("/spEmpDeldesc/{isEmpDeleted}/{offset}/{pageSize}/{field}")
	private Page<Employee> getAllJobswithSortAndPagiDesc(@PathVariable boolean isEmpDeleted,@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		return empService.getAllEmployeeswithSortAndPagiDelDesc(isEmpDeleted,offset, pageSize, field);
	}

	@GetMapping("/spEmpDelasc/{isEmpDeleted}/{offset}/{pageSize}/{field}")
	private Page<Employee> getAllJobswithSortAndPagiASC(@PathVariable boolean isEmpDeleted, @PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		return empService.getAllEmployeeswithSortAndPagiDelASC(isEmpDeleted, offset, pageSize, field);
	}
}