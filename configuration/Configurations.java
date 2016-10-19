package configuration;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


/**
 *
 * @author GINEL ; JBFLO
 */
public class Configurations {

    //Chemin relatif du fichier propriété
    private final String chemin = "./configurations/database.properties";
    //Nous servira de charger le fichier de configuraiton afin de travailler
    private PropertiesConfiguration config;

    public void save() {
        try {
            config.save();
        } catch (ConfigurationException ex) {
            Logger.getLogger(Configurations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Configurations() {
        try {
            
            //File f=new File();
           config = new PropertiesConfiguration(chemin);

        } catch (ConfigurationException ex) {
            ex.printStackTrace();
        }

    }

    public Configuration getConfig() {
        return config;
    }

    public void setURL(String url) {
        config.setProperty("data.database.url", url);
    }

    public void setPort(int port) {
        config.setProperty("data.database.port", port);
    }

    public void setLogin(String login) {
        config.setProperty("data.database.login", login);
    }

    public void setPassword(String password) {
        config.setProperty("data.database.password", password);
    }

    public void setDatabaseName(String name) {
        config.setProperty("data.database.database", name);
    }

    public String getDatabaseName() {
        return config.getString("data.database.database");
    }

    public String getURL() {
        return config.getString("data.database.url");
    }

    public int getPort() {
        return config.getInt("data.database.port");
    }

    public String getLogin() {
        return config.getString("data.database.login");
    }

    public String getPassword() {
        return config.getString("data.database.password");
    }
}
