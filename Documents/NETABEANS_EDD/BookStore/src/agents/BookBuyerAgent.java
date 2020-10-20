/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.core.Agent;
import behaviours.RequestPerformer;
//import gui.BookSellerGui;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import gui.BookBuyerGui;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JTextField;



public class BookBuyerAgent extends Agent {
  private String bookTitle;
  public String args;
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private BookBuyerAgent this_agent = this;
  //private BookBuyerGui gui;
  public BookBuyerGui gui = new BookBuyerGui(this);
  protected void setup() {
      
          System.out.println("Agente Comprador " + getAID().getName() + " esta listo");
          
          gui.showGui();
          pausa(15000);
          args = gui.title.trim();
          System.out.println("El TITULO del Libro es:"+gui.title);
          //bookTitle=gui.getDatos();
          //Object[] args; //= getArguments();
          //&& args.length > 0
          
          //
          
          if(args != null) /*&& args.length > 0*/  {
              
            //bookTitle = args[0];  
            bookTitle = args;  
            System.out.println("Buscando Libro: " + bookTitle);  
              addBehaviour(new TickerBehaviour(this, ticker_timer) {
                  protected void onTick() {
                      System.out.println("Intentando comprar " + bookTitle);
                      
                      DFAgentDescription template = new DFAgentDescription();
                      ServiceDescription sd = new ServiceDescription();
                      sd.setType("book-selling");
                      template.addServices(sd);
                      
                      try {
                          DFAgentDescription[] result = DFService.search(myAgent, template);
                          System.out.println("Se encontraron los siguientes agentes vendedores:");
                          sellerAgents = new AID[result.length];
                          for(int i = 0; i < result.length; i++) {
                              sellerAgents[i] = result[i].getName();
                              System.out.println(sellerAgents[i].getName());
                          }
                          
                      }catch(FIPAException fe) {
                          fe.printStackTrace();
                      }
                      
                      myAgent.addBehaviour(new RequestPerformer(this_agent));
                  }
              });
          } else {
              System.out.println("No se especifico ningun titulo");
              doDelete();
          } 
  }
  
  
  
  protected void takeDown() {
    gui.dispose();
    System.out.println("Agente Comprador " + getAID().getName() + " terminado");
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
  public static void pausa(int tiempo){
      try{
          Thread.sleep(tiempo);
      }
      catch(InterruptedException e){
          
      }
  }
}