    package rmi;

    import java.rmi.Naming;  
    import java.rmi.RemoteException;  
    import java.rmi.NotBoundException;  
    import java.net.MalformedURLException;  
    import java.rmi.AccessException;
    import java.rmi.registry.LocateRegistry;
    import java.rmi.registry.Registry;
    import java.util.logging.Level;
    import java.util.logging.Logger;
      
    public class RMIClient {  
       private Registry registry;
       private ICommunication rmiServer;
       
       public RMIClient(RMIServer server)
       {
           try 
           {
               registry=LocateRegistry.getRegistry(server.getIp(),server.getPorta());
           } 
           catch (RemoteException ex) 
           {
               Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
           }
           try 
           {
               rmiServer=(ICommunication)(registry.lookup("rmiServer"));
           } 
           catch (RemoteException ex) 
           {
               Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
           } 
           catch (NotBoundException ex) 
           {
               Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
           } 
       }
       
        // Método para testar o pacote sozinho
        public static void main( String args[] ) throws RemoteException, NotBoundException 
        {  
            new RMIClient(new RMIServer("localhost",123));
        }  
        
        public void enviarMensagem(String mensagem)
        {
           try 
           {
               System.out.println(rmiServer.enviarMensagem(mensagem));
           } 
           catch (RemoteException ex) 
           {
               //se der erro enviar mensagem para o lider pedindo para remover da lista
               Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }  