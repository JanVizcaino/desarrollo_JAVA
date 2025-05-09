
package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;

public class ProductoManager {
    public static void main(String[] args) {
        ProductoDAO producto =new ProductoDAOImpl();
        
        //agregar nuevo producto
        producto.insert(new Producto(200,"Pollo",10.0));
        
        //obtener el producto con el ID = 100
        Producto p = producto.read(200);
        System.out.println(p);
    }
}
