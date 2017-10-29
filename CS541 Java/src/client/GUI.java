package client;

import server.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * The class made to build the GUI of the application.
 * @author Dominic Duggan
 * @version 1.0
 * @time 02/05/2017
 */
public class GUI {

    private boolean connected;
    private UserManagerInterface userService;
    private PatientManagerInterface patientService;
    private ConsultationManagerInterface consultationService;
    private boolean isDoctor;
    private Notification notification;

    //GUI variables
    private JFrame frame1;
    private Container pane;
    private JPanel cards, secretaryPanel, doctorPanel, adminPanel;
    private final static String SECRETARY_PANEL = "Card with Secreatry Panel";
    private final static String DOCTOR_PANEL = "Card with Doctor Panel";
    private final static String ADMIN_PANEL = "Card with Admin Panel";
    private JTable table;
    private JScrollPane scrollPane,consultationScrollPane,doctorConsultationScrollPane;
    private Insets insets;
    private JMenuBar secretaryMenuBar, doctorMenuBar, adminMenuBar;
    private JMenu menuPatients, menuUsers, menuConsultations, menuDoctorConsultations;
    private JMenuItem menuItemAddPatient, menuItemAddUser, menuItemAddConsultation,
            menuItemViewPatients, menuItemViewPatientByPNC, menuItemViewUserByID, menuItemViewUsers, menuItemViewConsultations,
            menuItemViewConsultationByID, menuItemDoctorViewConsultationByID, menuItemDoctorViewConsultationByPatientPNC, menuItemViewConsultationsByPatientPNC,
            menuItemUpdatePatient, menuItemUpdateUser, menuItemUpdateConsultation, menuItemDoctorUpdateConsultation,
            menuItemDeleteUser, menuItemDeleteConsultation, menuItemCheckInPatient, menuItemCheckOutPatient;


    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblUserID;
    private JLabel lblPatientName;
    private JLabel lblUserName;
    private JLabel lblAddress;
    private JLabel lblDateOfBirth;
    private JLabel lblICN;
    private JLabel lblDoctorID;
    private JLabel lblPNC;
    private JLabel lblType;
    private JLabel lblOldPNC;
    private JLabel lblOldUserID;
    private JLabel lblUserUsername;
    private JLabel lblUserPassword;
    private JLabel lblConsultationID;
    private JLabel lblConsultationPatientPNC;
    private JLabel lblConsultationDoctorID;
    private JLabel lblConsultationOldID;
    private JLabel lblConsultationDate;
    private JLabel lblConsultationText;
    private JLabel lblDoctorConsultationID;
    private JLabel lblDoctorConsultationText;
    private JTextField txtUsername;
    private JTextField txtUserID;
    private JTextField txtPatientName;
    private JTextField txtUserName;
    private JTextField txtAddress;
    private JTextField txtICN;
    private JTextField txtPNC;
    private JTextField txtDoctorID;
    private JTextField txtDateOfBirth;
    private JTextField txtOldPNC;
    private JTextField txtOldUserID;
    private JTextField txtUserUsername;
    private JTextField txtConsultationID;
    private JTextField txtConsultationPatientPNC;
    private JTextField txtConsultationDoctorID;
    private JTextField txtConsultationOldID;
    private JTextField txtConsultationDate;
    private JTextArea txtConsultationText;
    private JTextArea txtDoctorConsultationText;
    private JTextField txtDoctorConsultationID;
    private JComboBox cbType;
    private JPasswordField txtPassword, txtUserPassword;
    private JButton btnConnect;
    private JButton btnAddPatient;
    private JButton btnAddUser;
    private JButton btnAddConsultation;
    private JButton btnUpdatePatient;
    private JButton btnUpdateUser;
    private JButton btnUpdateConsultation;
    private JButton btnDoctorUpdateConsultation;
    private JButton btnCheckIn;

    private static final int HS = 10, VS = 15; //HS=HorizontalSpacing VS=VerticalSpacing


    /**
     * Initialise the GUI. Creates and adds the different components of the GUI.
     * Shows the application's window and sets its position to the centre of the screen.
     */
    public GUI(UserManagerInterface userService, PatientManagerInterface patientService, ConsultationManagerInterface consultationService) {

        this.userService = userService;
        this.patientService = patientService;
        this.consultationService = consultationService;
        connected = false;
        isDoctor = false;

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }

        // Create the frame
        frame1 = new JFrame("Clinic");
        frame1.setSize(450, 100);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the components
        pane = frame1.getContentPane();
        secretaryPanel = new JPanel();
        doctorPanel = new JPanel();
        adminPanel = new JPanel();
        insets = pane.getInsets();
        pane.setLayout(null);
        cards = new JPanel(new CardLayout());
        cards.add(secretaryPanel, SECRETARY_PANEL);
        cards.add(doctorPanel, DOCTOR_PANEL);
        cards.add(adminPanel, ADMIN_PANEL);


        //Create the menuBars
        secretaryMenuBar = new JMenuBar();
        doctorMenuBar = new JMenuBar();
        adminMenuBar = new JMenuBar();

        //Create menus
        menuPatients = new JMenu("Patients");
        menuUsers = new JMenu("Users");
        menuConsultations = new JMenu("Consultations");
        menuDoctorConsultations = new JMenu("Consultations");

        //Create menu items
        menuItemAddPatient = new JMenuItem("Add Patient");
        menuItemAddUser = new JMenuItem("Add User");
        menuItemAddConsultation = new JMenuItem("Add Consultation");
        menuItemViewPatientByPNC = new JMenuItem("View Patient By PNC");
        menuItemViewUserByID = new JMenuItem("View User By ID");
        menuItemViewConsultationByID = new JMenuItem("View Consultation By ID");
        menuItemViewConsultationsByPatientPNC = new JMenuItem("View Consultation By Patient PNC");
        menuItemDoctorViewConsultationByID = new JMenuItem("View Consultation By ID");
        menuItemDoctorViewConsultationByPatientPNC = new JMenuItem("View Consultation By Patient PNC");
        menuItemViewPatients = new JMenuItem("View Patients");
        menuItemViewUsers = new JMenuItem("View Users");
        menuItemViewConsultations = new JMenuItem("View Consultations");
        menuItemUpdatePatient = new JMenuItem("Update Patient By PNC");
        menuItemUpdateUser = new JMenuItem("Update User By ID");
        menuItemUpdateConsultation = new JMenuItem("Update Consultation By ID");
        menuItemDoctorUpdateConsultation = new JMenuItem("Update Patient Consultation By ID");
        menuItemDeleteUser = new JMenuItem("Delete User By ID");
        menuItemDeleteConsultation = new JMenuItem("Delete Consultation ID");
        menuItemCheckInPatient = new JMenuItem("Check in Patient");
        menuItemCheckOutPatient = new JMenuItem("Check out Patient");

        //Create the labels
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        lblUserID = new JLabel("ID:");
        lblDoctorID = new JLabel("Doctor ID:");
        lblUserName = new JLabel("Name:");
        lblPatientName = new JLabel("Name:");
        lblAddress = new JLabel("Address:");
        lblDateOfBirth = new JLabel("DateOfBirth:");
        lblICN = new JLabel("Identity Card Number:");
        lblPNC = new JLabel("Personal Numeric Code:");
        lblType = new JLabel("Type:");
        lblOldPNC = new JLabel("Old PNC:");
        lblOldUserID = new JLabel("Old ID:");
        lblConsultationOldID = new JLabel("Old ID:");
        lblConsultationID = new JLabel("ID:");
        lblConsultationDate = new JLabel("Date:");
        lblConsultationPatientPNC = new JLabel("Patient PNC:");
        lblConsultationDoctorID = new JLabel("Doctor ID:");
        lblConsultationText = new JLabel("Observations:");
        lblDoctorConsultationText = new JLabel("Observations:");
        lblDoctorConsultationID = new JLabel("Consultation ID:");
        lblUserUsername = new JLabel("User Username:");
        lblUserPassword = new JLabel("User Password:");
        lblDateOfBirth = new JLabel("Date of birth:");


        ///Create text fields
        txtUsername = new JTextField(10);
        txtUserID = new JTextField(5);
        txtUserName = new JTextField(10);
        txtPatientName = new JTextField(10);
        txtAddress = new JTextField(15);
        txtDateOfBirth = new JTextField(10);

        txtICN = new JTextField(6);
        txtPNC = new JTextField(13);
        txtDoctorID = new JTextField(10);
        txtOldPNC = new JTextField(13);
        txtOldUserID = new JTextField(13);
        txtConsultationOldID = new JTextField(5);
        txtConsultationID = new JTextField(5);
        txtConsultationDate = new JTextField(10);
        txtConsultationPatientPNC = new JTextField(13);
        txtConsultationDoctorID = new JTextField(5);
        txtDoctorConsultationID = new JTextField(5);
        txtUserUsername = new JTextField(10);
        txtDateOfBirth = new JTextField(10);

        //scroll panes with text areas
        consultationScrollPane = new JScrollPane();
        txtConsultationText = new JTextArea(5, 70);
        txtConsultationText.setLineWrap(true);
        txtConsultationText.setWrapStyleWord(true);
        consultationScrollPane = new JScrollPane(txtConsultationText);

        txtDoctorConsultationText = new JTextArea(5, 70);
        txtDoctorConsultationText.setLineWrap(true);
        txtDoctorConsultationText.setWrapStyleWord(true);
        doctorConsultationScrollPane = new JScrollPane(txtDoctorConsultationText);
        doctorConsultationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        //Create Password field
        txtPassword = new JPasswordField(10);
        txtUserPassword = new JPasswordField(10);
        //Create buttons
        btnConnect = new JButton("Connect");
        btnAddPatient = new JButton("Add Patient");
        btnAddUser = new JButton("Add User");
        btnAddConsultation = new JButton("Add Consultation");
        btnUpdatePatient = new JButton("Update Patient");
        btnUpdateUser = new JButton("Update User");
        btnUpdateConsultation = new JButton("Update Consultation");
        btnDoctorUpdateConsultation = new JButton("Update Consultation");
        btnCheckIn = new JButton("Check in");

        String[] options = {"secretary", "doctor", "admin"};
        cbType = new JComboBox(options);

        //Add menus to me menuBars
        secretaryMenuBar.add(menuPatients);
        secretaryMenuBar.add(menuConsultations);
        doctorMenuBar.add(menuDoctorConsultations);
        adminMenuBar.add(menuUsers);

        //Add menuItems to menus
        menuPatients.add(menuItemAddPatient);
        menuPatients.add(menuItemViewPatients);
        menuPatients.add(menuItemViewPatientByPNC);
        menuPatients.add(menuItemUpdatePatient);
        menuPatients.add(menuItemCheckInPatient);
        menuPatients.add(menuItemCheckOutPatient);

        menuUsers.add(menuItemAddUser);
        menuUsers.add(menuItemViewUsers);
        menuUsers.add(menuItemViewUserByID);
        menuUsers.add(menuItemUpdateUser);
        menuUsers.add(menuItemDeleteUser);

        menuConsultations.add(menuItemAddConsultation);
        menuConsultations.add(menuItemViewConsultations);
        menuConsultations.add(menuItemViewConsultationsByPatientPNC);
        menuConsultations.add(menuItemViewConsultationByID);
        menuConsultations.add(menuItemUpdateConsultation);
        menuConsultations.add(menuItemDeleteConsultation);

        menuDoctorConsultations.add(menuItemDoctorViewConsultationByPatientPNC);
        menuDoctorConsultations.add(menuItemDoctorViewConsultationByID);
        menuDoctorConsultations.add(menuItemDoctorUpdateConsultation);

        // Add components to the panels
        pane.add(lblUsername);
        pane.add(lblPassword);
        pane.add(txtUsername);
        pane.add(txtPassword);
        pane.add(btnConnect);
        pane.add(cards, null);

        secretaryPanel.add(lblOldPNC);
        secretaryPanel.add(txtOldPNC);
        secretaryPanel.add(lblPNC);
        secretaryPanel.add(txtPNC);
        secretaryPanel.add(lblDoctorID);
        secretaryPanel.add(txtDoctorID);
        secretaryPanel.add(lblICN);
        secretaryPanel.add(txtICN);
        secretaryPanel.add(lblPatientName);
        secretaryPanel.add(txtPatientName);
        secretaryPanel.add(lblDateOfBirth);
        secretaryPanel.add(txtDateOfBirth);
        secretaryPanel.add(lblAddress);
        secretaryPanel.add(txtAddress);
        secretaryPanel.add(btnCheckIn);

        secretaryPanel.add(lblConsultationOldID);
        secretaryPanel.add(txtConsultationOldID);
        secretaryPanel.add(lblConsultationID);
        secretaryPanel.add(txtConsultationID);
        secretaryPanel.add(lblConsultationPatientPNC);
        secretaryPanel.add(txtConsultationPatientPNC);
        secretaryPanel.add(lblConsultationDoctorID);
        secretaryPanel.add(txtConsultationDoctorID);
        secretaryPanel.add(lblConsultationDate);
        secretaryPanel.add(txtConsultationDate);
        secretaryPanel.add(lblConsultationText);
        secretaryPanel.add(consultationScrollPane);

        doctorPanel.add(lblDoctorConsultationID);
        doctorPanel.add(txtDoctorConsultationID);
        doctorPanel.add(lblDoctorConsultationText);
        doctorPanel.add(doctorConsultationScrollPane);
        doctorPanel.add(btnDoctorUpdateConsultation);


        adminPanel.add(lblOldUserID);
        adminPanel.add(txtOldUserID);
        adminPanel.add(lblUserID);
        adminPanel.add(txtUserID);
        adminPanel.add(lblUserName);
        adminPanel.add(txtUserName);
        adminPanel.add(lblUserUsername);
        adminPanel.add(txtUserUsername);
        adminPanel.add(lblUserPassword);
        adminPanel.add(txtUserPassword);
        adminPanel.add(lblType);
        adminPanel.add(cbType);

        //buttons
        secretaryPanel.add(btnAddPatient);
        secretaryPanel.add(btnUpdatePatient);
        secretaryPanel.add(btnAddConsultation);
        secretaryPanel.add(btnUpdateConsultation);
        doctorPanel.add(btnDoctorUpdateConsultation);
        adminPanel.add(btnAddUser);
        adminPanel.add(btnUpdateUser);

        //set the position of the components
        //login components
        lblUsername.setBounds(insets.left + HS, insets.top + VS, lblUsername.getPreferredSize().width, lblUsername.getPreferredSize().height);
        txtUsername.setBounds(lblUsername.getX() + lblUsername.getWidth(), lblUsername.getY(), txtUsername.getPreferredSize().width, txtUsername.getPreferredSize().height);
        lblPassword.setBounds(txtUsername.getX() + txtUsername.getWidth() + HS, lblUsername.getY(), lblPassword.getPreferredSize().width, lblPassword.getPreferredSize().height);
        txtPassword.setBounds(lblPassword.getX() + lblPassword.getWidth(), lblUsername.getY(), txtPassword.getPreferredSize().width, txtPassword.getPreferredSize().height);
        btnConnect.setBounds(txtPassword.getX() + txtPassword.getWidth() + HS, lblPassword.getY(), btnConnect.getPreferredSize().width, btnConnect.getPreferredSize().height);

        cards.setVisible(false);
        cards.setBounds(insets.left + HS, lblUsername.getY() + lblUsername.getHeight() + VS, cards.getPreferredSize().width, cards.getPreferredSize().height + 70);
        secretaryPanel.setLayout(null);
        secretaryPanel.setBounds(cards.getX(), cards.getY(), secretaryPanel.getPreferredSize().width, secretaryPanel.getPreferredSize().height);

        doctorPanel.setLayout(null);
        doctorPanel.setBounds(cards.getX(), cards.getY(), doctorPanel.getPreferredSize().width, doctorPanel.getPreferredSize().height);

        adminPanel.setLayout(null);
        adminPanel.setBounds(cards.getX(), cards.getY(), adminPanel.getPreferredSize().width, adminPanel.getPreferredSize().height);

        lblOldPNC.setBounds(secretaryPanel.getInsets().left + HS, secretaryPanel.getInsets().top, lblOldPNC.getPreferredSize().width, lblOldPNC.getPreferredSize().height);
        txtOldPNC.setBounds(lblOldPNC.getX() + lblOldPNC.getWidth() + HS, lblOldPNC.getY(), txtOldPNC.getPreferredSize().width, txtConsultationOldID.getPreferredSize().height);

        lblConsultationOldID.setBounds(secretaryPanel.getInsets().left + HS, secretaryPanel.getInsets().top, lblConsultationOldID.getPreferredSize().width, lblConsultationOldID.getPreferredSize().height);
        txtConsultationOldID.setBounds(lblConsultationOldID.getX() + lblConsultationOldID.getWidth() + HS, lblConsultationOldID.getY(), txtConsultationOldID.getPreferredSize().width, txtConsultationOldID.getPreferredSize().height);

        lblOldUserID.setBounds(adminPanel.getInsets().left + HS, adminPanel.getInsets().top, lblOldUserID.getPreferredSize().width, lblOldUserID.getPreferredSize().height);
        txtOldUserID.setBounds(lblOldUserID.getX() + lblOldUserID.getWidth() + HS, lblOldUserID.getY(), txtOldUserID.getPreferredSize().width, txtOldUserID.getPreferredSize().height);

        //Sets the frame visible, and its position to the centre of the screen
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        setAllOptionsAndTableInvisible();

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!connected) { //connect button=connect
                    User user = userService.login(txtUsername.getText(), new String(txtPassword.getPassword()));
                    if (user.getID() != -1) {
                        if (user.getType().equals("secretary")) {
                            connected = true;
                            setSecretaryOptions();
                            isDoctor = false;
                        } else if (user.getType().equals("doctor")) {
                            connected = true;
                            setDoctorOptions();
                            isDoctor = true;
                            notification = new Notification(user.getID());
                            patientService.register(notification);
                        } else if (user.getType().equals("admin")) {
                            connected = true;
                            setAdminOptions();
                            isDoctor = false;
                        }
                    } else {
                        showMessage("Invalid login details");
                        connected = false;
                    }
                } else { //connect button=disconnect
                    setLoginEnabled(true);
                    setAllOptionsAndTableInvisible();
                    connected = false;
                    if (isDoctor) patientService.unregister(notification);
                }
                //after login attempt check status
                if (connected) {
                    setLoginEnabled(false);
                    setAllOptionsAndTableInvisible();
                } else setLoginEnabled(true);
            }
        });

        menuItemAddPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setAddPatientOptions(true);
            }
        });

        menuItemUpdatePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setUpdatePatientOptions(true);
            }
        });

        menuItemViewPatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = patientService.getPatients();
                Patient[] patientArray = new Patient[objects.length];
                for (int i = 0; i < objects.length; i++)
                    patientArray[i] = (Patient) objects[i];
                if (patientArray.length != 0) {
                    String[] columnNames = {"PNC", "ICN", "name", "address", "dateOfBirth", "checkedIn"};
                    Object[][] data = new Object[patientArray.length][6];
                    for (int i = 0; i < patientArray.length; i++) {
                        Patient patient = patientArray[i];
                        data[i][0] = patient.getPNC();
                        data[i][1] = patient.getICN();
                        data[i][2] = patient.getName();
                        data[i][3] = patient.getAddress();
                        GregorianCalendar gdate = patient.getDateOfBirth();
                        String year = new String(Integer.toString(gdate.get(GregorianCalendar.YEAR)));
                        String month = new String(Integer.toString(gdate.get(GregorianCalendar.MONTH) + 1));
                        String day = new String(Integer.toString(gdate.get(GregorianCalendar.DAY_OF_MONTH)));
                        data[i][4] = day + "-" + month + "-" + year;
                        data[i][5] = patient.getCheckedIn();
                    }
                    addTable(columnNames, data);
                } else showMessage("No patients found");
            }
        });

        menuItemViewPatientByPNC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long PNC = Long.parseLong(showInputMessage("Input the PNC of the Patient that you wish to view:"));
                    Patient patient = patientService.getPatient(PNC);
                    if (patient.getPNC() != -1) {
                        String[] columnNames = {"PNC", "ICN", "name", "address", "dateOfBirth", "checkedIn"};
                        Object[][] data = new Object[1][6];
                        data[0][0] = patient.getPNC();
                        data[0][1] = patient.getICN();
                        data[0][2] = patient.getName();
                        data[0][3] = patient.getAddress();
                        GregorianCalendar gdate = patient.getDateOfBirth();
                        String year = new String(Integer.toString(gdate.get(GregorianCalendar.YEAR)));
                        String month = new String(Integer.toString(gdate.get(GregorianCalendar.MONTH) + 1));
                        String day = new String(Integer.toString(gdate.get(GregorianCalendar.DAY_OF_MONTH)));
                        data[0][4] = day + "-" + month + "-" + year;
                        data[0][5] = patient.getCheckedIn();
                        addTable(columnNames, data);
                    } else showMessage("No Patient found or invalid PNC, (PNC must have 13 digits)");
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid PNC");
                    //nfe.printStackTrace();
                }

            }
        });

        menuItemCheckInPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setPatientCheckInOptions(true);
            }
        });

        menuItemCheckOutPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long PNC = Long.parseLong(showInputMessage("Patient PNC"));
                showMessage(patientService.checkOutPatient(PNC));
            }
        });

        btnAddPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtPatientName.getText();
                    String address = txtAddress.getText();
                    String dateOfBirth = txtDateOfBirth.getText();
                    if (name.equals("") || address.equals("") || dateOfBirth.equals(""))
                        showMessage("Name, address and date of birth fields should not be empty");
                    String status = patientService.addPatient(Long.parseLong(txtPNC.getText()), Integer.parseInt(txtICN.getText()), name, address, dateOfBirth);
                    showMessage(status);
                } catch (NumberFormatException exception) {
                    // exception.printStackTrace();
                    showMessage("Check ICN and PNC and try again");
                }
            }
        });


        btnUpdatePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtPatientName.getText();
                    String address = txtAddress.getText();
                    String dateOfBirth = txtDateOfBirth.getText();
                    if (name.equals("") || address.equals("") || dateOfBirth.equals(""))
                        showMessage("Name, address and date of birth fields should not be empty");
                    String status = patientService.updatePatient(Long.parseLong(txtOldPNC.getText()), Long.parseLong(txtPNC.getText()), Integer.parseInt(txtICN.getText()), name, address, dateOfBirth, 0);
                    showMessage(status);
                } catch (NumberFormatException exception) {
                    // exception.printStackTrace();
                    showMessage("Check oldPNC, PNC and ICN and try again");
                }
            }
        });

        btnCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showMessage(patientService.checkInPatient(Long.parseLong(txtPNC.getText()), Integer.parseInt(txtDoctorID.getText())));
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    showMessage("Check PNC and doctor ID and try again");
                }
            }
        });

        menuItemAddConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setAddConsultationOptions(true);
            }
        });

        menuItemUpdateConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setUpdateConsultationOptions(true);
            }
        });

        btnAddConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String status = consultationService.addConsultation(
                            Integer.parseInt(txtConsultationID.getText()), txtConsultationDate.getText(),
                            Long.parseLong(txtConsultationPatientPNC.getText()), Integer.parseInt(txtConsultationDoctorID.getText()), txtConsultationText.getText());
                    showMessage(status);
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid ID");
                }
            }
        });


        btnUpdateConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String status = consultationService.update(Integer.parseInt(txtConsultationOldID.getText()),
                            Integer.parseInt(txtConsultationID.getText()), txtConsultationDate.getText(),
                            Long.parseLong(txtConsultationPatientPNC.getText()), Integer.parseInt(txtConsultationDoctorID.getText()), txtConsultationText.getText());
                    showMessage(status);
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid oldID or ID");
                }
            }
        });

        menuItemViewConsultations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = consultationService.getConsultations();
                if (objects.length > 0) {
                    Consultation[] consultations = new Consultation[objects.length];
                    for (int i = 0; i < objects.length; i++)
                        consultations[i] = (Consultation) objects[i];
                    String[] columnNames = {"ID", "date", "PatientPNC", "DoctorID", "Observations"};
                    Object[][] data = new Object[consultations.length][5];
                    for (int i = 0; i < consultations.length; i++) {
                        data[i][0] = consultations[i].getID();
                        GregorianCalendar gdate = consultations[i].getDate();
                        String year = new String(Integer.toString(gdate.get(GregorianCalendar.YEAR)));
                        String month = new String(Integer.toString(gdate.get(GregorianCalendar.MONTH) + 1));
                        String day = new String(Integer.toString(gdate.get(GregorianCalendar.DAY_OF_MONTH)));
                        data[i][1] = day + "-" + month + "-" + year;
                        data[i][2] = consultations[i].getPatientPNC();
                        data[i][3] = consultations[i].getDoctorID();
                        data[i][4] = consultations[i].getObservations();
                    }
                    addTable(columnNames, data);
                } else showMessage("No Consultations found");
            }
        });

        menuItemViewConsultationByID.addActionListener(new ViewConsultationByIDActionListener());
        menuItemViewConsultationsByPatientPNC.addActionListener(new ViewConsultationsByPatientPNCActionListener());

        menuItemDeleteConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(JOptionPane.showInputDialog(frame1, "Input the ID of the Consultation that you wish to delete:"));
                    String status = consultationService.deleteConsultation(ID);
                    showMessage(status);
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid ID");
                }
            }
        });

        menuItemDoctorViewConsultationByID.addActionListener(new ViewConsultationByIDActionListener());
        menuItemDoctorViewConsultationByPatientPNC.addActionListener(new ViewConsultationsByPatientPNCActionListener());

        menuItemDoctorUpdateConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDoctorUpdateConsultationOptions(true);
            }
        });

        btnDoctorUpdateConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessage(consultationService.updateDoctorConsultation(Integer.parseInt(txtDoctorConsultationID.getText()), txtDoctorConsultationText.getText()));
            }
        });


        menuItemAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setAddUserOptions(true);
            }
        });

        menuItemUpdateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllOptionsInvisible();
                setUpdateUserOptions(true);
            }
        });

      btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showMessage(userService.addUser(Integer.parseInt(txtUserID.getText()), txtUserName.getText(), txtUserUsername.getText(),
                             new String(txtUserPassword.getPassword()), (String) cbType.getSelectedItem()));
                }
                 catch (NumberFormatException nfe) {
                    showMessage("Invalid ID, DateOfBirth or Owner's PNC");
                }
            }
        });

        btnUpdateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=txtUserName.getText();
                String username=txtUserUsername.getText();
                String password=new String(txtUserPassword.getPassword());
                if (name.equals("") || username.equals("") || password.equals(""))
                    showMessage("Name, username and password fields should not be empty");
                else
                try {
                    showMessage(userService.updateUser(Integer.parseInt(txtOldUserID.getText()), Integer.parseInt(txtUserID.getText()), name,
                           username ,password , (String) cbType.getSelectedItem()));
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid oldID, ID, DateOfBirth or Owner's PNC");
                }
            }
        });

       menuItemViewUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = userService.getUsers();
                if (objects.length > 0) {
                    User[] users = new User[objects.length];
                    for (int i = 0; i < objects.length; i++)
                        users[i] = (User) objects[i];
                    String[] columnNames = {"ID", "Name", "Username", "Password", "Type"};
                    Object[][] data = new Object[users.length][5];
                    for (int i = 0; i < users.length; i++) {
                        data[i][0] = users[i].getID();
                        data[i][1] = users[i].getName();
                        data[i][2] = users[i].getUsername();
                        data[i][3] = users[i].getPassword();
                        data[i][4] = users[i].getType();
                    }
                    addTable(columnNames, data);
                } else showMessage("No Users found");
            }
        });

        menuItemViewUserByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(JOptionPane.showInputDialog(frame1, "Input the ID of the User that you wish to view:"));
                    User user = userService.getUser(ID);
                    if (user.getID()!=-1) {
                        String[] columnNames = {"ID", "Name", "Username", "Password", "Type"};
                        Object[][] data = new Object[1][5];
                        data[0][0] = user.getID();
                        data[0][1] = user.getName();
                        data[0][2] = user.getUsername();
                        data[0][3] = user.getPassword();
                        data[0][4] = user.getType();
                        addTable(columnNames, data);
                    } else showMessage("No User found or invalid ID");
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid ID");
                    //nfe.printStackTrace();
                }
            }
        });

        menuItemDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(JOptionPane.showInputDialog(frame1, "Input the ID of the User that you wish to delete:"));
                    showMessage(userService.deleteUser(ID));
                } catch (NumberFormatException nfe) {
                    showMessage("Invalid ID");
                }
            }
        });

    }

    public class ViewConsultationByIDActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(frame1, "Input the ID of the Consultation that you wish to view:"));
                Consultation consultation = consultationService.getConsultation(ID);
                if (consultation.getID() != -1) {
                    String[] columnNames = {"ID", "date", "PatientPNC", "DoctorID", "Observations"};
                    Object[][] data = new Object[1][5];
                    data[0][0] = consultation.getID();
                    GregorianCalendar gdate = consultation.getDate();
                    String year = new String(Integer.toString(gdate.get(GregorianCalendar.YEAR)));
                    String month = new String(Integer.toString(gdate.get(GregorianCalendar.MONTH) + 1));
                    String day = new String(Integer.toString(gdate.get(GregorianCalendar.DAY_OF_MONTH)));
                    data[0][1] = day + "-" + month + "-" + year;
                    data[0][2] = consultation.getPatientPNC();
                    data[0][3] = consultation.getDoctorID();
                    data[0][4] = consultation.getObservations();
                    addTable(columnNames, data);
                }
                else showMessage("No Consultation found");
            } catch (NumberFormatException nfe) {
                showMessage("Invalid ID");
                //nfe.printStackTrace();
            }
        }
    }

    public class ViewConsultationsByPatientPNCActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                long PNC = Long.parseLong(JOptionPane.showInputDialog(frame1, "Input the Patient PNC of the Consultation that you wish to view:"));
                Object[] objects = consultationService.getConsultationsByPatientPNC(PNC);
                if (objects.length > 0) {
                    Consultation[] consultations = new Consultation[objects.length];
                    for (int i = 0; i < objects.length; i++)
                        consultations[i] = (Consultation) objects[i];
                    String[] columnNames = {"ID", "date", "PatientPNC", "DoctorID", "Observations"};
                    Object[][] data = new Object[consultations.length][5];
                    for (int i = 0; i < consultations.length; i++) {
                        data[i][0] = consultations[i].getID();
                        GregorianCalendar gdate = consultations[i].getDate();
                        String year = new String(Integer.toString(gdate.get(GregorianCalendar.YEAR)));
                        String month = new String(Integer.toString(gdate.get(GregorianCalendar.MONTH) + 1));
                        String day = new String(Integer.toString(gdate.get(GregorianCalendar.DAY_OF_MONTH)));
                        data[i][1] = day + "-" + month + "-" + year;
                        data[i][2] = consultations[i].getPatientPNC();
                        data[i][3] = consultations[i].getDoctorID();
                        data[i][4] = consultations[i].getObservations();
                    }
                    addTable(columnNames, data);
                } else showMessage("No Consultation found");
            } catch (NumberFormatException nfe) {
                showMessage("Invalid PNC");
                //nfe.printStackTrace();
            }
        }
    }

    private void setSecretaryOptions() {
        setPanel(SECRETARY_PANEL);
        frame1.setJMenuBar(secretaryMenuBar);
    }

    private void setDoctorOptions() {
        setPanel(DOCTOR_PANEL);
        frame1.setJMenuBar(doctorMenuBar);
    }

    private void setAdminOptions()
    {
        setPanel(ADMIN_PANEL);
        frame1.setJMenuBar(adminMenuBar);
    }

    private void setPanel(String panel) {
        CardLayout cardLayout = (CardLayout) (cards.getLayout());
        cardLayout.show(cards, panel);
    }

    private void addTable(String[] columnsNames, Object[][] data) {
        if (scrollPane != null) pane.remove(scrollPane);
        table = new JTable(data, columnsNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(pane.getInsets().left+HS, cards.getY()+cards.getHeight()+VS,scrollPane.getPreferredSize().width + 300, scrollPane.getPreferredSize().width);
        pane.add(scrollPane);
        frame1.setSize(900, scrollPane.getHeight() + 300);
        frame1.setLocationRelativeTo(null);
    }

    /**
     * sets the view so only login options are available or all others according to b
     *
     * @param b true to set enabled, false to set disabled
     */
    private void setLoginEnabled(boolean b) {
        lblUsername.setEnabled(b);
        txtUsername.setEnabled(b);
        lblPassword.setEnabled(b);
        txtPassword.setEnabled(b);
        if (b) {
            txtUsername.setText("");
            txtPassword.setText("");
            frame1.setJMenuBar(null);
            frame1.setSize(450, 100);
            frame1.setLocationRelativeTo(null);
            btnConnect.setText("Connect");
            cards.setVisible(false);

        } else {
            frame1.setSize(900, 300);
            frame1.setLocationRelativeTo(null);
            btnConnect.setText("Disconnect");
            btnConnect.setBounds(txtPassword.getX() + txtPassword.getWidth() + HS, lblPassword.getY(), btnConnect.getPreferredSize().width, btnConnect.getPreferredSize().height);
            cards.setVisible(true);
        }

    }

    private void setAllOptionsInvisible() {
        lblUserID.setVisible(false);
        lblPatientName.setVisible(false);
        lblUserName.setVisible(false);
        lblAddress.setVisible(false);
        lblDateOfBirth.setVisible(false);
        lblICN.setVisible(false);
        lblPNC.setVisible(false);
        lblDoctorID.setVisible(false);
        lblType.setVisible(false);
        lblOldPNC.setVisible(false);
        lblOldUserID.setVisible(false);
        lblUserUsername.setVisible(false);
        lblUserPassword.setVisible(false);
        lblConsultationID.setVisible(false);
        lblConsultationPatientPNC.setVisible(false);
        lblConsultationDoctorID.setVisible(false);
        lblConsultationOldID.setVisible(false);
        lblConsultationDate.setVisible(false);
        lblConsultationText.setVisible(false);
        lblDoctorConsultationID.setVisible(false);
        lblDoctorConsultationText.setVisible(false);
        txtUserID.setVisible(false);
        txtPatientName.setVisible(false);
        txtUserName.setVisible(false);
        txtAddress.setVisible(false);
        txtICN.setVisible(false);
        txtDoctorID.setVisible(false);
        txtPNC.setVisible(false);
        txtDateOfBirth.setVisible(false);
        txtOldPNC.setVisible(false);
        txtOldUserID.setVisible(false);
        txtUserUsername.setVisible(false);
        txtConsultationID.setVisible(false);
        txtConsultationPatientPNC.setVisible(false);
        txtConsultationDoctorID.setVisible(false);
        txtConsultationOldID.setVisible(false);
        txtConsultationDate.setVisible(false);
        consultationScrollPane.setVisible(false);
        txtConsultationText.setVisible(false);
        txtDoctorConsultationID.setVisible(false);
        txtDoctorConsultationText.setVisible(false);
        doctorConsultationScrollPane.setVisible(false);
        cbType.setVisible(false);
        txtUserPassword.setVisible(false);
        btnAddPatient.setVisible(false);
        btnAddUser.setVisible(false);
        btnAddConsultation.setVisible(false);
        btnUpdatePatient.setVisible(false);
        btnUpdateUser.setVisible(false);
        btnUpdateConsultation.setVisible(false);
        btnDoctorUpdateConsultation.setVisible(false);
        btnCheckIn.setVisible(false);
    }

    private void setAllOptionsAndTableInvisible() {
        setAllOptionsInvisible();
        if (scrollPane != null) scrollPane.setVisible(false);
    }

    private void setPatientComponentsPositions() {
        txtPNC.setBounds(lblPNC.getX() + lblPNC.getWidth() + HS, lblOldPNC.getY(), txtPNC.getPreferredSize().width, txtPNC.getPreferredSize().height);
        lblICN.setBounds(txtPNC.getX() + txtPNC.getWidth() + HS, lblOldPNC.getY(), lblICN.getPreferredSize().width, lblICN.getPreferredSize().height);
        txtICN.setBounds(lblICN.getX() + lblICN.getWidth() + HS, lblOldPNC.getY(), txtICN.getPreferredSize().width, txtICN.getPreferredSize().height);
        lblDoctorID.setBounds(txtPNC.getX() + txtPNC.getWidth() + HS, lblOldPNC.getY(), lblDoctorID.getPreferredSize().width, lblDoctorID.getPreferredSize().height);
        txtDoctorID.setBounds(lblDoctorID.getX() + lblDoctorID.getWidth() + HS, lblOldPNC.getY(), txtDoctorID.getPreferredSize().width, txtDoctorID.getPreferredSize().height);
        lblPatientName.setBounds(secretaryPanel.getX() + HS, lblOldPNC.getY() + lblOldPNC.getHeight() + VS, lblPatientName.getPreferredSize().width, lblPatientName.getPreferredSize().height);
        txtPatientName.setBounds(lblPatientName.getX() + lblPatientName.getWidth() + HS, lblPatientName.getY(), txtPatientName.getPreferredSize().width, txtPatientName.getPreferredSize().height);
        lblAddress.setBounds(txtPatientName.getX() + txtPatientName.getWidth() + HS, lblPatientName.getY(), lblAddress.getPreferredSize().width, lblAddress.getPreferredSize().height);
        txtAddress.setBounds(lblAddress.getX() + lblAddress.getWidth() + HS, lblPatientName.getY(), txtAddress.getPreferredSize().width, txtAddress.getPreferredSize().height);
        lblDateOfBirth.setBounds(txtAddress.getX() + txtAddress.getWidth() + HS, lblPatientName.getY(), lblDateOfBirth.getPreferredSize().width, lblDateOfBirth.getPreferredSize().height);
        txtDateOfBirth.setBounds(lblDateOfBirth.getX() + lblDateOfBirth.getWidth() + HS, lblPatientName.getY(), txtDateOfBirth.getPreferredSize().width, txtDateOfBirth.getPreferredSize().height);

        btnCheckIn.setBounds(txtDoctorID.getX() + txtDoctorID.getWidth() + HS, txtDoctorID.getY(), btnCheckIn.getPreferredSize().width, btnCheckIn.getPreferredSize().height);

        btnAddPatient.setBounds(txtDateOfBirth.getX() + txtDateOfBirth.getWidth() + HS, lblPatientName.getY(), btnAddPatient.getPreferredSize().width, btnAddPatient.getPreferredSize().height);
        btnUpdatePatient.setBounds(txtDateOfBirth.getX() + txtDateOfBirth.getWidth() + HS, lblPatientName.getY(), btnUpdatePatient.getPreferredSize().width, btnUpdatePatient.getPreferredSize().height);
    }

    private void setConsultationComponentsPositions() {
        txtConsultationID.setBounds(lblConsultationID.getX() + lblConsultationID.getWidth() + HS, lblConsultationID.getY(), txtConsultationID.getPreferredSize().width, txtConsultationID.getPreferredSize().height);
        lblConsultationPatientPNC.setBounds(txtConsultationID.getX() + txtConsultationID.getWidth() + HS, lblConsultationOldID.getY(), lblConsultationPatientPNC.getPreferredSize().width, lblConsultationPatientPNC.getPreferredSize().height);
        txtConsultationPatientPNC.setBounds(lblConsultationPatientPNC.getX() + lblConsultationPatientPNC.getWidth() + HS, lblConsultationOldID.getY(), txtConsultationPatientPNC.getPreferredSize().width, txtConsultationPatientPNC.getPreferredSize().height);
        lblConsultationDoctorID.setBounds(txtConsultationPatientPNC.getX() + txtConsultationPatientPNC.getWidth() + HS, lblConsultationOldID.getY(), lblConsultationDoctorID.getPreferredSize().width, lblConsultationDoctorID.getPreferredSize().height);
        txtConsultationDoctorID.setBounds(lblConsultationDoctorID.getX() + lblConsultationDoctorID.getWidth() + HS, lblConsultationOldID.getY(), txtConsultationDoctorID.getPreferredSize().width, txtConsultationDoctorID.getPreferredSize().height);
        lblConsultationDate.setBounds(txtConsultationDoctorID.getX() + txtConsultationDoctorID.getWidth() + HS, lblConsultationOldID.getY(), lblConsultationDate.getPreferredSize().width, lblConsultationDate.getPreferredSize().height);
        txtConsultationDate.setBounds(lblConsultationDate.getX() + lblConsultationDate.getWidth() + HS, lblConsultationDate.getY(), txtConsultationDate.getPreferredSize().width, txtConsultationDate.getPreferredSize().height);
        lblConsultationText.setBounds(secretaryPanel.getInsets().left + HS, lblConsultationDate.getY() + lblConsultationDate.getHeight() + VS, lblConsultationText.getPreferredSize().width, lblConsultationText.getPreferredSize().height);
        consultationScrollPane.setBounds(lblConsultationText.getX() + lblConsultationText.getWidth() + HS, lblConsultationText.getY(), consultationScrollPane.getPreferredSize().width, consultationScrollPane.getPreferredSize().height);

        btnAddConsultation.setBounds(consultationScrollPane.getX() + consultationScrollPane.getWidth() + HS, lblConsultationText.getY(), btnAddConsultation.getPreferredSize().width, btnAddConsultation.getPreferredSize().height);
        btnUpdateConsultation.setBounds(consultationScrollPane.getX() + consultationScrollPane.getWidth() + HS, lblConsultationText.getY(), btnUpdateConsultation.getPreferredSize().width, btnUpdateConsultation.getPreferredSize().height);
    }


    private void setDoctorUpdateConsultationPositions() {
        lblDoctorConsultationID.setBounds(doctorPanel.getInsets().left+HS,doctorPanel.getInsets().top+VS,lblDoctorConsultationID.getPreferredSize().width, lblDoctorConsultationID.getPreferredSize().height);
        txtDoctorConsultationID.setBounds(lblDoctorConsultationID.getX()+lblDoctorConsultationID.getWidth()+HS, lblDoctorConsultationID.getY(), txtDoctorConsultationID.getPreferredSize().width,  txtDoctorConsultationID.getPreferredSize().height);
        lblDoctorConsultationText.setBounds(doctorPanel.getInsets().left + HS, lblDoctorConsultationID.getY() + lblDoctorConsultationID.getHeight() + VS, lblDoctorConsultationText.getPreferredSize().width, lblDoctorConsultationText.getPreferredSize().height);
        doctorConsultationScrollPane.setBounds(lblDoctorConsultationText.getX() + lblDoctorConsultationText.getWidth() + HS, lblDoctorConsultationText.getY(),doctorConsultationScrollPane.getPreferredSize().width, doctorConsultationScrollPane.getPreferredSize().height);

        btnDoctorUpdateConsultation.setBounds(doctorConsultationScrollPane.getX()+doctorConsultationScrollPane.getWidth()+HS,lblDoctorConsultationText.getY(),  btnDoctorUpdateConsultation.getPreferredSize().width, btnDoctorUpdateConsultation.getPreferredSize().height);

    }

    private void setUserComponentsPositions() {
        txtUserID.setBounds(lblUserID.getX()+lblUserID.getWidth()+HS,lblOldUserID.getY(),txtUserID.getPreferredSize().width,txtUserID.getPreferredSize().height);
        lblUserName.setBounds(txtUserID.getX() +txtUserID.getWidth() + HS, lblOldUserID.getY(), lblUserName.getPreferredSize().width, lblUserName.getPreferredSize().height);
        txtUserName.setBounds(lblUserName.getX() +lblUserName.getWidth() + HS, lblOldUserID.getY(), txtUserName.getPreferredSize().width, txtUserName.getPreferredSize().height);

        lblUserUsername.setBounds(lblOldUserID.getX(), lblOldUserID.getY()+lblOldUserID.getHeight()+VS, lblUserUsername.getPreferredSize().width, lblUserUsername.getPreferredSize().height);
        txtUserUsername.setBounds(lblUserUsername.getX() +lblUserUsername.getWidth() + HS, lblUserUsername.getY(), txtUserUsername.getPreferredSize().width, txtUserUsername.getPreferredSize().height);
        lblUserPassword.setBounds(txtUserUsername.getX() +txtUserUsername.getWidth() + HS, lblUserUsername.getY(), lblUserPassword.getPreferredSize().width, lblUserPassword.getPreferredSize().height);
        txtUserPassword.setBounds(lblUserPassword.getX() +lblUserPassword.getWidth() + HS, lblUserUsername.getY(), txtUserPassword.getPreferredSize().width, txtUserPassword.getPreferredSize().height);
        lblType.setBounds(txtUserPassword.getX() + txtUserPassword.getWidth() + HS, lblUserUsername.getY(), lblType.getPreferredSize().width,  lblType.getPreferredSize().height);
        cbType.setBounds(lblType.getX()+lblType.getWidth()+HS,lblUserUsername.getY(),cbType.getPreferredSize().width,cbType.getPreferredSize().height);

        btnAddUser.setBounds(cbType.getX() +  cbType.getWidth() + HS, lblUserUsername.getY(), btnAddUser.getPreferredSize().width, btnAddUser.getPreferredSize().height);
        btnUpdateUser.setBounds(cbType.getX() +  cbType.getWidth() + HS, lblUserUsername.getY(), btnUpdateUser.getPreferredSize().width, btnUpdateUser.getPreferredSize().height);
    }

    private void setAddPatientOptions(boolean visible) {
        lblPNC.setBounds(lblOldPNC.getX(), lblOldPNC.getY(), lblPNC.getPreferredSize().width, lblPNC.getPreferredSize().height);
        setPatientComponentsPositions();
        lblPNC.setVisible(visible);
        txtPNC.setVisible(visible);
        lblICN.setVisible(visible);
        txtICN.setVisible(visible);
        lblPatientName.setVisible(visible);
        txtPatientName.setVisible(visible);
        lblAddress.setVisible(visible);
        txtAddress.setVisible(visible);
        lblDateOfBirth.setVisible(visible);
        txtDateOfBirth.setVisible(visible);
        btnAddPatient.setVisible(visible);
    }

    private void setUpdatePatientOptions(boolean visible)
    {
        lblPNC.setBounds(txtOldPNC.getX() + txtOldPNC.getWidth()+HS, lblOldPNC.getY(), lblPNC.getPreferredSize().width, lblPNC.getPreferredSize().height);
        setPatientComponentsPositions();
        lblOldPNC.setVisible(visible);
        txtOldPNC.setVisible(visible);
        lblPNC.setVisible(visible);
        txtPNC.setVisible(visible);
        lblICN.setVisible(visible);
        txtICN.setVisible(visible);
        lblPatientName.setVisible(visible);
        txtPatientName.setVisible(visible);
        lblAddress.setVisible(visible);
        txtAddress.setVisible(visible);
        lblDateOfBirth.setVisible(visible);
        txtDateOfBirth.setVisible(visible);
        btnUpdatePatient.setVisible(visible);
    }

    private void setPatientCheckInOptions(boolean visible)
    {
        lblPNC.setBounds(lblOldPNC.getX(), lblOldPNC.getY(), lblPNC.getPreferredSize().width, lblPNC.getPreferredSize().height);
        setPatientComponentsPositions();
        lblPNC.setVisible(visible);
        txtPNC.setVisible(visible);
        lblDoctorID.setVisible(visible);
        txtDoctorID.setVisible(visible);
        btnCheckIn.setVisible(visible);
    }

    private void setAddConsultationOptions(boolean visible) {
        lblConsultationID.setBounds(lblConsultationOldID.getX(), lblConsultationOldID.getY(), lblConsultationID.getPreferredSize().width, lblConsultationID.getPreferredSize().height);
        setConsultationComponentsPositions();
        lblConsultationID.setVisible(visible);
        txtConsultationID.setVisible(visible);
        lblConsultationPatientPNC.setVisible(visible);
        txtConsultationPatientPNC.setVisible(visible);
        lblConsultationDoctorID.setVisible(visible);
        txtConsultationDoctorID.setVisible(visible);
        lblConsultationDate.setVisible(visible);
        txtConsultationDate.setVisible(visible);
        lblConsultationText.setVisible(visible);
        txtConsultationText.setVisible(visible);
        consultationScrollPane.setVisible(visible);
        btnAddConsultation.setVisible(visible);
    }


    private void setUpdateConsultationOptions(boolean visible)
    {
        lblConsultationID.setBounds(txtConsultationOldID.getX()+txtConsultationOldID.getWidth()+HS, lblConsultationOldID.getY(), lblConsultationID.getPreferredSize().width, lblConsultationID.getPreferredSize().height);
        setConsultationComponentsPositions();
        lblConsultationOldID.setVisible(visible);
        txtConsultationOldID.setVisible(visible);
        lblConsultationID.setVisible(visible);
        txtConsultationID.setVisible(visible);
        lblConsultationPatientPNC.setVisible(visible);
        txtConsultationPatientPNC.setVisible(visible);
        lblConsultationDoctorID.setVisible(visible);
        txtConsultationDoctorID.setVisible(visible);
        lblConsultationDate.setVisible(visible);
        txtConsultationDate.setVisible(visible);
        lblConsultationText.setVisible(visible);
        txtConsultationText.setVisible(visible);
        consultationScrollPane.setVisible(visible);
        btnUpdateConsultation.setVisible(visible);

    }

    private void setDoctorUpdateConsultationOptions(boolean visible) {

        setDoctorUpdateConsultationPositions();
        lblDoctorConsultationID.setVisible(visible);
        txtDoctorConsultationID.setVisible(visible);
        lblDoctorConsultationText.setVisible(visible);
        txtDoctorConsultationText.setVisible(visible);
        doctorConsultationScrollPane.setVisible(visible);
        btnDoctorUpdateConsultation.setVisible(visible);

    }

   private void setAddUserOptions(boolean visible)
    {
        lblUserID.setBounds(lblOldUserID.getX(), lblOldUserID.getY(), lblUserID.getPreferredSize().width, lblUserID.getPreferredSize().height);
        setUserComponentsPositions();
        lblUserID.setVisible(visible);
        txtUserID.setVisible(visible);
        lblUserName.setVisible(visible);
        txtUserName.setVisible(visible);
        lblUserUsername.setVisible(visible);
        txtUserUsername.setVisible(visible);
        lblUserPassword.setVisible(visible);
        txtUserPassword.setVisible(visible);
        lblType.setVisible(visible);
        cbType.setVisible(visible);
        btnAddUser.setVisible(visible);
    }

    private void setUpdateUserOptions(boolean visible)
    {
        lblOldUserID.setVisible(visible);
        txtOldUserID.setVisible(visible);
        lblUserID.setBounds(txtOldUserID.getX() + txtOldUserID.getWidth() + HS, lblOldUserID.getY(), lblUserID.getPreferredSize().width,lblUserID.getPreferredSize().height);
        setUserComponentsPositions();
        lblUserID.setVisible(visible);
        txtUserID.setVisible(visible);
        lblUserName.setVisible(visible);
        txtUserName.setVisible(visible);
        lblUserUsername.setVisible(visible);
        txtUserUsername.setVisible(visible);
        lblUserPassword.setVisible(visible);
        txtUserPassword.setVisible(visible);
        lblType.setVisible(visible);
        cbType.setVisible(visible);
        btnUpdateUser.setVisible(visible);
    }

    /**
     * Shows an informative message
     * @param s the string containing the message to be showed
     */
    private void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    private String showInputMessage(String s) {
        return JOptionPane.showInputDialog(frame1, s);
    }

}