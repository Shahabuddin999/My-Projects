package com.zensar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zensar.entity.Company;
import com.zensar.entity.Employees;
import com.zensar.repository.EmployeeRepo;

import java.util.Iterator;
import java.util.List;

@RestController
public class PopulateCompanyAndEmp {
    
    @Autowired
    private EmployeeRepo employeeRepo1;

    @GetMapping(value = "/all88")
    //@ResponseBody
    public String getUsersContact() {
    	ModelAndView model = new ModelAndView();
    	
    	model.setViewName("welcome");
        model.addObject("empdetails", "aaaa");
        System.out.println(model);
    	return "welcome";
    }
    
    
    @GetMapping(value = "/all2")
    @ResponseBody
    public List<Employees> getUsersContactDetail() {       		
        List<Employees> list = (List<Employees>) employeeRepo1.findAll();
        Iterator<Employees> itr = list.iterator();
        while(itr.hasNext()) {
        	Employees emp = itr.next();
        	System.out.println("Name: "+emp.getEmpName()+",  Address: "+emp.getAddress()+",  EmpId: "+emp.getId());
        	Company c = emp.getCompany();
        	System.out.println();
        }
        return list;
    }
    
   
    @RequestMapping("/saveEmp")
    public String update() {
    	
        Employees emp = new Employees();
        Company company = new Company();
        company.setCompanyName("Designer");
        company.setCompanyOwner("Shahabuddin is owner");
        emp.setAddress("Banglor");
        emp.setEmpName("Deepak jha is emp");
        emp.setCompany(company);
        //company.setEmployee(emp);
        employeeRepo1.save(emp);
        return "Saved";
    }
}
