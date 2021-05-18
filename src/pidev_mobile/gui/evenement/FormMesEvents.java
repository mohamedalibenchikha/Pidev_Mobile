/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.List;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.services.EventService;

/**
 *
 * @author ASUS
 */
public class FormMesEvents extends BaseForm{
private List<EventLoisir> ListEvent;
private Resources res;
    public FormMesEvents(FormAcceuilEvent form,Resources res) {
         form.getToolbar().setTitle("Mes Evenement");
         this.res=res;
   setLayout(BoxLayout.y());
         setTitle("Mes Evenement");
         //setUIID("background");
         
        
        //this.setUIID("background");
        final FormMesEvents fl = this;

         //Listformations = new ArrayList<>();
         ListEvent = EventService.getInstance().getEParUser("freelancer",1);
         for(int i=0;i<ListEvent.size();i++){
             this.add(addMonEventholder(ListEvent.get(i),form));
         }
           
           FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
fab.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new FormAjoutEvent(form,res).show();
                 
              }
          });

    
       /* this.getStyle().setBgColor(0xFFFFFF);
         this.getStyle().setBgTransparency(100);*/
    
    
}

      public FormMesEvents refreshLayoutover(FormAcceuilEvent form) {
          
        this.removeAll();
        
         //setUIID("background");
         
       
        //this.setUIID("background");
        ListEvent = EventService.getInstance().getEParUser("freelancer",1);
         for(int i=0;i<ListEvent.size();i++){
             this.add(addMonEventholder(ListEvent.get(i), form));
         }

        this.revalidate();
        return this;
        
        
    }
      public Container addMonEventholder(EventLoisir f,FormAcceuilEvent form){
          Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(f.getImageE()).scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormMonEventDetails fd = new FormMonEventDetails(f,form,res);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
      }
    
    
}