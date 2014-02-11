package Principal;

import Database.Connect;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.RMIClient;
import rmi.RMIServer;

public class Maquina extends java.rmi.server.UnicastRemoteObject
{
        private ArrayList<Maquina> maquinas;
        private int id;
        private String ip;
        private int porta;
        private Connect connect;
        private Registry registry;
        private RMIServer server;
        private Maquina lider;
        private RMIClient client;
        
        public RMIServer getServer() 
        {
            return server;
        }
 
        public Maquina(int id,String ip, int porta) throws RemoteException, NotBoundException
        {
            this.id = id;
            this.ip = ip;
            this.porta = porta;
            
            this.maquinas = new ArrayList();
            
            this.server = new RMIServer(this.ip, this.porta);
            this.client = new RMIClient(this.server);
            
            System.out.println("maquina criada no ip "+this.ip+" e porta "+ this.porta);           
        }
        
        public void setMaquinas(ArrayList<Maquina> maquinas)
        {
            this.maquinas = maquinas;
        }
        
        public void enviarMensagemParaTodos(String message)
        {          
            for(Maquina maquina : maquinas)
            {
                maquina.client.enviarMensagem(message);
            }
        }
        
        public void enviarMensagemParaLider(String message)
        {
        }
        
        public Maquina eleicao(List ids)
        {
            int isStarted = 0;
            
            if(ids == null)
            {
                isStarted = 1;
                ids.add(this.id);
                //enviar para o proximo da lista
                // maquina.send(ids, id+1%3);
            }
            if(isStarted ==1)
            {
                escolheLider(id);
            }
        }
}
