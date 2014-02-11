package rmi;

import java.rmi.Remote;  
import java.rmi.RemoteException;  
  
public interface ICommunication extends Remote 
{    
    public String enviarMensagem( String msg ) throws RemoteException;  
    public String lerMensagem() throws RemoteException;  
}