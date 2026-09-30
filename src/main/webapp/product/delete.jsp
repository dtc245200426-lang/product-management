<%%@ page contentType="text/html;charset=UTF-8" language="java" %%>
<html><head><title>Xoa san pham</title></head><body>
<h1>Xoa san pham</h1>
<p>Ban co chac muon xoa: <b>${product.name}</b>?</p>
<p>Gia: ${product.price}</p>
<p>Mo ta: ${product.description}</p>
<p>Nha san xuat: ${product.manufacturer}</p>
<form method="post" action="products?action=delete">
<input type="hidden" name="id" value="${product.id}">
<button type="submit">Xoa san pham</button>
</form>
<p><a href="products">Huy</a></p>
</body></html>
