package green.event;

import java.util.Enumeration;
import java.util.Vector;

public class EventHub {
	
	private static Vector<EventReceiver> listeners;
	
	public static void registerListener(EventReceiver l){
		if(listeners == null) listeners = new Vector<EventReceiver>();
		listeners.addElement(l);
	}
	
	public static void fireEvent(Event event){
		if(listeners != null && !listeners.isEmpty()){
			Enumeration<EventReceiver> e = listeners.elements();
			while(e.hasMoreElements()){
				EventReceiver r = (EventReceiver) e.nextElement();
				r.processEvent(event);
			}
		}
	}
	

}
