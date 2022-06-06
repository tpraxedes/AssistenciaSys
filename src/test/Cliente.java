/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author thiag
 */
public class Cliente {
        
    int idCliente;
    String Nome;
    String CPFCNPJ;
    String Telefone;
    String Email;
    
    
    public void setCPFCNPJ(String cpf){
        this.CPFCNPJ = cpf;
    }
    
    public void setTelefone(String telefone){
        this.Telefone = telefone;
    }
    
    public void setEmail(String email){
        this.Email = email;
    }
    
    public void setNome(String nome){
        this.Nome = nome;
    }
    
    
    
    public String getCPFCNPJ(){
        if(this.CPFCNPJ != null)
        {
            return this.CPFCNPJ;
        }
        else
        {
           return "False"; 
        }
    }
    
    public String getTelefone(){
        if(this.Telefone != null)
        {
            return this.Telefone;
        }
        else
        {
           return "False"; 
        }
    }
    
    public String getEmail(){
        if(this.Email != null)
        {
            return this.Email;
        }
        else
        {
           return "False"; 
        }
    }
    
    public String getNome(){
        if(this.Nome != null)
        {
            return this.Nome;
        }
        else
        {
           return "False"; 
        }
    }
    
    
    
    public void locateData(String cpf){
        SQL sql = new SQL();
        var c = sql.getClienteData(cpf);
        this.CPFCNPJ = c.CPFCNPJ;
        this.Email = c.Email;
        this.Nome = c.Nome;
        this.Telefone = c.Telefone;
    }
    
    public boolean insertClient(){
        if(this.CPFCNPJ == null || this.Email == null || this.Nome == null || this.Telefone == null){
            JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS DO CLIENTE","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        SQL sql = new SQL();
        if(!sql.raw("insert into DadosCliente (CPFCNPJ, Email, Nome, Telefone) values ('"+this.CPFCNPJ+"','"+this.Email+"','"+this.Nome+"','"+this.Telefone+"')")){
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR O CLIENTE:\r\n"+sql.error,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }
    
}
