package DB_CORE;

import static DB_CORE.DB_CORE.Data;
import static DB_CORE.DB_CORE.DataR;
import static DB_CORE.DB_CORE.cercaDB;
import static DB_CORE.DB_CORE.getConnection;
import static DB_CORE.DB_CORE.updateDB;
import java.sql.*;

public class DB_FUNZ_CRT_VRG {
    /* ATTRIBUTI DELLA CLASSE (CONNESSIONE AL DB) */
    static String DB_URL = "jdbc:mysql://localhost:3306/client";    //INDIRIZZO DB
    static String DB_USR = "root";                                  //UTENTE DB
    //static String DB_KEY = "";                                    //PASSKEY DB DEBUG
    static String DB_KEY = "teamsoftware";                          //PASSKEY DB
    
    //static String DB_URL = "jdbc:mysql://83.169.3.127:3306/client";    //INDIRIZZO DB
    //static String DB_USR = "se_user";                                  //UTENTE DB
    //static String DB_KEY = "clqW38&8"; 
    
    /* VALORI DB */
    static String tCAF = "tableschede";                          //Table delle carte caffè
    static String tPRE = "tableprepagata";                       //Table delle carte prepagate
    
    /*FUNZIONI CARTA VERGNANO*/
    
    public static int getSaldoCaffe(String ID)throws Exception{
        String DB_CMD = ("SELECT ncaffe FROM " + tCAF + " WHERE ID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            int saldo = Integer.parseInt(result.getString("ncaffe"));
            return saldo;
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static float getSaldoPrepagata(String ID)throws Exception{
        String DB_CMD = ("SELECT vsaldo FROM " + tPRE + " WHERE pID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            float saldo = Float.parseFloat(result.getString("vsaldo"));
            return (saldo);
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static String getData_creazione(String ID)throws Exception{
        String DB_CMD = ("SELECT data_creazione FROM " + tCAF + " WHERE ID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            String data = result.getString("data_creazione");
            return data;
        }
        else return (null);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static String getpData_creazione(String ID)throws Exception{
        String DB_CMD = ("SELECT pdata_creazione FROM " + tPRE + " WHERE pID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            String data = result.getString("pdata_creazione");
            return data;
        }
        else return (null);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    } 
     
    public static String getData_utilizzo(String ID)throws Exception{
        String DB_CMD = ("SELECT data_utilizzo FROM " + tCAF + " WHERE ID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            String data = result.getString("data_utilizzo");
            return data;
        }
        else return (null);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static String getpData_utilizzo(String ID)throws Exception{
        String DB_CMD = ("SELECT pdata_utilizzo FROM " + tPRE + " WHERE pID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            String data = result.getString("pdata_utilizzo");
            return data;
        }
        else return (null);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static int getValore_sa(String ID)throws Exception{
        String DB_CMD = ("SELECT valore_sa FROM " + tCAF + " WHERE ID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            int vsa = Integer.parseInt(result.getString("valore_sa"));
            return vsa;
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static int getUltima_operazione(String ID)throws Exception{
        String DB_CMD = ("SELECT ultima_operazione FROM " + tCAF + " WHERE ID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            int ultop = Integer.parseInt(result.getString("ultima_operazione"));
            return ultop;
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static float getpUltima_operazione(String ID)throws Exception{
        String DB_CMD = ("SELECT pultima_operazione FROM " + tPRE + " WHERE pID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            float vuop = Float.parseFloat(result.getString("pultima_operazione"));
            return vuop;
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    public static int getTipo_op(String ID)throws Exception{
        String DB_CMD = ("SELECT tipo_op FROM " + tPRE + " WHERE pID ='"+ID+"'");
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result != null && result.next()){
            int tipop = Integer.parseInt(result.getString("tipo_op"));
            return tipop;
        }
        else return (-999);         //QUESTO VALORE NON DOVREBBE VENIRE MAI RITORNATO (IN CASO CONTRARIO, E' UN BIG PROBLEM)
    }
    
    
    
    public static int getTipoCarta(String ID) throws Exception{
        int tipoCarta = 0;                                      //0 = CARTA ASSENTE / ERRORE, 1 = CARTA CAFFE', 2 = CARTA PREPAGATA, 3 = MULTICARTA
        if(getSaldoCaffe(ID) != -999){
            tipoCarta = 1;                                      //SE E' CARTA CAFFE' RESTITUISCI 1
        }
        if(getSaldoPrepagata(ID) != -999){
            if (tipoCarta == 1) tipoCarta = 3;                  //SE E' CARTA CAFFE' E PREPAGATA RESTITUISCI 3
            else if (tipoCarta == 0) tipoCarta = 2;             //SE E' CARTA PREPAGATA RESTITUISCI 2
        }
        if(tipoCarta == 1 || tipoCarta == 2 || tipoCarta == 3){
            return (tipoCarta);
        }
        else{
            if(getConnection(DB_URL, DB_USR, DB_KEY)!= null){
                return (0);
            }
            else{
                return(-999);
            }
        }
    }
    
    
    public static boolean setSaldoCaffe(String ID, int newSaldo) throws Exception{ //!!!!!!!!!!!!!! VALORE SA !!!!!!!!!!!!!!!!!!!!!!! 
        String DB_CMD = "UPDATE " + tCAF + " set ncaffe = '"+ newSaldo +"' where ID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setSaldoPrepagata(String ID, float newSaldo) throws Exception{ //!!!!!!!!!!!!!! VALORE SA !!!!!!!!!!!!!!!!!!!!!!! 
        String DB_CMD = "UPDATE " + tPRE + " set vsaldo = '"+ newSaldo +"' where pID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setData_utilizzo(String ID, String DU) throws Exception{ 
        String DB_CMD = "UPDATE " + tCAF + " set data_utilizzo = '"+ DU +"' where ID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setpData_utilizzo(String ID, String DU) throws Exception{ 
        String DB_CMD = "UPDATE " + tPRE + " set pdata_utilizzo = '"+ DU +"' where pID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setUltima_operazione(String ID, int UOP) throws Exception{ 
        String DB_CMD = "UPDATE " + tCAF + " set ultima_operazione = '"+ UOP +"' where ID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setValore_sa(String ID, int vsa) throws Exception{ 
        String DB_CMD = "UPDATE " + tCAF + " set valore_sa = '"+ vsa +"' where ID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setpUltima_operazione(String ID, float UOP) throws Exception{ 
        String DB_CMD = "UPDATE " + tPRE + " set pultima_operazione = '"+ UOP +"' where pID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setTipo_op(String ID, int tipop) throws Exception{ 
        String DB_CMD = "UPDATE " + tPRE + " set tipo_op = '"+ tipop +"' where pID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static int creaCartaCaffe(String ID, int saldo) throws Exception{
        if (getTipoCarta(ID)==1 || getTipoCarta(ID)==3){                        //LA CARTA GIA' ESISTE
            return(0);
        }
        else{
            String data = Data();
            String DB_CMD = "INSERT INTO " + tCAF + " (ID,ncaffe,data_creazione,data_utilizzo,ultima_operazione,valore_sa) VALUES ('"+ID+"', '"+saldo+"' , '"+data+"' , 'MAI' , 0 , 0)";
            if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
                return(1);                                                      //CARTA CREATA    
            }
            else return(-999);
        }
    }
    
    public static int creaCartaPrepagata(String ID, float saldo) throws Exception{
        if (getTipoCarta(ID)==2 || getTipoCarta(ID)==3){
            return(0);
        }
        else{
            String data = Data();
            String DB_CMD = "INSERT INTO " + tPRE + " (pID,vsaldo,pdata_creazione,pdata_utilizzo,pultima_operazione,tipo_op) VALUES ('"+ID+"', '"+saldo+"' , '"+data+"' , 'MAI' , 0 , 0)";
            if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
                return(1);                                                      //CARTA CREATA    
            }
            else return(-999);
        }
    }
    
    public static boolean cancellaCartaCaffe(String ID) throws Exception{
        String DB_CMD = "DELETE FROM " + tCAF + " WHERE ID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean cancellaCartaPrepagata(String ID) throws Exception{
        String DB_CMD = "DELETE FROM " + tPRE + " WHERE pID = '"+ ID +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static float getRendiconto() throws Exception{
        String day = DataR();
        String DB_CMD = "SELECT vTotale FROM tablerendiconto WHERE rData ='"+day+"'";
        ResultSet result = cercaDB(DB_URL, DB_USR, DB_KEY, DB_CMD);
        if(result.next()){
            float Tg = Float.parseFloat(result.getString("vTotale"));
            return (Tg);
        }
        else return (-999);
    }
    
    public static boolean setRendiconto(float Tg) throws Exception{
        String day = DataR();
        String DB_CMD = "UPDATE tablerendiconto set vTotale = '"+ Tg +"' where rData = '"+ day +"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean newRendiconto(float Tg) throws Exception{
        String day = DataR();
        String DB_CMD = "INSERT INTO tablerendiconto (rData, vTotale) VALUES ('"+day+"', '"+Tg+"');";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setID(String IDn, String IDv) throws Exception{
        String DB_CMD = "UPDATE " +tCAF+ " SET ID ='"+IDn+"'"+ " where ID = '"+IDv+"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
    
    public static boolean setpID(String IDn, String IDv) throws Exception{
        String DB_CMD = "UPDATE " +tPRE+ " SET pID ='"+IDn+"'"+ " where pID = '"+IDv+"'";
        if(updateDB(DB_URL, DB_USR, DB_KEY, DB_CMD)){
            return (true);
        }
        else{
            return (false);
        }
    }
}
