import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserStorage userStorage = new UserStorage();
        PublicationStorage publicationStorage = new PublicationStorage();
        LikeStorage likeStorage = new LikeStorage();

        if (request.getParameter("object") == null
                && (!request.getParameter("object").equals("users")
                || !request.getParameter("object").equals("publication"))){

            session.setAttribute("error", "чтения данных");
            response.sendRedirect("/error");

        } else {
            try {
                if (request.getParameter("publication") != null) {
                    Publication publication = publicationStorage.findPublication(Long.parseLong(request.getParameter("publication")));
                    if (publication != null) {
                        request.setAttribute("object", publication);
                        request.setAttribute("name", "publication");
                        request.setAttribute("users", userStorage.findAllUser());
                        request.setAttribute("id", publication.getId());
                        request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request, response);
                    } else {
                        session.setAttribute("error", "чтения данных");
                        response.sendRedirect("/error");
                    }
                } else if (request.getParameter("user") != null) {

                    User user = userStorage.findUser(Long.parseLong(request.getParameter("user")));
                    if (user != null){
                        request.setAttribute("object", user);
                        request.setAttribute("name", "user");
                        request.setAttribute("id", user.getId());
                        request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request, response);

                    } else {

                        session.setAttribute("error", "чтения данных");
                        response.sendRedirect("/error");

                    }
                } else if (request.getParameter("like") != null){
                    Like like = likeStorage.findLike(Long.parseLong(request.getParameter("author")), Long.parseLong(request.getParameter("post")));
                    if (like != null) {
                        likeStorage.updateLike(like);
                        response.sendRedirect("/?object=like");
                    } else {
                        session.setAttribute("error", "чтения данных");
                        response.sendRedirect("/error");
                    }

                }
            } catch (NumberFormatException e) {

                session.setAttribute("error", "чтения данных");
                response.sendRedirect("/error");

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserStorage userStorage = new UserStorage();
        PublicationStorage publicationStorage = new PublicationStorage();

        if (request.getParameter("id") == null
                ||  request.getParameter("object") == null
                || (!request.getParameter("object").equals("user")
                && !request.getParameter("object").equals("publication"))){

            session.setAttribute("error", "чтения данных");
            response.sendRedirect("/error");

        } else {



            if (request.getParameter("object").equals("user")){

                Long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String mail = request.getParameter("mail");

                User user = userStorage.findUser(id);

                if(user != null){

                    user.setMail(mail);
                    user.setName(name);
                    user.setPhone(phone);

                    userStorage.updateUser(user);

                    response.sendRedirect("/");

                } else {
                    session.setAttribute("error", "чтения данных");
                    response.sendRedirect("/error");
                }

            } else if (request.getParameter("object").equals("publication")){

                String title = request.getParameter("title");
                Long id = Long.parseLong(request.getParameter("id"));
                String body = request.getParameter("body");
                Long author = Long.parseLong(request.getParameter("author"));

                Publication publication = publicationStorage.findPublication(id);

                if (publication != null){

                    publication.setBody(body);
                    publication.setIdAuthor(author);
                    publication.setTitle(title);

                    publicationStorage.updatePublication(publication);
                    response.sendRedirect("/");
                } else {
                    session.setAttribute("error", "чтения данных");
                    response.sendRedirect("/error");

                }

            } else {

                session.setAttribute("error", "чтения данных");
                response.sendRedirect("/error");

            }

        }


    }
}
