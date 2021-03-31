package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ConnectException;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class Cliente {
    private Socket cliente = null;
    private String IP;
    private ObjectOutputStream outCliente = null;
    private ObjectInputStream object = null;

    // Criação de metódo construtor que recebe um String (host) e inicia uma conexão Socket com o Servidor.
    public Cliente(String host){
       
        try {
            InetAddress inet = InetAddress.getByName(host);
            cliente = new Socket(inet.getHostAddress(), 60000);
            JOptionPane.showMessageDialog(new JOptionPane(), "Conexão feita com sucesso ao servidor.");
      
        } catch (ConnectException ex) {
           JOptionPane.showMessageDialog(new JOptionPane(), "------- Servidor não iniciado --- Conexão não feita. --------" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), "------- Erro na hora de se conectar ao host. --------" + ex.getMessage());
            System.exit(0);
        } 
       
    }
    
    // Metódo get para retornar um Socket nesse caso o do cliente.
    public Socket getCliente(){
        return cliente;
    }
    
    // Metódo get para retornar a porta nesse caso o do cliente.
    public int getPort(){
        return cliente.getPort();
    }
    
    // Metódo void para enviar uma mensagem para o Cliente.
    public void enviarDadosCliente (String mensagem){
        try{
            if(cliente != null){
                outCliente = new ObjectOutputStream(cliente.getOutputStream());
                outCliente.writeObject(mensagem);
                outCliente.flush();
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(new JOptionPane(), "Mensagem não enviada - Servidor fechado");
        }
    }
    
    // Metódo String para receber uma mensagem do Servidor.
    public String receberDadosCliente(){
      
        String mensagem = null;
            try{
                if(cliente != null){
                    object = new ObjectInputStream(cliente.getInputStream());
                    mensagem = String.valueOf(object.readObject());
                }
            }catch(IOException ex){
                JOptionPane.showMessageDialog(new JOptionPane(), "-------- SERVIDOR FECHADO --------");
                System.exit(0);
            }  catch (ClassNotFoundException ex) {  
                JOptionPane.showMessageDialog(new JOptionPane(), "-------- Erro na leitura de dados --------");
            } 
   
        return mensagem;
    }  
    
    // Metódo que retornar um boolean e verifica se a conexão foi feita.
    public boolean verificarConexao(){
        boolean vconexao = false;
        if(cliente.isClosed() == true){
            vconexao = true;
        }
        return vconexao;
    }
    
    // Metódo void que fecha a conexão Socket.
    public void fecharConexao(){
        try {
            cliente.close();
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(new JOptionPane(), "Erro ao fechar ao cliente");
        }
      
    }

   

}
