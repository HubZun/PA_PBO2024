
package items;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class strukForm extends javax.swing.JFrame {
    
//    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
//    private static final String user = "root";
//    private static final String pass = "";
//    
    public strukForm() throws SQLException{
        initComponents();
//        String idProduk = "", tanggal = "", username = "", nama = "";
//        int harga = 0, idTransaksi = 0, idUser = 0;
//        
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection con = DriverManager.getConnection(url,user,pass);
//            
//            String query = "select * from transaksi";
//
//            Statement st = con.createStatement();
//           
//            ResultSet rs =  st.executeQuery(query);
//
//            while(rs.next()){
//                    idTransaksi = rs.getInt("id_transaksi");
//                    idUser = rs.getInt("id_user");
//                    idProduk = rs.getString("id_produk");
//                    tanggal = rs.getString("tanggal");
//                    harga = rs.getInt("total_harga");
//                }
//           
//            query = "select username from tbuser where id_user = '"+idUser+"'";
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            
//            while(rs.next()){
//                    username = rs.getString("username");     
//                }
//            
//            query = "select nama from tbproduk where id_produk = '"+idProduk+"'";
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            
//            while(rs.next()){
//                    nama = rs.getString("nama");     
//                }
//            
//            con.close();
// 
//        } catch (Exception e) {
//            
//            System.out.println(e);
//        }
        
 
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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        id_transaksi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        id_transaksi.setText("id_transaksi");
        jPanel1.add(id_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("____________________________________");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 230, 470, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("E-KIOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        lblTanggal.setBackground(new java.awt.Color(0, 0, 0));
        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTanggal.setText("tanggal");
        jPanel1.add(lblTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 60, -1));

        jlabelUser.setBackground(new java.awt.Color(0, 0, 0));
        jlabelUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlabelUser.setText("user :");
        jPanel1.add(jlabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        namaUser.setBackground(new java.awt.Color(0, 0, 0));
        namaUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaUser.setText("namaUser");
        jPanel1.add(namaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setText("____________________________________");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, 26));

        namaProduk.setBackground(new java.awt.Color(0, 0, 0));
        namaProduk.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaProduk.setText("nama");
        jPanel1.add(namaProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        id_produk1.setBackground(new java.awt.Color(0, 0, 0));
        id_produk1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        id_produk1.setText("id_produk");
        jPanel1.add(id_produk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        hargaItem.setBackground(new java.awt.Color(0, 0, 0));
        hargaItem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaItem.setText("harga");
        jPanel1.add(hargaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        hargaItem1.setBackground(new java.awt.Color(0, 0, 0));
        hargaItem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaItem1.setText("PPN");
        jPanel1.add(hargaItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        ppnHarga.setBackground(new java.awt.Color(0, 0, 0));
        ppnHarga.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ppnHarga.setText("ppn10%");
        jPanel1.add(ppnHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        ppnHarga1.setBackground(new java.awt.Color(0, 0, 0));
        ppnHarga1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ppnHarga1.setText("Total :");
        jPanel1.add(ppnHarga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 63, -1));

        hargaTotal.setBackground(new java.awt.Color(0, 0, 0));
        hargaTotal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hargaTotal.setText("harga_total");
        jPanel1.add(hargaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 271, 115, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    public javax.swing.JButton btnBatal;
    public javax.swing.JButton btnConfirm;
    public javax.swing.JLabel hargaItem;
    private javax.swing.JLabel hargaItem1;
    public javax.swing.JLabel hargaTotal;
    public javax.swing.JLabel id_produk1;
    public javax.swing.JLabel id_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabelUser;
    public javax.swing.JLabel lblTanggal;
    public javax.swing.JLabel namaProduk;
    public javax.swing.JLabel namaUser;
    public javax.swing.JLabel ppnHarga;
    private javax.swing.JLabel ppnHarga1;
    // End of variables declaration//GEN-END:variables

    private void toString(int totalHarga) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
