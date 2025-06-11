    package co.edu.poli.ces3.socrates.socrates.servlet;

    import co.edu.poli.ces3.socrates.socrates.dao.User;
    import co.edu.poli.ces3.socrates.socrates.services.UserService;
    import com.google.gson.Gson;
    import com.google.gson.JsonObject;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.lang.reflect.Constructor;
    import java.lang.reflect.Field;
    import java.util.List;

    @WebServlet(name = "userServlet", value = "/users")
    public class UserServlet extends Servlet {

        private UserService userService = new UserService();
        private Gson gson = new Gson();

        public void init() {
            System.out.println("Users up");
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            List<User> users = userService.getUsers();

            out.print(gson.toJson(users));
        }

        @Override
        protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();

            JsonObject jsonUser = getParamsFromBody(req);

            Class<?> classUser = User.class;

            Field[] fields = classUser.getDeclaredFields();

            User userUpdate = new User();
            try {
                for(Field f : fields) {
                    if(jsonUser.has(f.getName())){
                        Class<?> fieldType = f.getType();
                        Object value = convertJsonElementToFieldType(jsonUser.get(f.getName()), fieldType);
                        f.setAccessible(true);
                        f.set(userUpdate, value);

                    }
                }

                userUpdate.setId_user(Integer.parseInt(req.getParameter("id_user")));

                User user = userService.upgrade(userUpdate);


                String json = gson.toJson(user);

                out.print(json);
                out.flush();

            }catch(Exception err) {
                err.printStackTrace();
            }
            out.flush();
        }
    }
