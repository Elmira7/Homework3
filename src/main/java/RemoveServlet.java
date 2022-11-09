import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RemoveServlet", value = "/remove")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserStorage userStorage = new UserStorage();
        PublicationStorage publicationStorage = new PublicationStorage();

        request.setAttribute("users", userStorage.findAllUser());
        request.setAttribute("publications", publicationStorage.findAllPublication());
        request.getRequestDispatcher("WEB-INF/remove.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserStorage userStorage = new UserStorage();
        PublicationStorage publicationStorage = new PublicationStorage();

        if (request.getParameter("object") == null
                && (!request.getParameter("object").equals("user")
                || !request.getParameter("object").equals("publication"))){

            session.setAttribute("error", "чтения данных");
            response.sendRedirect("/error");

        } else {

            if(request.getParameter("object").equals("publication")){

                String[] publications = request.getParameterValues("id");
                Long id;
                Publication publication;

                for (int i = 0; i < publications.length; i++) {
                    id = Long.parseLong(publications[i]);
                    publication = publicationStorage.findPublication(id);
                    if (publication == null){
                        session.setAttribute("error", "чтения данных");
                        response.sendRedirect("/error");
                    } else {
                        publicationStorage.removePublication(id);
                    }
                }

                response.sendRedirect("/");

            } else if(request.getParameter("object").equals("user")){

                String[] users = request.getParameterValues("id");
                Long id;
                User user;

                for (int i = 0; i < users.length; i++) {
                    id = Long.parseLong(users[i]);
                    user = userStorage.findUser(id);
                    if (user == null){
                        session.setAttribute("error", "чтения данных");
                        response.sendRedirect("/error");
                    } else {
                        userStorage.removeUser(id);
                    }
                }

                response.sendRedirect("/");

            }

        }
    }
}
