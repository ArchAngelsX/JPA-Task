<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
        h2 { color: #333; text-align: center; }
        form { background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 500px; margin: 0 auto; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type='text'], textarea { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        input[type='submit'] { background: #9477ed; color: white; border: none; padding: 10px 15px; border-radius: 5px; cursor: pointer; width: 100%; }
        input[type='submit']:hover { background: #77a4f4; }
        textarea { height: 100px; }
    </style>
</head>
<body>
    <h2>Product Details</h2>
    <form action="updateProduct.do" method="post">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>
        
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${product.category}" required>
        
        <label for="brand">Brand:</label>
        <input type="text" id="brand" name="brand" value="${product.brand}" required>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${product.description}</textarea>
        
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.price}" required>
        
        <input type="hidden" name="id" value="${product.id}"/>
        <input type="submit" value="Update Product">
    </form>
</body>
</html>