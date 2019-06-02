package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;
import org.dvb.ui.*;
import java.awt.Color;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.dvb.event.*;

import java.awt.event.ActionEvent;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener/*UserEventListener*/  {
   private XletContext actueleXletContext;
   private HScene scene;
   private boolean debug=true;
   
   
   private HStaticText tekstLabel;
   private HTextButton knop1, knop2, knop3, knop4;
   
   
    public HelloTVXlet()  {
       
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
     if(debug) System.out.println("Xlet Initialiseren");
     this.actueleXletContext = context;
     
     HSceneTemplate sceneTemplate= new HSceneTemplate();
    
     sceneTemplate.setPreference(
            org.havi.ui.HSceneTemplate.SCENE_SCREEN_DIMENSION,
            new HScreenDimension(1f, 1f), org.havi.ui.HSceneTemplate.REQUIRED);
            
     sceneTemplate.setPreference(org.havi.ui.HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.20f, 0.1f), org.havi.ui.HSceneTemplate.REQUIRED);
    
     scene= HSceneFactory.getInstance().getBestScene(sceneTemplate);
    
     tekstLabel = new HStaticText("Wie was de eerste astronaut in de ruimte?");
     
     tekstLabel.setLocation(0,0);
     tekstLabel.setSize(400,50);
     tekstLabel.setBackground(new DVBColor(100,100,255,179));
     tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
    
     scene.add(tekstLabel);
     
     knop1 = new HTextButton("Neil Armstrong");
     knop1.setLocation(100,100);
     knop1.setSize(250,50);
     knop1.setBackground(new DVBColor(0,0,0,179));
     knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setFocusTraversal(knop4, knop2, null, null);
     scene.add(knop1);
     
     knop2 = new HTextButton("Yuri Gagarin");
     knop2.setLocation(100,200);
     knop2.setSize(250,50);
     knop2.setBackground(new DVBColor(0,0,0,179));
     knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop2.setFocusTraversal(knop1, knop3, null, null);
    scene.add(knop2);
    
     knop3 = new HTextButton("Laika de Hond");
     knop3.setLocation(100,300);
     knop3.setSize(250,50);
     knop3.setBackground(new DVBColor(0,0,0,179));
     knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop3.setFocusTraversal(knop2, knop4, null, null);
     scene.add(knop3);
     
     knop4 = new HTextButton("Niemand");
     knop4.setLocation(100,400);
     knop4.setSize(250,50);
     knop4.setBackground(new DVBColor(0,0,0,179));
     knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop4.setFocusTraversal(knop3, knop1, null, null);
     scene.add(knop4);
     
     knop1.requestFocus();
     knop1.setActionCommand("knop1");
     knop2.addHActionListener(this);
     knop2.setActionCommand("knop2");
     knop3.addHActionListener(this);
     knop3.setActionCommand("knop3");
     knop4.addHActionListener(this);
     knop4.setActionCommand("knop4");
     
     knop1.addHActionListener(this);
     
    
    }
    public void actionPerformed (ActionEvent arg0){
        System.out.println(arg0.getActionCommand());
        String antwoord="";
        if (arg0.getActionCommand().equals("knop2")){
            antwoord="Proficiat, u heeft de vraag van vandaag juist beantwoord";
        }
        else {antwoord="Helaas, u heeft de vraag fout beantwoord";}
        
        HStaticText hst2=new HStaticText (antwoord,0,200,550,100);
        hst2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        hst2.setBackground(Color.BLUE);
   
        scene.add(hst2);
        scene.popToFront(hst2);
        scene.repaint();
    }

    public void startXlet() {
    
    if(debug) System.out.println("Xlet Starten");
    scene.validate();scene.setVisible(true);
    EventManager manager = EventManager.getInstance();
    
    UserEventRepository repository = new UserEventRepository("bewegen");
    
    repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
    
    //manager.addUserEventListener (this, repository);
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
   /* public void userEventReceived(org.dvb.event.UserEvent e){
        if(e.getType() == KeyEvent.KEY_PRESSED){
        System.out.println("Pushed Button");
        switch (e.getCode()){
            case HRcEVENT.VK_UP :
                System.out.println("VK_UP is PRESSED");
                break;
                
            case HRcEVENT.VK_DOWN :
                System.out.println("VK_DOWN is PRESSED");
                break;
        }
        }
    }*/
}
