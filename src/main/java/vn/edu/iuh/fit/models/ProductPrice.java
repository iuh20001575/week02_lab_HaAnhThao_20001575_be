package vn.edu.iuh.fit.models;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    @Column(columnDefinition = "DATETIME(6)")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDateTime price_date_time;
    @Column(columnDefinition = "VARCHAR(255)")
    private String note;
    @Column(columnDefinition = "DOUBLE", nullable = false)
    private double price;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
