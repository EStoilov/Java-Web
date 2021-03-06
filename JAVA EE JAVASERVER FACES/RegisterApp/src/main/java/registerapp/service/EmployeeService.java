package registerapp.service;

import registerapp.domain.models.service.EmployeeServiceModel;

import java.util.List;
public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);
    
    List<EmployeeServiceModel> getAllEmployees();
    
    void removeEmployee(String id);
}
