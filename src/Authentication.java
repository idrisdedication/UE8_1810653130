import javax.swing.*;
import java.io.*;

public class Authentication {
    private File file;

    public Authentication(){
        this.file = new File("C:\\Benutzer\\Latif\\UE8_LB\\FH_KufsteinTirol\\login.txt");
        if(!this.file.exists()){
            try{
                this.file.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String login(String user) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String _user = parts[0];
                String _pw = parts[1];
                if (_user.equals(user))
                    return _pw;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String password) {
        if(password.contains(";")){
            JOptionPane.showMessageDialog(null, "Das Passwort darf folgende Zeichen nicht enthalten: ;");
            return false;
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(this.file, true));
            bw.write(username +";"+password);
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bw != null)
                try{
                    bw.close();
                } catch (IOException ioEx){
                    ioEx.printStackTrace();
                }
        }
        return false;
    }
}