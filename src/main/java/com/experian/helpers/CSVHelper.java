package com.experian.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.experian.helpers.CSVHelper;
import com.experian.mvc.ajax.webappranking.WebApp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVHelper{
    
    public static List<WebApp> getWebApps(InputStream ios) throws FileNotFoundException  {
        //Get scanner instance
        Scanner scanner = new Scanner(ios);
         
        //Set the delimiter used in file
        //scanner.useDelimiter("\\n");
         
        //Get all tokens and store them in some data structure
        //I am just printing them

        List<WebApp> result = new ArrayList<WebApp>();
        scanner.next();
        while (scanner.hasNext())  {
            WebApp webapp = new WebApp();
            
            String line = scanner.next();
            String[] items = line.split("\\|");
            
            if(items.length > 0){
              webapp.setRankdate(items[0]);
              webapp.setWebsite(items[1]);
              webapp.setVisit(items[2]);
            }
            
            result.add(webapp);
        }
         
        //Do not forget to close the scanner  
        scanner.close();
        
        return result;
    }
}