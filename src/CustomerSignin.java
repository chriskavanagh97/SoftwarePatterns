import javax.swing.*;

public class CustomerSignin extends Menu implements Interface {

    public CustomerSignin(){
        loop = true;
        loop2 = true;
        boolean cont = false;

        Customer customer = null;
        while(loop)
        {
            Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

            for (Customer aCustomer: customerList){

                if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))//search customer list for matching customer ID
                {
                    found = true;
                    customer = aCustomer;
                }
            }

            if(found = false)
            {
                int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                if(Usernotfound == JOptionPane.NO_OPTION)
                {
                    f.dispose();
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
            Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

            if(!customer.getPassword().equals(customerPassword))//check if custoemr password is correct
            {

                int incorrectpassword  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
                if(incorrectpassword == JOptionPane.NO_OPTION){
                    f.dispose();
                    loop2 = false;
                    menuStart();
                }
            }
            else
            {
                loop2 =false;
                cont = true;
            }
        }

        if(cont)
        {
            f.dispose();
            e = customer;
            customer();
        }
    }
}
