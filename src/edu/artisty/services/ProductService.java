/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import edu.artisty.entities.Category;
import edu.artisty.entities.Product;
import edu.artisty.utiles.DataSource;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Nour Benkairia
 */
public class ProductService implements IService<Product> {

    public FileInputStream fis;
    public File file;
    Connection cnx = DataSource.getInstance().getCnx();
    Product t = new Product();

    @Override
    public void ajouter(Product t) {
        try {
            if (exists(t.getNom()) != true) {

                try {

                    String req = "INSERT INTO `product`(`nom`, `description`,`prix`,`img`, `cat_p`,`user_id`,`url`) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setString(1, t.getNom());
                    ps.setString(2, t.getDescription());
                    ps.setDouble(3, t.getPrix());

//            File imageFile = new File("C:/Users/Nour Benkairia/Documents/NetBeansProjects/ArtistyProject/src/edu/artisty/images.png");
                    // byte[] imageData;
                    // try {
                    File file = new File(t.getImg().toString());

                    Path path = file.toPath();
                    String img = path.toString();

                    //  imageData = Files.readAllBytes(t.getImg().toPath());
                    //  ps.setBytes(4, imageData);
                    ps.setString(4, img);

//                    } catch (IOException ex) {
//                        System.err.println(ex.getMessage());
//
//                    }
//    try {
//         fis=new FileInputStream(file);
//          ps.setBinaryStream(4, fis, file.length());
//     } catch (FileNotFoundException ex) {
//        System.err.println(ex.getMessage());
//     }
                    Category c = new Category();

                    ps.setString(5, t.getCat_p().getNom());
                    ps.setInt(6, t.getUser_id());
                    ps.setString(7, t.getUrl());
                    ps.executeUpdate();
                    System.out.println("Product added successfully !");
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(Product p) {
        try {
            String req = "DELETE FROM product WHERE id_p = ? ";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, p.getId_p());
            ste.executeUpdate();
            System.out.println("Product deleted successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean deleteProduct(int productId) {
    try {
        PreparedStatement stmt = cnx.prepareStatement("DELETE FROM product WHERE id_p = ?");
        stmt.setInt(1, productId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected == 1; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    

    @Override
    public void modifier(Product t) {
        try {
            String req = "UPDATE `product` SET `nom`='" + t.getNom() + "',`description`='" + t.getDescription() + "',`prix`='" + t.getPrix() + "',`img`='" + t.getImg() + "',`cat_p`='" + t.getCat_p().getNom() + "',`user_id`='" + t.getUser_id() + "',`url`='" + t.getUrl() + "' WHERE `id_p`= '" + t.getId_p() + "';";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product updated successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void updateProduct(Product product) {
    try {
        PreparedStatement stmt = cnx.prepareStatement("UPDATE product SET nom = ?, description = ?, prix = ?, img = ?, cat_p = ? , user_id = ?, url = ? WHERE id_p = ?");
        stmt.setString(1, product.getNom());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrix());
        
         File file = new File(product.getImg().toString());
         Path path = file.toPath();
         String img = path.toString();
        
         stmt.setString(4,img);
        
        
       
        stmt.setString(5, product.getCat_p().getNom());
        stmt.setDouble(6, product.getUser_id());
        stmt.setString(7, product.getUrl());
        stmt.setInt(8, product.getId_p());
        stmt.executeUpdate();
         
    } catch (SQLException e) {
        e.printStackTrace();
       
    }
}

    
    

    @Override
    public Product getOneById(int id) {
        Product p = null;
        try {
            String req = "SELECT * FROM `product`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Blob blob;
                try {
                    blob = rs.getBlob("img");
                    File file = new File("output.png");
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(blob.getBytes(1, (int) blob.length()));

                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                String req1 = "SELECT * FROM Category WHERE id_cat = ?";//rs.getInt(1)
                Statement st1 = cnx.createStatement();
                ResultSet rs1 = st.executeQuery(req);

                while (rs1.next()) {

                    Category cat = new Category(rs1.getInt(1), rs1.getNString(2));

                    p = new Product(rs.getInt(1),
                            rs.getString("nom"),
                            rs.getString("description"),
                            rs.getDouble(4),
                            file,
                            cat,
                            rs.getInt(7),
                            rs.getString("url"));
                    System.out.println(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        Product t = new Product();
        CategoryService cs = new CategoryService();

        try {
            String req = "SELECT * FROM product";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
//    try {
//        byte[] data = Files.readAllBytes(t.getImg().toPath());
//        String fich ="fich.txt";
//        File fichier = new File(fich);
//        FileOutputStream outputstream = new FileOutputStream(fichier);
//        outputstream.write(data);
//       
//        
//    } catch (IOException ex) {
//        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
//    }

//          
//                InputStream is = rs.getBinaryStream("img");
//    try {
//         OutputStream os = new FileOutputStream(new File("image.png"));
//        byte[] content = new byte [1024];
//        int Size= 0;
//        while((Size= is.read(content)) != -1){
//        os.write(content, Size, Size);
//    }
//        os.close();
//        is.close();
//    } catch (FileNotFoundException ex) {
//        System.out.println(ex.getMessage());
//    } catch (IOException ex) {
//        System.out.println(ex.getMessage());
//    }
         //   while (rs.next()) {
                //   Blob blob;
                //String img;
                //      try {
                // blob = rs.getBlob("img");
                //img = rs.getString("img");

//                    File file = new File("output.png");
                //File file = new File("output.png");
                // Path path = Paths.get(img);
                //  File file = path.toFile();
                //FileUtils.writeStringToFile(file, img);
//                    try {
//                        FileOutputStream fos = new FileOutputStream(file);
//                        fos.write(blob.getBytes(1, (int) blob.length()));
//
//                    } catch (FileNotFoundException ex) {
//                        System.out.println(ex.getMessage());
//                    } catch (IOException ex) {
//                        System.out.println(ex.getMessage());
//                    }
//
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//                String req1 = "SELECT nom FROM Category WHERE nom = ?";//rs.getInt(1)
//                Statement st1 = cnx.createStatement();
//                ResultSet rs1 = st1.executeQuery(req);
                while (rs.next()) {

                    //Category cat = new Category(rs.getNString(2));
                    File file;
                    Product P = new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            new File(rs.getString(5)),
                            cs.getOneByName(rs.getString(6)),
                            rs.getInt(7),
                            rs.getString(8));
                    list.add(P);
                    System.out.println(cs.getOneByName(rs.getString(6)));
                    System.out.println(list);
                }

           // }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);
        return list;
    }

    public boolean RechercherProduit(int id) {
        String req = null;
        boolean x = false;

        try {
            req = "SELECT * FROM product WHERE Id_p = ?";
            PreparedStatement pstmt = this.cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                System.out.println("Produit Existe");
                x = true;
            } else {
                System.out.println("Produit n'existe pas");
                x = false;
            }
        } catch (SQLException var6) {
            System.err.println(var6.getMessage());
        }
        return x;

    }

    public Product getOneByName(String nom) {

        CategoryService cs = new CategoryService();
        try {
            String req = "SELECT * FROM product WHERE `nom`= '" + nom + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                File file;

                Product P = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        new File(rs.getString(5)),
                        cs.getOneByName(rs.getString(6)),
                        rs.getInt(7),
                        rs.getString(8));

                return P;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;

    }

    public boolean exists(String nom) throws SQLException {

        PreparedStatement a = cnx.prepareStatement("SELECT * FROM product");
        ResultSet rs = a.executeQuery();
        while (rs.next()) {
            if (nom.equals(rs.getString("nom"))) {
                System.out.println("this Product already exists");
                return true;
            }
        }
        return false;

    }
//    public List<String> getNameCat(){
//        List<String> list = new ArrayList<>();
//        try{
//             String req1 = "SELECT nom FROM Category WHERE nom = ?";//rs.getInt(1)
//                Statement st1 = cnx.createStatement();
//                ResultSet rs1 = st1.executeQuery(req1);
//                
//                while(rs1.next()){
//                    
//                    Category cat = new Category(rs1.getNString(2));
//                }
//            
//        }catch{
//            
//        }
//        
//    }

}
