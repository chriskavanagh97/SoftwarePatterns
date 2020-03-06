import javax.swing.*;

public class Admin extends Menu implements Interface{

    Admin(){
        loop = true;
        loop2 = true;
        boolean cont = false;
        while(loop)
        {
            Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

            if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
            {

                int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                if(Usernotfound == JOptionPane.NO_OPTION)
                {
                    f1.dispose();
                    loop = false;
                    loop2 = false;
                    menuStart();
                }
            }
            else
            {
                loop = false;
            }
        }


        while(loop2)
        {
            Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

            if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin password
            {
                int incorrectpassword  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
                if(incorrectpassword == JOptionPane.NO_OPTION){
                    f1.dispose();
                    loop2 = false;
                    menuStart();
                }
            }
            else
            {
                loop2 = false;
                cont = true;
            }
        }

        if(cont)
        {
            //f1.dispose();
            admin();
        }
    }
}
