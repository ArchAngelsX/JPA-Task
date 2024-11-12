package tugas1.infrastructure;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {

	
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext ctx = sce.getServletContext();
         String dbUrl = ctx.getInitParameter("jdbcUrl");
         String dbUser = ctx.getInitParameter("jdbcUser");
         String dbPassword = ctx.getInitParameter("jdbcPassword");
         
         DBUtils.setJdbcURL(dbUrl);
         DBUtils.setJdbcUser(dbUser);
         DBUtils.setJdbcPassword(dbPassword);
    }
	
}
