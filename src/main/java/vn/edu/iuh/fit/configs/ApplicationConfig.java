package vn.edu.iuh.fit.configs;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.db.Connection;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    public ApplicationConfig() {
        Connection.getInstance().getEntityManager();
    }
}
