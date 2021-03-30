package servidorchat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server{
    private ServerSocket servidor;
    private String endereco;
    private InetSocketAddress ip;
    private int port;
    private Socket cliente;
    private InetAddress local;
    private String host;
    private ObjectOutputStream outCliente;
    private ObjectInputStream object;
    private InetAddress inet;
    
    // Metódo construtor vazio.
    public Server(){
        try {
            local = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Criação de metódo construtor e criação de um ServerSocket.
    public Server(int i){
    
        try{
           servidor = new ServerSocket(60000);
           local = InetAddress.getLocalHost();
           inet = InetAddress.getLocalHost();
           port = servidor.getLocalPort();
           object = null;
           System.out.println(port);
           endereco = local.getHostAddress();
           host = local.getHostName();
           System.out.println(host);
           ip = new InetSocketAddress(endereco, 60000);
           outCliente = null;
           if(!servidor.isBound()){
                 servidor.bind(ip);
           }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage() + ": " + "Erro ");
        }
    }
    
    // GET de retorno do host do computador que foi iniciado o servidor.
    public String getHostName(){
        return host;
    }
    
    // GET de retorno do host do computador que foi iniciado o servidor..
    public String getIPLocalHost(){
        return local.getHostAddress();
    }
   
    // GET de retorno do ServerSocket (Servidor) do computador que foi iniciado o servidor..
    public ServerSocket getServer(){
        return servidor;
    }
    
    // GET de retorno do Cliente (Socket) do computador que foi iniciado o servidor.
    public Socket getCliente(){
        return cliente;
        
    }
    // GET de retorno tipo int da porta na qual o computador foi iniciado o servidor.
    public int getPort(){
        return cliente.getPort();
    }
    // Espera uma conexão até que ela é feita e libera um objeto da Classe Socket..
    public void conectarServer(){
        try {
            cliente = servidor.accept();
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(new JOptionPane(), "Conexão ao cliente não realizada. ");
        } 
    }
    // Método do tipo void que executa um envio de mensagem para o cliente, através da conexão que foi aceita, assim ele recebe uma mensagem como parametro..
    public void enviarDadosServidor(String mensagem){
        try{
            if(cliente != null){
                outCliente = new ObjectOutputStream(cliente.getOutputStream());
                outCliente.writeObject(mensagem);
                outCliente.flush();
            }
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(new JOptionPane(), "-------- Messagem não enviada - Cliente fechado --------"); 
        }
    
    }
     // Método do tipo String que retorna um recebimento de mensagem do cliente, através da conexão que foi aceita, assim ele recebe uma mensagem como parametro..
    public String receberDadosServidor(){
        String mensagem = null;
        try{
            if(cliente != null){
               object = new ObjectInputStream(cliente.getInputStream());
               mensagem = String.valueOf(object.readObject());
               if(mensagem.equalsIgnoreCase("")){
                  mensagem = "";
                }
                mensagem = mensagem.trim();  
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(new JOptionPane(), "-------- CLIENTE FECHADO  --------");
            System.exit(0);
        }catch (ClassNotFoundException ex) { 
           JOptionPane.showMessageDialog(new JOptionPane(), "-------- Erro na leitura de dados --------"); 
        }
         
        return mensagem;
     }
     // Fecha a conexão obtida com o cliente e encerra o servidor, metódo do tipo void.
     public void fecharConexao(){
        try {
            if(cliente != null){
                cliente.close();
            }
            servidor.close();
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(new JOptionPane(), "Erro ao fechar o servidor.");
        }
     }
  
}
