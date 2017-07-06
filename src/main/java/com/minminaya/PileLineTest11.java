package com.minminaya;

import com.minminaya.model.MeiTuModel;
import com.minminaya.model.PicInfo;
import com.minminaya.utils.DownLoadImage;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ·
 * Created by Niwa on 2017/6/30.
 */
public class PileLineTest11 implements Pipeline {
    public PileLineTest11() {
        System.out.println("PileLineTest11初始化");

    }

    int downLoadPosition = 1;
    List<MeiTuModel> meiTuModels = new ArrayList<MeiTuModel>();
    String webtitle;

    public void process(ResultItems resultItems, Task task) {
//        System.out.println("图片抓取页为：" + resultItems.getRequest().getUrl());
        List<PicInfo> picInfos = new ArrayList<PicInfo>();

//        System.out.println(resultItems.get("webTitle"));
        List<String> urls = resultItems.get("picName");
        for (int i = 0; i < urls.size(); i++) {
            picInfos.add(new PicInfo(urls.get(i), "pic" + i));
        }

        MeiTuModel meiTuModel = new MeiTuModel(picInfos, resultItems.get("webTitle").toString());

        System.out.println("抓取图片序号为" + downLoadPosition + "开始");

        try {
            for (int i = 0; i < urls.size(); i++) {
//                DownLoadImage.downLoad(urls.get(i), "pic" + i + ".jpg", "D:\\webmagic\\Spider3\\pic" + downLoadPosition);
                DownLoadImage.downLoad(urls.get(i), "pic" + i + ".jpg", "D:\\webmagic\\Spider5\\" + resultItems.get("webTitle"));
            }
            downLoadPosition++;
        } catch (IOException e) {
            e.printStackTrace();
        }

        meiTuModels.add(meiTuModel);
        System.out.println("当前集合中地址值为" + meiTuModel.getPicInfos().get(3).getPicUrl());
        System.out.println("大集合大小" + meiTuModels.size());
        System.out.println("大集合值为" + meiTuModels.get(2).getPicInfos().get(3).getPicUrl());
        System.out.println("大集合值title值为" + meiTuModels.get(2).getWebTitle());

        if(downLoadPosition == 10){
            System.out.println("到达10次");
        }

        System.out.println("抓取图片序号为" + downLoadPosition + "结束");
    }
}
