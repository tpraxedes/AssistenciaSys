/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.List;

/**
 *
 * @author thiag
 */
public class Status {
    
    private SQL sql = new SQL();
    protected int idStatus;
    protected String descStatus;
    public List<Status> status;
        
    public void getStatus(){
        status = sql.getStatus();
    }
    
}
