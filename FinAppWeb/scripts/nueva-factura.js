document.addEventListener("DOMContentLoaded", inicializar, false);

function inicializar() {
    valoresPredeterminados();
}

function valoresPredeterminados() {
    var ffirma = document.getElementById('ffirma');
    var fechaHoy = new Date();

    var anio = fechaHoy.toLocaleString().split(' ')[0].split('/')[2];
    var mes = fechaHoy.toLocaleString().split(' ')[0].split('/')[1];
    var dia = fechaHoy.toLocaleString().split(' ')[0].split('/')[0];

    ffirma.value = anio+'-'+mes+'-'+dia;
}

function insertarFactura() {
    var titulo = document.getElementById('titulo').value;
    var ffirma =document.getElementById('ffirma').value;
    var fvencimiento = document.getElementById('fvencimiento').value;
    var mfacturado = document.getElementById('mfacturado').value;
    var tipomoneda = document.getElementById('tipomoneda').value;
    var cusuario = Number(localStorage.getItem('idUsuario'));

    var rutaPOSTFactura = 'http://localhost:8085/facturas';

    axios({
        method:'post',
        url:rutaPOSTFactura,
        data:{
            ntitulofactura: titulo,
            mvalornominal: mfacturado,
            dfirma: ffirma,
            dvencimiento: fvencimiento,
            cusuario: {
                cusuario: cusuario,
            },
            ttipomoneda: tipomoneda
        }
    }).then(data => {
        window.location="./lista-facturas.html";
    }).catch(function(error) {
        console.log(error);
    });
}