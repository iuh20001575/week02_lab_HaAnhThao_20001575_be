<%--
  Created by IntelliJ IDEA.
  User: HaAnhThao
  Date: 29/09/2023
  Time: 09:51:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>404 Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/notFound.css">
</head>
<body>
    <main>
        <jsp:include page="components/header.jsp" />
        <section>
            <h2 class="fs-1 fw-bolder bg-primary text-white text-center">Sorry!! Something Went Wrong!!!</h2>
            <h1 class="fw-bolder text-primary text-center lh-1">40 + 4 = <span class="text-danger">404</span></h1>
            <p class="fs-2 fw-bolder text-primary text-center">The Page You Are Looking For is Missing, Misspelled or Doesn't Exist!</p>
        </section>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
