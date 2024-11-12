<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="tugas1.kelas.Product" %>
<%@ page import="tugas1.kelas.OrderCart" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
        h2 { color: #333; text-align: center; }
        form { text-align: center; margin-bottom: 20px; }
        table { width: 80%; margin: 0 auto; border-collapse: collapse; background: #fff; }
        th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #59bce7; color: white; }
        tr:hover { background-color: #f1f1f1; }
        input[type='submit'] { background: #9477ed; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer; }
        input[type='submit']:hover { background: #77a4f4; }
    </style>
</head>
<body>
    <h2>Product List</h2>
    <form action="${pageContext.request.contextPath}/login.do" method="get">
    <input type="submit" value="Logout">
</form>
    <form action='addProduct.do' method='post'><input type='submit' value='Add Product'></form>

    <table>
        <tr><th>ID</th><th>Name</th><th>Category</th><th>Brand</th><th>Description</th><th>Price</th><th>Actions</th></tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.category}</td>
                <td>${product.brand}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <form action='viewProduct.do' method='get' style='display:inline;'>
                        <input type='hidden' name='id' value='${product.id}'/>
                        <input type='submit' value='View'/>
                    </form>
                    <form action='deleteProduct.do' method='post' style='display:inline;'>
                        <input type='hidden' name='id' value='${product.id}'/>
                        <input type='submit' value='Delete' onclick='return confirm("Are you sure you want to delete this product?");'/>
                    </form>
                    <form action="addToCart.do" method='post' style='display:inline;'>
					    <input type='hidden' name='productId' value='${product.id}'/>
					    <input type='number' name='quantity' min='1' value='1' required style="width: 50px;"/>
					    <input type='submit' value='Add To Cart'/>
					</form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>