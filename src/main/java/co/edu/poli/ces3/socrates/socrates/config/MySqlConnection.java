package co.edu.poli.ces3.socrates.socrates.config;

import co.edu.poli.ces3.socrates.socrates.utils.annotations.Column;
import co.edu.poli.ces3.socrates.socrates.utils.annotations.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public abstract class MySqlConnection {

    private String user;
    private String password;
    private String host;
    private String dataBase;
    private int port;
    private String url;

    private Connection connection;

    public MySqlConnection() throws Exception {
        this.user = "us_user";
        this.password = "ces2025";
        this.dataBase = "socratesdb";
        this.host = "localhost";
        this.port = 3306;
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dataBase;

        Boolean result = this.connect();

        if(!result){
            throw new Exception("Error connecting db");
        }
    }

    public boolean connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Conección exitosa!!");
            return true;
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public abstract void disconnect();

    protected Connection getConnection(){
        return this.connection;
    }

    public QueryResult getQueryUpdateAndParams(Object data, Class<?> clazz) {
        boolean hasFieldsToUpdate = false;
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        LinkedList<Object> valuesFieldsToUpdate = new LinkedList<>();
        LinkedList<Object> valuesFieldsPrimaryKey = new LinkedList<>();

        if(tableAnnotation != null){
            StringBuilder sql = new StringBuilder("UPDATE " + tableAnnotation.name() + " SET ");
            StringBuilder sqlWhere = new StringBuilder(" WHERE ");

            try {
                for (Field field: clazz.getDeclaredFields()) {
                    Column column = field.getAnnotation(Column.class);
                    field.setAccessible(true);
                    if(column != null && field.get(data) != null){
                        if(!column.primaryKey()) {
                            sql.append(column.name()).append(" = ?, ");
                            valuesFieldsPrimaryKey.add(field.getName());
                            hasFieldsToUpdate = true;
                        } else {
                            sqlWhere.append(column.name()).append(" = ? AND ");
                            valuesFieldsPrimaryKey.add(field.get(data));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            if(hasFieldsToUpdate) {
                sql.deleteCharAt(sql.length()-1);
                sqlWhere.deleteCharAt(sql.length()-1);

                sql.append(sqlWhere);

                valuesFieldsToUpdate.addAll(valuesFieldsPrimaryKey);

                return new QueryResult(sql.toString(), valuesFieldsToUpdate);
            }

            return null;
        } else {
            throw new IllegalStateException("Class " + clazz.getSimpleName() + " is missing @Table annotation");
        }
    }
}
