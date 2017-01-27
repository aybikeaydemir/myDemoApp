package com.mycompany.app;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/*
A={Ayşe, Fatma, Esra, Ali, Veli, Ahmet, Mehmet}
A ile A (AXA) arasındaki  kartezyen carpımı sonucunda binaryRelation(ikili bağıntı) şu şekilde olur.

E={(Ayşe, Ali), (Ali, Ayşe), (Esra, Mehmet), (Mehmet, Esra) ...}

Benim programım da ise  girilen sayi dizisinin 

B={ 455,78,62,78,546,}

ve girilen ikili bağıtının bu kümeye ait olup olmadığını sorgular. 

örnek :

( 455 , 78 ) bu kümenin bağıntısı mıdır ? TRUE

(62 , 5 ) FALSE

 */
import java.util.ArrayList;
public class App 
{
       
    public static boolean binaryRelation (ArrayList<Integer> array, int a,int b) {
        boolean num =false;
        boolean num2=false;
        System.out.println("");
        if (array == null) return false;
  
        for (int combination1 : array) 
          if (combination1 == a )
        	  num=true;
        
        for (int combination2 : array) //&& int combination2 : array ) {
            if (combination2 == b )
          	  num2=true;
        
        if(num == true && num2== true)
                return true;
        return false;
      }
 public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, Passenger!");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
// BURASI ARRAYİN ALİNMASİ
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          System.out.println(inputList);

// sayilarin girilmesi

          String input2 = req.queryParams("input2").replaceAll("\\s","");
          String input3 = req.queryParams("input3").replaceAll("\\s","");
          int input2AsInt = Integer.parseInt(input2);
          int input3AsInt = Integer.parseInt(input3);
          boolean result = App.binaryRelation(inputList, input2AsInt,input3AsInt);

         Map map = new HashMap();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },

            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }




}
