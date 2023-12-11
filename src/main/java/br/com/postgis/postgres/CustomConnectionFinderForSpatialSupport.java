package br.com.postgis.postgres;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import lombok.SneakyThrows;
import oracle.jdbc.driver.OracleConnection;
import org.geolatte.geom.codec.db.oracle.ConnectionFinder;

import java.sql.Connection;

public class CustomConnectionFinderForSpatialSupport implements ConnectionFinder {
    @SneakyThrows
    @Override
    public Connection find(Connection connection) {
        return ((HikariProxyConnection) connection).unwrap(OracleConnection.class);
    }
}
