package com.minminaya.utils;

import com.minminaya.MeituRepoProcessor;
import com.minminaya.PineLineMeizi;
import us.codecraft.webmagic.Spider;

/**
 * Created by Niwa on 2017/7/9.
 */
public class RefreshDataBase {

    public static void refresh() {
        SqlUtilForReBuild.rebuild();

        Spider.create(new MeituRepoProcessor())
                .addUrl("http://www.meizitu.com/")
                .addPipeline(new PineLineMeizi())
                .thread(1)
                .run();
    }


}
