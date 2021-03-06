package registerapp.repository;

import registerapp.domain.entities.Employee;
public interface EmployeeRepository extends GenericRepository<Employee, String> {
    public void removeById(String id);
}
