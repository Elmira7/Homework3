import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomePageServlet", value = "/")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationStorage publicationStorage = new PublicationStorage();
        UserStorage userStorage = new UserStorage();
        LikeStorage likeStorage = new LikeStorage();
        String catalog = null;
        HttpSession session = request.getSession();
        List<Publication> publications = new ArrayList<>();
        List<User> users = new ArrayList<>();

        if (request.getParameter("filter") != null){

            if (request.getParameter("filter").equals("publication")){
                Long author = null;
                String date = null;
                if (request.getParameter("author") == null || request.getParameter("author").equals("")){
                    author = null;
                } else {
                    author = Long.parseLong(request.getParameter("author"));
                }

                if (request.getParameter("date") == null || request.getParameter("date").equals("")){
                    date = null;
                } else {
                    date = request.getParameter("date");
                }

                users = userStorage.findAllUser();
                publications = publicationStorage.findPublicationByFilter(author, date);

            } else if (request.getParameter("filter").equals("user")){
                String name = null;
                String phone = null;
                if (request.getParameter("name") == null || request.getParameter("name").equals("")){
                    name = null;
                } else {
                    name = request.getParameter("name");
                }

                if (request.getParameter("phone") == null || request.getParameter("phone").equals("")){
                    phone = null;
                } else {
                    phone = request.getParameter("phone");
                }

                users = userStorage.findAllUser();

            }

        } else {
            publications = publicationStorage.findAllPublication();
            users = userStorage.findAllUser();
        }

        if (request.getParameter("object") == null){

            catalog = "publication";

        } else if (request.getParameter("object").equals("publication")
                || request.getParameter("object").equals("user")
                || request.getParameter("object").equals("like")){

            catalog = request.getParameter("object");

        } else {

            session.removeAttribute("error");
            //request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);

        }

        request.setAttribute("users", users);
        request.setAttribute("catalog", catalog);
        request.setAttribute("publications", publications);
        request.setAttribute("likes", likeStorage.findAllLikes());
        request.getRequestDispatcher("WEB-INF/homepage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
