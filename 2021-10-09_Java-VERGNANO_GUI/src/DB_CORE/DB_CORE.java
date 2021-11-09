package DB_CORE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class DB_CORE {
    
    public static Connection getConnection(String DB_URL, String DB_USR, String DB_KEY) throws Exception{   //INDIRIZZO DB, DB USERNAME, DB PASSWORD
        try{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USR, DB_KEY);
            return conn;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public static void Log(String cmd) throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hf = new SimpleDateFormat("HH:mm:ss");
        Date dt = new Date();
        String d = df.format(dt);
        String o = hf.format(dt);
        String msg = (d + "_" + o + "_" + cmd);
        System.out.println(msg);
        
        //PER RASPBERRY 2.0
        try (BufferedWriter out = new BufferedWriter(new FileWriter("/home/pi/Backup/"+d+"/Log/Log.txt", true))) {
            out.write("---------------------------------\n"+msg + "\n");
        } catch (IOException e) {
            // errore
        }
        
        //PER TEST A CASA
        /*
         try (BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\nicco\\Desktop\\VRGNTXT\\COMANDI.txt", true))) {
            out.write("---------------------------------\n"+msg + "\n");
        } catch (IOException e) {
            // errore
        }
        */
        
    }
    
    
    public static String Data(){                                        //FUNZIONE DATA, GIORNO/MESE/ANNO
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
        
    public static String DataR(){                                       //FUNZIONE DATA ANNO-MESE-GIORNO
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return (strDate);
    }
    
    public static void spegniMacchina(){
        Runtime runtime = Runtime.getRuntime();
        try{
            Process proc = runtime.exec("shutdown -h now");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static ResultSet cercaDB(String DB_URL, String DB_USR, String DB_KEY, String DB_CMD) throws Exception{   //LEGGI SUL DB 
        Connection con = getConnection(DB_URL, DB_USR, DB_KEY);                                                     //RECUPERA LA CONNESSIONE
        if (con == null){                                                                                           //SE LA CONNESSIONE NON SI E' STABILITA
            return(null);                   
        }
        else{                                                                                                       //SE LA CONNESSIONE C'è
            PreparedStatement statement = con.prepareStatement(DB_CMD);                                             //PREPARA IL COMANDO
            ResultSet result = statement.executeQuery();                                                            //LEGGI DAL DB
            return(result);                                                                                         //RESTITUISCI I RISULTATI
        }
    }
    
    public static boolean updateDB(String DB_URL, String DB_USR, String DB_KEY, String DB_CMD) throws Exception{    //SCRIVI SUL DB
        Connection con = getConnection(DB_URL, DB_USR, DB_KEY);                                                     //CONNESSIONE
        if (con == null){
            return(false);                                                                                          //OPERAZIONE NON ESEGUITA
        }
        else{
            PreparedStatement statement = con.prepareStatement(DB_CMD);                                             //PREPARA IL COMANDO
            statement.executeUpdate();                                                                              //AGGIORNA IL DB
            con.close();
            Log(DB_CMD);
            return(true);                                                                                           //OPERAZIONE ESEGUITA
        }
    }
    
    public static String leggiCarta(){
    String IDC = JOptionPane.showInputDialog(null,"Passa la carta sul lettore");
        String ID;
        if (IDC != null && ! "".equals(IDC)){                       //IDC E' UN VALORE ACCETTABILE?
            if(IDC.length() > 10){                                  //SE HO PRESO UN VALORE PIU' LUNGO DI 10
                ID = IDC.substring(0,10);                           //PRENDI SOLO I PRIMI 10 NUMERI (SUCCEDE QUANDO LEGGE DUE VOLTE)
            }
            else{
                ID = IDC;                                           //SENNO' VA GIA' BENE COSI'
            }
            return(ID);
        }
        else JOptionPane.showMessageDialog(null,"Carta non letta, RIPROVA!");
        return(null);    
    }
    
    public static JsonObject Meteo() throws MalformedURLException, IOException{
        String citta = "Imperia";
        String apikey = "a4212d477161d19f986ea53b8927c31d";
        String lingua = "it";
        String unita = "metric";
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+citta+"&units="+unita+"&appid="+apikey+"&lang="+lingua);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");   
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                System.out.println("GET METEO REQUEST \n"+inputLine);
                JsonObject jsonObject = new JsonParser().parse(inputLine).getAsJsonObject();
                return (jsonObject);
            }
        }
        return null;
    }
    
    
}
