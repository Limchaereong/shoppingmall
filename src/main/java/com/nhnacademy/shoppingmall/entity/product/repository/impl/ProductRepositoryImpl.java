package com.nhnacademy.shoppingmall.entity.product.repository.impl;

import com.nhnacademy.shoppingmall.entity.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;

import java.sql.*;
import java.util.Optional;
import java.util.*;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Optional<Product> findById(int productId) {
        String sql = "SELECT product_id, category_id, product_name, product_price, thumbnail_image, detail_image, product_description, created_at, updated_at FROM products WHERE product_id = ?";
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("product_price"),
                        resultSet.getString("thumbnail_image"),
                        resultSet.getString("detail_image"),
                        resultSet.getString("product_description"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("updated_at").toLocalDateTime()
                );
                return Optional.ofNullable(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Page<Product>> findProductList(int pageSize, int currentPage) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "SELECT * FROM products LIMIT ?, ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, (currentPage - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("product_price"),
                        resultSet.getString("thumbnail_image"),
                        resultSet.getString("detail_image"),
                        resultSet.getString("product_description"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("updated_at").toLocalDateTime()
                );
                products.add(product);
            }

            String countSql = "SELECT COUNT(*) FROM products";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(countSql)) {
                resultSet = preparedStatement1.executeQuery();
                resultSet.next();
                long totalCount = resultSet.getLong(1);

                return Optional.of(new Page<>(products, totalCount));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllList() {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "SELECT * FROM products;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            List<Product> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("product_price"),
                        resultSet.getString("thumbnail_image"),
                        resultSet.getString("detail_image"),
                        resultSet.getString("product_description"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("updated_at").toLocalDateTime());

                product.setProductId(resultSet.getInt("product_id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductPrice(resultSet.getBigDecimal("product_price"));
                product.setThumbnailImage(resultSet.getString("thumbnail_image"));
                product.setDetailImage(resultSet.getString("detail_image"));
                product.setProductDescription(resultSet.getString("product_description"));
                product.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                product.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "INSERT INTO products (product_id, category_id, product_name, product_price, thumbnail_image, detail_image, product_description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setBigDecimal(4, product.getProductPrice());
            preparedStatement.setString(5, product.getThumbnailImage());
            preparedStatement.setString(6, product.getDetailImage());
            preparedStatement.setString(7, product.getProductDescription());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(product.getCreatedAt()));
            preparedStatement.setTimestamp(9, Timestamp.valueOf(product.getUpdatedAt()));

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int deleteByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "DELETE FROM products WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, productId);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if(connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "UPDATE products SET category_id = ?, product_name = ?, product_price = ?, thumbnail_image = ?, detail_image = ?, product_description = ?, updated_at = ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setBigDecimal(3, product.getProductPrice());
            preparedStatement.setString(4, product.getThumbnailImage());
            preparedStatement.setString(5, product.getDetailImage());
            preparedStatement.setString(6, product.getProductDescription());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(product.getUpdatedAt()));
            preparedStatement.setInt(8, product.getProductId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}