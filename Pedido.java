//clase base pedido
public class Pedido {
    private int id;
    private String cliente;
    private double monto;

    // constructor de pedido
    public Pedido(int id, String cliente, double monto) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;

    }

    // Getter para el atributo id (solo lectura)
    public int getId() {
        return id;
    }

    // no tengo un setter para el id,
    // Getter para el atributo nombre
    public String getcliente() {
        return cliente;
    }

    // no hay Setter para modificar el cliente

    // Getter para el atributo monto no deberia poder modificar

    // Setter para modificar el monto no deberia poder modificar

    // Método para mostrar la información del artículo
    public void mostrarPedido() {
        // Este método puede ser sobrescrito por subclases (polimorfismo)
        System.out.println("ID: " + id + " | Cliente: " + cliente + " | Monto: $" + monto);
    }

}
