package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cust_id", columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "VARCHAR(250)", nullable = false)
    private String address;
    @Column(columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "cust_name", columnDefinition = "VARCHAR(150)", nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orderList;
}
