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
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    PedidoBusiness pedidoBusiness = new PedidoBusiness();
    ClienteBusiness clienteBusiness = new ClienteBusiness();
    ProductoBusiness productoBusiness = new ProductoBusiness();
    ProductoxpedidoBusinees productoxpedidoBusinees = new ProductoxpedidoBusinees();
    private List<SelectItem> productos = new ArrayList<SelectItem>();

    public void insertar() {
        this.pedido.getProductoxpedidos().add(this.pedidoDetalle);
        this.pedidoDetalle = new Productoxpedido();
        try {
            // this.pedidoBusiness.addPedido(this.pedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // WebUtil.redirect("/pedidos/" + this.pedido.getPedCodigo());
    }

    public void actualizar() {
        if (this.pedido != null) {

            try {
                this.pedidoBusiness.updatePedido(this.pedido);
            } catch (Exception ex) {
                Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebUtil.sendRedirect("/pedidos/" + this.id);
        }
    }

    public void agregarProducto() throws Exception {
        if (WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE()) == null) {
            WebUtil.sendRedirect("/login");
        } else {
            Cliente cliente = (Cliente) WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE());
            
            if (this.id == 0) {
                this.pedido.setPedTipo("pedido");
                this.pedido.setCliente(cliente);
                this.pedido.setPedFechahora(new Date());
                this.pedidoBusiness.addPedido(this.pedido);

                List<Pedido> _pedidos = this.pedidoBusiness.listPedidoxClientexTipo(cliente.getCliCodigo(), "pedido");

                this.pedido = _pedidos.get(_pedidos.size() - 1);

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
            
            WebUtil.sendRedirect("/pedidos/" + this.id);
        }
    }

    public void eliminarProducto(Integer codProducto) {
        try {
            ProductoxpedidoId codPedido = new ProductoxpedidoId(this.id, codProducto);
            Productoxpedido pedidoDetalle = this.productoxpedidoBusinees.getProductoxpedido(codPedido);

            this.productoxpedidoBusinees.deleteProductoxpedido(pedidoDetalle);
        } catch (Exception ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebUtil.sendRedirect("/pedidos/" + this.id);
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
                this.pedido = new Pedido();
            }
        }

        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        try {
            return pedidoBusiness.listPedidoxTipo("pedido");
        } catch (Exception ex) {
            return new ArrayList<Pedido>();
        }
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
        this.productos = new ArrayList<SelectItem>();

        List<Producto> _productos;

        try {
            _productos = this.productoBusiness.listProducto();
        } catch (Exception ex) {
            _productos = new ArrayList<Producto>();
        }

        for (Producto c : _productos) {
            productos.add(new SelectItem(c.getProdCodigo(), c.getProdNombre()));
        }

        return productos;
    }
}