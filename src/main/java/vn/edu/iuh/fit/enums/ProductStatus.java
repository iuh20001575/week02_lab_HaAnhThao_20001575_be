package vn.edu.iuh.fit.enums;

public enum ProductStatus {
    ACTIVE(1), INACTIVE(0), DISCONTINUED(-1);

    private final int status;

    private ProductStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
