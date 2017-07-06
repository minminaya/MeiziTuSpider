package com.minminaya;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * meitu爬虫测试
 * Created by Niwa on 2017/7/1.
 */
public class MeituRepoProcessor implements PageProcessor {


    public MeituRepoProcessor() {
    }

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
        List<String> urls = page.getHtml().css("#maincontent").links().regex("(http:\\/\\/www\\.meizitu\\.com\\/a\\/(\\d{4})\\.html)").all();
//        List<String> urls = page.getHtml().regex("(http://www\\.meizitu\\.com\\/a\\/(.*?)\\.html)").all();
        page.addTargetRequests(urls);

//        page.putField("picName", page.getHtml().links().xpath("//*[@id=\"picture\"]").regex("(http:\\/\\/mm\\.howkuai\\.com\\/wp-content\\/uploads\\/(.*)\\.jpg)").all());

        page.putField("webTitle", page.getHtml().xpath("//*[@id=\"maincontent\"]/div[1]/div[1]/h2/a/text()"));
        //先用xpath过滤到具体大的块，接着正则表达式提取出来
        page.putField("picName", page.getHtml().xpath("//*[@id=\"picture\"]/p/img[@src]").regex("(http:\\/\\/mm\\.howkuai\\.com\\/wp-content\\/uploads\\/(.*)\\.jpg)").all());

        if (page.getResultItems().get("picName") == null) {
            page.setSkip(true);
        }

        List<String> nextUrls = page.getHtml().regex("(\\/a\\/more_(.*?)\\.html)").all();
        page.putField("nextUrls", nextUrls);
        page.addTargetRequests(nextUrls);


    }

    public Site getSite() {
        return site;
    }
}
