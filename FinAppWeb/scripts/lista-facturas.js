document.addEventListener("DOMContentLoaded", inicializar, false);

function inicializar() {
    listarFacturas()
}

function listarFacturas () {
    var usuario = localStorage.getItem('idUsuario');
    var rutaGETFacturas = 'http://localhost:8085/facturas/usuario/'+usuario;

    var listaFacturas = document.getElementById('listaFacturas');

    axios.get(rutaGETFacturas)
    .then(data => {
        console.log(data);
        for(valor of data.data)
        {
            listaFacturas.insertAdjacentHTML("beforeend", `
            <tr>
                <td width="5%">
                    <i class="zmdi zmdi-file-text zmdi-hc-4x" style="color:#04BE5B;"></i>
                </td>
                <td width="10%">
                    <h5>Titulo</h5>
                    <p class="font-weight-bold">${valor.ntitulofactura}</p>                                            
                </td>
                <td width="10%">
                    <div>
                        <h5>V. Nominal</h5>
                        <p>${valor.mvalornominal} ${valor.ttipomoneda}</p>
                    </div>                                                                            
                </td>
                <td width="10%">
                    <div>
                        <h5>Fech. Firma</h5>
                        <p>${valor.dfirma}</p>
                    </div>                                                                            
                </td>
                <td width="10%">
                    <div>
                        <h5>Fech. Vencimiento</h5>
                        <p>${valor.dvencimiento}</p>
                    </div>                                                                            
                </td>
                <td width="18%">
                    <div class="col-md-6">
                        <a href="simulacion-factura.html"><button type="button" class="subscribe btn btn-warning btn-block" onclick="seleccionarFactura(${valor.cfactura})">SIMULAR</button></a>
                    </div>                                                                               
                </td>
            </tr>`)
        }
    })
}

function seleccionarFactura(cfactura) {
    localStorage.setItem('seleccionFactura', cfactura);
    window.location="./simulacion-cartera.html";
}