import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    public void addUser(User user) {
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, phone, mail) values (?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ?, phone = ?, mail = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAllUser(){
        List<User> users = new ArrayList<>();

        try(Connection connection = DataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("select * from users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("mail")));
            }

        } catch (SQLException e){
            throw new RuntimeException();
        }

        return users;
    }

    public User findUser(Long id){
        User user = null;

        try (Connection connection = DataSource.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("mail"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public void removeUser(Long id){
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
