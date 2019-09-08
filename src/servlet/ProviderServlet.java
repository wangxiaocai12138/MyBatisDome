package servlet;

import pojo.Provider;
import pojo.User;
import service.provider.ProviderService;
import service.provider.impl.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ProviderServlet extends HttpServlet {
   ProviderService providerService=new ProviderServiceImpl();
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
       String method=request.getParameter("method");
       response.getWriter().print("你好");
       System.out.println("_+_+__+_++----------"+method);
       if("query".equals(method)){
            query(request,response);
       }else if("add".equals(method)){
           System.out.println("_+_+__+_++----------ss");
            add(request,response);
       }
   }
    //添加
   private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proCode=request.getParameter("proCode");
        String proName=request.getParameter("proName");
        String proContact=request.getParameter("proContact");
        String proPhone=request.getParameter("proPhone");
        String proAddress=request.getParameter("proAddress");
        String proFax=request.getParameter("proFax");
        String proDesc=request.getParameter("proDesc");
        User user=(User) request.getSession().getAttribute("userSession");
        Provider provider=new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setCreatedBy(user.getId());
        provider.setCreationDate(new Date());
        provider.setModifyBy(user.getId());
        provider.setModifyDate(new Date());
        System.out.println("_+_+__+_++----------");
        int rows=providerService.addProvider(provider);

        if(rows>0){
            request.setAttribute("info","添加成功！");
        }else{
            request.setAttribute("info","添加失败！");
        }
        request.getRequestDispatcher("provider.do?method=query").forward(request,response);
   }

    //查询
   private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String queryProCode=request.getParameter("queryProCode");
         String queryProName=request.getParameter("queryProName");
         List<Provider> list=providerService.qureProviderAll(queryProCode,queryProName);
         request.setAttribute("providerList",list);
         request.getRequestDispatcher("providerlist.jsp").forward(request,response);
   }

   public void doPost(HttpServletRequest request,
                     HttpServletResponse response){

   }
}
