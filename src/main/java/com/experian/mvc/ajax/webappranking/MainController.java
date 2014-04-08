package com.experian.mvc.ajax.webappranking;

import com.experian.mvc.ajax.webappranking.WebApp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.experian.helpers.CSVHelper;
import com.experian.mvc.ajax.webappranking.WebApp;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(value="/webapp")
public class MainController{
  @Autowired
  ServletContext servletContext;

  @RequestMapping(method=RequestMethod.GET)
  public @ResponseBody WebApp[] doit(@RequestParam String datepicker) {
    List<WebApp> webapplist = null;
    try{
      webapplist = CSVHelper.getWebApps(servletContext.getResourceAsStream("/WEB-INF/data.csv"));
   
    } catch (FileNotFoundException fe) {
      fe.printStackTrace();
    }
    

    if(webapplist!=null && !webapplist.isEmpty()){
      int i = 0;
      WebApp[] webapps = new WebApp[webapplist.size()];
      for(WebApp listItem : webapplist){
        if(datepicker.equals(listItem.getRankdate())){
          webapps[i++] = listItem;
        }
      }
      
      if (i == 0)
        return new WebApp[0];
      
      return webapps;
    }   
    
   
    return new WebApp[0];
  }

}