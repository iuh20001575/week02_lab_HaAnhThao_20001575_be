<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.backend.models.CartDetail" %>
<%@ page import="vn.edu.iuh.fit.backend.models.ProductPrice" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Customer" %><%
    Object cartDetailsO = request.getAttribute("cartDetails");

    if (cartDetailsO == null) {
        request.getRequestDispatcher("notFound.jsp").forward(request, response);
        return;
    }

    Object productPricesO = request.getAttribute("productPrices");
    Object customerO = session.getAttribute("customer");

    List<CartDetail> cartDetails = (List<CartDetail>) cartDetailsO;
    List<ProductPrice> productPrices = (List<ProductPrice>) productPricesO;
    Customer customer = (Customer) customerO;

    System.out.println(cartDetails);
    System.out.println(productPrices);
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Checkout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <main>
        <jsp:include page="components/header.jsp" />
        <section class="container">

        </section>
    </main>
</body>
</html>
