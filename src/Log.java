import java.io.*;
import java.util.Date;

public class Log {
    private File file;

    public Log(){
        this.file = new File("C:\\Benutzer\\Latif\\UE8_LB\\FH_KufsteinTirol\\logger.txt");
        if(!this.file.exists()){
            try{
                this.file.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int failedLogIn(){
        int zaehler = 0;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(this.file));
            String zeile;
            while((zeile = br.readLine()) != null)
            {
                String[]parts = zeile.split(";");
                if(parts[3].equals("false"))
                    zaehler++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
        }
        return zaehler;

    }

    public void logs(String username, String password, boolean erfolgreicherLogin){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(this.file, true));
            bw.write(new Date()+";"+username+";"+ password.substring(0,2)+"###"+password.substring(password.length()-2, password.length())+";"+erfolgreicherLogin+System.lineSeparator());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bw !=null)
                try{
                    bw.close();
                }catch (IOException ioex){
                    ioex.printStackTrace();
                }
        }
    }
}