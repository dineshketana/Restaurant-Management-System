import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class RestaurantManagement extends JFrame implements ActionListener {
    private final String predefinedUsername = "ADMIN";
    private final String predefinedPassword = "1234";
    private JTextField uf;
    private JPasswordField pf;
    private JButton AD;
    private JButton CD;
    private JButton lb;
    private JLabel c;
    private JLabel title;

    public RestaurantManagement() {
        setTitle("Restaurant Management");
        setSize(1366, 700);
        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\dines\\Downloads\\Pic1.jpg");
        title = new JLabel("    Restaurant Management System", imageIcon, SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 35));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.CENTER);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 0, 10));
        centerPanel.setBackground(Color.lightGray);
        c = new JLabel("WHO ARE YOU?", SwingConstants.CENTER);
        c.setFont(new Font("Times New Roman", Font.BOLD, 25));
        c.setBackground(Color.GREEN);
        centerPanel.add(c);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.lightGray);
        AD = new JButton("ADMIN");
        AD.addActionListener(this);
        AD.setBackground(Color.RED);
        AD.setForeground(Color.BLACK);
        AD.setFont(new Font("Times New Roman", Font.BOLD, 18));
        buttonPanel.add(AD);
        CD = new JButton("CUSTOMER");
        CD.addActionListener(this);
        CD.setBackground(Color.orange);
        CD.setForeground(Color.black);
        CD.setFont(new Font("Times New Roman", Font.BOLD, 18));
        buttonPanel.add(CD);
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AD) {
            JFrame adminFrame = new JFrame("ADMIN DETAILS");
            adminFrame.setSize(1366, 768);
            setLayout(new FlowLayout());
            adminFrame.setLayout(null);
            JLabel p = new JLabel("ADMIN DETAILS");
            p.setBounds(590, 50, 300, 60);
            p.setFont(new Font("Georgia", Font.BOLD, 30));
            p.setForeground(Color.yellow);
            adminFrame.add(p);
            JLabel ul = new JLabel("   USERNAME", JLabel.CENTER);
            ul.setFont(new Font("Times New Roman", Font.BOLD, 25));
            ul.setBounds(200, 200, 500, 30);
            JLabel pl = new JLabel("   PASSWORD", JLabel.CENTER);
            pl.setBounds(200, 300, 500, 30);
            pl.setFont(new Font("Times New Roman", Font.BOLD, 25));
            JLabel sl = new JLabel(" ");
            uf = new JTextField(50);
            uf.setBounds(600, 180, 430, 80);
            uf.setFont(new Font("Times New Roman", Font.BOLD, 25));
            pf = new JPasswordField(50);
            pf.setBounds(600, 280, 430, 80);
            pf.setFont(new Font("Times New Roman", Font.BOLD, 25));
            lb = new JButton("LOGIN");
            lb.setBounds(600, 380, 430, 80);
            lb.setBackground(Color.red);
            lb.setForeground(Color.WHITE);
            lb.setFont(new Font("Times New Roman", Font.BOLD, 25));
            adminFrame.getContentPane().setBackground(Color.black);
            ul.setForeground(Color.white);
            pl.setForeground(Color.white);
            uf.setForeground(Color.black);
            pf.setForeground(Color.black);
            uf.setBackground(Color.lightGray);
            pf.setBackground(Color.lightGray);
            adminFrame.add(ul);
            adminFrame.add(uf);
            adminFrame.add(pl);
            adminFrame.add(pf);
            adminFrame.add(new JLabel(" "));
            adminFrame.add(lb);
            adminFrame.add(new JLabel(" "));
            adminFrame.add(sl);
            lb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = uf.getText();
                    String password = new String(pf.getPassword());
                    if (username.equals(predefinedUsername) && password.equals(predefinedPassword)) {
                        sl.setText("LOGIN SUCCESS!!");
                        JFrame adminFrame1 = new JFrame("INSERTION TAB");
                        adminFrame1.setSize(1366, 768);
                        setLayout(new FlowLayout());
                        adminFrame1.setLayout(null);
                        adminFrame1.getContentPane().setBackground(Color.black);
                        JLabel info = new JLabel("YOU ARE LOGIN SUCCESSFULL!");
                        info.setBounds(510, 40, 500, 30);
                        info.setFont(new Font("Georgia", Font.BOLD, 20));
                        info.setForeground(Color.yellow);
                        adminFrame1.add(info);
                        JLabel info1 = new JLabel("---------- NOW YOU CAN UPDATE YOUR RestaurantManagement ----------");
                        info1.setBounds(400, 80, 580, 30);
                        info1.setFont(new Font("Georgia", Font.BOLD, 20));
                        info1.setForeground(Color.GREEN);
                        adminFrame1.add(info1);
                        JLabel info2 = new JLabel(" ");
                        info2.setBounds(855, 650, 430, 50);
                        info2.setForeground(Color.green);
                        info2.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        JLabel il1 = new JLabel("ENTER ITEM ID:");
                        il1.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        il1.setBounds(250, 160, 500, 30);
                        il1.setForeground(Color.orange);
                        JLabel il2 = new JLabel("ENTER ITEM NAME:");
                        il2.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        il2.setBounds(250, 260, 500, 30);
                        il2.setForeground(Color.orange);
                        JLabel il3 = new JLabel("ENTER ITEM SINGLE COST:");
                        il3.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        il3.setBounds(250, 360, 500, 30);
                        il3.setForeground(Color.orange);
                        JLabel il4 = new JLabel("ENTER ITEM DOUBLE COST:");
                        il4.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        il4.setBounds(250, 460, 500, 30);
                        il4.setForeground(Color.orange);
                        JLabel il5 = new JLabel("ENTER ITEM MANDI COST:");
                        il5.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        il5.setBounds(250, 560, 500, 30);
                        il5.setForeground(Color.orange);
                        JTextField if1 = new JTextField();
                        if1.setBounds(550, 150, 550, 50);
                        if1.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        if1.setForeground(Color.black);
                        if1.setBackground(Color.lightGray);
                        JTextField if2 = new JTextField();
                        if2.setBounds(550, 250, 550, 50);
                        if2.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        if2.setForeground(Color.black);
                        if2.setBackground(Color.lightGray);
                        JTextField if3 = new JTextField();
                        if3.setBounds(550, 350, 550, 50);
                        if3.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        if3.setForeground(Color.black);
                        if3.setBackground(Color.lightGray);
                        JTextField if4 = new JTextField();
                        if4.setBounds(550, 450, 550, 50);
                        if4.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        if4.setForeground(Color.black);
                        if4.setBackground(Color.lightGray);
                        JTextField if5 = new JTextField();
                        if5.setBounds(550, 550, 550, 50);
                        if5.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        if5.setForeground(Color.black);
                        if5.setBackground(Color.lightGray);
                        JButton inst = new JButton("INSERT");
                        inst.setBounds(225, 650, 430, 50);
                        inst.setBackground(Color.red);
                        inst.setForeground(Color.WHITE);
                        inst.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        adminFrame1.add(info);
                        adminFrame1.add(info1);
                        adminFrame1.add(il1);
                        adminFrame1.add(if1);
                        adminFrame1.add(il2);
                        adminFrame1.add(if2);
                        adminFrame1.add(il3);
                        adminFrame1.add(if3);
                        adminFrame1.add(il4);
                        adminFrame1.add(if4);
                        adminFrame1.add(il5);
                        adminFrame1.add(if5);
                        adminFrame1.add(inst);
                        adminFrame1.add(info2);
                        adminFrame1.setVisible(true);
                        adminFrame.setVisible(false);
                        inst.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int v1 = Integer.parseInt(if1.getText());
                                String v2 = if2.getText();
                                int v3 = Integer.parseInt(if3.getText());
                                int v4 = Integer.parseInt(if4.getText());
                                int v5 = Integer.parseInt(if5.getText());
                                try {
                                    String sql = "INSERT INTO RestaurantManagement VALUES (?, ?, ?, ?, ?)";
                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                    Connection connection1 = DriverManager
                                            .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "DBMS");
                                    PreparedStatement pstmt = connection1.prepareStatement(sql);
                                    pstmt.setInt(1, v1);
                                    pstmt.setString(2, v2);
                                    pstmt.setInt(3, v3);
                                    pstmt.setInt(4, v4);
                                    pstmt.setInt(5, v5);
                                    int rowsInserted = pstmt.executeUpdate();
                                    if (rowsInserted > 0) {
                                        info2.setText("INSERTED SUCCESSFULLY");
                                    } else {
                                        info2.setText("INSERTION FAILED");
                                    }
                                    pstmt.close();
                                    connection1.close();
                                } catch (ClassNotFoundException | SQLException d) {
                                    d.printStackTrace();
                                    info2.setText("INSERTION FAILED: " + d.getMessage());
                                }
                            }
                        });
                    } else {
                        System.out.println("Incorrect username or password");
                    }
                }
            });
            adminFrame.setVisible(true);
        } else if (e.getSource() == CD) {
            JFrame cf = new JFrame("CUSTOMER DETAILS");
            cf.setSize(1366, 768);
            setLayout(new FlowLayout());
            cf.setLayout(null);
            JLabel q = new JLabel("CUSTOMER DETAILS");
            q.setBounds(500, 50, 600, 60);
            q.setFont(new Font("Georgia", Font.BOLD, 30));
            q.setForeground(Color.red);
            cf.add(q);
            JLabel cnl = new JLabel("ENTER YOUR NAME ", JLabel.CENTER);
            cnl.setFont(new Font("Times New Roman", Font.BOLD, 25));
            cnl.setBounds(140, 200, 500, 30);
            cnl.setForeground(Color.white);
            JLabel cpl = new JLabel("ENTER PHONE NUMBER ", JLabel.CENTER);
            cpl.setFont(new Font("Times New Roman", Font.BOLD, 25));
            cpl.setBounds(66, 305, 700, 30);
            cpl.setForeground(Color.white);
            JLabel cl = new JLabel(" ");
            JTextField cnf = new JTextField(10);
            cnf.setFont(new Font("Times New Roman", Font.BOLD, 25));
            cnf.setBounds(600, 180, 430, 80);
            cnf.setForeground(Color.BLACK);
            cnf.setBackground(Color.lightGray);
            JTextField cpf = new JTextField(10);
            cpf.setFont(new Font("Times New Roman", Font.BOLD, 25));
            cpf.setBounds(600, 280, 430, 80);
            cpf.setForeground(Color.BLACK);
            cpf.setBackground(Color.lightGray);
            JButton sb = new JButton("SUBMIT");
            sb.setBounds(600, 380, 430, 80);
            sb.setFont(new Font("Times New Roman", Font.BOLD, 25));
            sb.setForeground(Color.BLACK);
            sb.setBackground(Color.orange);
            cf.add(cnl);
            cf.add(cnf);
            cf.add(cpl);
            cf.add(cpf);
            cf.add(new JLabel(" "));
            cf.add(sb);
            cf.add(new JLabel(" "));
            cf.add(cl);
            cf.getContentPane().setBackground(Color.black);
            sb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = cnf.getText();
                    String phone = cpf.getText();
                    if (name.isEmpty() || phone.isEmpty()) {
                        cl.setText("MUST ENTER NAME AND PHONE NUMBER");
                        cl.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        cl.setBounds(600, 490, 500, 50);
                        cl.setForeground(Color.RED);
                        cl.setBackground(Color.lightGray);
                    } else {
                        cl.setText("LOGIN SUCCESS!!");
                        cl.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        cl.setBounds(600, 490, 500, 50);
                        cl.setForeground(Color.GREEN);
                        cl.setBackground(Color.lightGray);
                        try {
                            String sql = "INSERT INTO CUSTOMER(CNAME,CPHONE) VALUES (?, ?)";
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                            Connection connection1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                                    "DBMS", "DBMS");
                            PreparedStatement pstmt = connection1.prepareStatement(sql);
                            pstmt.setString(1, name);
                            pstmt.setString(2, phone);
                            int rowsInserted = pstmt.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.print("INSERTED SUCCESSFULLY");
                            } else {
                                System.out.print("INSERTION FAILED");
                            }
                            pstmt.close();
                            connection1.close();
                        } catch (ClassNotFoundException | SQLException d) {
                            d.printStackTrace();
                        }
                        JFrame md = new JFrame("RestaurantManagement DETAILS");
                        md.setSize(1366, 768);
                        md.getContentPane().setBackground(Color.BLACK);
                        setLayout(new FlowLayout());
                        md.setLayout(null);
                        JLabel info = new JLabel("SELECT YOUR RestaurantManagement");
                        info.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        info.setBounds(250, 50, 300, 20);
                        info.setForeground(Color.yellow);
                        JLabel id = new JLabel("ENTER ITEM ID:");
                        id.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        id.setBounds(250, 305, 300, 18);
                        id.setForeground(Color.yellow);
                        JLabel pack = new JLabel("ENTER PACK(SC,DC,MC):");
                        pack.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        pack.setBounds(250, 405, 300, 18);
                        pack.setForeground(Color.yellow);
                        JLabel qn = new JLabel("ENTER QUANTITY:");
                        qn.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        qn.setBounds(250, 505, 300, 18);
                        qn.setForeground(Color.yellow);
                        JTextField idf = new JTextField();
                        idf.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        idf.setBounds(600, 290, 450, 50);
                        idf.setForeground(Color.BLACK);
                        idf.setBackground(Color.lightGray);
                        JTextField packf = new JTextField();
                        packf.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        packf.setBounds(600, 390, 450, 50);
                        packf.setForeground(Color.BLACK);
                        packf.setBackground(Color.lightGray);
                        JTextField qnf = new JTextField();
                        qnf.setFont(new Font("Times New Roman", Font.BOLD, 25));
                        qnf.setBounds(600, 490, 450, 50);
                        qnf.setForeground(Color.BLACK);
                        qnf.setBackground(Color.lightGray);
                        JButton bill = new JButton("BILL");
                        bill.setBounds(600, 560, 450, 50);
                        bill.setFont(new Font("Times New Roman", Font.BOLD, 18));
                        bill.setForeground(Color.BLACK);
                        bill.setBackground(Color.pink);
                        JLabel info2 = new JLabel(" ");
                        String data[][] = new String[100][5];
                        try {
                            String sql = "SELECT IID,INAME, SC, DC, MC FROM RestaurantManagement ORDER BY IID";
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                            Connection connection1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                                    "DBMS", "DBMS");
                            Statement stmt = connection1.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            int i = 0;
                            while (rs.next()) {
                                data[i][0] = Integer.toString(rs.getInt(1));
                                data[i][1] = rs.getString(2);
                                data[i][2] = Integer.toString(rs.getInt(3));
                                data[i][3] = Integer.toString(rs.getInt(4));
                                data[i][4] = Integer.toString(rs.getInt(5));
                                i++;
                            }
                        } catch (Exception f) {
                            System.out.print(f);
                        }
                        String column[] = { "ITEM ID", "ITEM NAME", "SINGLE COST", "DOUBLE COST", "MANDI COST" };
                        JTable jt = new JTable(data, column);
                        jt.setForeground(Color.BLACK);
                        TableColumnModel columnModel = jt.getColumnModel();
                        int itemNameColumnIndex = 1;
                        TableColumn itemNameColumn = columnModel.getColumn(itemNameColumnIndex);
                        itemNameColumn.setPreferredWidth(300);
                        JScrollPane sp = new JScrollPane(jt);
                        sp.setBounds(250, 100, 800, 150);
                        md.add(info);
                        md.add(info2);
                        md.add(sp);
                        md.add(new JLabel(" "));
                        md.add(id);
                        md.add(idf);
                        md.add(pack);
                        md.add(packf);
                        md.add(qn);
                        md.add(qnf);
                        md.add(bill);
                        md.add(new JLabel(" "));
                        md.setVisible(true);
                        bill.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String i1 = idf.getText();
                                    String i2 = packf.getText();
                                    int i3 = Integer.parseInt(qnf.getText());
                                    int bll = 0;
                                    int d = 0;
                                    Class.forName("oracle.jdbc.driver.OracleDriver");
                                    Connection connection1 = DriverManager
                                            .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "DBMS");
                                    String sql = "SELECT " + i2 + " FROM RestaurantManagement WHERE IID=?";
                                    PreparedStatement pstmt = connection1.prepareStatement(sql);
                                    pstmt.setString(1, i1);
                                    ResultSet rs = pstmt.executeQuery();
                                    if (rs.next()) {
                                        d = rs.getInt(1);
                                    }
                                    bll = i3 * d;
                                    String upd = "UPDATE CUSTOMER SET CBILL=? WHERE CNAME=? AND CPHONE=?";
                                    PreparedStatement updateStmt = connection1.prepareStatement(upd);
                                    updateStmt.setInt(1, bll);
                                    updateStmt.setString(2, name);
                                    updateStmt.setString(3, phone);
                                    updateStmt.executeUpdate();
                                    rs.close();
                                    pstmt.close();
                                    updateStmt.close();
                                    connection1.close();
                                    String i5 = idf.getText();
                                    JFrame bd = new JFrame("BILL SUMMARRY");
                                    bd.setBounds(100, 200, 600, 500);
                                    bd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    bd.setSize(500, 500);
                                    bd.getContentPane().setBackground(Color.BLACK);
                                    setLayout(new FlowLayout());
                                    bd.setLayout(null);
                                    JLabel nmB = new JLabel("BILL SUMMARY");
                                    nmB.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    nmB.setBounds(180, 50, 300, 20);
                                    nmB.setForeground(Color.PINK);
                                    JLabel nm = new JLabel("NAME:");
                                    nm.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    nm.setBounds(50, 100, 300, 20);
                                    nm.setForeground(Color.green);
                                    JLabel pn = new JLabel("PHONE:");
                                    pn.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    pn.setBounds(50, 150, 300, 20);
                                    pn.setForeground(Color.green);
                                    JLabel it = new JLabel("ITEM ORDERED:");
                                    it.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    it.setBounds(50, 200, 300, 20);
                                    it.setForeground(Color.green);
                                    JLabel bl = new JLabel("BILL:");
                                    bl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    bl.setBounds(50, 250, 300, 20);
                                    bl.setForeground(Color.green);
                                    JTextField nmf = new JTextField();
                                    nmf.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                    nmf.setBounds(230, 90, 220, 30);
                                    nmf.setForeground(Color.BLACK);
                                    nmf.setBackground(Color.lightGray);
                                    JTextField pnf = new JTextField();
                                    pnf.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                    pnf.setBounds(230, 140, 220, 30);
                                    pnf.setForeground(Color.BLACK);
                                    pnf.setBackground(Color.lightGray);
                                    JTextField itf = new JTextField();
                                    itf.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                    itf.setBounds(230, 190, 220, 30);
                                    itf.setForeground(Color.BLACK);
                                    itf.setBackground(Color.lightGray);
                                    JTextField blf = new JTextField();
                                    blf.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                    blf.setBounds(230, 240, 220, 30);
                                    blf.setForeground(Color.BLACK);
                                    blf.setBackground(Color.lightGray);
                                    String fetchSql = "SELECT CNAME, CPHONE, CBILL FROM CUSTOMER WHERE CNAME=? AND CPHONE=?";
                                    Connection connection2 = DriverManager
                                            .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "DBMS");
                                    PreparedStatement fetchStmt = connection2.prepareStatement(fetchSql);
                                    fetchStmt.setString(1, name);
                                    fetchStmt.setString(2, phone);
                                    ResultSet rsFetch = fetchStmt.executeQuery();
                                    if (rsFetch.next()) {
                                        String a = rsFetch.getString(1);
                                        String b = rsFetch.getString(2);
                                        int fetchedBill = rsFetch.getInt(3);
                                        nmf.setText(a);
                                        pnf.setText(b);
                                        blf.setText(String.valueOf(fetchedBill));
                                    }
                                    fetchSql = "SELECT INAME FROM RestaurantManagement WHERE IID=?";
                                    PreparedStatement fetchStmt1 = connection2.prepareStatement(fetchSql);
                                    fetchStmt1.setString(1, i5);
                                    ResultSet rsFetch1 = fetchStmt1.executeQuery();
                                    if (rsFetch1.next()) {
                                        String c = rsFetch1.getString(1);
                                        itf.setText(c);

                                    }
                                    rsFetch.close();
                                    fetchStmt.close();
                                    rsFetch1.close();
                                    fetchStmt1.close();
                                    connection2.close();
                                    bd.add(nm);
                                    bd.add(nmB);
                                    bd.add(nmf);
                                    bd.add(pn);
                                    bd.add(pnf);
                                    bd.add(it);
                                    bd.add(itf);
                                    bd.add(bl);
                                    bd.add(blf);
                                    JLabel vst = new JLabel("THANK YOU VISIT AGAIN!");
                                    vst.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                    vst.setBounds(100, 400, 300, 20);
                                    vst.setForeground(Color.orange);
                                    bd.add(vst);
                                    bd.setVisible(true);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                    }
                }
            });
            cf.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RestaurantManagement();
            }
        });
    }
}
