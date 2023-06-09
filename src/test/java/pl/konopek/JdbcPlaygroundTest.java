package pl.konopek;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class JdbcPlaygroundTest {
    //Integration Test
    public static final String PRODUCT_ID = "f1f21a3d-d205-465f-8da7-29c0bf5a5d59";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DROP TABLE product_catalog__products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `product_catalog__products` (" +
                "`id` varchar(255) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(150)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY (id)" +
        ");");
    }

    @Test
    public void playground() {
        int results = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assertThat(results).isEqualTo(0);
    }

    @Test
    public void playgroundInsert() {
        jdbcTemplate.execute("INSERT INTO `product_catalog__products` (id, description, picture, price) " +
                "VALUES " +
                "('p1', 'p1 description', 'p1 picture', 20.25)," +
                "('p2', 'p2 description', 'p2 picture', 25.25)" +
                "; ");

        int results = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assertThat(results).isEqualTo(2);
    }

    @Test
    public void selectPlayground() {
        jdbcTemplate.execute("INSERT INTO `product_catalog__products` (id, description, picture, price) " +
                "VALUES " +
                "('f1f21a3d-d205-465f-8da7-29c0bf5a5d59', 'p1 description', 'p1 picture', 20.25)," +
                "('f1f21a3d-d205-465f-8da7-29c0bf5a5d60', 'p2 description', 'p2 picture', 25.25)" +
                "; ");

        String query = "Select * FROM `product_catalog__products` where id = ?";

        Product product = jdbcTemplate.queryForObject(query, new Object[]{PRODUCT_ID}, new ProductRowMapper());

        assertThat(product.getId()).isEqualTo(PRODUCT_ID);
    }

    @Test
    public void selectPlaygroundMapperVialambda() {
        jdbcTemplate.execute("INSERT INTO `product_catalog__products` (id, description, picture, price) " +
                "VALUES " +
                "('f1f21a3d-d205-465f-8da7-29c0bf5a5d59', 'p1 description', 'p1 picture', 20.25)," +
                "('f1f21a3d-d205-465f-8da7-29c0bf5a5d60', 'p2 description', 'p2 picture', 25.25)" +
                "; ");

        String query = "Select * FROM `product_catalog__products` where id = ?";

        Product product = jdbcTemplate.queryForObject(query, new Object[]{PRODUCT_ID}, (rs, i) -> {
            Product p = new Product(UUID.fromString(rs.getString("id")));
            p.setDescription(rs.getString("description"));
            return p;
        });

        assertThat(product.getId()).isEqualTo(PRODUCT_ID);
    }

    @Test
    public void addProduct() {
        Product product = new Product(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO `product_catalog__products` (id, description, picture, price) values " +
                "(?,?,?,?)", product.getId(), product.getDescription(), product.getPicture(), product.getPrice()
        );

        String query = "Select * FROM `product_catalog__products` where id = ?";

        Product loaded = jdbcTemplate.queryForObject(query, new Object[]{product.getId()}, (rs, i) -> {
            Product p = new Product(UUID.fromString(rs.getString("id")));
            p.setDescription(rs.getString("description"));
            return p;
        });

        assertThat(loaded.getId()).isEqualTo(product.getId());
    }

    class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product(UUID.fromString(resultSet.getString("id")));
            product.setDescription(resultSet.getString("description"));

            return product;
        }
    }

    class Product {
        String id;
        String description;
        String picture;
        BigDecimal price;

        public Product(UUID id) {
            this.id = id.toString();
        }

        public String getId() {
            return id;
        }

        public Product setId(String id) {
            this.id = id;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Product setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getPicture() {
            return picture;
        }

        public Product setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Product setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
    }
}
