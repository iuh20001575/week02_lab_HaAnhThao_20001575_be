<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.backend.models.CartDetail" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Product" %>
<%@ page import="vn.edu.iuh.fit.backend.models.ProductImage" %>
<%@ page import="vn.edu.iuh.fit.backend.models.ProductPrice" %>
<%
    Object cartDetailsO = session.getAttribute("cartDetails");

    if (cartDetailsO == null) {
        response.sendRedirect("control-servlet?action=cart");
        return;
    }

    session.removeAttribute("cartDetails");

    List<CartDetail> cartDetails = (List<CartDetail>) cartDetailsO;
    List<ProductPrice> productPrices = (List<ProductPrice>) session.getAttribute("productPrices");
    int cartSize = cartDetails.size();
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cart Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/cart.css">
</head>
<body>
    <main>
        <jsp:include page="components/header.jsp" />
        <section class="container">
            <table class="table table-hover mt-3">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total amount</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int  i = 0; i < cartSize; ++i) {
                        CartDetail cartDetail = cartDetails.get(i);
                        Product product = cartDetail.getProduct();
                        List<ProductImage> productImageList = product.getProductImageList();
                        String imagePath = productImageList == null || productImageList.isEmpty() ? "images/alternate_image.png" : productImageList.get(0).getPath();
                    %>
                        <tr>
                            <th class="align-middle">
                                <input class="form-check-input mt-0 select-item" type="checkbox" value="">
                            </th>
                            <td>
                                <div class="d-flex gap-1 align-items-center">
                                    <img class="product-image" src="<%= imagePath %>" alt="<%= product.getName() %>">
                                    <p class="m-0"><%= product.getName() %></p>
                                </div>
                            </td>
                            <td class="align-middle price"><%= String.format("%.2f", productPrices.get(i).getPrice()) %></td>
                            <td class="align-middle">
                                <div class="input-group mb-3 m-0 align-items-center" data-product-id="<%= product.getProduct_id() %>" data-cart-id="<%= cartDetail.getCart().getCustomer().getId() %>">
                                    <span class="input-group-text minus pointer-event user-select-none">-</span>
                                    <input type="number" min="1" class="form-control qty-input" value="<%= cartDetail.getQuantity() %>">
                                    <span class="input-group-text plus pointer-event user-select-none">+</span>
                                </div>
                            </td>
                            <td class="align-middle total-price"><%= String.format("%.2f", productPrices.get(i).getPrice() * cartDetail.getQuantity()) %></td>
                            <td class="align-middle">
                                <form action="control-servlet?action=delete-cart-detail" method="post">
                                    <input hidden="hidden" type="text" name="cart_id" value="<%= cartDetail.getCart().getCustomer().getId() %>">
                                    <input hidden="hidden" type="text" name="product_id" value="<%= product.getProduct_id() %>">
                                    <button type="submit" class="btn btn-danger">Remove</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="position-fixed bg-white bottom-0 start-0 end-0 border-top border-primary border-3">
                <div class="d-flex flex-1">
                <div class="container p-3 d-flex align-items-center justify-content-between">
                    <div class="d-flex align-items-center gap-4">
                        <label class="d-flex align-items-center">
                            <input class="form-check-input mt-0 select-all" type="checkbox" value="">
                            <span class="ms-2">Select all (<%= cartSize %>)</span>
                        </label>
                    </div>
                    <div class="d-flex gap-4 align-items-center">
                        <p class="d-flex align-items-center m-0">
                            Total Payment (<span class="product-count">0</span>&nbsp; Products):
                            <span class="ms-2 fs-3 text-danger price">0</span>
                        </p>
                        <button type="button" class="btn btn-primary">Buy</button>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="./js/handleQuantityInput.js"></script>
    <script src="./js/cart.js"></script>
</body>
</html>
