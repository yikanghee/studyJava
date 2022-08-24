package tutorial.study.payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorial.study.payroll.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
