package com.ssafy.api.controller;

import com.ssafy.db.entity.outLink;
import com.ssafy.api.service.UserServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/open")
public class JsoupController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping("/")
    public ResponseEntity<Map<String, List<String>>> getOpenGraphTag(@RequestParam String link){
        Document doc;
        String url = link;
        Map<String, List<String>> result = new HashMap<String,List<String>>();
        System.out.println("Jsoup 들어옴");
        try {
            doc = Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")
                    .get();
            Elements ogElements = doc.select("meta[property^=og], meta[name^=og]");
            for (Element e : ogElements) {
                String target= e.hasAttr("property") ? "property" : "name";

                if(!result.containsKey(e.attr(target))){
                    result.put(e.attr(target), new ArrayList<String>());
                }
                result.get(e.attr(target)).add(e.attr("content"));
            }

            for(String s : result.keySet())
                System.out.println(s + " : " + result.get(s));

            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/links")
    public ResponseEntity<List<outLink>> getLinks(@RequestParam String id){
        List<outLink> linkList = userServiceImpl.getLinks(id);
        return ResponseEntity.ok().body(linkList);
    }

    @GetMapping("/addLink")
    public ResponseEntity<outLink> addLink(@RequestParam String id, @RequestParam String link){
        Map<String, List<String>> res = this.getOpenGraphTag(link).getBody();

        outLink outLink = new outLink();
        outLink.setUserId(id);

//        System.out.println("링크!!: " + res.get("og:link"));
        for(String a : res.keySet()){
            System.out.println("요소: " + a);
        }
        outLink.setLink(res.get("og:url").get(0));
        outLink.setTitle(res.get("og:title").get(0));
        outLink.setImage(res.get("og:image").get(0));

        userServiceImpl.addLink(outLink);

        return ResponseEntity.ok().body(outLink);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userServiceImpl.delete(id);
        return ResponseEntity.ok().build();
    }
}
