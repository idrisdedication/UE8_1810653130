import javax.swing.*;

public class Aufruf {
    public static void main(String[] args) {
        Authentication authentication = new Authentication();
        Log logger = new Log();

        int eingabe = JOptionPane.showConfirmDialog(null, "Sind Sie bereits ein Nutzer?","Nutzer", JOptionPane.YES_NO_OPTION);

        if(eingabe==0){
            String user = JOptionPane.showInputDialog("Wie lautet Ihr Benutzername?");
            String pw = JOptionPane.showInputDialog("Wie lautet Ihr Passwort?");
            crypter(pw);
            if (authentication.login(user) != null) {
                if (crypter(authentication.login(user)).equals(pw)) {
                    secret();
                    logger.logs(user, pw, true);
                }

                else {
                    JOptionPane.showMessageDialog(null, "Passwort ist nicht gültig!");
                    logger.logs(user, pw, false);
                }
            }
        }

        if(eingabe==1) {
            String _user = JOptionPane.showInputDialog("Wie soll Ihr Benutzername lauten?");
            String _pw = JOptionPane.showInputDialog("Wie soll Ihr Passwort lauten?");
            authentication.register(_user, _pw);
            JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch! Sie sind jetzt registriert!");
        }

        System.out.println(logger.failedLogIn());

    }

    private static void secret(){
        JOptionPane.showMessageDialog(null, "Geheimnis: \nEs gibt kein Geheimnis mein Freund!");

    }
    public static String crypter (String value) {
        char[] values = value.toCharArray();

        for (int i = 0; i < values.length; i++) {
            char letter = values[i];

            if (letter >= 'a' && letter <= 'z') {
                if (letter > 'm') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            } else if (letter >= 'A' && letter <= 'Z') {
                if (letter > 'M') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            }
            values[i] = letter;
        }
        return new String(values);
    }
}