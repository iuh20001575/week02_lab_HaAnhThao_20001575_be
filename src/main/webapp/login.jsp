<%
  String phone = (String) session.getAttribute("phone");
  String password = (String) session.getAttribute("password");

  session.removeAttribute("phone");
  session.removeAttribute("password");
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/login.css">
</head>
<body>
  <main class="d-flex justify-content-center align-items-center">
    <section class="m-3 p-3 shadow rounded">
      <h1 class="h1 text-center">Login</h1>
      <form class="" action="control-servlet?action=login" method="post">
        <div class="form-floating mb-3">
          <input required name="phone" type="tel" class="form-control" id="phone" value="<%= phone == null ? "" : phone %>">
          <label for="phone">Phone</label>
        </div>
        <div class="form-floating mb-3">
          <input required name="password" type="password" class="form-control" id="password" value="<%= password == null ? "" : password %>">
          <label for="password">Password</label>
        </div>
        <div class="invalid-feedback error-message mb-3">
          <%= session.getAttribute("message") == null ? "" : session.getAttribute("message") %>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </section>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  <script src="./js/toast.js"></script>
  <script>
    const toastType = '<%= session.getAttribute("toast-type") %>';
    const toastMessage = '<%= session.getAttribute("toast-message") %>';

    <%
      session.removeAttribute("toast-type");
      session.removeAttribute("toast-message");
    %>

    if (toastType !== 'null')
      addToast(toastType, toastMessage);
  </script>
</body>
</html>
