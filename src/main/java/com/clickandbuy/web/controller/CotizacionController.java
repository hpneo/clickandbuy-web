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
    private List<Pedido> cotizaciones = new ArrayList<Pedido>();
    PedidoBusiness pedidoBusiness = new PedidoBusiness();
    ClienteBusiness clienteBusiness = new ClienteBusiness();
    ProductoBusiness productoBusiness = new ProductoBusiness();
    ProductoxpedidoBusinees productoxpedidoBusinees = new ProductoxpedidoBusinees();
    private List<SelectItem> productos = new ArrayList<SelectItem>();

    public void insertar() {

        this.cotizacionDetalle = new Productoxpedido();

        this.cotizacion.getProductoxpedidos().add(this.cotizacionDetalle);
    }

    public void actualizar() throws Exception {
        if (this.cotizacion != null) {
            try {
                this.pedidoBusiness.updatePedido(this.cotizacion);
            } catch (PedidoException ex) {
                Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebUtil.sendRedirect("/cotizaciones/" + this.id);
        }
    }

    public void agregarProducto() throws Exception {
        try {
            Cliente cliente = new Cliente();
            if (this.clienteBusiness.listCliente().isEmpty()) {
                cliente.setCliNombreusuario("cliente1");
                cliente.setCliContrasenia("cliente");
                cliente.setCliDireccion("");
                cliente.setCliNombreempresa("Empresa1");
                cliente.setCliRuc("1234567890");

                this.clienteBusiness.addCliente(cliente);
            }
            cliente = this.clienteBusiness.listCliente().get(0);

            if (this.id == 0) {
                this.cotizacion = new Pedido();
                this.cotizacion.setPedTipo("cotizacion");
                this.cotizacion.setCliente(cliente);
                this.cotizacion.setPedFechahora(new Date());
                this.pedidoBusiness.addPedido(this.cotizacion);

                List<Pedido> _pedidos = this.pedidoBusiness.listPedidoxClientexTipo(cliente.getCliCodigo(), "cotizacion");

                this.cotizacion = _pedidos.get(_pedidos.size() - 1);

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
        } catch (PedidoException ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebUtil.sendRedirect("/cotizaciones/" + this.id);
    }

    public void eliminarProducto(Integer codProducto) throws Exception {
        try {
            this.productoxpedidoBusinees = new ProductoxpedidoBusinees();
            ProductoxpedidoId codPedido = new ProductoxpedidoId(this.id, codProducto);
            Productoxpedido pedidoDetalle = this.productoxpedidoBusinees.getProductoxpedido(codPedido);

            this.productoxpedidoBusinees.deleteProductoxpedido(pedidoDetalle);
        } catch (PedidoException ex) {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebUtil.sendRedirect("/cotizaciones/" + this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getCotizacion() throws Exception {
        if (this.id != 0) {
            try {
                this.cotizacion = this.pedidoBusiness.getPedido(this.id);
            } catch (PedidoException ex) {
                this.cotizacion = new Pedido();
            }
        }

        return this.cotizacion;
    }

    public void setCotizacion(Pedido cotizacion) {
        this.cotizacion = cotizacion;
    }

    public List<Pedido> getCotizaciones() throws Exception {
        try {
            return pedidoBusiness.listPedidoxTipo("cotizacion");
        } catch (PedidoException ex) {
            return new ArrayList<Pedido>();
        }
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

    public List<SelectItem> getProductos() throws Exception {
        this.productos = new ArrayList<SelectItem>();

        List<Producto> _productos;

        try {
            _productos = this.productoBusiness.listProducto();
        } catch (PedidoException ex) {
            _productos = new ArrayList<Producto>();
        }

        for (Producto c : _productos) {
            productos.add(new SelectItem(c.getProdCodigo(), c.getProdNombre()));
        }

        return productos;
    }
}