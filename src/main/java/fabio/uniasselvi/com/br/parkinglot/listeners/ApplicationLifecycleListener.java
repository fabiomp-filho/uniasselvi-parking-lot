package fabio.uniasselvi.com.br.parkinglot.listeners;

import fabio.uniasselvi.com.br.parkinglot.dao.EntityManagerUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Código de inicialização da aplicação
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerUtil.close();
    }
}