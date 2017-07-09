package com.minminaya;

import com.minminaya.model.MeiTuModel;
import com.minminaya.model.User;
import com.minminaya.utils.SqlUtilForReBuild;
import com.minminaya.utils.SqlUtilForSelectData;
import us.codecraft.webmagic.Spider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * */
@Path("hello")
public class HelloJsersy {
    @GET
    @Produces("application/json;charset=utf8")
    public String getString() {

//        Spider.create(new MeituRepoProcessor())
//                .addUrl("http://www.meizitu.com/")
////                .addPipeline(new ConsolePipeline())
//                .addPipeline(new PineLineMeizi())
////                .addPipeline(new PileLineTest11())
//                .thread(1)
//                .run();
        return "hello jersey";
    }

    @GET
    @Path("{username}")
    @Produces("application/json;charset=utf8")
    public String getParam(@PathParam("username") String username) {
        return "hello  " + username;
    }

    @GET
    @Path("/params")
    @Produces("application/json;charset=utf8")
//    @Produces("text/plain")
    public String getMultiParam(@QueryParam("name") String name) {
        return "hello  " + name;
    }

    @GET
    @Path("/getuser")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf8")
    public List<MeiTuModel> getUserJson() {
        //下面注释的是测试代码
//        List<User> users = new ArrayList<User>();
//        User user = new User();
//        user.setAge(27);
//        user.setUserid("005");
//        user.setUsername("Fmand");
//
//
//        for (int i = 0; i < 5; i++) {
//            users.add(user);
//        }
        //        return users;

        SqlUtilForSelectData sqlUtilForSelectData = new SqlUtilForSelectData();
        List<MeiTuModel> meiTuModels = sqlUtilForSelectData.selectDataFormeiziweboneTable(1, 2);
        return meiTuModels;
    }


    @GET
    @Path("/pic")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf8")
    public List<MeiTuModel> getMeiziJson(@QueryParam("size") int size,
                                         @QueryParam("offset") int offset) {

        int realStart = size * (offset - 1) + 1;
        int realEnd = realStart + size - 1;

        SqlUtilForSelectData sqlUtilForSelectData = new SqlUtilForSelectData();
        List<MeiTuModel> meiTuModels = sqlUtilForSelectData.selectDataFormeiziweboneTable(realStart, realEnd);
        return meiTuModels;
    }


    @GET
    @Path("/refreshDataBase")
    @Produces("application/json;charset=utf8")
    public String refreshDatabase() {
        long time1 = System.currentTimeMillis();

        SqlUtilForReBuild.rebuild();

        Spider.create(new MeituRepoProcessor())
                .addUrl("http://www.meizitu.com/")
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new PineLineMeizi())
//                .addPipeline(new PileLineTest11())
                .thread(1)
                .run();


        long time2 = System.currentTimeMillis();

        return "刷新成功，用时：" + (time2 - time1);
    }
}