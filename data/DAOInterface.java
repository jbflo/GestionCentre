/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author JBFLO ; GINEL Cette interface contient le prototype de toutes les méthodes
 * d'accès aux données indispendables aux couches suppérieures de l'application
 * c'est à dire aux couches contrôleurs. Cette interface sera sera implémentée
 * par la classe DataBaseHibernateUtil.
 */
public interface DAOInterface {

    //méthodes concernant Allocation
    public List allocationFindAll();

    public List allocationFindByDateAlloc(Date dateAlloc);

    public List allocationFindByIdAlloc(String idAlloc);

    public void allocationAdd(String idAlloc, Set versementSet);

    //méthodes concernant Bon
    public List bonFindAll();

    public List bonFindByNumero(String numero);

    public List bonFindfindByMarqueteur(String marqueteur);

    public List bonFindByDateEmission(Date dateEmission);

    public List bonFindByNbreCompart(Integer nbreCompart);

    public List bonFindByNumeroLot(String numero);

    public List bonFindByNumeroBonChargement(String numeroBon);

    public List bonFindByDensite(Float densite);

    public List bonFindByTemperature(Float temperature);


    //méthodes concernant Commande
    public List commandeFindAll();

    public List commandeFindByIdCommand(String idCommand);

    public List commandeFindByPrixUnitaire(Float prixUnitaire);

    public List commandeFindByQuantite(Integer n);

    public List commandeFindByDateCommande(Date dateCommande);



    //méthodes concernant Company
    public List companyFindAll();

    public List companyFindByCompany(String company);

    public void addCompany(String company, Set vehiculeSet);

    //méthodes concernant Compartiment
    public List compartimentFindAll();

    public List compartimentFindByIdCompartiment(Integer idCompartiment);

    public List compartimentFindByCreuxDepart(Float creuxDepart);

    public List compartimentFindByCreuxArrivee(Float creuxArrivee);

    public List compartimentFindByLibelle(String libelle);



    //méthodes concernant Cuve
    public List cuveFindAll();

    public List cuveFindByCuveID(String cuveID);

    public List cuveFindByCapacite(Float capacite);

    public List cuveFindByHauteur(Float hauteur);



    //méthodes concernant Gerant
    public List gerantFindAll();

    public List gerantFindByNom(String nom);

    public List gerantFindByPrenom(String prenom);

    public List gerantFindByTelephone(String telephone);

    public List gerantFindByEmail(String email);

    public List gerantFindByCni(String cni);

    public List gerantFindByPassword(String password);

    public List gerantFindByLogin(String login);

    public void addGerant(String cni, String nom, String prenom, String telephone, String email, String password, String login);

    //méthodes concernant Ilot
    public void addIlot(String nom, Set volucompteurSet, Set allocationSet);

    public List ilotFindAll();

    public List ilotFindByNo(String nom);

    //méthodes concernant Jeaugeage
    public List jeaugeageFindAll();

    public List jeaugeageFindByIdJeaugeage(String idJeaugeage);

    public List jeaugeageFindByCapTot(Float capTot);

    public List jeaugeageFindByHauteurAct(Float hauteurAct);

    public List jeaugeageFindByQteAct(Float qteAct);

    public List jeaugeageFindByHauteurEau(Float hauteurEau);

    public List jeaugeageFindByQuantiteEau(Float quantiteEau);



    //méthodes concernant Livraison
    public List livraisonFindAll();

    public List livraisonFindByTransactionID(String transactionID);

    public List livraisonFindByDateReceipt(Date dateReceipt);

    public List livraisonFindByDensite(Float densite);

    public List livraisonFindByTemperature(Float temperature);

    public List livraisonFindByQuantite(Float quantite);

    public List livraisonFindByNumero(String numero);
    


    //méthodes concernant LivraisonGaz
    public List livraisonGazFindAll();

    public List livraisonGazFindByLivraisonID(String livraisonID);

    public List livraisonGazFindByQuantite(int quantite);

    public List livraisongazFindByAcheteur(String acheteur);

    public List livraisonGazFindByDateLivraison(Date dateLivraison);
    
   // public void addLivraisonGaz()

    //méthodes concernant Pannes
    public List panneFindAll();

    public List panneFindByLibelle(String libelle);

    //méthodes concernant Pistolets
    public List pistoletFindAll();

    public List pistoletFindByIdPistolet(String idPistolet);

    public List pistoletFindByIndex(int index);

    //méthodes concernant PointagePompiste
    public List pointagepompisteFindAll();

    public List pointagepompisteFindByHeure(Date heure);

    public List pointagepompisteFindById(Integer id);

    //méthodes concernant Pompiste
    public List pompisteFindAll();

    public List pompisteFindByNom(String nom);

    public List pompisteFindByCni(String cni);

    public List pompisteFindByPrenom(String prenom);

    public List pompisteFindByTelephone(String telephone);

    public List pompisteFindByEmail(String email);

    public List PompistefindByLogin(String login);

    public List pompisteFindByPassword(String password);

    //méthodes concernant PrixUnitaire
    public List prixunitaireFindAll();

    public List prixunitaireFindByMontant(Float montant);

    //méthodes concernant Produit
    public List produitFindAll();

    public List produitFindByIdProduit(String idProduit);

    //méthodes concernant Quart
    public List quartFindAll();

    public List quartFindByNumero(Integer numero);

    public List quartFindByHeureDebut(Date heureDebut);

    public List quartFindByHeureFin(Date heureFin);

    //méthodees concernant Repartition
    public List repartitionFindAll();

    public List repartitionFindByIdRepartition(String idRepartition);

    public List repartitionFindByDateRepartition(Date dateRepartition);

    public List repartitionFindByQuantite(Float quantite);

    //méthodes concernant Station
    public List stationFindAll();

    public List stationFindByNom(String nom);

    public List stationFindByLocalisation(String localisation);

    public List stationFindByIdentifiant(String identifiant);

    public List stationFindByDateFonctionnement(Date dateFonctionnement);

    //méthodes concernant TypeGaz
    public List typeGazFindAll();

    public List typeGazFindByTypeID(String typeID);

    public List typeGazFindByFabriquant(String fabriquant);

    public List typeTypeGazFindByMasse(float masse);

    public List typeGazFindByEtat(String etat);

    public List typeGazFindByPrixAchatPlein(float prixAchatPlein);

    public List typeGazFindByPrixAchatVide(float prixAchatVide);

    public List typegazFindByPrixAchatEchange(float prixAchatEchange);

    public List typegazFindByPrixVentePlein(float prixVentePlein);

    public List typegazFindByPrixVenteVide(float prixVenteVide);

    public List typegazFindByPrixVenteEchange(float prixVenteEchnage);

    //méthodes concernant TypePanne
    public List typepanneFindAll();

    public List typepanneFindByIdType(String idType);

    //méthodes concernant Vehicule
    public List vehiculeFindAll();

    public List vehiculeFindByMatricule(String matricule);

    public List vehiculeFindByDriverName(String driverName);

    public List vehiculeFindByCni(String cni);

    //méthodes concernant VenteGaz
    public List ventegazFindAll();

    public List ventegazFindByCodeVente(String codeVente);

    public List ventegazFindByQuantite(int quantite);

    public List ventegazFindByAcheteur(String acheteur);

    public List ventegazFindByDateVente(Date dateVente);

    //méthodes concernant Versement
    public List versementFindAll();

    public List versementFindByMontant(Float montant);

    public List versementFindByManquant(Float manquant);

    public List versementFindByHeureVersement(Date heureVersement);

    public List versementFindById(Integer id);

    //méthodes concernant volucompteur
    public List volucompteurFindAll();

    public List volucompteurFindByNomVolucompteur(String nomVolucompteur);

}
