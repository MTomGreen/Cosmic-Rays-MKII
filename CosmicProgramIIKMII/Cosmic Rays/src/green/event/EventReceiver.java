package green.event;

import java.util.EventListener;

public interface EventReceiver extends EventListener {
	
	public abstract void processEvent(Event e);

}
