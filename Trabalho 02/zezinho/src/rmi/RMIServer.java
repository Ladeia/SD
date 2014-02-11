package rmi;

import java.net.InetAddress;
import java.rmi.Naming;  
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
  
public class RMIServer extends java.rmi.server.UnicastRemoteObject implements ICommunication {  

    public int getPorta() {
        return thisPort;
    }

    public String getIp() {
        return thisAddress;
    }
  
    private int      thisPort;
    private String   thisAddress;
    private Registry registry;
    
    public RMIServer(String ip, int porta) throws RemoteException 
    {      
        this.thisAddress=ip;
        this.thisPort=porta;
        try
        {
            // create the registry and bind the name and object.
            registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e)
        {
            throw e;
        }
    }  
  
    // Método para testar o pacote sozinho
    public static void main(String[] args) {  
        try 
        {  
            new RMIServer("localhost",213);
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @Override
    public String enviarMensagem(String msg) throws RemoteException 
    {
        System.out.println(msg);
        return "Mensagem recebida com sucesso";
    }

    @Override
    public String lerMensagem() throws RemoteException 
    {
        return ("lendo mensagem");
    }
}