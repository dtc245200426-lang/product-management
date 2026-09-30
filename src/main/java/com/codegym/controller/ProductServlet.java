package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendRedirect(
                        request.getContextPath() + "/products"
                );
        }
    }

    private void listProducts(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = productService.findAll();

        request.setAttribute("products", products);

        request.getRequestDispatcher("/product/list.jsp")
                .forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/product/create.jsp")
                .forward(request, response);
    }

    private void createProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String manufacturer = request.getParameter("manufacturer");

        int id = (int) (Math.random() * 100000);

        Product product = new Product(
                id,
                name,
                price,
                description,
                manufacturer
        );

        productService.save(product);

        request.setAttribute(
                "message",
                "Product was created successfully"
        );

        request.getRequestDispatcher("/product/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        if (product == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/product/edit.jsp")
                .forward(request, response);
    }

    private void updateProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        if (product == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        product.setName(request.getParameter("name"));
        product.setPrice(
                Double.parseDouble(request.getParameter("price"))
        );
        product.setDescription(
                request.getParameter("description")
        );
        product.setManufacturer(
                request.getParameter("manufacturer")
        );

        productService.update(id, product);

        request.setAttribute("product", product);
        request.setAttribute(
                "message",
                "Product was updated successfully"
        );

        request.getRequestDispatcher("/product/edit.jsp")
                .forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        if (product == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/product/delete.jsp")
                .forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        if (product == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        productService.remove(id);

        response.sendRedirect(
                request.getContextPath() + "/products"
        );
    }

    private void viewProduct(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        if (product == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/product/view.jsp")
                .forward(request, response);
    }

    private void searchProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        if (name == null) {
            name = "";
        }

        List<Product> products =
                productService.findByName(name);

        request.setAttribute("products", products);
        request.setAttribute("searchName", name);

        request.getRequestDispatcher("/product/list.jsp")
                .forward(request, response);
    }
}