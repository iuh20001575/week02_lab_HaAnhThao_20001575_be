package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.frontend.models.CustomerModel;
import vn.edu.iuh.fit.frontend.models.ProductModel;
import vn.edu.iuh.fit.frontend.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/control-servlet"})
public class ControlServlet extends HttpServlet {
    private final ProductModel productModel;
    private final CustomerModel customerModel;

    public ControlServlet() {
        productModel = new ProductModel();
        customerModel = new CustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null)
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        else if (action.equalsIgnoreCase("products"))
            handleGetProducts(req, resp);
        else if (action.equalsIgnoreCase("product"))
            handleGetProduct(req, resp);
    }

    private void handleGetProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");

        if (page == null)
            page = "1";

        List<ProductPrice> products = productModel.getProducts(Utils.convertToInteger(page));
        long pages = productModel.getPages();

        HttpSession session = req.getSession(true);

        session.setAttribute("products", products);
        session.setAttribute("pages", pages);

        resp.sendRedirect("?page=" + page);
    }

    private void handleGetProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Optional<ProductPrice> productPrice = productModel.getProduct(Utils.convertToLong(id));

        if (productPrice.isEmpty())
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        else {
            HttpSession session = req.getSession(true);

            session.setAttribute("product", productPrice.get());

            resp.sendRedirect("product.jsp?id=" + id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null)
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        else if (action.equalsIgnoreCase("login"))
            handlePostLogin(req, resp);
    }

    private void handlePostLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        Optional<Customer> customer = customerModel.login(phone, password);
        HttpSession session = req.getSession(true);

        if (customer.isEmpty()) {
            session.setAttribute("toast-type", "danger");
            session.setAttribute("toast-message", "Phone or password incorrect.");
            session.setAttribute("phone", phone);
            session.setAttribute("password", password);

            resp.sendRedirect("login.jsp");
        } else {
            session.setAttribute("customer", customer.get());
            resp.sendRedirect(getServletContext().getContextPath() + "/?page=1");
        }
    }
}
