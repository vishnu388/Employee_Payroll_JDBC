import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollServiceTest {
	EmployeePayrollService employeePayrollService;
	List<EmployeePayrollData> employeePayrollList;

	@Test
	public void given3EmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount(){
		employeePayrollService = new EmployeePayrollService();
		employeePayrollList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollList.size());
	}
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
		employeePayrollService = new EmployeePayrollService();
		employeePayrollList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Terisa", 20000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
		employeePayrollService = new EmployeePayrollService();
		employeePayrollList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalaryWithPreparedStatement("Terisa", 20000.00);
		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}
	@Test
	public void givenDateRange_WhenRetrievedEmployee_ShouldReturnEmpCount() throws EmployeePayrollException {
		employeePayrollService = new EmployeePayrollService();
		employeePayrollList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollDataForDateRange(startDate, endDate);
		Assert.assertEquals(3, employeePayrollList.size());
	}

	@Test
	public void givenPayrollData_WhenAverageSalaryRetrieveByGender_ShouldReturnProperValue() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		Map<String, Double> averageSalaryByGender = employeePayrollService.averageSalaryByGender();
		System.out.println(employeePayrollService.averageSalaryByGender());
		boolean actual=employeePayrollService.averageSalaryByGender() != null;
		Assert.assertTrue(actual);
	}
	@Test
	public void givenPayrollData_WhenAverageGenderRetrieveByGender_ShouldReturnProperValue() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		List<String> minSalarybyFm = employeePayrollService.minSalaryByGender();
		System.out.println(employeePayrollService.minSalaryByGender());
		boolean actual=employeePayrollService.minSalaryByGender() != null;
		Assert.assertTrue(actual);
	}

}
