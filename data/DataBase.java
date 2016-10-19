
package data;

import configuration.Configurations;
import static controller.FXMLDocumentControllerCreateChantier.chantier;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GINEL ; JBFLO
 */
public class DataBase {

    private Configurations configuration;
    private String urlConnexion;
    private int port;
    private String host;
    private String databaseName;
    private String login;
    private String password;
    private String pilotString;
    private String strURL = "";

    public DataBase(String pilotString) throws SQLException {
        configuration = new Configurations();
        this.port = configuration.getPort();
        this.host = configuration.getURL();
        this.databaseName = configuration.getDatabaseName();
        this.login = configuration.getLogin();
        this.password = configuration.getPassword();
        this.pilotString = pilotString;
        try {

            Class.forName(pilotString);
            String chaine = null;
            if (pilotString.compareToIgnoreCase("com.mysql.jdbc.Driver") == 0) {
                chaine = "jdbc:mysql";
            }
            this.strURL = chaine + "://" + host + ":" + port + "/" + databaseName;
            //  Connection con = DriverManager.getConnection(this.strURL, login, password);
            // . . . 
            // con.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non chargé !");
            e.printStackTrace();

        }// catch (SQLException e) {
        //   e.printStackTrace();
        //}

    }

    public void insertAnnexe(String nom, String format, InputStream annexe, int chantierid) throws SQLException {
        Connection con = null;
        String requete = "insert into annexe (nom, format, annexe,chantierid) values (?,?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, format);
            ps.setBlob(3, annexe);
            ps.setInt(4, chantierid);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void deleteChantier(String nom) throws SQLException {
        Connection con = null;
        String requete = "delete from chantiers where nom = ?";
        try {
            con = DriverManager.getConnection(this.strURL, this.login, this.password);
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec Suppression");
            }
        } catch (Exception E) {

        } finally {
            con.close();
        }
    }

    public void deleteUtilisateur(String login) throws SQLException {
        Connection con = null;
        String requete = "delete from utilisateur where login = ?";
        try {
            con = DriverManager.getConnection(this.strURL, this.login, this.password);
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, login);
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec Suppression");
            }
        } catch (Exception E) {

        } finally {
            con.close();
        }
    }

    public void deleteProblemeRencontre(int idprobleme, int idchantier) throws SQLException {
        Connection con = null;
        String requete = "delete from problemechantier where idprobleme = ? and idchantier=?";
        try {
            con = DriverManager.getConnection(this.strURL, this.login, this.password);
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, idprobleme);
            ps.setInt(2, idchantier);
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec Suppression");
            }
        } catch (Exception E) {

        } finally {
            con.close();
        }
    }

    public void deleteProbleme(String probleme) throws SQLException {
        Connection con = null;
        String requete = "delete from probleme where nom = ?";
        //   try {
        con = DriverManager.getConnection(this.strURL, this.login, this.password);
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setString(1, probleme);

        if (ps.executeUpdate() != 0) {

        } else {
            System.out.println("Echec Suppression");
        }
      //  } catch (Exception E) {

        //} finally {
        //   con.close();
        // }
    }

    public void deleteSolution(int idsolution) throws SQLException {
        Connection con = null;
        String requete = "delete from solution where idsolution = ?";
        //   try {
        con = DriverManager.getConnection(this.strURL, this.login, this.password);
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setInt(1, idsolution);

        if (ps.executeUpdate() != 0) {

        } else {
            System.out.println("Echec Suppression");
        }
      //  } catch (Exception E) {

        //} finally {
        //   con.close();
        // }
    }

    public void insertData(String requete) throws SQLException {

        Connection con = DriverManager.getConnection(this.strURL, login, password);
        java.sql.Statement stmt = con.createStatement();
        int n = stmt.executeUpdate(requete);
        con.close();
        System.out.println("voici le nombre de lignes" + n);

    }

    public void insertUser(String nom, String prenom, String login, String password, String poste) throws SQLException {
        Connection con = null;
        String requete = "insert into utilisateur(nom,prenom,login,password,poste)values(?,?,?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, this.login, this.password);
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setString(5, poste);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }
    
     public void updateUser(String nom, String prenom, String logi, String passw, String poste , String log ) throws SQLException {
        Connection con = null;
        String requete = "update utilisateur set nom =? , prenom =? , login =? , password =? , poste =?  where login =? ";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
              int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, logi);
            ps.setString(4, passw);
            ps.setString(5, poste);
            ps.setString(6, log);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }


    public void insertProcedure(int numero, String libelle, int idsolution) throws SQLException {
        Connection con = null;
        String requete = "insert into etape (numero, libelle, idsolution) values (?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, numero);
            ps.setString(2, libelle);
            ps.setInt(3, idsolution);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void deleteProcedure(int idprocedure) throws SQLException {
        Connection con = null;
        String requete = "delete from etape where idetape= ?";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, idprocedure);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void updateProcedure(int numero, int idprocedure) throws SQLException {
        Connection con = null;
        String requete = "update etape set numero =? where idetape = ? ";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, numero);
            ps.setInt(2, idprocedure);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void insertProbleme(String nom, String libelle) throws SQLException {
        Connection con = null;
        String requete = "insert into probleme (nom, libelle) values (?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, libelle);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }
    public void updateProbleme(String nom, String libelle, String identifiant ) throws SQLException {
        Connection con = null;
        String requete = "update probleme set nom =? , libelle =? where idprobleme =? ";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, libelle);
            ps.setString(3, identifiant);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void insertSolution(String nom, String libelle, int idprobleme) throws SQLException {
        Connection con = null;
        String requete = "insert into solution (nom, libelle,probleme_idprobleme) values (?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, libelle);
            ps.setInt(3, idprobleme);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void insertChantier(String nom, String localisation, String typologie, String utilite, String emprise, Timestamp date, String actif) throws SQLException {
        Connection con = null;
        String requete = "insert into chantiers (nom, localisation, typologie, utilite, emprise, etape, date, actif) values (?,?,?,?,?,?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, localisation);
            ps.setString(3, typologie);
            ps.setString(4, utilite);
            ps.setString(5, emprise);
            ps.setString(6, "");
            ps.setTimestamp(7, date);
            ps.setString(8, actif);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void insertProblemeChantier(int idprobleme, int idchantier) throws SQLException {
        Connection con = null;
        String requete = "insert into problemechantier (idprobleme, idchantier) values (?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, idprobleme);
            ps.setInt(2, idchantier);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public void insertProbleme(String nom, String probleme, int chantierid) throws SQLException {
        Connection con = null;
        String requete = "insert into probleme (nom, libelle, chantier_idchantier) values (?,?,?)";
        try {
            con = DriverManager.getConnection(this.strURL, login, password);

            /*java.sql.Statement stmt = con.createStatement();
             int n = stmt.executeUpdate(requete);*/
            //con.close();
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, nom);
            ps.setString(2, probleme);
            ps.setInt(3, chantierid);
            System.out.println(ps.toString());
            if (ps.executeUpdate() != 0) {

            } else {
                System.out.println("Echec enregistrement");
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("Echec enregistrement");
        } finally {
            con.close();
        }
    }

    public String getData(String requete) {
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.Statement stmt = con.createStatement();

            ResultSet rs1 = stmt.executeQuery(requete);

            while (rs1.next()) {
                re = re + rs1.getString(1) + ";;;" + rs1.getString(2) + ";;;" + rs1.getString(3) + ";;;" + rs1.getString(4) + ";;;" + rs1.getString(5) + ";;;" + rs1.getString(6) + ";;;" + rs1.getString(7) + ";;;" + rs1.getString(7) + ";;;" + rs1.getString(8) + ";;;" + rs1.getString(9) + ";;;" + rs1.getInt(10) + ";;;" + rs1.getString(11) + ";;;" + rs1.getString(12) + ";;;" + rs1.getString(13) + ";;;" + rs1.getString(14) + ";;;" + rs1.getString(15);
                re = re + ":::";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getDataAnnexe(int chantierid) {
        String requete = "select * from annexe where chantierid = ?";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, chantierid);
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getInt("idannexe") + ":::" + rs1.getString("nom") + ":::" + rs1.getString("format");
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getDataChantier() {
        String requete = "select * from chantiers";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString(1) + ":::" + rs1.getString(2) + ":::" + rs1.getString(3) + ":::" + rs1.getString(4) + ":::" + rs1.getString(5) + ":::" + rs1.getString(6) + ":::" + rs1.getString(7) + ":::" + rs1.getTimestamp(8) + ":::" + rs1.getString(9);
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getDataSolution(int idprobleme) {
        String requete = "select * from solution where probleme_idprobleme = ?";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, idprobleme);
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString(1) + ":::" + rs1.getString(2) + ":::" + rs1.getString(3);
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getDataProcedure(int idsolution) {
        String requete = "select * from etape where idsolution = ?";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, idsolution);
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString(1) + ":::" + rs1.getString(2) + ":::" + rs1.getString(3);
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getDataProbleme() {
        String requete = "select * from probleme";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString(1) + ":::" + rs1.getString(2) + ":::" + rs1.getString(3);
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getNameStation() {
        String requete = "select * from station";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString(2) + ":::";

            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public String getCodeStation(String nom) {
        String requete = "select * from station where nom_station = ?";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            //re = "";
            rs1.next();
            re = rs1.getString("id");

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return re;
    }

    /**
     *
     * @param login
     * @return the index with represent from where we should send data
     */
    public boolean getIfUserExists(String logi) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);
            String requete = "SELECT * FROM utilisateur WHERE login = ?";

            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, logi);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    /**
     *
     * @param login
     * @return the index with represent from where we should send data
     */
    public boolean identify(String id, String password) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, this.password);
            String requete = "SELECT * FROM utilisateur WHERE login = ? and password = ? ";

            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, id);
            stmt.setString(2, password);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public boolean getIfChantierExists(String nom) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM chantiers WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public boolean getIfSolutionExists(String nom) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM solution WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public boolean getIfProblemeExists(String nom) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM probleme WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public boolean getIfProblemeExists(int idprobleme, int idchantier) throws SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM problemechantier WHERE idprobleme = ? and idchantier = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, idprobleme);
            stmt.setInt(2, idchantier);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public String getUser() {
        String requete = "select * from utilisateur";
        String re = "";
        try {
            Connection con = DriverManager.getConnection(this.strURL, login, password);
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                re = re + rs1.getString("nom") + ":::" + rs1.getString("prenom") + ":::" + rs1.getString("poste") + ":::" + rs1.getString("login");
                re += ";;;";
            }

            con.close();
            System.out.println("voici le nombre de lignes" + re);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return re;
    }

    public int getChantierId(String nom) throws SQLException {
        int result = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM chantiers WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                //result = "";
            } else {
                result = rs1.getInt("idchantier");
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public String getProbleme(String nom) throws SQLException {
        String result = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM probleme WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            if (rs1.next()) {
                result = rs1.getInt("idprobleme") + ":::" + rs1.getString("nom") + ":::" + rs1.getString("libelle");
            }

            //}
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public String getProbleme(int id) throws SQLException {
        String result = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM probleme WHERE idprobleme = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, id);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            if (rs1.next()) {
                result = rs1.getInt("idprobleme") + ":::" + rs1.getString("nom") + ":::" + rs1.getString("libelle");
            }

            //}
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public int getProblemeId(String nom) throws SQLException {
        int result = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM probleme WHERE nom = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setString(1, nom);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            if (rs1.next()) {
                result = rs1.getInt("idprobleme");
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public String getIDProbleme(int idchantier) throws SQLException {
        String result = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(strURL, login, password);

            String requete = "SELECT * FROM problemechantier WHERE idchantier = ?";
            java.sql.PreparedStatement stmt = con.prepareStatement(requete);
            stmt.setInt(1, idchantier);
            ResultSet rs1 = stmt.executeQuery();
            int i = 0;
            while (rs1.next()) {
                result = result + rs1.getInt("idprobleme") + ":::";
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            con.close();
        }
        return result;
    }

    public String getUrlConnexion() {
        return urlConnexion;
    }

    public void setUrlConnexion(String urlConnexion) {
        this.urlConnexion = urlConnexion;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStrURL() {
        return strURL;
    }

    public void setStrURL(String strURL) {
        this.strURL = strURL;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPilotString() {
        return pilotString;
    }

    public void setPilotString(String pilotString) {
        this.pilotString = pilotString;
    }

}
