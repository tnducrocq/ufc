package com.fcannizzaro.jsoup.sample.model;

import com.fcannizzaro.jsoup.annotations.interfaces.Attr;
import com.fcannizzaro.jsoup.annotations.interfaces.Child;
import com.fcannizzaro.jsoup.annotations.interfaces.Selector;
import com.fcannizzaro.jsoup.annotations.interfaces.Text;

/**
 * Created by Francesco Cannizzaro (fcannizzaro)
 */


@Selector(".marginalia-container:has(img)")
public class Package {

    @Selector(".type-sm")
    public static class Info {

        @Text("a[href*='package']")
        public String version;

        @Text("a[href*='~']")
        public String author;

    }

    @Attr(query = "h3 a", attr = "href")
    public String link;

    @Text(value = ".bidon", optional = true)
    public String name;

    @Text("p.type-ellipsis")
    public String description;

    @Attr(query = "img.mts", attr = "src")
    public String icon;

    @Child
    public Info info;

}
