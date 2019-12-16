
package modelos;

public class Pagos {

    private int id_pago;
    private String forma_pago;

    public Pagos() {
    }

    public Pagos(int id_pago, String forma_pago) {
        this.id_pago = id_pago;
        this.forma_pago = forma_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

        
}
