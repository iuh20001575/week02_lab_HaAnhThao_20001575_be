<%@ page import="vn.edu.iuh.fit.backend.models.ProductPrice" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Product" %>
<%@ page import="vn.edu.iuh.fit.backend.models.ProductImage" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Customer" %>
<%
    Object product_o = session.getAttribute("product");

    String id = request.getParameter("id");

    if (product_o == null) {
        request.getRequestDispatcher("control-servlet?action=product&id=" + id).forward(request, response);
        return;
    }

    Object customerO = session.getAttribute("customer");

    session.removeAttribute("product");
    ProductPrice productPrice = (ProductPrice) product_o;
    Product product = productPrice.getProduct();
    List<ProductImage> productImageList = productPrice.getProduct().getProductImageList();
    String imagePath = productImageList == null || productImageList.isEmpty() ? "images/alternate_image.png" : productImageList.get(0).getPath();
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><%= product.getName() %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>
    <main class="d-flex gap-4 flex-column">
        <jsp:include page="components/header.jsp" />
        <section class="container-fluid">
            <div class="row">
                <div class="col col-4">
                    <div class="ratio ratio-4x3 h-full">
                        <img src="<%= imagePath %>" class="card-img-top object-fit-cover rounded" alt="<%= product.getName() %>">
                    </div>
                </div>
                <div class="col col-8">
                    <h1 class="fs-5"><%= product.getName() %></h1>
                    <p class="fs-2 fw-medium text-danger">₫ <%= productPrice.getPrice() %></p>
                    <p><%= product.getDescription() %></p>
                    <div class="row">
                        <%
                            assert productImageList != null;
                            for (int i = 0; i < Math.min(4, productImageList.size()); ++i) {
                                ProductImage productImage = productImageList.get(i);
                        %>
                            <div class="col col-3">
                                <div class="ratio ratio-3x4">
                                    <img src="<%= productImage.getPath() %>" class="card-img-top object-fit-cover rounded" alt="<%= product.getName() %>">
                                </div>
                            </div>
                        <%
                            }
                        %>
                    </div>
                    <div class="col col-3 mt-3">
                        <div class="input-group mb-3 ">
                            <span class="input-group-text minus pointer-event user-select-none">-</span>
                            <input type="number" min="1" class="form-control text-center qty-input" value="1">
                            <span class="input-group-text plus pointer-event user-select-none">+</span>
                        </div>
                    </div>
                    <div class="d-flex gap-3 mt-3">
                        <form action="control-servlet?action=add-cart-detail" method="post" class="m-0">
                            <%
                                if (customerO != null) {
                                    Customer customer = (Customer) customerO;
                            %>
                                <input type="hidden" name="cust_id" value="<%= customer.getId()  %>">
                            <% } %>
                            <input type="hidden" name="prod_id" value="<%= product.getProduct_id()  %>">
                            <input type="hidden" name="qty" value="1">
                            <button type="submit" class="btn btn-outline-primary">Add to cart</button>
                        </form>
                        <button class="btn btn-primary" type="button">Buy</button>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="./js/toast.js"></script>
    <script src="./js/handleQuantityInput.js"></script>
    <script src="./js/product.js"></script>
    <script>
        const toastType = '<%= session.getAttribute("toast-type") %>'
        const toastMessage = '<%= session.getAttribute("toast-message") %>'

        <%
          session.removeAttribute("toast-type");
          session.removeAttribute("toast-message");
        %>

        if (toastType !== 'null')
            addToast(toastType, toastMessage);

    </script>
</body>
</html>
