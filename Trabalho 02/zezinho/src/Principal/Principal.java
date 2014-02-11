package Principal;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Principal.Maquina;
import java.rmi.NotBoundException;


public class Principal 
{
        private ArrayList maquinas;
        
	public Principal() throws NotBoundException
	{
            this.maquinas = new ArrayList();
            try {
                init();
            } catch (RemoteException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
	public static void main(String[] args) throws NotBoundException
	{
            new Principal();
	}
	
	public void sendElective()
	{
	  //lista.add(ID);
	  //RMI.send(maquinas[(ID+1)%3]);
	}
        
        private void init() throws RemoteException, NotBoundException
	{
         Maquina maq1 = new Maquina(01,"localhost",8081);
         Maquina maq2 = new Maquina(02,"localhost",8082);
         Maquina maq3 = new Maquina(03,"localhost",8083);
         
	  maquinas = new ArrayList();
	  maquinas.add(maq1);
	  maquinas.add(maq2);
	  maquinas.add(maq3);
          
          maq1.setMaquinas(maquinas);
          maq2.setMaquinas(maquinas);
          maq3.setMaquinas(maquinas);
          
          maq1.enviarMensagemParaTodos("hello world");
          
          //maq1.Eleicao();
	}
	

}
