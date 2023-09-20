package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import vn.edu.iuh.fit.converters.ProductStatusConverter;
import vn.edu.iuh.fit.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    @Column(columnDefinition = "VARCHAR(250)", nullable = false)
    private String description;
    @Column(name = "manufacturer_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String manufacturer;
    @Column(columnDefinition = "VARCHAR(150)", nullable = false)
    private String name;
    @Column(columnDefinition = "INT(11)")
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus status;
    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private String unit;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImageList;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Product() {
    }

    public Product(String name, String description, String unit, String manufacturer, ProductStatus status) {
        this.description = description;
        this.manufacturer = manufacturer;
        this.name = name;
        this.status = status;
        this.unit = unit;
    }
}
