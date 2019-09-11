package servlet;

import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import pojo.Count;
import service.count.CountService;
import service.count.impl.CountServiceImpl;
import test.NewUserMapperTest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountServlet extends HttpServlet {
    CountService countService=new CountServiceImpl();
    /*引入日志文件*/
    public Logger logger=Logger.getLogger(CountServlet.class);

    public void doGet(HttpServletRequest request
            , HttpServletResponse response) throws IOException {
        List<Count> list= countService.getCountByDateDescOrLimitOne();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Integer> map=new HashMap<>();
        PrintWriter out=response.getWriter();
        if(list.size()>0){
            Count count=list.get(0);
            /*今天是否是第一次有人进入页面*/
            if(!sdf.format(count.getDate()).equals(sdf.format(new Date()))){
                Count newsCount=new Count(new Date(),1,count.getSumCount()+1);
                countService.addCountByDate(newsCount);
                map.put("sumCount",count.getSumCount()+1);
            }else{
                /*获取ip*/
                InetAddress addIp=InetAddress.getLocalHost();
                Object ip= request.getSession().getAttribute("ip");
                if(ip==null){
                    /*第一次入页面*/
                    request.getSession().setAttribute("ip",addIp.getHostAddress());
                    Count newsCount=new Count(count.getId(),count.getDate(),count.getCount()+1,count.getSumCount()+1);
                    int rows=countService.updateCountById(newsCount);
                    map.put("sumCount",count.getSumCount()+1);
                }else{
                    /*session 未过期时再次进入相关页面*/
                    map.put("sumCount",count.getSumCount());
                }
            }
            out.print(JSONArray.toJSONString(map));
        }
    }

    public void doPost(HttpServletRequest request
            , HttpServletResponse response) throws IOException {
       doGet(request,response);
    }
}
