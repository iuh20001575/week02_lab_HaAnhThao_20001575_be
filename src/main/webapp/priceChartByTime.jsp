<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>
<%
    Object productsO = request.getAttribute("products");
    Object pricesO = request.getAttribute("prices");
    String prodId = request.getParameter("prod-id");

    if (productsO == null || pricesO == null) {
        request.getRequestDispatcher("control-servlet?action=price-chart-by-time").forward(request, response);
        return;
    }

    Map<Long, String> products = (Map<Long, String>) productsO;
    Map<LocalDateTime, Double> prices = (Map<LocalDateTime, Double>) pricesO;
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Price chart by time</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<main class="d-flex flex-column min-vh-100">
    <jsp:include page="components/header.jsp" />
    <section class="d-flex flex-grow-1">
        <jsp:include page="components/navbarDashboard.jsp" />
        <div class="flex-fill p-3">
            <div class="d-flex gap-3 align-items-center justify-content-between">
                <p class="fs-4 m-0">Product: </p>
                <select class="form-select product">
                    <%
                        for (Long key : products.keySet()) {


                    %>
                        <option value="<%= key %>" <%=key.toString().equals(prodId) ? "selected" : "" %>>
                            #<%= key %> - <%= products.get(key) %>
                        </option>
                    <% } %>
                </select>
            </div>
            <div>
                <canvas id="myChart"></canvas>
            </div>
        </div>
    </section>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    <%--  Chart  --%>
    const ctx = document.getElementById('myChart');
    const pricesJSON = "<%= prices %>";
    const replacedString = pricesJSON.replace(/=/g, ":");

    const jsonString = replacedString.replace(/(\d{4}-\d{2}-\d{2}T\d{2}:\d{2})/g, '"$1"');

    const prices = JSON.parse(jsonString);

    const skipped = (ctx, value) => ctx.p0.skip || ctx.p1.skip ? value : undefined;
    const down = (ctx, value) => ctx.p0.parsed.y > ctx.p1.parsed.y ? value : undefined;

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: Object.keys(prices),
            datasets: [{
                label: 'Price chart',
                data: Object.values(prices),
                pointStyle: 'circle',
                pointRadius: 10,
                pointHoverRadius: 15,
                segment: {
                    borderColor: ctx => skipped(ctx, 'rgb(0,0,0,0.2)') || down(ctx, 'rgb(192,75,75)'),
                    borderDash: ctx => skipped(ctx, [6, 6]),
                },
                spanGaps: true
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Handle
    const select = document.querySelector("select");

    select.addEventListener('change', () => {
        window.location = 'control-servlet?action=price-chart-by-time&prod-id=' + select.value
    })
</script>
</body>
</html>
