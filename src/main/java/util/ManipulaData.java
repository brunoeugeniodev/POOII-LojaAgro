/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author bruno
 */
public class ManipulaData {
    public Date string2Date (String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date dataSaida = Date.valueOf(LocalDate.parse(data, formato));
        
        return dataSaida;
    }
    
    public String date2String (String data){
        try{
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            data = new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
}
