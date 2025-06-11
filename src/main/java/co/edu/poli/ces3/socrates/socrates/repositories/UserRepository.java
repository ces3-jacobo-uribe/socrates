package co.edu.poli.ces3.socrates.socrates.repositories;

import co.edu.poli.ces3.socrates.socrates.config.MySqlConnection;
import co.edu.poli.ces3.socrates.socrates.config.QueryResult;
import co.edu.poli.ces3.socrates.socrates.dao.User;
import co.edu.poli.ces3.socrates.socrates.interfaces.ICrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends MySqlConnection implements ICrud {

    public UserRepository() throws Exception {}

    @Override
    public void disconnect() {

    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public Object update(User user) throws SQLException {
        QueryResult queryResult = getQueryUpdateAndParams(user, User.class);

        if(queryResult != null) {
            PreparedStatement preparedStatement = this.getConnection()
                    .prepareStatement(queryResult.getSql());

            int i = 1;

            for (Object value: queryResult.getParameters()) {
                preparedStatement.setObject(i++, value);
            }

            if(preparedStatement.executeUpdate() > 0) {
                return this.findById(user.getId_user());
            }
        }

        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Object findById(int id) {
        String query = "select * from users WHERE id_user";
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String query = "select * from users";
        Connection connection = this.getConnection();
        PreparedStatement statement = connection
                .prepareStatement(query);
        ResultSet result = statement.executeQuery();

        List<User> list = new ArrayList<User>();

        while(result.next()) {
            list.add(
                    new User(
                            result.getInt("id_user"),
                            result.getString("name"),
                            result.getString("lastname"),
                            result.getString("password"),
                            result.getDate("birthdate"),
                            result.getString("email"),
                            result.getBoolean("is_active"),
                            result.getString("phone"),
                            result.getString("gender"),
                            result.getDate("created_at"),
                            result.getDate("updated_at"),
                            result.getDate("deleted_at")
                    )
            );
        }

        return list;
    }
}
