
package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;

public class ProductoManager {
    public static void main(String[] args) {
        ProductoDAO producto =new ProductoDAOImpl();
        
//        //agregar nuevo producto
//        producto.insert(new Producto(200,"Ceviche",22.0));
//        Producto p = producto.read(200);
//        System.out.println("Producto añadido :"+p);

//        //edita el producto
//        producto.update(new Producto(200,"Pollo",10.0));
//        Producto p = producto.read(200);
//        System.out.println("Producto editado: "+p);
//
//        //borra el producto
//        Producto p = producto.read(200);
//        System.out.println("Producto a borrar: "+p);
//        producto.delete(new Producto(200, "Pollo", 10.0));

    }
}
