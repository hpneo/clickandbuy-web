/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.PedidoBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.business.ClienteBusiness;
import clickandbuy.upc.edu.core.business.ProductoxpedidoBusinees;
import clickandbuy.upc.edu.core.entity.Cliente;
import clickandbuy.upc.edu.core.entity.Pedido;
import clickandbuy.upc.edu.core.entity.Productoxpedido;
import clickandbuy.upc.edu.core.entity.ProductoxpedidoId;
import clickandbuy.upc.edu.core.entity.Producto;
import com.clickandbuy.web.util.Constantes;
import com.clickandbuy.web.util.WebUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;

/**
 *
 * @author GUSTAVO
 */
@ManagedBean(name = "pedidoController")
@RequestScoped
public class PedidoController {

    @ManagedProperty("#{param.id}")
    private int id = 0;
    private Pedido pedido = new Pedido();
    private Productoxpedido pedidoDetalle = new Productoxpedido();
    PedidoBusiness pedidoBusiness = new PedidoBusiness();
    ClienteBusiness clienteBusiness = new ClienteBusiness();
    ProductoBusiness productoBusiness = new ProductoBusiness();
    ProductoxpedidoBusinees productoxpedidoBusinees = new ProductoxpedidoBusinees();
    static String INDEX_ROUTE = "/pedidos";
    static String SHOW_ROUTE = "/pedidos/%d";

    public void insertar() {
        this.pedido.getProductoxpedidos().add(this.pedidoDetalle);
        this.pedidoDetalle = new Productoxpedido();
    }

    public void actualizar() {
        if (this.pedido != null) {

            try {
                this.pedidoBusiness.updatePedido(this.pedido);
            } catch (Exception ex) {
                Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebUtil.sendRedirect(String.format(SHOW_ROUTE, this.id));
        }
    }

    public void agregarProducto() throws Exception {
        if (WebUtil.getObjectSesion(Constantes.getSESIONCLIENTE()) == null) {
            WebUtil.sendRedirect("/login");
        } else {
            Cliente cliente = (Cliente) WebUtil.getObjectSesion(Constantes.getSESIONCLIENTE());
            
            if (this.id == 0) {
                this.pedido.setPedTipo(Constantes.PEDIDO());
                this.pedido.setCliente(cliente);
                this.pedido.setPedFechahora(new Date());
                this.pedidoBusiness.addPedido(this.pedido);

                List<Pedido> pedidosCollection = this.pedidoBusiness.listPedidoxClientexTipo(cliente.getCliCodigo(), "pedido");

                this.pedido = pedidosCollection.get(pedidosCollection.size() - 1);

                this.id = this.pedido.getPedCodigo();
            }
            
            Producto pedidoProducto = this.productoBusiness.getProductoByCode(this.pedidoDetalle.getProducto().getProdCodigo());
            this.pedidoDetalle.setProducto(pedidoProducto);

            this.pedidoDetalle.setPedido(this.pedido);

            BigDecimal precioTotal = this.pedidoDetalle.getProducto().getProdPrecioventa();
            precioTotal = precioTotal.multiply(new BigDecimal(this.pedidoDetalle.getPropedCantidad()));

            this.pedidoDetalle.setPropedPreciototal(precioTotal);
            this.pedidoDetalle.setId(new ProductoxpedidoId(this.pedido.getPedCodigo(), this.pedidoDetalle.getProducto().getProdCodigo()));
            this.productoxpedidoBusinees.addProductoxpedido(this.pedidoDetalle);
            
            WebUtil.sendRedirect(String.format(SHOW_ROUTE, this.id));
        }
    }

    public void eliminarProducto(Integer codProducto) {
        try {
            ProductoxpedidoId codPedido = new ProductoxpedidoId(this.id, codProducto);
            Productoxpedido pedidoDetalleAux = this.productoxpedidoBusinees.getProductoxpedido(codPedido);

            this.productoxpedidoBusinees.deleteProductoxpedido(pedidoDetalleAux);
        } catch (Exception ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebUtil.sendRedirect(String.format(SHOW_ROUTE, this.id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        if (this.id != 0) {
            try {
                this.pedido = this.pedidoBusiness.getPedido(this.id);
            } catch (Exception ex) {
                Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
                this.pedido = new Pedido();
            }
        }

        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            pedidos = pedidoBusiness.listPedidoxTipo("pedido");
        } catch (Exception ex) {
           Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pedidos;
    }

    public Productoxpedido getPedidoDetalle() {
        if (this.pedidoDetalle.getProducto() == null) {
            this.pedidoDetalle.setProducto(new Producto());
        }
        return this.pedidoDetalle;
    }

    public void setPedidoDetalle(Productoxpedido pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public List<SelectItem> getProductos() {
        List<SelectItem> productos = new ArrayList<SelectItem>();
        List<Producto> productosCollection = new ArrayList<Producto>();

        try {
            productosCollection = this.productoBusiness.listProducto();
        } catch (Exception ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Producto c : productosCollection) {
            productos.add(new SelectItem(c.getProdCodigo(), c.getProdNombre()));
        }

        return productos;
    }
}