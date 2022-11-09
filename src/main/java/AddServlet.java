import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserStorage userStorage = new UserStorage();
        LikeStorage likeStorage = new LikeStorage();
        PublicationStorage publicationStorage = new PublicationStorage();

        if (request.getParameter("object") == null
                && (!request.getParameter("object").equals("user")
                || !request.getParameter("object").equals("publication"))){

            session.setAttribute("error", "чтения данных");
            response.sendRedirect("/error");

        } else {
            try {
                if (request.getParameter("object").equals("user")) {

                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String mail = request.getParameter("mail");
                    User user = new User(name, phone, mail);
                    userStorage.addUser(user);
                    response.sendRedirect("/?object=user");

                } else if (request.getParameter("object").equals("publication")) {

                    Long author = Long.parseLong(request.getParameter("author"));
                    String title = request.getParameter("title");
                    String body = request.getParameter("body");
                    publicationStorage.addPublication(new Publication(author, title, body, new Date()));
                    response.sendRedirect("/");

                } else if (request.getParameter("object").equals("like")) {

                    Long author = Long.parseLong(request.getParameter("author"));
                    Long post = Long.parseLong(request.getParameter("post"));
                    if (likeStorage.findLike(author, post) == null) {
                        likeStorage.addLike(new Like(author, post));
                    }
                    response.sendRedirect("/?object=like");

                } else {
                    session.setAttribute("error", "чтения данных");
                    response.sendRedirect("/error");
                }
            } catch (NumberFormatException e) {

                session.setAttribute("error", "чтения данных");
                response.sendRedirect("/error");

            }
        }

    }
}
