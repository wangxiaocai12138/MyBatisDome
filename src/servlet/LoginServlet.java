package servlet;

import pojo.User;
import service.user.UserService;
import service.user.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录页面控制器
public class LoginServlet extends HttpServlet {
    UserService service=new UserServiceImpl();
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String userCode=request.getParameter("userCode");
        String userPassword=request.getParameter("userPassword");
        System.out.println(userCode+"-------"+userPassword);
        User lUser=new User();
        lUser.setUserCode(userCode);
        lUser.setUserPassword(userPassword);
        User user =service.loginByCodeAndPwd(lUser);
        if (user!=null){
            request.getSession().setAttribute("userSession",user);
                response.sendRedirect("jsp/frame.jsp");
        }else{
            request.setAttribute("error","用户名或者密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
