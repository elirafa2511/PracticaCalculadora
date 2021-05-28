
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryukai
 */
public class client {
    
    public static void main(String args[]) throws RemoteException, MalformedURLException{
        
        client c = new client();
        c.connectRemote();
    }

    private void connectRemote() throws RemoteException, MalformedURLException {
        
        //Scanner reader = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        int op = 0;
        int res=0;
        
        String ip;
        ip = JOptionPane.showInputDialog("Ingrese la direccion ip");
        
        try{
           
        Registry reg = LocateRegistry.getRegistry(ip,1099);
        
       
        Adder ad = (Adder) Naming.lookup("//"+ip+"/Calculadora");
        for(;;){
            op = Integer.parseInt(JOptionPane.showInputDialog(null,"Escoja la actividad a realizar\n 1.Suma\n 2.Resta\n 3.Multiplicacion\n 4.Division \n 5.Salir"));
            if(op==5){
            JOptionPane.showMessageDialog(null,"CERRANDO CLIENTE");
            System.exit(0);
             }else if(op < 5){
            num1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el valor de a"));
            num2 = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el valor de b"));
             }
        if(op==4 && num2==0){
            JOptionPane.showMessageDialog(null,"No se puede dividir numeros entre 0");
            continue;
        }
        switch(op){
            
            case 1:
                res = ad.add(num1,num2);
                JOptionPane.showMessageDialog(null,"El resultado de "+num1+" + "+num2+" = "+ res);
                break;
                
            case 2:
                 res = ad.res(num1,num2);
                 JOptionPane.showMessageDialog(null,"El resultado de "+num1+" - "+num2+" = "+ res);
                break;
                
            case 3:
                 res = ad.mult(num1,num2);
                 JOptionPane.showMessageDialog(null,"El resultado de "+num1+" x "+num2+" = "+ res);
                break;
                
            case 4:
                 res = ad.div(num1,num2);
                 JOptionPane.showMessageDialog(null,"El resultado de "+num1+" / "+num2+" = "+ res);
                break;          
            default :
                JOptionPane.showMessageDialog(null,"OPCION INCORRECTA");
                break;
        }
        
        }
        }catch(NotBoundException | RemoteException e){
            System.out.println("Exception " + e);
        }
    }
    
}
