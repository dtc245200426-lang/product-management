<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<%%@ taglib prefix="c" uri="jakarta.tags.core" %%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Quan ly san pham</title></head><body>
<h1>Danh sach san pham</h1>
<p><a href="products?action=create">Them san pham moi</a></p>
<form method="get" action="products">
<input type="hidden" name="action" value="search">
<input type="text" name="name" value="${searchName}" placeholder="Nhap ten san pham">
<button type="submit">Tim kiem</button>
<a href="products">Tat ca san pham</a>
</form>
<br>
<table border="1" cellpadding="8">
<tr><th>ID</th><th>Ten</th><th>Gia</th><th>Mo ta</th><th>Nha san xuat</th><th>Thao tac</th></tr>
<c:forEach items="${products}" var="product">
<tr>
<td>${product.id}</td>
<td>${product.name}</td>
<td>${product.price}</td>
<td>${product.description}</td>
<td>${product.manufacturer}</td>
<td><a href="products?action=view^&id=${product.id}">Xem</a> | <a href="products?action=edit^&id=${product.id}">Sua</a> | <a href="products?action=delete^&id=${product.id}">Xoa</a></td>
</tr>
</c:forEach>
</table></body></html>
