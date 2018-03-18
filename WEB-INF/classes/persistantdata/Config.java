package persistantdata;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import mediatheque.PersistentMediatheque;

@WebListener
public class Config implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        PersistentMediatheque m = new MediathequeData();
    }
    
    public void contextDestroyed(ServletContextEvent event) {
        // Do your thing during webapp's shutdown.
    }
}