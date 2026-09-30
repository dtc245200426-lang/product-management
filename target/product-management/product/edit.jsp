<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<html><head><title>Sua san pham</title></head><body>
<h1>Cap nhat san pham</h1>
<p>${message}</p>
<form method="post" action="products?action=edit">
<input type="hidden" name="id" value="${product.id}">
<p>Ten: <input name="name" value="${product.name}" required></p>
<p>Gia: <input type="number" step="0.01" min="0" name="price" value="${product.price}" required></p>
<p>Mo ta: <input name="description" value="${product.description}"></p>
<p>Nha san xuat: <input name="manufacturer" value="${product.manufacturer}"></p>
<button type="submit">Cap nhat</button>
</form>
<p><a href="products">Quay lai danh sach</a></p>
</body></html>
