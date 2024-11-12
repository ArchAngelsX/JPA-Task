<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="tugas1.kelas.OrderItem" %>
<%@ page import="tugas1.kelas.OrderCart" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
        h2 { color: #333; text-align: center; }
        table { width: 80%; margin: 0 auto; border-collapse: collapse; background: #fff; }
        th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #59bce7; color: white; }
        tr:hover { background-color: #f1f1f1; }
        input[type='submit'] { background: #9477ed; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer; }
        input[type='submit']:hover { background: #77a4f4; }
    </style>
    <script type="text/javascript">
        function confirmOrder() {
            // Tampilkan alert konfirmasi
            var confirmation = confirm("Apakah Anda yakin ingin memesan?");
            if (confirmation) {
                // Jika pengguna menekan OK, kirim form
                return true;
            } else {
                // Jika pengguna menekan Cancel, batalkan pengiriman form
                return false;
            }
        }
    </script>
</head>
<body>
    <h2>Your Order Cart</h2>
    <table>
        <tr><th>Product Name</th><th>Price</th><th>Quantity</th><th>Subtotal</th></tr>
        <c:forEach var="item" items="${sessionScope.shoppingCart.items}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.price}</td>
                <td>${item.quantity}</td>
                <td style="text-align: right;">Rp. ${item.product.price * item.quantity}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3"><strong>Total:</strong></td>
            <td style="text-align: right;">
                <strong>
                    <c:set var="total" value="0" />
                    <c:forEach var="item" items="${sessionScope.shoppingCart.items}">
                        <c:set var="total" value="${total + (item.product.price * item.quantity)}" />
                    </c:forEach>
                    Rp. ${total}
                </strong>
            </td>
        </tr>
    </table>
    <form action='placeOrder.do' method='post' style='text-align: center; margin-top: 20px;' onsubmit="return confirmOrder();">
        <input type='submit' value='Place Order'/>
    </form>
    <form action='product.do' method='get' style='text-align: center; margin-top: 20px;'>
        <input type='submit' value='Continue Shopping'/>
    </form>
</body>
</html>