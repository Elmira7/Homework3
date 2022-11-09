import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeStorage {

    public List<Like> findAllLikes(){
        List<Like> likes = new ArrayList<>();
        try(Connection connection = DataSource.getConnection()){
            UserStorage userStorage = new UserStorage();
            PublicationStorage publicationStorage = new PublicationStorage();
            PreparedStatement statement = connection.prepareStatement("select * from likes");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long idUser = resultSet.getLong("id_user");
                Long idPost = resultSet.getLong("id_publication");
                likes.add(new Like(
                        idUser,
                        idPost,
                        resultSet.getDate("date"),
                        userStorage.findUser(idUser).getName(),
                        publicationStorage.findPublication(idPost).getTitle()
                ));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return likes;
    }

    public void addLike(Like like){
        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into likes (id_user, id_publication, date) values (?, ?, now())");
            preparedStatement.setLong(1, like.getIdAuthor());
            preparedStatement.setLong(2, like.getIdPost());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Like findLike(Long author, Long post){
        Like like = null;
        UserStorage userStorage = new UserStorage();
        PublicationStorage publicationStorage = new PublicationStorage();

        try (Connection connection = DataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from likes where id_user = ? and id_publication = ?");
            preparedStatement.setLong(1, author);
            preparedStatement.setLong(2, post);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long idUser = resultSet.getLong("id_user");
                Long idPost = resultSet.getLong("id_publication");
                like = new Like(
                        idUser,
                        idPost,
                        resultSet.getDate("date"),
                        userStorage.findUser(idUser).getName(),
                        publicationStorage.findPublication(idPost).getTitle()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return like;
    }

    public void updateLike(Like like){

        if (findLike(like.getIdAuthor(), like.getIdPost()) == null){
            return;
        }
        try (Connection connection = DataSource.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("update likes set date = now() where id_publication = ? and id_user = ?");
            preparedStatement.setLong(1, like.getIdPost());
            preparedStatement.setLong(2, like.getIdAuthor());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
