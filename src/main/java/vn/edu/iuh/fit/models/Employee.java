package vn.edu.iuh.fit.models;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import vn.edu.iuh.fit.converters.EmployeeStatusConverter;
import vn.edu.iuh.fit.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id", columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "VARCHAR(250)", nullable = false)
    private String address;
    @Column(columnDefinition = "DATETIME(6)", nullable = false)
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDateTime dob;
    @Column(columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "full_name", columnDefinition = "VARCHAR(150)", nullable = false)
    private String fullname;
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private String phone;
    @Column(columnDefinition = "INT(11)", nullable = false)
    @Convert(converter = EmployeeStatusConverter.class)
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Order> lstOrder;
}
