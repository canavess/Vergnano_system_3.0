package GUI;

/*
VALORE_SA = VALORE NUMERICO (CAFFE') SCALATO/AGGIUNTO NELL'ULTIMA OPERAZIONE
ULTIMA_OPERAZIONE = TIPO DI OPERAZIONE COMPIUTA
n = AZIONE_CHE_ESEGUE (AZIONE_ORIGINARIA) chiamato anche puntatore
0 = NO AZIONI DA ANNULLARE
1 = ANNULLA PAGAMENTO (PAGA)
2 = ELIMINA CARTA (CREA)
3 = ANNULLA RICARICA 10 (RICARICA +10)
4 = CREA CARTA (ELIMINA)
5 = AGGIUNGI (SOTTRAI)
6 = SOTTRAI (AGGIUNGI)

*/


import static DB_CORE.DB_CORE.Data;
import static DB_CORE.DB_CORE.leggiCarta;
import static DB_CORE.DB_FUNZ_CRT_VRG.cancellaCartaCaffe;
import static DB_CORE.DB_FUNZ_CRT_VRG.cancellaCartaPrepagata;
import static DB_CORE.DB_FUNZ_CRT_VRG.getData_creazione;
import static DB_CORE.DB_FUNZ_CRT_VRG.getData_utilizzo;
import static DB_CORE.DB_FUNZ_CRT_VRG.getRendiconto;
import static DB_CORE.DB_FUNZ_CRT_VRG.getSaldoCaffe;
import static DB_CORE.DB_FUNZ_CRT_VRG.getSaldoPrepagata;
import static DB_CORE.DB_FUNZ_CRT_VRG.getTipoCarta;
import static DB_CORE.DB_FUNZ_CRT_VRG.getTipo_op;
import static DB_CORE.DB_FUNZ_CRT_VRG.getUltima_operazione;
import static DB_CORE.DB_FUNZ_CRT_VRG.getValore_sa;
import static DB_CORE.DB_FUNZ_CRT_VRG.getpData_creazione;
import static DB_CORE.DB_FUNZ_CRT_VRG.getpData_utilizzo;
import static DB_CORE.DB_FUNZ_CRT_VRG.getpUltima_operazione;
import static DB_CORE.DB_FUNZ_CRT_VRG.newRendiconto;
import static DB_CORE.DB_FUNZ_CRT_VRG.setData_utilizzo;
import static DB_CORE.DB_FUNZ_CRT_VRG.setID;
import static DB_CORE.DB_FUNZ_CRT_VRG.setRendiconto;
import static DB_CORE.DB_FUNZ_CRT_VRG.setSaldoCaffe;
import static DB_CORE.DB_FUNZ_CRT_VRG.setSaldoPrepagata;
import static DB_CORE.DB_FUNZ_CRT_VRG.setTipo_op;
import static DB_CORE.DB_FUNZ_CRT_VRG.setUltima_operazione;
import static DB_CORE.DB_FUNZ_CRT_VRG.setValore_sa;
import static DB_CORE.DB_FUNZ_CRT_VRG.setpData_utilizzo;
import static DB_CORE.DB_FUNZ_CRT_VRG.setpID;
import static DB_CORE.DB_FUNZ_CRT_VRG.setpUltima_operazione;
import javax.swing.JOptionPane;


public class Database {
    
    /*per un futuro annulla eliminazione
    static int ncff;
    static float vsld;
    static String dataUso;
    static String dataCreaz;
    static String pdataUso;
    static String pdataCreaz;
    static int valoresa;
    static int ultimaop;
    static int tipop;
    static float pultimaop;
    static boolean cprp;
    static boolean ccff;
    */
    
    public static void Saldo()throws Exception{     //SALDO CARTA PREPAGATA E CARTA CAFFE' PAGINA PRINCIPALE
        String ID = leggiCarta();
        if (ID != null){
            switch(getTipoCarta(ID)){
                
                case 0:                                                         //CARTA NON PRESENTE NELLE TABLE    
                    JOptionPane.showMessageDialog(null, "La carta non esiste!");
                break;
                
                case 1:                                                         //CARTA CAFFE'
                    int saldoCaffe = getSaldoCaffe(ID);
                    if(saldoCaffe != -999){
                        JOptionPane.showMessageDialog(null, "-- CARTA CAFFE' -- \n"
                                +"Saldo Caffe': "+saldoCaffe
                                );
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERRORE, riprova!");
                    }
                break;
                
                case 2:                                                         //CARTA PREPAGATA
                    float saldoPrepagata = getSaldoPrepagata(ID);
                    if(saldoPrepagata != -999){
                        JOptionPane.showMessageDialog(null, "-- CARTA PREPAGATA -- \n"
                                +"Saldo Prepagata: "+saldoPrepagata+" EUR"
                                );
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "ERRORE, riprova");
                    }
                break;
                
                case 3:                                                         //MULTICARTA (CAFFE' + PREPAGATA)
                    int vCaffe = getSaldoCaffe(ID);
                    float vsaldo = getSaldoPrepagata(ID);
                    JOptionPane.showMessageDialog(null, "-- MULTICARTA -- \n"
                            +"Saldo Caffe': "+vCaffe+" \n"
                            +"Saldo Prepagata: "+ vsaldo+" EUR"
                            );
                break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Errore nel Database");
                break;
            }
        }
    }
    
    public static void infoCarta()throws Exception{                             //INFO CARTA PREPAGATA E CARTA CAFFE' PAGINA PRINCIPALE
        String ID = leggiCarta();
        if (ID != null){
            switch(getTipoCarta(ID)){
                
                case 0:                                                         //CARTA NON PRESENTE NELLE TABLE    
                    JOptionPane.showMessageDialog(null, "La carta non esiste!");
                break;
                
                case 1:                                                         //CARTA CAFFE'
                    int saldoCaffe = getSaldoCaffe(ID);
                    if(saldoCaffe != -999){
                        String data_c = getData_creazione(ID);
                        String data_u = getData_utilizzo(ID);
                        int ultima_op = getUltima_operazione(ID);
                        int vsa = getValore_sa(ID);
                        if(data_c != null && data_u != null && ultima_op != -999 && vsa != -999){
                            JOptionPane.showMessageDialog(null, "-- CARTA CAFFE' -- \n"
                                    +"Saldo Caffe': " +saldoCaffe+ "\n"
                                    +"Data Creazione: " +data_c+ "\n"
                                    +"Data Utilizzo: " +data_u+ "\n"
                                    +"Ultima Operazione: " +vsa+ "\n"
                                    //+"Tipo di operazione: " +ultima_op
                                    );
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ERRORE, problemi con la carta!");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERRORE, riprova!");
                    }
                break;
                
                case 2:                                                         //CARTA PREPAGATA
                    float saldoPrepagata = getSaldoPrepagata(ID);
                    if(saldoPrepagata != -999){
                        String pdata_c = getpData_creazione(ID);
                        String pdata_u = getpData_utilizzo(ID);
                        float pultima_op = getpUltima_operazione(ID);
                        int tipo_op = getTipo_op(ID);
                        if(pdata_c != null && pdata_u != null && pultima_op != -999 && tipo_op != -999){
                            JOptionPane.showMessageDialog(null, "-- CARTA PREPAGATA -- \n"
                                    +"Saldo Prepagata: " +saldoPrepagata+ " EUR\n"
                                    +"Data Creazione: " +pdata_c+ "\n"
                                    +"Data Utilizzo: " +pdata_u+ "\n"
                                    +"Ultima Operazione: " +pultima_op+ "\n"
                                    //+"Tipo Op: " +tipo_op
                                    );
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "ERRORE, problemi con la carta!");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "ERRORE, riprova");
                    }
                break;
                
                case 3:                                                         //MULTICARTA (CAFFE' + PREPAGATA)
                    int vCaffe = getSaldoCaffe(ID);
                    float vsaldo = getSaldoPrepagata(ID);
                    if (vCaffe != -999 && vsaldo != -999){
                        //PRENDO I VALORI DELLA PARTE CAFFE'
                        String data_c = getData_creazione(ID);
                        String data_u = getData_utilizzo(ID);
                        int ultima_op = getUltima_operazione(ID);
                        int vsa = getValore_sa(ID);
                        
                        //PRENDO I VALORI DELLA PARTE PREPAGATA
                        String pdata_c = getpData_creazione(ID);
                        String pdata_u = getpData_utilizzo(ID);
                        float pultima_op = getpUltima_operazione(ID);
                        int tipo_op = getTipo_op(ID);
                        
                        if(data_c != null && data_u != null && ultima_op != -999 && vsa != -999){               //TESTO CHE I VALORI CARTA CAFFE' SIANO VALIDI
                            if(pdata_c != null && pdata_u != null && pultima_op != -999 && tipo_op != -999){    //TESTO QUELLI DELLA PARTE PREPAGATA
                                JOptionPane.showMessageDialog(null, "-- MULTICARTA -- \n "
                                        +"Saldo Caffe': " +vCaffe+ "\n"
                                        +"Saldo Prepagata: " +vsaldo+ " EUR\n"
                                        +"Data Creazione C. Caff/Pre: " +data_c+ " / " +pdata_c+ "\n"
                                        +"Data Utilizzo C. Caff/Pre: " +data_u+ " / " +pdata_u+ "\n"
                                        +"Ultima Op. C. Caff/Pre: " +ultima_op+ " / " +pultima_op+ "\n"
                                        //+"Valore sa / Tipo Op: " +vsa+ " / " +tipo_op
                                );
                            }
                            else JOptionPane.showMessageDialog(null, "ERRORE, problemi con la carta!");
                        }
                        else JOptionPane.showMessageDialog(null, "ERRORE, problemi con la carta!");
                    }
                    
                break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Errore nel Database");
                break;
            }
        }
    }
    
    public static void eliminaCarta() throws Exception{
        String ID = leggiCarta();
        int scelta;
        int cffrim;
        float sldrim;
        if(ID != null){
            switch(getTipoCarta(ID)){
                case 0:
                    JOptionPane.showMessageDialog(null, "La carta non esiste!");
                break;
                
                case 1:
                    cffrim = getSaldoCaffe(ID);
                    scelta = JOptionPane.showConfirmDialog(null, "-- CANCELLARE --\n"
                            +"CARTA CAFFE' \n"
                            +"N°: " +ID+ "\n"
                            +"CON: "+cffrim+" CAFFE'", "WARNING", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION){
                        if(cancellaCartaCaffe(ID)){
                            JOptionPane.showMessageDialog(null, "Carta Caffè Cancellata!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ERRORE, problemi nel cancellare la carta");
                        }
                    }
                break;
                
                case 2:
                    sldrim = getSaldoPrepagata(ID);
                    scelta = JOptionPane.showConfirmDialog(null, "-- CANCELLARE --\n"
                            +"CARTA PREPAGATA \n"
                            +"N°: " +ID+ "\n"
                            +"CON: "+sldrim+" EUR", "WARNING", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION){
                        if(cancellaCartaPrepagata(ID)){
                            JOptionPane.showMessageDialog(null, "Carta Prepagata Cancellata!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ERRORE, problemi nel cancellare la carta");
                        }
                    }
                break;
                
                case 3:
                    cffrim = getSaldoCaffe(ID);
                    sldrim = getSaldoPrepagata(ID);
                    Object[] opzioni = {"CAF", "PRE", "MULTI", "NO"};
                    scelta = JOptionPane.showOptionDialog(null, "-- CANCELLARE --\n"
                            +"Quale parte della multicarta?\n"
                            +"N°: " +ID+ "\n"
                            +"CON: " +cffrim+ " CAF e " +sldrim+ " EUR", 
                            "WARNING", JOptionPane.WARNING_MESSAGE, 0, null, opzioni, null);
                    switch(scelta){
                        case 0:
                            cancellaCartaCaffe(ID);
                            JOptionPane.showMessageDialog(null, "Carta Caffè Cancellata!");
                        break;
                        
                        case 1:
                            cancellaCartaPrepagata(ID);
                            JOptionPane.showMessageDialog(null, "Carta Prepagata Cancellata!");
                        break;
                        
                        case 2:
                            cancellaCartaCaffe(ID);
                            cancellaCartaPrepagata(ID);
                            JOptionPane.showMessageDialog(null, "Multicarta Cancellata!");
                        break;
                            
                    }
                break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Errore nel Database");
                break;
            }
        }
    }
                
   
    
    public static void scalaCaffe(String IDC, int numero)throws Exception{      //NON USO IL LEGGICARTA(), PERCHE' DEVE PRENDERE IL VALORE DEL JTEXTFIELD SULLA HOME
        String ID;
        if (IDC != null && ! "".equals(IDC)){       //IDC E' UN VALORE ACCETTABILE?
            if(IDC.length() > 10){
                ID = IDC.substring(0,10);
            }
            else{
                ID = IDC;
            }
            int vcaffe = getSaldoCaffe(ID);
            if(vcaffe == -999){
                JOptionPane.showMessageDialog(null, "ERRORE Carta non presente!");
            }
            else if(vcaffe < numero){
                JOptionPane.showMessageDialog(null, "SALDO INSUFFICIENTE\n"+"Saldo caffè: "+vcaffe);
            }
            else{
                int newValore = vcaffe - numero;
                if(newValore >= 0){
                    boolean esito = setSaldoCaffe(ID, newValore);               //SETTO IL NUOVO SALDO
                    boolean esito2 = setValore_sa(ID, numero);                  //SCRIVO IL VALORE DEL MOVIMENTO
                    boolean esito3 = setUltima_operazione(ID, 1);               //SCRIVO IL TIPO DI MOVIMENTO (1 = SCALA CAFFE')
                    boolean esito4 = setData_utilizzo(ID, Data());
                    if(esito && esito2 && esito3 && esito4){                              //DEVONO RITORNARE TUTTI E TRE "TRUE"
                        JOptionPane.showMessageDialog(null, "CAFFE' SCALATO/I\n"+"Saldo caffè: "+newValore);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERRORE nell'aggiornamento del DB!");
                    }
                }
                
            }
            
        }
        else JOptionPane.showMessageDialog(null, "Carta non letta, RIPROVA");
    }
    
    
    public static void ricaricaCaffe(int numero)throws Exception{
        String ID = leggiCarta();
        if(ID != null){
            int vcaffe = getSaldoCaffe(ID);
            if(vcaffe != -999){
                int saldonuovo = vcaffe + numero;
                boolean esito = setSaldoCaffe(ID, saldonuovo);
                boolean esito2 = setValore_sa(ID, numero);
                boolean esito3 = setUltima_operazione(ID,3);
                boolean esito4 = setData_utilizzo(ID, Data());
                if(esito && esito2 && esito3 && esito4){
                    JOptionPane.showMessageDialog(null, "CARTA RICARICATA\n"+"Saldo caffè: "+saldonuovo);
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERRORE nell'aggiornamento del DB!");
                }
            }
            else{
            JOptionPane.showMessageDialog(null, "ERRORE carta non presente!");
            }
        }
    }

    public static void Annulla() throws Exception{
        int ultima_op;
        int tipo_op;
        int vsa;
        float pultima_op;
        String ID = leggiCarta();
        if(ID != null){
            int tipoCarta = getTipoCarta(ID);
            switch (tipoCarta){
                case 0:
                    JOptionPane.showMessageDialog(null,"La carta non esiste!");
                break;
                
                case 1:
                    ultima_op = getUltima_operazione(ID);
                    vsa = getValore_sa(ID);
                    switch(ultima_op){
                        case 0:                 //NO AZ. DA ANN.
                            JOptionPane.showMessageDialog(null,"Nessuna azione annullabile!");
                        break;
                        
                        case 1:                 //CAFFE' SCALATO (LO RIAGGIUNGIAMO)
                            int Scelta1 = JOptionPane.showConfirmDialog (null, "---CARTA CAFFE'--- \n Annullare: Scala Caffe' ("+vsa+")","WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta1 == JOptionPane.YES_OPTION){
                                Aggiungi(vsa,ID,0);
                            }
                        break;
                        
                        /* case 2:              //DOVREBBE ESSERE PER IL CREA CARTA MA BOH
                        break; */
                        
                        case 3:
                            int Scelta3 = JOptionPane.showConfirmDialog (null, "---CARTA CAFFE'--- \n Annullare: Ricarica +"+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta3 == JOptionPane.YES_OPTION){
                                Sottrai(vsa,ID,0);
                            }
                        break;
                        
                        /* case 4:              //DOVREBBE ESSERE PER L'ELIMINA CARTA MA ANCHE QUA BOH
                        break;*/
                        
                        case 5:
                            int Scelta5 = JOptionPane.showConfirmDialog (null, "---CARTA CAFFE'--- \n Annullare: Sottrai "+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta5 == JOptionPane.YES_OPTION){
                                Aggiungi(vsa,ID,0);
                            }
                        break;
                        
                        case 6:
                            int Scelta6 = JOptionPane.showConfirmDialog (null, "---CARTA CAFFE'--- \n Annullare: Aggiungi "+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta6 == JOptionPane.YES_OPTION){
                                Sottrai(vsa,ID,0);
                            }
                        break;
                        
                        default:
                            JOptionPane.showMessageDialog(null,"Errore");
                        break;
                    }
                    
                break;
                
                case 2:
                    tipo_op = getTipo_op(ID);
                    pultima_op = getpUltima_operazione(ID);
                    switch (tipo_op){
                        case 90:        //NO AZ. DA ANN.
                            JOptionPane.showMessageDialog(null,"Nessuna azione annullabile!");
                        break;
                        
                        case 91:        //ANNULLARE pAGGIUNGI
                            int Scelta91 = JOptionPane.showConfirmDialog (null, "---CARTA PREPAGATA--- \n Annullare: Aggiungi "+pultima_op+" EUR","WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta91 == JOptionPane.YES_OPTION){
                                pSottrai(pultima_op, ID, false, 90);
                            }
                        break;
                        
                        case 92:        //ANNULLARE pSOTTRAI
                            int Scelta92 = JOptionPane.showConfirmDialog (null, "---CARTA PREPAGATA--- \n Annullare: Sottrai "+pultima_op+" EUR","WARNING",JOptionPane.YES_NO_OPTION);
                            if(Scelta92 == JOptionPane.YES_OPTION){
                                pAggiungi(pultima_op, ID, true, 90);
                            }
                        break;
                        
                        default:
                            JOptionPane.showMessageDialog(null,"Errore");
                        break;
                    }
                break;
                
                case 3:
                    Object[] opzioni = {"CAF", "PRE", "NO"};
                    int scelta = JOptionPane.showOptionDialog(null, "-- MULTICARTA --\n"+
                            "QUALE TIPO DI OP. ANNULLARE?", "WARNING", JOptionPane.WARNING_MESSAGE, 0, null, opzioni, null);
                    switch(scelta){
                        case 0:
                            ultima_op = getUltima_operazione(ID);
                            vsa = getValore_sa(ID);
                            switch(ultima_op){
                                case 0:                 //NO AZ. DA ANN.
                                    JOptionPane.showMessageDialog(null,"Nessuna azione annullabile!");
                                break;
                        
                                case 1:                 //CAFFE' SCALATO (LO RIAGGIUNGIAMO)
                                    int Scelta1 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Scala Caffe' ("+vsa+")","WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta1 == JOptionPane.YES_OPTION){
                                        Aggiungi(vsa,ID,0);
                                    }
                                break;
                        
                                /* case 2:              //DOVREBBE ESSERE PER IL CREA CARTA MA BOH
                                break; */
                        
                                case 3:
                                    int Scelta3 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Ricarica +"+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta3 == JOptionPane.YES_OPTION){
                                        Sottrai(vsa,ID,0);
                                    }
                                break;
                        
                                /* case 4:              //DOVREBBE ESSERE PER L'ELIMINA CARTA MA ANCHE QUA BOH
                                break;*/
                        
                                case 5:
                                    int Scelta5 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Sottrai "+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta5 == JOptionPane.YES_OPTION){
                                        Aggiungi(vsa,ID,0);
                                    }
                                break;
                        
                                case 6:
                                    int Scelta6 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Aggiungi "+vsa,"WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta6 == JOptionPane.YES_OPTION){
                                        Sottrai(vsa,ID,0);
                                    }
                                break;
                        
                                default:
                                    JOptionPane.showMessageDialog(null,"Errore");
                                break;
                            }
                        break;
                        
                        case 1:
                            tipo_op = getTipo_op(ID);
                            pultima_op = getpUltima_operazione(ID);
                            switch (tipo_op){
                                case 90:        //NO AZ. DA ANN.
                                    JOptionPane.showMessageDialog(null,"Nessuna azione annullabile!");
                                break;
                        
                                case 91:        //ANNULLARE pAGGIUNGI
                                    int Scelta91 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Aggiungi "+pultima_op+" EUR","WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta91 == JOptionPane.YES_OPTION){
                                        pSottrai(pultima_op, ID, false, 90);
                                    }
                                break;
                        
                                case 92:        //ANNULLARE pSOTTRAI
                                    int Scelta92 = JOptionPane.showConfirmDialog (null, "--- MULTICARTA --- \n Annullare: Sottrai "+pultima_op+" EUR","WARNING",JOptionPane.YES_NO_OPTION);
                                    if(Scelta92 == JOptionPane.YES_OPTION){
                                        pAggiungi(pultima_op, ID, true, 90);
                                    }
                                break;
                        
                                default:
                                    JOptionPane.showMessageDialog(null,"Errore");
                                break;
                            }
                        break;
                    }
                break;
                
                default:
                    JOptionPane.showMessageDialog(null,"PROBLEMI CON IL DB!");
                break;   
            }
        }
    }
    
    public static void pRendiconto() throws Exception{
            float Tg = getRendiconto();
            if(Tg != -999){
                JOptionPane.showMessageDialog(null, "Totale giornaliero: "+Tg);
            }
            else if (Tg == -999) JOptionPane.showMessageDialog(null,"Attenzione, nessun movimento oggi!");
            else JOptionPane.showMessageDialog(null,"PROBLEMI CON IL DB!");
    }
    
    
    public static void creaCartaCaffe(int ncaffe)throws Exception{
        String ID = leggiCarta();
        if(ID != null){
            int esito = DB_CORE.DB_FUNZ_CRT_VRG.creaCartaCaffe(ID, ncaffe);
            switch(esito){
                case 0:         //LA CARTA ESISTE GIA'
                    JOptionPane.showMessageDialog(null,"Attenzione, la carta esiste già!");
                break;
                    
                case 1:         //CARTA CREATA
                    JOptionPane.showMessageDialog(null,"Carta caffè correttamente creata");
                break;
                
                case -999:      //ERRORE DATABASE
                    JOptionPane.showMessageDialog(null,"Errore nel DB!");
                break;
                
                default:        //ERRORE PROGRAMMA
                    JOptionPane.showMessageDialog(null,"Errore nel programma!");
                break;     
            }
        }
    }

    public static void creaCartaPrepagata(float SaldoIniz, int CashBack)throws Exception{
        String ID = leggiCarta();
        SaldoIniz = SaldoIniz + ((SaldoIniz*CashBack)/100);
        if(ID != null){
            int esito = DB_CORE.DB_FUNZ_CRT_VRG.creaCartaPrepagata(ID, SaldoIniz);
            switch(esito){
                case 0:         //LA CARTA ESISTE GIA'
                    JOptionPane.showMessageDialog(null,"Attenzione, la carta esiste già!");
                break;
                    
                case 1:         //CARTA CREATA
                    JOptionPane.showMessageDialog(null,"Carta prepagata correttamente creata");
                break;
                
                case -999:      //ERRORE DATABASE
                    JOptionPane.showMessageDialog(null,"Errore nel DB!");
                break;
                
                default:        //ERRORE PROGRAMMA
                    JOptionPane.showMessageDialog(null,"Errore nel programma!");
                break;     
            }
        }
    }
    
    public static void Sottrai(int numero, String ID, int Op)throws Exception{
        int vcaffe = getSaldoCaffe(ID);
        if(vcaffe == -999) JOptionPane.showMessageDialog(null, "ERRORE, Carta non presente");
        else if (vcaffe < numero) JOptionPane.showMessageDialog(null, "Saldo NON sufficiente \n"+"Saldo Caffe': "+vcaffe);
        else if (vcaffe >= numero){
            int newsaldo = vcaffe - numero;
            boolean esito = setSaldoCaffe(ID, newsaldo);
            boolean esito2 = setValore_sa(ID, numero);
            boolean esito3 = setUltima_operazione(ID, Op);
            boolean esito4 = setData_utilizzo(ID, Data());
            if(esito && esito2 && esito3 && esito4){
                JOptionPane.showMessageDialog(null, "CAFFE' SCALATI \n Saldo Nuovo: "+newsaldo);
            }
            else{
                JOptionPane.showMessageDialog(null, "ERRORE nel DB!");
            }
        }
    }    
    public static void funzSottrai(int numero)throws Exception{
        String ID = leggiCarta();
        if (ID != null) Sottrai(numero, ID, 5);
    }
    
    public static void Aggiungi(int numero, String ID, int Op)throws Exception{
        int vcaffe = getSaldoCaffe(ID);
        if (vcaffe == -999) JOptionPane.showMessageDialog(null, "ERRORE, Carta non presente");
        else{
            int newsaldo = vcaffe + numero;
            boolean esito = setSaldoCaffe(ID, newsaldo);
            boolean esito2 = setValore_sa(ID, numero);
            boolean esito3 = setUltima_operazione(ID, Op);
            boolean esito4 = setData_utilizzo(ID, Data());
            if(esito && esito2 && esito3 && esito4){
                JOptionPane.showMessageDialog(null, "CAFFE' AGGIUNTI \n Saldo Nuovo: "+newsaldo);
            }
            else{
                JOptionPane.showMessageDialog(null, "ERRORE nel DB!");
            }
        }
    }


        public static void funzAggiungi(int numero)throws Exception{
        String ID = leggiCarta();
        if (ID != null) Aggiungi(numero, ID, 6);
        }
    
   
    
    public static void rimpiazzaCarta(String idvecchio)throws Exception{
        int Tipo = getTipoCarta(idvecchio);
        String ID = leggiCarta();
        int TipoCN = getTipoCarta(ID);
        if(ID != null && TipoCN == 0){
            switch (Tipo){
                case 0:
                    JOptionPane.showMessageDialog(null,"CARTA DA RIMPIAZZARE NON ESISTENTE");
                break;
            
                case 1:
                    if(setID(ID, idvecchio)){
                        JOptionPane.showMessageDialog(null,"Carta Caffe' RIMPIAZZATA\n"+"SALDO: "+getSaldoCaffe(ID));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERRORE");
                    }
                break;
            
                case 2:
                    if(setpID(ID, idvecchio)){
                        JOptionPane.showMessageDialog(null,"Carta Prepagata RIMPIAZZATA\n"+"SALDO: "+getSaldoPrepagata(ID));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERRORE");
                    }
                break;
            
                case 3:
                    if(setID(ID, idvecchio) && setpID(ID, idvecchio)){
                        JOptionPane.showMessageDialog(null,"Multicarta RIMPIAZZATA\n"+"SALDO CAF: "+getSaldoCaffe(ID)+"\n SALDO: "+getSaldoPrepagata(ID)+" EUR.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERRORE");
                    }
                break;
            
                default:
                    JOptionPane.showMessageDialog(null,"ERRORE CON IL DATABASE");
                break;
            }
        }
        else if(TipoCN == 1 || TipoCN == 2 || TipoCN == 3){
            JOptionPane.showMessageDialog(null,"CARTA NUOVA GIA' REGISTRATA!!");
        }
        else{
            JOptionPane.showMessageDialog(null,"ERRORE CON IL DATABASE");
        }
    }
        
    public static void pAggiungi(float numero, String ID, boolean ANN, int VSA)throws Exception{
        float saldo = getSaldoPrepagata(ID);
            if(saldo != -999){
                float newsaldo = saldo + numero;
                boolean esito = setSaldoPrepagata(ID, newsaldo);
                boolean esito2 = setTipo_op(ID, VSA);
                boolean esito3 = setpUltima_operazione(ID, numero);
                boolean esito4 = setpData_utilizzo(ID, Data());
                if(esito && esito2 && esito3 && esito4){
                    if (ANN == true) pAggGiorno(-numero);
                    JOptionPane.showMessageDialog(null, "SALDO AGGIUNTO \n Saldo Nuovo: "+newsaldo+" EUR.");
                }
                else JOptionPane.showMessageDialog(null,"ERRORE nel DB");
            }
            else JOptionPane.showMessageDialog(null,"Attenzione, scheda non presente!");
        }
    
    
    public static void funzpAggiungi(float numero, boolean ANN)throws Exception{
        String ID = leggiCarta();
        if(ID != null) pAggiungi(numero,ID, ANN, 91);
    }
    
    public static void funzpSottrai(float numero, boolean ANN)throws Exception{
        String ID = leggiCarta();
        if(ID != null) pSottrai(numero,ID, ANN, 92);
    }
    
    public static void pSottrai(float numero,String ID, boolean ANN, int VSA)throws Exception{
        float saldo = getSaldoPrepagata(ID);
        if(saldo == -999) JOptionPane.showMessageDialog(null,"Attenzione, scheda non presente!");
        else if(saldo < numero){
            JOptionPane.showMessageDialog(null, "Saldo NON sufficiente \n"+"Saldo Prepagata: "+saldo);
        }
        else if (saldo >= numero){
            float newsaldo = saldo - numero;
            boolean esito = setSaldoPrepagata(ID, newsaldo);
            boolean esito2 = setTipo_op(ID, VSA);
            boolean esito3 = setpUltima_operazione(ID, numero);
            boolean esito4 = setpData_utilizzo(ID, Data());
            if(esito && esito2 && esito3 && esito4){
                if (ANN == true) pAggGiorno(numero); 
                JOptionPane.showMessageDialog(null, "SALDO SOTTRATTO \n Saldo Nuovo: "+newsaldo+" EUR.");
            }
            else JOptionPane.showMessageDialog(null,"ERRORE nel DB");
        }
    }
    
    
    public static void pAggGiorno(float Val) throws Exception{
        float Tg = getRendiconto();
        if(Tg != -999){
            //LA DATA ESISTE E VA AGGIORNATO IL VALORE 
            Tg += Val;
            setRendiconto(Tg);
        }
        else if (Tg == -999){
        //LA DATA VA CREATA (PRIMO MOVIMENTO)
            newRendiconto(Val);
        }
        else JOptionPane.showMessageDialog(null,"PROBLEMI CON IL DB!");         //ALTRI PROBLEMI (DB?)
    }



}