

function insertarFactura() {
    var titulo = document.getElementById('titulo').value;
    var ffirma =document.getElementById('ffirma').value;
    var fvencimiento = document.getElementById('fvencimiento').value;
    var mfacturado = document.getElementById('mfacturado').value;
    var tipomoneda = document.getElementById('tipomoneda').value;
    var cusuario = Number(localStorage.getItem('idUsuario'));

    var rutaPOSTFactura = 'http://localhost:8085/facturas';

    console.log(titulo);
    console.log(ffirma);
    console.log(fvencimiento);
    console.log(mfacturado);
    console.log(tipomoneda);

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