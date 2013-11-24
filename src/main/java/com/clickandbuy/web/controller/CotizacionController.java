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
import clickandbuy.upc.edu.core.exception.PedidoException;
import clickandbuy.upc.edu.core.exception.ProductoException;
import clickandbuy.upc.edu.core.exception.ProductoxpedidoException;
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
@ManagedBean(name = "cotizacionController")
@RequestScoped
public class CotizacionController {

    @ManagedProperty("#{param.id}")
    private int id = 0;
    private Pedido cotizacion = new Pedido();
    private Productoxpedido cotizacionDetalle = new Productoxpedido();
    PedidoBusiness pedidoBusiness = new PedidoBusiness();
    ClienteBusiness clienteBusiness = new ClienteBusiness();
    ProductoBusiness productoBusiness = new ProductoBusiness();
    ProductoxpedidoBusinees productoxpedidoBusinees = new ProductoxpedidoBusinees();
    static String INDEX_ROUTE = "/cotizaciones";
    static String SHOW_ROUTE = "/cotizaciones/%d";

    public void insertar() {
        this.cotizacion.getProductoxpedidos().add(this.cotizacionDetalle);
        this.cotizacionDetalle = new Productoxpedido();
    }

    public void actualizar() throws Exception {
        if (this.cotizacion != null) {
            try {
                this.pedidoBusiness.updatePedido(this.cotizacion);
            } catch (PedidoException ex) {
                Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebUtil.sendRedirect(String.format(SHOW_ROUTE, this.id));
        }
    }

    public void agregarProducto() throws Exception {
        if (WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE()) == null) {
            WebUtil.sendRedirect("/login");
        } else {
            Cliente cliente = (Cliente) WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE());

            if (this.id == 0) {
                this.cotizacion = new Pedido();
                this.cotizacion.setPedTipo("cotizacion");
                this.cotizacion.setCliente(cliente);
                this.cotizacion.setPedFechahora(new Date());
                this.pedidoBusiness.addPedido(this.cotizacion);

                List pedidos = this.pedidoBusiness.listPedidoxClientexTipo(cliente.getCliCodigo(), Constantes.getTIPO_DE_PEDIDO());

                this.cotizacion = (Pedido) pedidos.get(pedidos.size() - 1);

                this.id = this.cotizacion.getPedCodigo();
            }

            Producto pedidoProducto = this.productoBusiness.getProductoByCode(this.cotizacionDetalle.getProducto().getProdCodigo());
            this.cotizacionDetalle.setProducto(pedidoProducto);

            this.cotizacionDetalle.setPedido(this.cotizacion);

            BigDecimal precioTotal = this.cotizacionDetalle.getProducto().getProdPrecioventa();
            precioTotal = precioTotal.multiply(new BigDecimal(this.cotizacionDetalle.getPropedCantidad()));

            this.cotizacionDetalle.setPropedPreciototal(precioTotal);
            this.cotizacionDetalle.setId(new ProductoxpedidoId(this.cotizacion.getPedCodigo(), this.cotizacionDetalle.getProducto().getProdCodigo()));
            this.productoxpedidoBusinees.addProductoxpedido(this.cotizacionDetalle);

            WebUtil.sendRedirect(String.format(SHOW_ROUTE, this.id));
        }
    }

    public void eliminarProducto(Integer codProducto) throws ProductoxpedidoException {
        try {
            this.productoxpedidoBusinees = new ProductoxpedidoBusinees();
            ProductoxpedidoId codPedido = new ProductoxpedidoId(this.id, codProducto);
            Productoxpedido pedidoDetalle = this.productoxpedidoBusinees.getProductoxpedido(codPedido);

            this.productoxpedidoBusinees.deleteProductoxpedido(pedidoDetalle);
        } catch (ProductoxpedidoException ex) {
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

    public Pedido getCotizacion() throws PedidoException {
        if (this.id != 0) {
            try {
                this.cotizacion = this.pedidoBusiness.getPedido(this.id);
            } catch (PedidoException ex) {
                Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.cotizacion;
    }

    public void setCotizacion(Pedido cotizacion) {
        this.cotizacion = cotizacion;
    }

    public List<Pedido> getCotizaciones() throws PedidoException {
        List<Pedido> cotizaciones = new ArrayList<Pedido>();
        try {
            cotizaciones = pedidoBusiness.listPedidoxTipo("cotizacion");
        } catch (PedidoException ex) {
            Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cotizaciones;
    }

    public Productoxpedido getCotizacionDetalle() {
        if (this.cotizacionDetalle.getProducto() == null) {
            this.cotizacionDetalle.setProducto(new Producto());
        }
        return this.cotizacionDetalle;
    }

    public void setCotizacionDetalle(Productoxpedido pedidoDetalle) {
        this.cotizacionDetalle = pedidoDetalle;
    }

    public List<SelectItem> getProductos() throws ProductoException {
        List<SelectItem> productos = new ArrayList<SelectItem>();

        List<Producto> lproductos = new ArrayList<Producto>();

        try {
            lproductos = this.productoBusiness.listProducto();
        } catch (ProductoException ex) {
            Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Producto c : lproductos) {
            productos.add(new SelectItem(c.getProdCodigo(), c.getProdNombre()));
        }

        return productos;
    }
}