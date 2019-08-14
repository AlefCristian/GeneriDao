/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module GenericDao {
    requires sqlite.jdbc;
    requires mysql.connector.java;
    requires gradle.simple;
    requires protobuf.java;
    requires guava;
    requires java.sql;
    exports dao.jdbc;
    exports model;
    exports dao;
}
