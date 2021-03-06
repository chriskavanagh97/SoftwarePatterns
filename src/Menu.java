
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;
public class Menu extends JFrame implements Interface{


    boolean found = false, loop = true, loop2 = true;

    private int position = 0;
    public String password;
    private Customer customer = null;
    private CustomerAccount acc = new CustomerAccount();
    JFrame f, f1;
    JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
    JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
    JLabel customerIDLabel, passwordLabel;
    JTextField customerIDTextField, passwordTextField;
    Container content;
    Customer e;


    JPanel panel2;
    JButton add;
    String 	PPS,firstName,surname,DOB,CustomerID;

    public static void main(String[] args)
    {
        Menu driver = new Menu();
        driver.menuStart();
    }

    public void menuStart()
    {
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/

        f = new JFrame("User Type");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) { System.exit(0); }
        });

        JPanel userTypePanel = new JPanel();
        final ButtonGroup userType = new ButtonGroup();
        JRadioButton radioButton;
        userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
        radioButton.setActionCommand("Customer");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("Administrator"));
        radioButton.setActionCommand("Administrator");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("New Customer"));
        radioButton.setActionCommand("New Customer");
        userType.add(radioButton);

        JPanel continuePanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        continuePanel.add(continueButton);

        Container content = f.getContentPane();
        content.setLayout(new GridLayout(2, 1));
        content.add(userTypePanel);
        content.add(continuePanel);

        continueButton.addActionListener(ae -> {
            String user = userType.getSelection().getActionCommand(  );

            //if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
            if(user.equals("New Customer"))
            {
                new NewCustomer();

            }

            //if user select ADMIN----------------------------------------------------------------------------------------------
            if(user.equals("Administrator")	)
            {
                new Admin();
            }

            //if user selects CUSTOMER ----------------------------------------------------------------------------------------
            if(user.equals("Customer")	)
            {
                 new CustomerSignin();
            }

        });f.setVisible(true);
    }

    public void panelAdditions(JPanel panel) {
        panel.add(firstNameLabel);
        panel.add(firstNameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(pPPSLabel);
        panel.add(pPSTextField);
        panel.add(dOBLabel);
        panel.add(dOBTextField);
    }

    public void admin()
    {
        dispose();

        f = new JFrame("Administrator Menu");
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) { System.exit(0); }
        });
        f.setVisible(true);

        JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteCustomer = new JButton("Delete Customer");
        deleteCustomer.setPreferredSize(new Dimension(250, 20));
        deleteCustomerPanel.add(deleteCustomer);

        JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setPreferredSize(new Dimension(250, 20));
        deleteAccountPanel.add(deleteAccount);

        JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton bankChargesButton = new JButton("Apply Bank Charges");
        bankChargesButton.setPreferredSize(new Dimension(250, 20));
        bankChargesPanel.add(bankChargesButton);

        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton interestButton = new JButton("Apply Interest");
        interestPanel.add(interestButton);
        interestButton.setPreferredSize(new Dimension(250, 20));

        JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton editCustomerButton = new JButton("Edit existing Customer");
        editCustomerPanel.add(editCustomerButton);
        editCustomerButton.setPreferredSize(new Dimension(250, 20));

        JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton navigateButton = new JButton("Navigate Customer Collection");
        navigatePanel.add(navigateButton);
        navigateButton.setPreferredSize(new Dimension(250, 20));

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton summaryButton = new JButton("Display Summary Of All Accounts");
        summaryPanel.add(summaryButton);
        summaryButton.setPreferredSize(new Dimension(250, 20));

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton accountButton = new JButton("Add an Account to a Customer");
        accountPanel.add(accountButton);
        accountButton.setPreferredSize(new Dimension(250, 20));

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton returnButton = new JButton("Exit Admin Menu");
        returnPanel.add(returnButton);

        JLabel label1 = new JLabel("Please select an option");

        content = f.getContentPane();
        content.setLayout(new GridLayout(9, 1));
        content.add(label1);
        content.add(accountPanel);
        content.add(bankChargesPanel);
        content.add(interestPanel);
        content.add(editCustomerPanel);
        content.add(navigatePanel);
        content.add(summaryPanel);
        content.add(deleteCustomerPanel);
        //	content.add(deleteAccountPanel);
        content.add(returnPanel);

        bankChargesButton.addActionListener(ae -> {


        });

        interestButton.addActionListener(ae -> {

            if(customerList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                admin();

            }
            else
            {
                while(loop)
                {
                    Object customerID = JOptionPane.showInputDialog(null, "Customer ID of Customer You Wish to Apply Interest to:");

                    for (Customer aCustomer: customerList){

                        if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))
                        {
                            found = true;
                            customer = aCustomer;
                            loop = false;
                        }
                    }

                    if(!found)
                    {
                        int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                         if(Usernotfound == JOptionPane.NO_OPTION)
                        {
                            f.dispose();
                            loop = false;

                            admin();
                        }
                    }
                    else
                    {
                        f.dispose();
                        f = new JFrame("Administrator Menu");
                        f.setSize(400, 300);
                        f.setLocation(200, 200);
                        f.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) { System.exit(0); }
                        });
                        f.setVisible(true);


                        JComboBox<String> box = new JComboBox<>();
                        for (int i =0; i < customer.getAccounts().size(); i++)
                        {


                            box.addItem(customer.getAccounts().get(i).getNumber());
                        }
                        box.getSelectedItem();

                        JPanel boxPanel = new JPanel();

                        JLabel label = new JLabel("Select an account to apply interest to:");
                        boxPanel.add(label);
                        boxPanel.add(box);
                        JPanel buttonPanel = new JPanel();
                        JButton continueButton = new JButton("Apply Interest");
                        returnbutton(boxPanel, buttonPanel, continueButton);
                        JButton returnButton1 = null;


                        if(customer.getAccounts().isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                            f.dispose();
                            admin();
                        }
                        else
                        {

                            for(int i = 0; i < customer.getAccounts().size(); i++)
                            {
                                if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
                                {
                                    acc = customer.getAccounts().get(i);
                                }
                            }
                            continueButton.addActionListener(ae13 -> {
                                String euro = "\u20ac";
                                double interest ;
                                while(loop)
                                {
                                    String interestString = JOptionPane.showInputDialog(null, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");//the isNumeric method tests to see if the string entered was numeric.
                                    if(isNumeric(interestString))
                                    {

                                        interest = Double.parseDouble(interestString);
                                        loop = false;

                                        acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest/100)));

                                        JOptionPane.showMessageDialog(null, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
                                    }


                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                                    }


                                }

                                f.dispose();
                                admin();
                            });

                            assert false;
                            returnButton1.addActionListener(new ActionListener(  ) {
                                public void actionPerformed(ActionEvent ae) {
                                    f.dispose();
                                    menuStart();
                                }
                            });

                        }
                    }
                }
            }

        });

        editCustomerButton.addActionListener(ae -> {

            if(customerList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                admin();

            }
            else
            {

                while(loop)
                {
                    Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

                    for (Customer aCustomer: customerList){

                        if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))
                        {
                            found = true;
                            customer = aCustomer;
                        }
                    }
                    if(!found)
                    {

                        int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                         if(Usernotfound == JOptionPane.NO_OPTION)
                        {
                            f.dispose();
                            loop = false;

                            admin();
                        }
                    }
                    else
                    {
                        loop = false;
                    }

                }

                f.dispose();

                f.dispose();
                f = new JFrame("Administrator Menu");
                f.setSize(400, 300);
                f.setLocation(200, 200);
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) { System.exit(0); }
                });
                labelcreation();
                JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JPanel cancelPanel = new JPanel();

                panelAdditions(textPanel);
                textPanel.add(customerIDLabel);
                textPanel.add(customerIDTextField);
                textPanel.add(passwordLabel);
                textPanel.add(passwordTextField);

                firstNameTextField.setText(customer.getFirstName());
                surnameTextField.setText(customer.getSurname());
                pPSTextField.setText(customer.getPPS());
                dOBTextField.setText(customer.getDOB());
                customerIDTextField.setText(customer.getCustomerID());
                passwordTextField.setText(customer.getPassword());


                JButton saveButton = new JButton("Save");
                JButton cancelButton = new JButton("Exit");
                cancelPanel.add(cancelButton, BorderLayout.SOUTH);
                cancelPanel.add(saveButton, BorderLayout.SOUTH);
                //	content.removeAll();
                Container content = f.getContentPane();
                content.setLayout(new GridLayout(2, 1));
                content.add(textPanel, BorderLayout.NORTH);
                content.add(cancelPanel, BorderLayout.SOUTH);

                f.setContentPane(content);
                f.setSize(340, 350);
                f.setLocation(200, 200);
                f.setVisible(true);
                f.setResizable(false);

                saveButton.addActionListener(ae15 -> {

                    customer.setFirstName(firstNameTextField.getText());
                    customer.setSurname(surnameTextField.getText());
                    customer.setPPS(pPSTextField.getText());
                    customer.setDOB(dOBTextField.getText());
                    customer.setCustomerID(customerIDTextField.getText());
                    customer.setPassword(passwordTextField.getText());

                    JOptionPane.showMessageDialog(null, "Changes Saved.");
                });

                cancelButton.addActionListener(ae14 -> {
                    f.dispose();

                    admin();
                });
            }});
        summaryButton.addActionListener(ae -> {
            f.dispose();

            f = new JFrame("Summary of Transactions");
            f.setSize(400, 700);
            f.setLocation(200, 200);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) { System.exit(0); }
            });
            f.setVisible(true);

            JLabel label11 = new JLabel("Summary of all transactions: ");

            JPanel returnPanel1 = new JPanel();
            JButton returnButton12 = new JButton("Return");
            returnPanel1.add(returnButton12);

            JPanel textPanel = new JPanel();

            textPanel.setLayout( new BorderLayout() );
            JTextArea textArea = new JTextArea(40, 20);
            textArea.setEditable(false);
            textPanel.add(label11, BorderLayout.NORTH);
            textPanel.add(textArea, BorderLayout.CENTER);
            textPanel.add(returnButton12, BorderLayout.SOUTH);

            JScrollPane scrollPane = new JScrollPane(textArea);
            textPanel.add(scrollPane);

            //For each customer, for each account, it displays each transaction.
            for (Customer value : customerList) {
                for (int b = 0; b < value.getAccounts().size(); b++) {
                    acc = value.getAccounts().get(b);
                    for (int c = 0; c < value.getAccounts().get(b).getTransactionList().size(); c++) {

                        textArea.append(acc.getTransactionList().get(c).toString());
                        //Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use this to keep a running total but I couldnt get it  working.

                    }
                }
            }

            textPanel.add(textArea);
            content.removeAll();

            Container content = f.getContentPane();
            content.setLayout(new GridLayout(1, 1));
                content.add(label11);
            content.add(textPanel);
            content.add(returnPanel1);

            returnButton12.addActionListener(ae16 -> {
                f.dispose();
                admin();
            });
        });

        navigateButton.addActionListener(ae -> {
            f.dispose();

            if(customerList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                admin();
            }
            else
            {

                JButton first, previous, next, last, cancel;
                JPanel gridPanel, buttonPanel, cancelPanel;
                Container content = getContentPane();
                content.setLayout(new BorderLayout());
                buttonPanel = new JPanel();
                gridPanel = new JPanel(new GridLayout(8, 2));
                cancelPanel = new JPanel();

                labelcreation();

                first = new JButton("First");
                previous = new JButton("Previous");
                next = new JButton("Next");
                last = new JButton("Last");
                cancel = new JButton("Cancel");

                setFirst();

                firstNameTextField.setEditable(false);
                surnameTextField.setEditable(false);
                pPSTextField.setEditable(false);
                dOBTextField.setEditable(false);
                customerIDTextField.setEditable(false);
                passwordTextField.setEditable(false);

                panelAdditions(gridPanel);
                gridPanel.add(customerIDLabel);
                gridPanel.add(customerIDTextField);
                gridPanel.add(passwordLabel);
                gridPanel.add(passwordTextField);

                buttonPanel.add(first);
                buttonPanel.add(previous);
                buttonPanel.add(next);
                buttonPanel.add(last);

                cancelPanel.add(cancel);

                content.add(gridPanel, BorderLayout.NORTH);
                content.add(buttonPanel, BorderLayout.CENTER);
                content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
                first.addActionListener(ae17 -> {
                    position = 0;
                    setFirst();
                });

                previous.addActionListener(ae18 -> {

                    if(position >= 1)
                    {
                        position = position - 1;

                        setCustomer();
                    }
                });

                next.addActionListener(ae19 -> {

                    if(!(position == customerList.size()-1))
                    {
                        position = position + 1;

                        setCustomer();
                    }

                });

                last.addActionListener(ae110 -> {

                    position = customerList.size() - 1;
                    setCustomer();
                });

                cancel.addActionListener(ae111 -> {
                    dispose();
                    admin();
                });
                setContentPane(content);
                setSize(400, 300);
                setVisible(true);
            }
        });

        accountButton.addActionListener(ae -> {
            f.dispose();

            if(customerList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                f.dispose();
                admin();
            }
            else
            {

                loop = true;
                found = false;
                while(loop)
                {
                    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Add an Account to:");

                    for (Customer aCustomer: customerList){

                        if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))
                        {
                            found = true;
                            customer = aCustomer;
                        }
                    }

                    if(!found)
                    {
                        int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);

                         if(Usernotfound == JOptionPane.NO_OPTION)
                        {
                            f.dispose();
                            loop = false;
                            admin();
                        }
                    }
                    else
                    {
                        loop = false;
                        //a combo box in an dialog box that asks the admin what type of account they wish to create (deposit/current)
                        String[] choices = { "Current Account", "Deposit Account" };
                        String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
                                "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

                        if(account.equals("Current Account"))
                        {
                            //create current account

                            double balance = 0;
                            String number = "C" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1);//this simple algorithm generates the account number
                            ArrayList<AccountTransaction> transactionList = new ArrayList<>();
                            int randomPIN = (int)(Math.random()*9000)+1000;
                            String pin = String.valueOf(randomPIN);
                            ATMCard atm = new ATMCard(randomPIN, true);

                            CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);

                            customer.getAccounts().add(current);
                            JOptionPane.showMessageDialog(null, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);

                            f.dispose();
                            admin();
                        }

                        if(account.equals("Deposit Account"))
                        {
                            //create deposit account

                            double balance = 0, interest = 0;
                            String number = "D" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1);//this simple algorithm generates the account number
                            ArrayList<AccountTransaction> transactionList = new ArrayList<>();
                            CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);

                            customer.getAccounts().add(deposit);
                            JOptionPane.showMessageDialog(null, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);

                            f.dispose();
                            admin();
                        }

                    }
                }
            }
        });

        deleteCustomer.addActionListener(ae -> {


            if(customerList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                dispose();
                admin();
            }
            else
            {
                {
                    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");

                    for (Customer aCustomer: customerList){

                        if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))
                        {
                            found = true;
                            customer = aCustomer;
                        }
                    }


                    if(!found) {

                        int Usernotfound = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                        if (Usernotfound == JOptionPane.NO_OPTION) {
                            f.dispose();
                            loop = false;
                            admin();

                        }
                    }
                    else
                    {
                        if(customer.getAccounts().size()>0)
                        {
                            JOptionPane.showMessageDialog(null, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            customerList.remove(customer);
                            JOptionPane.showMessageDialog(null, "Customer Deleted " ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }}
        });
        deleteAccount.addActionListener(ae -> {
            {
                Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");

                for (Customer aCustomer: customerList){

                    if(aCustomer.getCustomerID().equalsIgnoreCase((String) customerID))
                    {
                        found = true;
                        customer = aCustomer;
                        loop = false;
                    }
                }

                if(!found)
                {
                    int Usernotfound  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                    if (Usernotfound == JOptionPane.YES_OPTION) {
                        loop = true;
                    }
                    else if(Usernotfound == JOptionPane.NO_OPTION)
                    {
                        f.dispose();
                        loop = false;
                        admin();
                    }
                }

            }});
        returnButton.addActionListener(ae -> {
            f.dispose();
            menuStart();
        });
    }


    private void setCustomer() {
        firstNameTextField.setText(customerList.get(position).getFirstName());
        surnameTextField.setText(customerList.get(position).getSurname());
        pPSTextField.setText(customerList.get(position).getPPS());
        dOBTextField.setText(customerList.get(position).getDOB());
        customerIDTextField.setText(customerList.get(position).getCustomerID());
        passwordTextField.setText(customerList.get(position).getPassword());
    }

    private void setFirst() {
        firstNameTextField.setText(customerList.get(0).getFirstName());
        surnameTextField.setText(customerList.get(0).getSurname());
        pPSTextField.setText(customerList.get(0).getPPS());
        dOBTextField.setText(customerList.get(0).getDOB());
        customerIDTextField.setText(customerList.get(0).getCustomerID());
        passwordTextField.setText(customerList.get(0).getPassword());
    }

    private void labelcreation() {
        firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
        surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
        pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
        dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
        customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
        passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
        firstNameTextField = new JTextField(20);
        surnameTextField = new JTextField(20);
        pPSTextField = new JTextField(20);
        dOBTextField = new JTextField(20);
        customerIDTextField = new JTextField(20);
        passwordTextField = new JTextField(20);
    }

    private void returnbutton(JPanel boxPanel, JPanel buttonPanel, JButton continueButton) {
        JButton returnButton = new JButton("Return");
        buttonPanel.add(continueButton);
        buttonPanel.add(returnButton);
        Container content = f.getContentPane();
        content.setLayout(new GridLayout(2, 1));
        content.add(boxPanel);
        content.add(buttonPanel);
    }

    public void customer()
    {
        f = new JFrame("Customer Menu");
        Customer e1 = e;
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) { System.exit(0); }
        });
        f.setVisible(true);

        if(e.getAccounts().size() == 0)
        {
            JOptionPane.showMessageDialog(null, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
            f.dispose();
            menuStart();
        }
        else
        {
            JPanel buttonPanel = new JPanel();
            JPanel boxPanel = new JPanel();
            JPanel labelPanel = new JPanel();
            JLabel label = new JLabel("Select Account:");
            labelPanel.add(label);
            JButton returnButton = new JButton("Return");
            buttonPanel.add(returnButton);
            JButton continueButton = new JButton("Continue");
            buttonPanel.add(continueButton);

            JComboBox<String> box = new JComboBox<String>();
            for (int i =0; i < e.getAccounts().size(); i++)
            {
                box.addItem(e.getAccounts().get(i).getNumber());
            }
            for(int i = 0; i<e.getAccounts().size(); i++)
            {
                if(e.getAccounts().get(i).getNumber() == box.getSelectedItem() )
                {
                    acc = e.getAccounts().get(i);
                }
            }

            boxPanel.add(box);
            content = f.getContentPane();
            content.setLayout(new GridLayout(3, 1));
            content.add(labelPanel);
            content.add(boxPanel);
            content.add(buttonPanel);

            returnButton.addActionListener(ae -> {
                f.dispose();
                menuStart();
            });

            continueButton.addActionListener(ae -> {

                f.dispose();

                f = new JFrame("Customer Menu");
                f.setSize(400, 300);
                f.setLocation(200, 200);
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) { System.exit(0); }
                });
                f.setVisible(true);

                JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton statementButton = new JButton("Display Bank Statement");
                statementButton.setPreferredSize(new Dimension(250, 20));

                statementPanel.add(statementButton);

                JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton lodgementButton = new JButton("Lodge money into account");
                lodgementPanel.add(lodgementButton);
                lodgementButton.setPreferredSize(new Dimension(250, 20));

                JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton withdrawButton = new JButton("Withdraw money from account");
                withdrawalPanel.add(withdrawButton);
                withdrawButton.setPreferredSize(new Dimension(250, 20));

                JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton returnButton1 = new JButton("Exit Customer Menu");
                returnPanel.add(returnButton1);

                JLabel label1 = new JLabel("Please select an option");

                content = f.getContentPane();
                content.setLayout(new GridLayout(5, 1));
                content.add(label1);
                content.add(statementPanel);
                content.add(lodgementPanel);
                content.add(withdrawalPanel);
                content.add(returnPanel);

                statementButton.addActionListener(ae1 -> {
                    f.dispose();
                    f = new JFrame("Customer Menu");
                    f.setSize(400, 600);
                    f.setLocation(200, 200);
                    f.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) { System.exit(0); }
                    });
                    f.setVisible(true);

                    JLabel label11 = new JLabel("Summary of account transactions: ");

                    JPanel returnPanel1 = new JPanel();
                    JButton returnButton11 = new JButton("Return");
                    returnPanel1.add(returnButton11);

                    JPanel textPanel = new JPanel();

                    textPanel.setLayout( new BorderLayout() );
                    JTextArea textArea = new JTextArea(40, 20);
                    textArea.setEditable(false);
                    textPanel.add(label11, BorderLayout.NORTH);
                    textPanel.add(textArea, BorderLayout.CENTER);
                    textPanel.add(returnButton11, BorderLayout.SOUTH);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textPanel.add(scrollPane);

                    for (int i = 0; i < acc.getTransactionList().size(); i ++)
                    {
                        textArea.append(acc.getTransactionList().get(i).toString());

                    }

                    textPanel.add(textArea);
                    content.removeAll();

                    Container content = f.getContentPane();
                    content.setLayout(new GridLayout(1, 1));
                    //	content.add(label1);
                    content.add(textPanel);
                    //content.add(returnPanel);

                    returnButton11.addActionListener(ae11 -> {
                        f.dispose();
                        customer();
                    });
                });

                lodgementButton.addActionListener(ae12 -> {

                    boolean on = true;
                    double balance = 0;

                    if(acc instanceof CustomerCurrentAccount)
                    {
                        int count = 3;
                        int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
                        loop = true;

                        while(loop)
                        {
                            if(count == 0)
                            {
                                JOptionPane.showMessageDialog(null, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
                                ((CustomerCurrentAccount) acc).getAtm().setValid(false);
                                customer();
                                loop = false;
                                on = false;
                            }

                            String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
                            int i = Integer.parseInt(Pin);

                            if(on)
                            {
                                if(checkPin == i)
                                {
                                    loop = false;
                                    JOptionPane.showMessageDialog(null, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);

                                }
                                else
                                {
                                    count --;
                                    JOptionPane.showMessageDialog(null, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                        }

                    }		if(on == true)
                    {
                        String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric.
                        if(isNumeric(balanceTest))
                        {

                            balance = Double.parseDouble(balanceTest);
                            loop = false;

                                                            }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                        }

                        String euro = "\u20ac";
                        acc.setBalance(acc.getBalance() + balance);
                        // String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        Date date = new Date();
                        String date2 = date.toString();
                        String type = "Lodgement";
                        double amount = balance;




                        AccountTransaction transaction = new AccountTransaction(date2, type, amount);
                        acc.getTransactionList().add(transaction);

                        JOptionPane.showMessageDialog(null,   euro + balance + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "New balance = "  +  euro + acc.getBalance(),"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
                    }

                });

                withdrawButton.addActionListener(ae13 -> {

                    boolean on = true;
                    double withdraw = 0;

                    if(acc instanceof CustomerCurrentAccount)
                    {
                        int count = 3;
                        int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
                        loop = true;

                        while(loop)
                        {
                            if(count == 0)
                            {
                                JOptionPane.showMessageDialog(null, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
                                ((CustomerCurrentAccount) acc).getAtm().setValid(false);
                                customer();
                                loop = false;
                                on = false;
                            }

                            String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
                            int i = Integer.parseInt(Pin);

                            if(on)
                            {
                                if(checkPin == i)
                                {
                                    loop = false;
                                    JOptionPane.showMessageDialog(null, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);

                                }
                                else
                                {
                                    count --;
                                    JOptionPane.showMessageDialog(null, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);

                                }

                            }
                        }

                    }		if(on == true)
                    {
                        String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");//the isNumeric method tests to see if the string entered was numeric.
                        if(isNumeric(balanceTest))
                        {

                            withdraw = Double.parseDouble(balanceTest);
                            loop = false;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                        }
                        if(withdraw > 500)
                        {
                            JOptionPane.showMessageDialog(null, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                            withdraw = 0;
                        }
                        if(withdraw > acc.getBalance())
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
                            withdraw = 0;
                        }

                        String euro = "\u20ac";
                        acc.setBalance(acc.getBalance()-withdraw);
                        //recording transaction:
                        //		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        Date date = new Date();
                        String date2 = date.toString();

                        String type = "Withdraw";
                        double amount = withdraw;

                        AccountTransaction transaction = new AccountTransaction(date2, type, amount);
                        acc.getTransactionList().add(transaction);


                        JOptionPane.showMessageDialog(null, euro  + withdraw +  " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "New balance = " + euro + acc.getBalance()  ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                returnButton1.addActionListener(new ActionListener(  ) {
                    public void actionPerformed(ActionEvent ae) {
                        f.dispose();
                        menuStart();
                    }
                });		});
        }
    }

    public static boolean isNumeric(String str)  // a method that tests if a string is numeric
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}