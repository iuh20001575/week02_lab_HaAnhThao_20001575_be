package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @Column(columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long image_id;
    @Column(columnDefinition = "VARCHAR(250)")
    private String alternative;
    @Column(columnDefinition = "VARCHAR(250)", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
