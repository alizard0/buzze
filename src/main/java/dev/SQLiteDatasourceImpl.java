package dev;

import auth.Credentials;
import auth.Datasource;
import auth.Salt;
import auth.Token;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static util.AuthUtil.hash;
import static util.CheckUtil.isNotNull;

public class SQLiteDatasourceImpl implements Datasource {

    private Connection c;
    private boolean autoCommit = false;

    public SQLiteDatasourceImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public SQLiteDatasourceImpl withAutoCommit(final boolean autocommit) {
        this.autoCommit = autocommit;
        return this;
    }

    private void initConnection() throws SQLException {
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        c.setAutoCommit(autoCommit);
    }

    private void closeConnection() throws SQLException {
        c.close();
    }

    public void createTable(final String query) throws SQLException {
        executeUpdate(query);
    }

    public void initDatabase() throws SQLException {
        createTable("CREATE TABLE " + Token.class.getName() + " (email CHAR(256) PRIMARY KEY NOT NULL, token CHAR(256) NOT NULL)");
        createTable("CREATE TABLE " + Salt.class.getName() + " (email CHAR(256) PRIMARY KEY NOT NULL, salt CHAR(256) NOT NULL)");
        createTable("CREATE TABLE " + Credentials.class.getName() + " (email CHAR(256) PRIMARY KEY NOT NULL, hashedPassword CHAR(256) NOT NULL)");
    }

    public void executeUpdate(final String query) throws SQLException {
        isNotNull(query);
        initConnection();
        Statement stmt = c.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
        closeConnection();
    }

    public void insert(final String query) throws SQLException {
        executeUpdate(query);
    }

    public void update(final String query) throws SQLException {
        executeUpdate(query);
    }

    public void delete(final String query) throws SQLException {
        executeUpdate(query);
    }

    public ResultSet select(final String query) throws SQLException {
        isNotNull(query);
        initConnection();
        Statement stmt = c.createStatement();
        ResultSet retval = stmt.executeQuery(query);
        stmt.close();
        closeConnection();
        return retval;
    }

    @Override
    public Token getTokenByEmail(final String email) throws SQLException {
        isNotNull(email);
        String sql = "SELECT email, token FROM " + Token.class.getName() + " WHERE email='" + email + "'";
        return queryToken(sql);
    }

    @Override
    public Token getToken(final String token) throws SQLException {
        isNotNull(token);
        String sql = "SELECT email, token FROM " + Token.class.getName() + " WHERE token='" + token + "'";
        return queryToken(sql);
    }

    private Token queryToken(final String sql) throws SQLException {
        ResultSet rs = select(sql);
        rs.next();
        return new Token(rs.getString("email"), rs.getString("token"));
    }

    @Override
    public String getHashedPassword(final String email) throws SQLException {
        isNotNull(email);
        String sql = "SELECT email, hashedPassword FROM " + Credentials.class.getName() + " WHERE email='" + email + "'";
        ResultSet rs = select(sql);
        rs.next();
        Credentials credentials = new Credentials(rs.getString("email"));
        credentials.setHashedPassword(rs.getString("hashedPassword"));
        return credentials.getHashedPassword();
    }

    @Override
    public Salt getSalt(final String email) throws SQLException {
        isNotNull(email);
        String sql = "SELECT email, salt FROM " + Salt.class.getName() + " WHERE email='" + email + "'";
        ResultSet rs = select(sql);
        rs.next();
        return new Salt(rs.getString("email"), rs.getString("salt"));
    }

    @Override
    public void persistCredentials(final Credentials credentials) throws SQLException {
        isNotNull(credentials);
        String sql = "INSERT INTO " + Credentials.class.getName()
                + " (email, hashedPassword) VALUES ('" + credentials.getEmail() + "', '" + credentials.getHashedPassword() + "')";
        insert(sql);
    }

    @Override
    public void updatePassword(final String email, final String password) throws NoSuchAlgorithmException, SQLException {
        isNotNull(email);
        Salt newSalt = new Salt(email);
        updateSalt(newSalt);
        String hashedPassword = hash(password + newSalt.getSalt());
        String sql = "UPDATE " + Credentials.class.getName() + " SET hashedPassword='" + hashedPassword + "' WHERE email='" + email + "'";
        update(sql);
    }

    @Override
    public void persistSalt(final Salt salt) throws SQLException {
        isNotNull(salt);
        String sql = "INSERT INTO " + Salt.class.getName()
                + " (email, salt) VALUES ('" + salt.getEmail() + "', '" + salt.getSalt() + "')";
        insert(sql);
    }

    @Override
    public void updateSalt(Salt salt) throws SQLException {
        isNotNull(salt);
        String sql = "UPDATE " + Salt.class.getName()
                + " SET salt ='" + salt.getSalt() + "' WHERE email='" + salt.getEmail() + "'";
        update(sql);
    }

    @Override
    public void persistToken(final Token token) throws SQLException {
        isNotNull(token);
        String sql = "INSERT INTO " + Token.class.getName()
                + " (email, token) VALUES ('" + token.getEmail() + "', '" + token.getToken() + "')";
        insert(sql);
    }

    @Override
    public void updateToken(Token token) throws SQLException {
        isNotNull(token);
        String sql = "UPDATE " + Token.class.getName()
                + " SET token ='" + token.getToken() + "' WHERE email='" + token.getEmail() + "'";
        update(sql);
    }
}
