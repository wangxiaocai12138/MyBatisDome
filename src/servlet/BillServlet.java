package servlet;

import com.alibaba.fastjson.JSONArray;
import pojo.Bill;
import pojo.Provider;
import pojo.User;
import service.bill.BillService;
import service.bill.impl.BillServiceImpl;
import service.provider.ProviderService;
import service.provider.impl.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//订单管理页面控制器
public class BillServlet extends HttpServlet {
    BillService billService=new BillServiceImpl();
    ProviderService providerService=new ProviderServiceImpl();

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        if("query".equals(method)){
            query(request,response);
        }else if("getproviderlist".equals(method)){
            getproviderlist(request,response);
        }else if("add".equals(method)){
            add(request,response);
        }else if("delbill".equals(method)){
            delbill(request,response);
        }else if("view".equals(method)){
            view(request,response);
        }else if("modify".equals(method)){
            modify(request,response);
        }else if("modifysave".equals(method)){
            modifysave(request,response);
        }

    }
    /*修改*/
    private void modifysave(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.valueOf(request.getParameter("id"));
        String billCode=request.getParameter("billCode");
        String productName=request.getParameter("productName");
        String productUnit=request.getParameter("productUnit");
        BigDecimal productCount=new BigDecimal(request.getParameter("productCount"));
        BigDecimal totalPrice=new BigDecimal(request.getParameter("totalPrice"));
        int providerId= Integer.valueOf(request.getParameter("providerId"));
        int isPayment=Integer.valueOf(request.getParameter("isPayment"));
        User user=(User) request.getSession().getAttribute("userSession");

        Bill bill = new Bill();
        bill.setId(id);
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setModifyBy(user.getId());
        bill.setModifyDate(new Date());

        int rows=billService.modifyBillById(bill);
        if(rows>0){
            request.setAttribute("messigs","修改成功！");
        }else{
            request.setAttribute("messigs","修改失败！");
        }
        try {
            request.getRequestDispatcher("bill.do?method=query").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    /*跳转修改页面*/
    private void modify(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.valueOf(request.getParameter("billid"));
        Bill bill=billService.qureBillById(id);
        request.setAttribute("bill",bill);
        try {
            request.getRequestDispatcher("billmodify.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*查看*/
    private void view(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.valueOf(request.getParameter("billid"));
        Bill bill=billService.qureBillById(id);
        request.setAttribute("bill",bill);
        try {
            request.getRequestDispatcher("billview.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*删除*/
    private void delbill(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.valueOf(request.getParameter("billid"));
        int rows=billService.delBillById(id);
        Map<String,String> map=new HashMap<>();
        PrintWriter out=null;
        try {
            out=response.getWriter();
            if(rows>0){
                map.put("delResult","true");
            }else{
                map.put("delResult","false");
            }
            out.print(JSONArray.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    /*添加*/
    private void add(HttpServletRequest request, HttpServletResponse response) {
        String billCode=request.getParameter("billCode");
        String productName=request.getParameter("productName");
        String productUnit=request.getParameter("productUnit");
        BigDecimal productCount=new BigDecimal(request.getParameter("productCount"));
        BigDecimal totalPrice=new BigDecimal(request.getParameter("totalPrice"));
        int providerId= Integer.valueOf(request.getParameter("providerId"));
        int isPayment=Integer.valueOf(request.getParameter("isPayment"));
        User user=(User) request.getSession().getAttribute("userSession");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setCreationDate(new Date());
        bill.setCreatedBy(user.getId());
        bill.setModifyBy(user.getId());
        bill.setModifyDate(new Date());

        int rows=billService.addBill(bill);
        if(rows>0){
            request.setAttribute("messigs","添加成功！");
        }else{
            request.setAttribute("messigs","添加失败！");
        }
        try {
            request.getRequestDispatcher("bill.do?method=query").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /*添加下拉框ajax*/
    private void getproviderlist(HttpServletRequest request, HttpServletResponse response) {
        List<Provider> providerList=providerService.qureProviderAll(null,null);
        try {
            PrintWriter out=response.getWriter();
            out.print(JSONArray.toJSONString(providerList));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    //查询所有订单
    private void query(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        String productName=request.getParameter("queryProductName");
        String p=request.getParameter("queryProviderId");
        String i=request.getParameter("queryIsPayment");
        Integer providerId= 0;
        Integer isPayment= 0;
        if(p!=null){
            providerId= Integer.valueOf(p);
        }
        if(i!=null){
            isPayment=Integer.valueOf(i);
        }


        List<Bill> list=billService.qureBillAll(productName,providerId,isPayment);
        List<Provider> providerList=providerService.qureProviderAll(null,null);
        request.setAttribute("providerList",providerList);
        request.setAttribute("billList",list);
        request.getRequestDispatcher("billlist.jsp").forward(request,response);
    }

}
