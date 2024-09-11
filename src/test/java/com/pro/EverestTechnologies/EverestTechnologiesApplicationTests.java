package com.pro.EverestTechnologies;

import com.pro.EverestTechnologies.model.Employee;
import com.pro.EverestTechnologies.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.pro.EverestTechnologies.repository.EmployeeRepository;
import com.pro.EverestTechnologies.repository.StudentRepository;
import com.pro.EverestTechnologies.service.EmployeeServiceImpl;
import com.pro.EverestTechnologies.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EverestTechnologiesApplicationTests {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAllStudents_Success() {
		Student s1 = new Student(1L, "chaitanya", "chaitanta9989@gmail.com", "NewYork");
		Student s2 = new Student(2L, "Manish", "Manish@gmail.com", "California");
		Student s3 = new Student(3L, "Vasundhara", "Vasundhara@gmail.com", "Buffalo");

		List<Student> students = new ArrayList<>();
		students.add(s1);
		students.add(s2);
		students.add(s3);

		when(studentRepository.findAll()).thenReturn(students);

		List<Student> result = studentService.getAllStudents();

		assertEquals(3, result.size());
		assertEquals("chaitanya", result.get(0).getName());
	}

	@Test
	public void saveStudent_Success() {
		Student student = new Student(1L, "chaitanya", "chaitanta9989@gmail.com", "NewYork");

		when(studentRepository.save(student)).thenReturn(student);

		Student result = studentService.saveStudent(student);

		assertEquals("chaitanya", result.getName());
		assertEquals("chaitanta9989@gmail.com", result.getEmail());
		assertEquals("NewYork", result.getAddress());
	}

	@Test
	public void saveStudents_Success() {
		Student s1 = new Student(1L, "chaitanya", "chaitanta9989@gmail.com", "NewYork");
		Student s2 = new Student(2L, "Manish", "Manish@gmail.com", "California");
		List<Student> students = List.of(s1, s2);

		when(studentRepository.saveAll(students)).thenReturn(students);

		List<Student> result = studentService.saveStudents(students);

		assertEquals(2, result.size());
		assertEquals("Manish", result.get(1).getName());
	}

	@Test
	public void getStudentById_Exists() {
		Student student = new Student(1L, "chaitanya", "chaitanta9989@gmail.com", "NewYork");

		when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

		Optional<Student> result = studentService.getStudentById(1L);

		assertTrue(result.isPresent());
		assertEquals("chaitanya", result.get().getName());
	}

	@Test
	public void getStudentById_NotFound() {
		when(studentRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Student> result = studentService.getStudentById(1L);

		assertFalse(result.isPresent());
	}

	@Test
	public void updateStudent_Exists() {
		Student existingStudent = new Student(1L, "chaitanya", "chaitanta9989@gmail.com", "NewYork");
		Student updatedStudent = new Student(1L, "Chaitanya Updated", "chaitanya.updated@gmail.com", "California");

		when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
		when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

		Student result = studentService.updateStudent(1L, updatedStudent);

		assertEquals("Chaitanya Updated", result.getName());
		assertEquals("chaitanya.updated@gmail.com", result.getEmail());
	}

	@Test
	public void updateStudent_NotFound() {
		Student updatedStudent = new Student(1L, "Chaitanya Updated", "chaitanya.updated@gmail.com", "California");

		when(studentRepository.findById(1L)).thenReturn(Optional.empty());

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			studentService.updateStudent(1L, updatedStudent);
		});

		assertEquals("Student not found with id 1", thrown.getMessage());
	}

	@Test
	public void deleteStudent_Exists() {
		when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student()));

		studentService.deleteStudent(1L);

		// Verify that deleteById was called with the correct argument
		verify(studentRepository).deleteById(1L);
	}

	@Test
	public void deleteStudent_NotFound() {
		when(studentRepository.findById(1L)).thenReturn(Optional.empty());

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			studentService.deleteStudent(1L);
		});

		assertEquals("Student not found with id 1", thrown.getMessage());
	}

	// EmployeeService Tests
	@Test
	public void saveEmployee_Success() {
		Employee employee = new Employee(1L, "John Doe","Engineering","john.doe@example.com");

		// Mock the save method to return the same employee
		when(employeeRepository.save(employee)).thenReturn(employee);

		// Call the service method
		Employee result = employeeService.saveEmployee(employee);

		// Assert the expected values
		assertEquals("John Doe", result.getName());
		assertEquals("john.doe@example.com", result.getEmail());
		assertEquals("Engineering", result.getDepartment());
	}


	@Test
	public void saveEmployees_Success() {
		Employee e1 = new Employee(1L, "John Doe", "john.doe@example.com", "Engineering");
		Employee e2 = new Employee(2L, "Jane Doe", "jane.doe@example.com", "Marketing");
		Employee e3 = new Employee(3L, "Sam Smith", "sam.smith@example.com", "Finance");
		List<Employee> employees = List.of(e1, e2, e3);

		when(employeeRepository.saveAll(employees)).thenReturn(employees);

		List<Employee> result = employeeService.saveEmployees(employees);

		assertEquals(3, result.size());
		assertEquals("John Doe", result.get(0).getName());
		assertEquals("Jane Doe", result.get(1).getName());
		assertEquals("Sam Smith", result.get(2).getName());
	}

	@Test
	public void getAllEmployees_Success() {
		Employee e1 = new Employee(1L, "John Doe", "john.doe@example.com", "Engineering");
		Employee e2 = new Employee(2L, "Jane Doe", "jane.doe@example.com", "Marketing");
		Employee e3 = new Employee(3L, "Sam Smith", "sam.smith@example.com", "Finance");
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);

		when(employeeRepository.findAll()).thenReturn(employees);

		List<Employee> result = employeeService.getAllEmployees();

		assertEquals(3, result.size());
		assertEquals("John Doe", result.get(0).getName());
		assertEquals("Jane Doe", result.get(1).getName());
		assertEquals("Sam Smith", result.get(2).getName());
	}

	@Test
	public void getEmployeeById_Exists() {
		Employee employee = new Employee(1L, "John Doe", "john.doe@example.com", "Engineering");

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		Optional<Employee> result = employeeService.getEmployeeById(1L);

		assertTrue(result.isPresent());
		assertEquals("John Doe", result.get().getName());
	}

	@Test
	public void getEmployeeById_NotFound() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Employee> result = employeeService.getEmployeeById(1L);

		assertFalse(result.isPresent());
	}

	@Test
	public void updateEmployee_Exists() {
		// Setup existing and updated employee details
		Employee existingEmployee = new Employee(1L, "John Doe", "Engineering", "john.doe@example.com");
		Employee updatedEmployee = new Employee(1L, "John Doe Updated", "Marketing", "john.doe.updated@example.com");

		// Mock repository behavior
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
		when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

		// Call the service method
		Employee result = employeeService.updateEmployee(1L, updatedEmployee);

		// Assert the updated employee's details
		assertEquals("John Doe Updated", result.getName());
		assertEquals("john.doe.updated@example.com", result.getEmail()); // Ensure email is updated
		assertEquals("Marketing", result.getDepartment()); // Ensure department is updated
	}

	@Test
	public void updateEmployee_NotFound() {
		Employee updatedEmployee = new Employee(1L, "John Doe Updated", "john.doe.updated@example.com", "Marketing");

		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			employeeService.updateEmployee(1L, updatedEmployee);
		});

		assertEquals("Employee not found with id 1", thrown.getMessage());
	}

	@Test
	public void deleteEmployee_Exists() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));

		employeeService.deleteEmployee(1L);

		verify(employeeRepository).deleteById(1L);
	}

	@Test
	public void deleteEmployee_NotFound() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			employeeService.deleteEmployee(1L);
		});

		assertEquals("Employee not found with id 1", thrown.getMessage());
	}

}
