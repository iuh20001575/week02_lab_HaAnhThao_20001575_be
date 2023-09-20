package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Column(columnDefinition = "DOUBLE", nullable = false)
    private double price;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;
    @Column(columnDefinition = "DOUBLE", nullable = false)
    private double quantity;
    @Column(columnDefinition = "VARCHAR(255)")
    private String note;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
