import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RestaurantManagement extends JFrame implements ActionListener {
    // --- Admin creds ---
    private static final String ADMIN_USER = "ADMIN";
    private static final String ADMIN_PASS = "1234";

    // --- JDBC config ---
    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String JDBC_USER = "DBMS";
    private static final String JDBC_PASS = "DBMS";

    private JButton adminBtn, customerBtn;

    public RestaurantManagement() {
        setTitle("Restaurant Management System");
        setSize(820, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Restaurant Management System", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout());
        adminBtn = new JButton("Admin");
        customerBtn = new JButton("Customer");
        adminBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        center.add(adminBtn);
        center.add(customerBtn);
        add(center, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminBtn)
            new AdminLogin();
        if (e.getSource() == customerBtn)
            new CustomerLogin();
    }

    // ---------------- Admin Login ----------------
    class AdminLogin extends JFrame {
        JTextField user;
        JPasswordField pass;

        AdminLogin() {
            setTitle("Admin Login");
            setSize(360, 240);
            setLayout(null);
            setLocationRelativeTo(null);

            JLabel u = new JLabel("Username:");
            JLabel p = new JLabel("Password:");
            u.setBounds(30, 40, 100, 30);
            p.setBounds(30, 90, 100, 30);
            add(u);
            add(p);

            user = new JTextField();
            pass = new JPasswordField();
            user.setBounds(130, 40, 180, 30);
            pass.setBounds(130, 90, 180, 30);
            add(user);
            add(pass);

            JButton login = new JButton("Login");
            login.setBounds(130, 140, 100, 35);
            add(login);

            login.addActionListener(ae -> {
                if (ADMIN_USER.equals(user.getText()) && ADMIN_PASS.equals(new String(pass.getPassword()))) {
                    dispose();
                    new AdminPanel();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            });

            setVisible(true);
        }
    }

    // ---------------- Admin Panel (Insert Item) ----------------
    class AdminPanel extends JFrame {
        JTextField id, name, sc, dc, mc;

        AdminPanel() {
            setTitle("Add Menu Item");
            setSize(520, 320);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(6, 2, 10, 10));

            add(new JLabel("Item ID:"));
            id = new JTextField();
            add(id);
            add(new JLabel("Item Name:"));
            name = new JTextField();
            add(name);
            add(new JLabel("Single Cost:"));
            sc = new JTextField();
            add(sc);
            add(new JLabel("Double Cost:"));
            dc = new JTextField();
            add(dc);
            add(new JLabel("Mandi Cost:"));
            mc = new JTextField();
            add(mc);

            JButton insert = new JButton("Insert");
            add(new JLabel());
            add(insert);

            insert.addActionListener(ae -> insertItem());
            setVisible(true);
        }

        void insertItem() {
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO RESTAURANTMANAGEMENT (IID, INAME, SC, DC, MC) VALUES (?,?,?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, parseInt(id.getText(), "Item ID"));
                    ps.setString(2, requireText(name.getText(), "Item Name"));
                    ps.setInt(3, parseInt(sc.getText(), "Single Cost"));
                    ps.setInt(4, parseInt(dc.getText(), "Double Cost"));
                    ps.setInt(5, parseInt(mc.getText(), "Mandi Cost"));
                    int n = ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, n > 0 ? "Item added." : "Insert failed.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    // ---------------- Customer Login ----------------
    class CustomerLogin extends JFrame {
        JTextField nameField, phoneField;

        CustomerLogin() {
            setTitle("Customer Login");
            setSize(380, 240);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel n = new JLabel("Name:");
            n.setBounds(40, 40, 100, 30);
            add(n);

            JLabel p = new JLabel("Phone:");
            p.setBounds(40, 90, 100, 30);
            add(p);

            nameField = new JTextField();
            nameField.setBounds(120, 40, 200, 30);
            add(nameField);

            phoneField = new JTextField();
            phoneField.setBounds(120, 90, 200, 30);
            add(phoneField);

            JButton go = new JButton("Proceed");
            go.setBounds(120, 140, 110, 35);
            add(go);

            go.addActionListener(ae -> {
                try {
                    String name = requireText(nameField.getText(), "Name");
                    String phone = requireText(phoneField.getText(), "Phone");
                    insertCustomer(name, phone);
                    dispose();
                    new CustomerOrder(name, phone);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            });

            setVisible(true);
        }

        // ✅ FIXED insertCustomer method
        void insertCustomer(String name, String phone) {
            try (Connection con = getConnection()) {
                // check if already exists
                PreparedStatement check = con.prepareStatement(
                        "SELECT COUNT(*) FROM CUSTOMER WHERE CNAME=? AND CPHONE=?");
                check.setString(1, name);
                check.setString(2, phone);
                ResultSet rs = check.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                rs.close();
                check.close();

                if (count == 0) {
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO CUSTOMER (CNAME, CPHONE, CBILL) VALUES (?, ?, 0)");
                    ps.setString(1, name);
                    ps.setString(2, phone);
                    ps.executeUpdate();
                    ps.close();
                }
            } catch (SQLException e) {
                // Catch SQL exceptions specifically
                JOptionPane.showMessageDialog(this, "Customer save failed: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // ✅ Catch generic Exception (to handle getConnection() throws Exception)
                JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ---------------- Customer Order ----------------
    class CustomerOrder extends JFrame {
        JTextField idField, qtyField;
        JComboBox<String> packBox;
        DefaultTableModel model;

        final String cname, cphone;

        CustomerOrder(String cname, String cphone) {
            this.cname = cname;
            this.cphone = cphone;

            setTitle("Order Menu");
            setSize(920, 600);
            setLocationRelativeTo(null);
            setLayout(null);

            model = new DefaultTableModel(new String[] { "Item ID", "Name", "SC", "DC", "MC" }, 0) {
                @Override
                public boolean isCellEditable(int r, int c) {
                    return false;
                }
            };
            JTable table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(40, 40, 830, 230);
            add(sp);

            // load menu
            try (Connection con = getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st
                            .executeQuery("SELECT IID, INAME, SC, DC, MC FROM RESTAURANTMANAGEMENT ORDER BY IID")) {
                while (rs.next())
                    model.addRow(
                            new Object[] { rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5) });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Load menu failed: " + ex.getMessage());
            }

            // Controls
            addLabel("Item ID:", 40, 300);
            idField = addText(140, 300, 140);
            addLabel("Pack:", 320, 300);
            packBox = new JComboBox<>(new String[] { "SC", "DC", "MC" });
            packBox.setBounds(380, 300, 100, 30);
            add(packBox);
            addLabel("Qty:", 520, 300);
            qtyField = addText(570, 300, 100);

            JButton bill = new JButton("Generate Bill");
            bill.setBounds(700, 300, 170, 30);
            add(bill);

            bill.addActionListener(ae -> generateBill());
            setVisible(true);
        }

        void addLabel(String t, int x, int y) {
            JLabel l = new JLabel(t);
            l.setBounds(x, y, 100, 30);
            add(l);
        }

        JTextField addText(int x, int y, int w) {
            JTextField t = new JTextField();
            t.setBounds(x, y, w, 30);
            add(t);
            return t;
        }

        void generateBill() {
            try (Connection con = getConnection()) {
                int itemId = parseInt(idField.getText(), "Item ID");
                String pack = (String) packBox.getSelectedItem();
                int qty = parseInt(qtyField.getText(), "Quantity");
                if (qty <= 0)
                    throw new IllegalArgumentException("Quantity must be > 0");

                // SAFE column mapping: never concatenate user text into SQL
                String column = switch (pack) {
                    case "SC" -> "SC";
                    case "DC" -> "DC";
                    case "MC" -> "MC";
                    default -> throw new IllegalArgumentException("Invalid pack");
                };

                // fetch unit price and item name
                int unitPrice = 0;
                String itemName = "";
                try (PreparedStatement ps = con
                        .prepareStatement("SELECT INAME, SC, DC, MC FROM RESTAURANTMANAGEMENT WHERE IID=?")) {
                    ps.setInt(1, itemId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next())
                            throw new IllegalArgumentException("Item ID not found");
                        itemName = rs.getString("INAME");
                        int sc = rs.getInt("SC"), dc = rs.getInt("DC"), mc = rs.getInt("MC");
                        unitPrice = ("SC".equals(column) ? sc : ("DC".equals(column) ? dc : mc));
                    }
                }

                int total = unitPrice * qty;

                // Update CUSTOMER.CBILL for info (not required, but keeps parity with your
                // original)
                try (PreparedStatement upd = con
                        .prepareStatement("UPDATE CUSTOMER SET CBILL=? WHERE CNAME=? AND CPHONE=?")) {
                    upd.setInt(1, total);
                    upd.setString(2, cname);
                    upd.setString(3, cphone);
                    upd.executeUpdate();
                }

                // Open payment window
                new PaymentWindow(cname, cphone, itemId, itemName, pack, qty, unitPrice, total);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    // ---------------- Payment Window ----------------
    class PaymentWindow extends JFrame {
        final String cname, cphone, pack, itemName;
        final int itemId, qty, unitPrice, total;

        JComboBox<String> methodBox;
        JTextField paidField, changeField;

        PaymentWindow(String cname, String cphone, int itemId, String itemName, String pack, int qty, int unitPrice,
                int total) {
            this.cname = cname;
            this.cphone = cphone;
            this.itemId = itemId;
            this.itemName = itemName;
            this.pack = pack;
            this.qty = qty;
            this.unitPrice = unitPrice;
            this.total = total;

            setTitle("Checkout");
            setSize(520, 400);
            setLocationRelativeTo(null);
            setLayout(null);

            int y = 30;
            int lh = 28;

            addLine("Customer:", cname + " (" + cphone + ")", 30, y);
            y += lh;
            addLine("Item:", itemName + " [" + pack + "] x " + qty, 30, y);
            y += lh;
            addLine("Unit Price:", "₹" + unitPrice, 30, y);
            y += lh;
            addLine("Total:", "₹" + total, 30, y);
            y += lh + 10;

            JLabel mLbl = new JLabel("Payment Method:");
            mLbl.setBounds(30, y, 140, 30);
            add(mLbl);
            methodBox = new JComboBox<>(new String[] { "CASH", "CARD", "UPI" });
            methodBox.setBounds(180, y, 140, 30);
            add(methodBox);
            y += 40;

            JLabel paidLbl = new JLabel("Amount Paid:");
            paidLbl.setBounds(30, y, 140, 30);
            add(paidLbl);
            paidField = new JTextField();
            paidField.setBounds(180, y, 140, 30);
            add(paidField);
            y += 40;

            JLabel changeLbl = new JLabel("Change:");
            changeLbl.setBounds(30, y, 140, 30);
            add(changeLbl);
            changeField = new JTextField();
            changeField.setEditable(false);
            changeField.setBounds(180, y, 140, 30);
            add(changeField);
            y += 50;

            JButton confirm = new JButton("Confirm Payment");
            confirm.setBounds(180, y, 160, 35);
            add(confirm);

            // live change calc
            paidField.getDocument().addDocumentListener(new DocumentListener() {
                void recalc() {
                    try {
                        int paid = paidField.getText().isBlank() ? 0 : Integer.parseInt(paidField.getText().trim());
                        int change = paid - total;
                        changeField.setText("₹" + Math.max(0, change));
                    } catch (NumberFormatException ex) {
                        changeField.setText("₹0");
                    }
                }

                public void insertUpdate(DocumentEvent e) {
                    recalc();
                }

                public void removeUpdate(DocumentEvent e) {
                    recalc();
                }

                public void changedUpdate(DocumentEvent e) {
                    recalc();
                }
            });

            confirm.addActionListener(ae -> confirmPayment());
            setVisible(true);
        }

        void addLine(String label, String value, int x, int y) {
            JLabel l = new JLabel(label);
            l.setBounds(x, y, 120, 30);
            add(l);
            JLabel v = new JLabel(value);
            v.setBounds(x + 130, y, 340, 30);
            add(v);
        }

        void confirmPayment() {
            try {
                int paid = parseInt(paidField.getText(), "Amount Paid");
                if (paid < total)
                    throw new IllegalArgumentException("Amount paid is less than total.");

                String method = (String) methodBox.getSelectedItem();
                int change = paid - total;

                // Persist payment
                try (Connection con = getConnection();
                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO PAYMENT (CNAME, CPHONE, ITEM_ID, PACK, QTY, UNIT_PRICE, TOTAL, METHOD, PAID, CHANGE, STATUS) "
                                        +
                                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)")) {
                    ps.setString(1, cname);
                    ps.setString(2, cphone);
                    ps.setInt(3, itemId);
                    ps.setString(4, pack);
                    ps.setInt(5, qty);
                    ps.setInt(6, unitPrice);
                    ps.setInt(7, total);
                    ps.setString(8, method);
                    ps.setInt(9, paid);
                    ps.setInt(10, change);
                    ps.setString(11, "PAID");
                    ps.executeUpdate();
                }

                // Show receipt
                new ReceiptWindow(cname, cphone, itemName, pack, qty, unitPrice, total, method, paid, change);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    // ---------------- Receipt Window (Printable) ----------------
    class ReceiptWindow extends JFrame {
        JTextArea area;

        ReceiptWindow(String cname, String cphone, String itemName, String pack, int qty, int unitPrice, int total,
                String method, int paid, int change) {
            setTitle("Receipt");
            setSize(520, 520);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 14));
            add(new JScrollPane(area), BorderLayout.CENTER);

            JButton printBtn = new JButton("Print (Ctrl+P)");
            printBtn.addActionListener(ae -> {
                try {
                    area.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Print failed: " + ex.getMessage());
                }
            });
            add(printBtn, BorderLayout.SOUTH);

            StringBuilder sb = new StringBuilder();
            sb.append("********** RESTAURANT RECEIPT **********\n");
            sb.append("Date/Time : ").append(new java.util.Date()).append("\n");
            sb.append("----------------------------------------\n");
            sb.append("Customer  : ").append(cname).append("\n");
            sb.append("Phone     : ").append(cphone).append("\n");
            sb.append("----------------------------------------\n");
            sb.append(String.format("%-20s %3s x %-3d = ₹%d\n", itemName, pack, qty, unitPrice * qty));
            sb.append("----------------------------------------\n");
            sb.append(String.format("%-20s %15s\n", "Subtotal", "₹" + total));
            // (Optional) tax/discount logic could be added here
            sb.append("----------------------------------------\n");
            sb.append(String.format("%-20s %15s\n", "Paid (" + method + ")", "₹" + paid));
            sb.append(String.format("%-20s %15s\n", "Change", "₹" + change));
            sb.append("----------------------------------------\n");
            sb.append("Status: PAID\n");
            sb.append("Thank you! Visit again.\n");
            sb.append("****************************************\n");

            area.setText(sb.toString());
            setVisible(true);
        }
    }

    // ---------------- Utilities ----------------
    private static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    private static int parseInt(String s, String field) {
        if (s == null || s.trim().isEmpty())
            throw new IllegalArgumentException(field + " is required.");
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(field + " must be a number.");
        }
    }

    private static String requireText(String s, String field) {
        if (s == null || s.trim().isEmpty())
            throw new IllegalArgumentException(field + " is required.");
        return s.trim();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RestaurantManagement::new);
    }
}
