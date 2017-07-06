package com.minminaya;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Niwa on 2017/6/16.
 */
public class GithubRepoPageProcessor implements PageProcessor {


    public GithubRepoPageProcessor() {
    }

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    public void process(Page page) {
        page.putField("readme", page.getHtml().css("body > div.content.wrap > section.ppcs.phdnews.clearfix > div.phdnews_txt.fr > div:nth-child(2)").toString());

    }

    public Site getSite() {
        return site;
    }
}
