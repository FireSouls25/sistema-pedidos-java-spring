package co.ucc.pedidos.service;

import co.ucc.pedidos.data.DatosPrueba;
import co.ucc.pedidos.model.FechaPedidoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FechaPedidoServiceTest {

    @InjectMocks
    private FechaPedidoService fechaPedidoService;

    private FechaPedidoModel fechaPedido;

    @BeforeEach
    void setUp() {
        fechaPedido = DatosPrueba.crearFechaPedido();
    }

    @Test
    void testCalcularFechaEstimada() {
        Date resultado = fechaPedidoService.calcularFechaEstimada(fechaPedido);
        
        assertNotNull(resultado);
        assertEquals(fechaPedido.getFechaEstimada(), resultado);
    }

    @Test
    void testCalcularFechaEstimada_Nulo() {
        Date resultado = fechaPedidoService.calcularFechaEstimada(null);
        
        assertNull(resultado);
    }

    @Test
    void testActualizarFechaRecibo() {
        Date nuevaFecha = new Date();
        
        fechaPedidoService.actualizarFechaRecibo(fechaPedido, nuevaFecha);
        
        assertEquals(nuevaFecha, fechaPedido.getFechaRecibir());
    }

    @Test
    void testActualizarFechaRecibo_Nulo() {
        assertDoesNotThrow(() -> {
            fechaPedidoService.actualizarFechaRecibo(null, new Date());
        });
    }

    @Test
    void testVerificarRetrasoPedido_ConRetraso() {
        fechaPedido.setFechaEstimada(new Date(System.currentTimeMillis() - 86400000));
        fechaPedido.setFechaRecibir(new Date());
        
        boolean resultado = fechaPedidoService.verificarRetrasoPedido(fechaPedido);
        
        assertTrue(resultado);
    }

    @Test
    void testVerificarRetrasoPedido_SinRetraso() {
        fechaPedido.setFechaEstimada(new Date(System.currentTimeMillis() + 86400000));
        fechaPedido.setFechaRecibir(new Date());
        
        boolean resultado = fechaPedidoService.verificarRetrasoPedido(fechaPedido);
        
        assertFalse(resultado);
    }

    @Test
    void testVerificarRetrasoPedido_FechasNulas() {
        fechaPedido.setFechaEstimada(null);
        fechaPedido.setFechaRecibir(null);
        
        boolean resultado = fechaPedidoService.verificarRetrasoPedido(fechaPedido);
        
        assertFalse(resultado);
    }

    @Test
    void testVerificarRetrasoPedido_Nulo() {
        boolean resultado = fechaPedidoService.verificarRetrasoPedido(null);
        
        assertFalse(resultado);
    }
}
