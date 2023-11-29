package tech.neckel.jdbc.product;

import org.springframework.stereotype.Service;
import tech.neckel.jdbc.database.DbException;
import tech.neckel.jdbc.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (description, price) VALUES (?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setBigDecimal(2, product.getPrice());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long generatedId = generatedKeys.getLong(1);
                        product.setId(generatedId);
                    }
                }
            }


        } catch (SQLException e) {
            throw new DbException(e);
        }

        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    list.add(
                        Product.builder()
                            .id(resultSet.getLong("id"))
                            .description(resultSet.getString("description"))
                            .price(resultSet.getBigDecimal("price"))
                        .build());
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e);
        }

        return list;
    }
}
