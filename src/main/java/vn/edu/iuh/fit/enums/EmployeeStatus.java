package vn.edu.iuh.fit.enums;

public enum EmployeeStatus {
    WORKING(1), UN_WORKING(0), QUITTED(-1);

    private final int status;

    private EmployeeStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
