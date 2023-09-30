<%@ page import="vn.edu.iuh.fit.backend.models.Customer" %>
<%
    Object cartCountO = session.getAttribute("cartCount");
    Object customerO = session.getAttribute("customer");
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp?page=1">
            <img src="images/logo.png" alt="Logo" width="32" height="32" class="d-inline-block align-text-top">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp?page=1">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">Dashboard</a>
                </li>
            </ul>
        </div>
        <div class="ml-auto d-flex align-items-center gap-3">
            <div class="d-flex align-items-center">
                <a class="navbar-brand m-0" href="cart.jsp">
                        <img src="images/shopping-cart.png" alt="Logo" width="32" height="32" class="d-inline-block align-text-top">
                </a>
                <span class="d-flex justify-content-center align-items-center p-2 rounded-circle bg-danger text-white w-6 h-6 mr-3"><%= cartCountO == null ? 0 : cartCountO %></span>
            </div>
            <%
                if (customerO == null) {
            %>
                <a class="btn btn-primary" href="login.jsp">Login</a>
            <% } else { %>
                <p class="m-0 fw-semibold"><%= ((Customer) customerO).getName() %></p>
            <% } %>
        </div>
    </div>
</nav>