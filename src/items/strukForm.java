
package items;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class strukForm extends javax.swing.JFrame {
    
    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    
    public strukForm() throws SQLException{
        initComponents();
        String idProduk = "", tanggal = "", username = "", nama = "";
        int harga = 0, idTransaksi = 0, idUser = 0;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,user,pass);
            
            String query = "select * from transaksi";

            Statement st = con.createStatement();
           
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                    idTransaksi = rs.getInt("id_transaksi");
                    idUser = rs.getInt("id_user");
                    idProduk = rs.getString("id_produk");
                    tanggal = rs.getString("tanggal");
                    harga = rs.getInt("total_harga");
                }
           
            query = "select username from tbuser where id_user = '"+idUser+"'";
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                    username = rs.getString("username");     
                }
            
            query = "select nama from tbproduk where id_produk = '"+idProduk+"'";
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                    nama = rs.getString("nama");     
                }
            
            con.close();
 
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        
        id_transaksi.setText(String.valueOf(idTransaksi));
        lblTanggal.setText(tanggal);
        namaProduk.setText(nama);
        hargaItem.setText(String.valueOf(harga));
        id_produk1.setText(idProduk);
        namaUser.setText(username);
        ppnHarga.setText(String.valueOf(harga * 10 / 100));
        hargaTotal.setText(String.valueOf(harga + (harga * 10 / 100)));
        
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBatal = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        id_transaksi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        jlabelUser = new javax.swing.JLabel();
        namaUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        namaProduk = new javax.swing.JLabel();
        id_produk1 = new javax.swing.JLabel();
        hargaItem = new javax.swing.JLabel();
        hargaItem1 = new javax.swing.JLabel();
        ppnHarga = new javax.swing.JLabel();
        ppnHarga1 = new javax.swing.JLabel();
        hargaTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBatal.setText("BATAL");

        btnConfirm.setText("CONFIRM");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        id_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        id_transaksi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        id_transaksi.setText("id_transaksi");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("_____________________________");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("E-KIOS");

        lblTanggal.setBackground(new java.awt.Color(0, 0, 0));
        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTanggal.setText("tanggal");

        jlabelUser.setBackground(new java.awt.Color(0, 0, 0));
        jlabelUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlabelUser.setText("user :");

        namaUser.setBackground(new java.awt.Color(0, 0, 0));
        namaUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaUser.setText("namaUser");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setText("_____________________________");

        namaProduk.setBackground(new java.awt.Color(0, 0, 0));
        namaProduk.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaProduk.setText("nama");

        id_produk1.setBackground(new java.awt.Color(0, 0, 0));
        id_produk1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        id_produk1.setText("id_produk");

        hargaItem.setBackground(new java.awt.Color(0, 0, 0));
        hargaItem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaItem.setText("harga");

        hargaItem1.setBackground(new java.awt.Color(0, 0, 0));
        hargaItem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaItem1.setText("PPN");

        ppnHarga.setBackground(new java.awt.Color(0, 0, 0));
        ppnHarga.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ppnHarga.setText("ppn10%");

        ppnHarga1.setBackground(new java.awt.Color(0, 0, 0));
        ppnHarga1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ppnHarga1.setText("Total :");

        hargaTotal.setBackground(new java.awt.Color(0, 0, 0));
        hargaTotal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaTotal.setText("harga_total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(id_transaksi)
                                .addGap(46, 46, 46)
                                .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(namaUser))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(id_produk1)
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaProduk)
                                    .addComponent(hargaItem1))
                                .addGap(73, 73, 73)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hargaItem)
                                    .addComponent(ppnHarga))))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ppnHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hargaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_transaksi)
                    .addComponent(lblTanggal)
                    .addComponent(jlabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namaUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaProduk)
                    .addComponent(hargaItem)
                    .addComponent(id_produk1))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hargaItem1)
                    .addComponent(ppnHarga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ppnHarga1)
                    .addComponent(hargaTotal))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnBatal)
                        .addGap(94, 94, 94)
                        .addComponent(btnConfirm)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnConfirm))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  
    public static void main(String args[]) {
       //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(strukForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(strukForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(strukForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(strukForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new strukForm().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(strukForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel hargaItem;
    private javax.swing.JLabel hargaItem1;
    private javax.swing.JLabel hargaTotal;
    private javax.swing.JLabel id_produk1;
    private javax.swing.JLabel id_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabelUser;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel namaProduk;
    private javax.swing.JLabel namaUser;
    private javax.swing.JLabel ppnHarga;
    private javax.swing.JLabel ppnHarga1;
    // End of variables declaration//GEN-END:variables

    private void toString(int totalHarga) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
