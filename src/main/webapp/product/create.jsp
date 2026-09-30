<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<html><head><title>Them san pham</title></head><body>
<h1>Them san pham moi</h1>
<p>${message}</p>
<form method="post" action="products?action=create">
<p>Ten: <input name="name" required></p>
<p>Gia: <input type="number" step="0.01" min="0" name="price" required></p>
<p>Mo ta: <input name="description"></p>
<p>Nha san xuat: <input name="manufacturer"></p>
<button type="submit">Them san pham</button>
</form>
<p><a href="products">Quay lai danh sach</a></p>
</body></html>
