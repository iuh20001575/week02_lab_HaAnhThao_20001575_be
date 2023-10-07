package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.frontend.models.CartDetailModel;
import vn.edu.iuh.fit.frontend.models.CustomerModel;
import vn.edu.iuh.fit.frontend.models.ProductModel;
import vn.edu.iuh.fit.frontend.models.ProductPriceModel;
import vn.edu.iuh.fit.frontend.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/control-servlet"})
public class ControlServlet extends HttpServlet {
    private final ProductModel productModel;
    private final CustomerModel customerModel;
    private final CartDetailModel cartDetailModel;
    private final ProductPriceModel productPriceModel;

    public ControlServlet() {
        productModel = new ProductModel();
        customerModel = new CustomerModel();
        cartDetailModel = new CartDetailModel();
        productPriceModel = new ProductPriceModel();
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
        else if (action.equalsIgnoreCase("cart"))
            handleGetCart(req, resp);
    }

    private Optional<Customer> getCustomerFromSession(HttpServletRequest req) {
        return getCustomerFromSession(req.getSession(true));
    }

    private Optional<Customer> getCustomerFromSession(HttpSession session) {
        Object customerO = session.getAttribute("customer");

        if (customerO == null)
            return Optional.empty();

        Customer customer = (Customer) customerO;

        return Optional.of(customer);
    }

    private void handleGetCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);

        Optional<Customer> customerOptional = getCustomerFromSession(session);

        if (customerOptional.isEmpty())
            resp.sendRedirect("login.jsp");
        else {
            Customer customer = customerOptional.get();

            List<CartDetail> cartDetails = cartDetailModel.getCartDetailsByCustomerId(customer.getId());
            List<Long> list = new ArrayList<>();
            for (CartDetail cartDetail : cartDetails) {
                Long productId = cartDetail.getProduct().getProduct_id();
                list.add(productId);
            }
            List<ProductPrice> productPrices = productPriceModel.getActiveProductsWithNewPriceByProductIds(list);

            long cartCount = cartDetailModel.countByCustomer(customer.getId());

            session.setAttribute("cartCount", cartCount);
            session.setAttribute("cartDetails", cartDetails);
            session.setAttribute("productPrices", productPrices);
            resp.sendRedirect("cart.jsp");
        }
    }

    private void handleGetProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String page = req.getParameter("page");

        if (page == null)
            page = "1";

        List<ProductPrice> products = productModel.getProducts(Utils.convertToInteger(page));
        long pages = productModel.getPages();

        HttpSession session = req.getSession(true);

        session.setAttribute("products", products);
        session.setAttribute("pages", pages);

        Optional<Customer> customerOptional = getCustomerFromSession(session);
        long cartCount = 0;

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            cartCount = cartDetailModel.countByCustomer(customer.getId());
        }

        session.setAttribute("cartCount", cartCount);

        req.setAttribute("products", products);
        req.getRequestDispatcher(getServletContext().getContextPath() + "?page=" + page).forward(req, resp);
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
        else if (action.equalsIgnoreCase("delete-cart-detail")) {
            handleDeleteCartDetail(req, resp);
        } else if (action.equalsIgnoreCase("add-cart-detail"))
            handlePostAddCartDetail(req, resp);
        else if (action.equalsIgnoreCase("checkout"))
            handlePostCheckout(req, resp);

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

    private void handleDeleteCartDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long cartId = Long.parseLong(req.getParameter("cart_id"));
        long productId = Long.parseLong(req.getParameter("product_id"));

        cartDetailModel.delete(productId, cartId);
        HttpSession session = req.getSession(true);
        Object cartCountO = session.getAttribute("cartCount");

        if (cartCountO != null) {
            long cartCount = (long) cartCountO;

            session.setAttribute("cartCount", --cartCount);
        }

        resp.sendRedirect("cart.jsp");
    }

    private void handlePostAddCartDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String custIdS = req.getParameter("cust_id");

        if (custIdS == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        HttpSession session = req.getSession(true);
        String toastType = "danger";
        String toastMessage = "Add fail.";

        try {
            long custId = Long.parseLong(custIdS);
            long prodId = Long.parseLong(req.getParameter("prod_id"));
            int qty = Integer.parseInt(req.getParameter("qty"));

            CartDetail cartDetail = new CartDetail(new Product(prodId), new Cart(new Customer(custId)), qty);

            boolean b = cartDetailModel.add(cartDetail);

            if (b) {
                toastType = "success";
                toastMessage = "Add success!";
            }
        } catch (Exception e) {
            toastMessage = e.getMessage();
        } finally {
            session.setAttribute("toast-type", toastType);
            session.setAttribute("toast-message", toastMessage);
            if (toastType.equals("danger"))
                resp.sendRedirect(req.getHeader("Referer"));
            else
                resp.sendRedirect(getServletContext().getContextPath() + "/?page=1");
        }

    }

    private void handlePostCheckout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String[] productIds = req.getParameterValues("product_id[]");
        String custIdS = req.getParameter("cust_id");

        try {
            List<Long> productIdList = Arrays.stream(productIds).map(Long::parseLong).collect(Collectors.toList());
            long custId = Long.parseLong(custIdS);

            List<CartDetail> cartDetails = cartDetailModel.getByProductIds(productIdList, custId);
            List<ProductPrice> productPrices = productPriceModel.getActiveProductsWithNewPriceByProductIds(productIdList);

            req.setAttribute("cartDetails", cartDetails);
            req.setAttribute("productPrices", productPrices);
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("notFound.jsp").forward(req, resp);
        }

    }
}
