package com.order.web.modules.config.controller;


import com.order.web.bean.ApiResult;
import com.order.web.modules.config.service.DocumentService;
import com.order.web.util.ApiTools;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    DocumentService documentService;

    @RequestMapping("/port")
    public ApiResult getConfig(){
        Document document = null;
        String message = "success";
        Object port = null;
        try {
            document = documentService.getReader().read(new FileReader("config.xml"));
            List nodes = document.selectNodes("/port");
            for (Iterator iter = nodes.iterator();iter.hasNext();){

               port = ((Element)iter.next()).attribute("port").getData();
            }
            Element root = document.getRootElement();
            // 遍历节点
            for (Iterator iter = root.elementIterator();iter.hasNext();){
                System.out.println("cargo");
                iter.next();
            }
        } catch (DocumentException e) {
            message = "配置文件格式错误";
        } catch (FileNotFoundException e) {
            message = "配置文件不存在";
        }
        return ApiTools.result(1000,message,Integer.parseInt(port.toString()));
    }


    @RequestMapping(value = "/port", method = RequestMethod.POST)
    public ApiResult createConfig(){
        Document document = DocumentHelper.createDocument();
        Element ports = document.addElement("port");
        ports.addComment("定义端口");
        ports.addElement("logo").addAttribute("type","svg");
        ports.addAttribute("port","8080").addAttribute("table","prefix");


        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter("config.xml");

            document.write(fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ApiTools.result(1000, "Create the new config file.", null);
    }
}
