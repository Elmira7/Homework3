import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PublicationStorage {

    public List<Publication> findAllPublication(){
        List<Publication> publications = new ArrayList<>();

        try(Connection connection = DataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("select * from publication");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                publications.add(new Publication(resultSet.getLong("id"),
                        resultSet.getLong("id_author"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getDate("date")));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }

        return publications;
    }


    public Publication findPublication(Long id){
        Publication publication = null;

        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from publication where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publication = new Publication(resultSet.getLong("id"),
                        resultSet.getLong("id_author"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return publication;
    }

    public List<Publication> findPublicationByFilter(Long author, String date){
        List<Publication> publications = new ArrayList<>();
        try(Connection connection = DataSource.getConnection()){

            String sqlSelect = "select * from publication";
            if (author != null || date != null){
                sqlSelect += " where";
                if (author != null){
                    sqlSelect += " id_author = ?";
                }
                if (date != null){
                    if (author != null) sqlSelect += " and";
                    sqlSelect += " date = ?";
                }
            }

            PreparedStatement statement = connection.prepareStatement(sqlSelect);

            int attr = 1;

            if (author != null){
                statement.setLong(attr, author);
                attr++;
            }
            if (date != null){
                statement.setDate(attr, Date.valueOf(date));
            }



            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                publications.add(new Publication(resultSet.getLong("id"),
                        resultSet.getLong("id_author"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getDate("date")));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }

        return publications;

    }

    public void updatePublication(Publication publication){
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("update publication set id_author = ?, date = ?, title = ?, body = ? where id = ?");
            preparedStatement.setLong(1, publication.getIdAuthor());
            preparedStatement.setDate(2, (Date) publication.getDate());
            preparedStatement.setString(3, publication.getTitle());
            preparedStatement.setString(4, publication.getBody());
            preparedStatement.setLong(5, publication.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addPublication(Publication publication) {
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into publication (id_author, date, title, body) values (?, now(), ?, ?)");
            preparedStatement.setLong(1, publication.getIdAuthor());
            preparedStatement.setString(2, publication.getTitle());
            preparedStatement.setString(3, publication.getBody());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePublication(Long id){
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from publication where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
