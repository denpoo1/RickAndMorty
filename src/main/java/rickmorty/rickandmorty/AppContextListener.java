/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataManager.fetchAndSaveData();
    }
}
