/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author thiag
 */
public class OS {
    
    int osN;
    String descricaoProduto;
    String nSerie;
    String tipo;
    String descricaoProblema;
    String cpfcnpj;
    protected Date data = Date.from(Instant.now());
    List<OcorrenciasOS> ocorrencias = new ArrayList<>();
    
    
    public boolean cadastraOS(){
        if(this.descricaoProblema == null || this.nSerie == null || this.tipo == null || this.descricaoProduto == null || this.data == null){
            JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS DA OS","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        SQL sql = new SQL();
        if(!sql.raw("insert into DadosOS (CPFCNPJ,descricaoProduto, nSerie, tipo, descricaoProblema) values ('"+this.cpfcnpj+"','"+this.descricaoProduto+"','"+this.nSerie+"','"+this.tipo+"','"+this.descricaoProblema+"')")){
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR A OS:\r\n"+sql.error,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    
    public boolean cadastraOcorrencias(String descricao, int idStatus){
        
        SQL sql = new SQL();
        String query = "insert into OcorrenciasOS (idOS,idStatus, descricaoOcorrencia) values ("+this.osN+","+String.valueOf(idStatus)+",'"+descricao+"')";
        if(!sql.raw(query)){
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR A Ocorrência:\r\n"+sql.error,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public List<OS> getOS(String CPFCNPJ){
        SQL sql = new SQL();
        return sql.getOSs(CPFCNPJ);
    }
    
    public void getOcorrencias(int os){
        SQL sql = new SQL();
        ocorrencias = sql.getOcorrencias(os);
        
    }
    
    
    public int getLastGeneratedOS(){
        SQL sql = new SQL();
        
        return sql.getLastOS();
    }
    
    public boolean getOSData(){
     
        if(this.osN == 0){
            JOptionPane.showMessageDialog(null, "ESCOLHA CORRETAMENTE O Nº DA OS","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try{
        SQL sql = new SQL();
        OS os = sql.getOSData(this.osN);        
        return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    void getOS() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
