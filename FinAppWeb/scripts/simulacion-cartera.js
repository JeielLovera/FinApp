document.addEventListener("DOMContentLoaded", inicializar, false);

//Variables globales para los costos
var arrTipoCostosIniciales = [];
var arrTipoCostosFinales = [];
var arrValorCostosIniciales = [];
var arrValorCostosFinales = [];
var totalMontoCostosIniciales = 0;
var totalMontoCostosFInales = 0;

//Variables globales para las facturas
var facturas = [];
var arrFacturas = [];
var ultimaFactura = 0;
var ultimoFactoring=0;
var arrDVencimiento = [];
var arrVNominal = [];
var arrTCEA=[];
var arrMEntregado=[];
var arrMRecibido=[];
var arrMDescuento=[];
var arrMNeto=[];
var arrFrecCap=[];
var arrFrecTasa=[];
var arrPDescuento=[];
var arrPTasa=[];
var arrPTcea=[];
var ddescntTodos;

//Variables globales para las funciones de agregar
var numCostosIniciales = 0;
var numCostosFinales = 0;


//Función de carga
function inicializar() {
    variablesPredeterminadas();
    listarFacturas();
    obtenerUltimaFactura();
}

//Funcion para establecer las variables predeterminadas del formulario
function variablesPredeterminadas() {
    var fdescuentoCartera = document.getElementById('fdescuentoCartera');
    var seccionCapitalizacion = document.getElementById('seccionCapitalizacion');
    var fechaHoy = new Date();

    var anio = fechaHoy.toLocaleString().split(' ')[0].split('/')[2];
    var mes = fechaHoy.toLocaleString().split(' ')[0].split('/')[1];
    var dia = fechaHoy.toLocaleString().split(' ')[0].split('/')[0];

    fdescuentoCartera.value = anio+'-'+mes+'-'+dia;

    seccionCapitalizacion.hidden = true;
}

//Funcion para listar todas las facturas registradas a nombre del usuario en la seccion de "Facturas"
function listarFacturas() {
    var listaFacturas = document.getElementById('listaFacturas');
    var usuario = localStorage.getItem('idUsuario');
    var rutaGETFacturas = 'http://localhost:8085/facturas/usuario/'+usuario;

    axios.get(rutaGETFacturas)
    .then(data => {
        facturas = data.data;
        for(factura of data.data)
        {
            listaFacturas.insertAdjacentHTML("beforeend", `
                <tr>
                    <td width="5%">
                        <div class="checkbox">
                            <input id="cbFactura${factura.cfactura}" type="checkbox">
                            <label for="cbFactura${factura.cfactura}">
                            </label>
                        </div>
                    </td>
                    <td width="5%">
                        <i class="zmdi zmdi-file-text zmdi-hc-4x" style="color:#04BE5B;"></i>
                    </td>
                    <td width="25%">
                        <p class="font-weight-bold">${factura.ntitulofactura}</p>
                    </td>
                </tr>    
                `
            )
        }
    });   
}

//Funcion para obtener el código de la ultima factura registrada
function obtenerUltimaFactura() {
    var rutaGETFacturas = 'http://localhost:8085/facturas';

    axios.get(rutaGETFacturas)
    .then(data => {
        ultimaFactura = data.data[data.data.length -1].cfactura;
        console.log(ultimaFactura);
    }).catch(function(error){console.log(error);});

    axios.ge(ritaGETFactorings)
    .then(data => {
        ultimoFactoring=data.data[data.data.length-1].cfactoring;
        console.log(ultimoFactoring);
    }).catch(function(error){console.log(error);});
}

//Funcion para agregar un campo de costo inicial
function agregarCostosIniciales() {
    var costosIniciales = document.getElementById('costosIniciales');

    numCostosIniciales++;

    costosIniciales.insertAdjacentHTML('beforeend', `    
        <div class="col-md-8">
        <select class="form-control" id="tipoCostoInicial${numCostosIniciales}">
            <option value="1">Portes</option>
            <option value="2">Fotocopias</option>
            <option value="3">Comisión de estudio</option>
            <option value="4">Comisión de intermediación</option>
            <option value="5">Gastos de administración</option>
            <option value="6">Gastos notariales</option>
            <option value="7">Seguro</option>
            <option value="8">Otros gastos</option> 
        </select>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <input type="text" class="form-control" name="" placeholder="Valor en efectivo" style="height: 38px;" id="valorCostoInicial${numCostosIniciales}">
            </div>
        </div>`
    )
}

//Funcion para agregar un campo de costo final
function agregarCostosFinales() {
    var costosFInales = document.getElementById('costosFinales');
    
    numCostosFinales++;

    costosFInales.insertAdjacentHTML('beforeend', `
        <div class="col-md-8">
            <select class="form-control" id="tipoCostoFinal${numCostosFinales}">
                <option value="1">Portes</option>                                                
                <option value="5">Gastos de administración</option>
                <option value="8">Otros gastos</option>                                                
            </select>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <input type="text" class="form-control" name="" placeholder="Valor en efectivo" style="height: 38px;" id="valorCostoFinal${numCostosFinales}">
            </div>
        </div>`
    )
}

//Funcion para setear el arreglo de costos iniciales a registrar
function setArrCostosIniciales() {
    var tipoCostoInicial;
    var valorCostoInicial;
    var btnRegistrarInicial = document.getElementById('btnRegistrarInicial');
    var btnAgregarInicial = document.getElementById('btnAgregarInicial');

    for(var i = 1; i <= numCostosIniciales; i++)
    {
        tipoCostoInicial = document.getElementById('tipoCostoInicial'+i).value;
        valorCostoInicial = Number(document.getElementById('valorCostoInicial'+i).value).toFixed(2);

        arrTipoCostosIniciales.push(tipoCostoInicial);
        arrValorCostosIniciales.push(valorCostoInicial);
    }

    btnRegistrarInicial.disabled = true;
    btnAgregarInicial.disabled = true;
}

//Funcion para setear el arreglo de costos finales a registrar
function setArrCostosFinales() {
    var tipoCostoFinal;
    var valorCostoFinal;
    var btnRegistrarFinal = document.getElementById('btnRegistrarFinal');
    var btnAgregarFinal = document.getElementById('btnAgregarFinal');

    for(var i = 1; i <= numCostosFinales; i++)
    {
        tipoCostoFinal = document.getElementById('tipoCostoFinal'+i).value;
        valorCostoFinal = Number(document.getElementById('valorCostoFinal'+i).value).toFixed(2);

        arrTipoCostosFinales.push(tipoCostoFinal);
        arrValorCostosFinales.push(valorCostoFinal);
    }

    btnRegistrarFinal.disabled = true;
    btnAgregarFinal.disabled = true;
}

//Funcion para simular la cartera
function simularCartera() {
    //Para ejecutar la simulación
    obtenerFacturasARegistrar();
    
    //Se calcula el Factoring
    for(var i=0;i<arrFacturas.length;i++){
        Calcular_Factoring(i);
    }
    
    //calculo de van y tir


    //Despues de ejecutar la simulación
    limpiarInputsCostos();
    limpiarInputsFacturas();
    habilitarBotonesCostos();
}

function registrarCartera() {

    var rutaCartera='http://localhost:8085/carterafacturas';
    var rutaFactorings='http://localhost:8085/factorings';
    var titulocartera=document.getElementById('titulofactoring').value;
    var tbanco=document.getElementById('tbanco').value;

    axios({
        method: 'POST',
        url: rutaCartera,
        data: {
            mtotarecibido: Number(0),
            ncarterafactura: titulocartera,
            pTir: Number(0),
            ptotalTcea: Number(0)
        }
    })
    .then( data => {
        axios.get(rutaCartera)
        .then(data =>{
            for(var i=0;i<arrFacturas.length;i++){
                crt=data.data[data.data.length-1];
                axios({
                    method: 'POST',
                    url: rutaFactorings,
                    data: {
                        ccartera_factura: crt,
                        cfactura:{
                            cfactura: Number(arrFacturas[i])
                        } ,
                        ctipointeres:{
                            ctipointeres: Number(arrTipoCostosIniciales[i])
                        },
                        ddescuento: ddescntTodos,
                        mdescuento: Number(arrMDescuento[i]),
                        mentregado: Number(arrMEntregado[i]),
                        mneto: Number(arrMNeto[i]),
                        mrecibido: Number(arrMRecibido[i]),
                        nfactoring: titulocartera,
                        numfrecuenciacapitalizacion: Number(arrFrecCap[i]),
                        numfrecuenciatasaoriginal: Number(arrFrecTasa[i]),
                        pdescuento: Number(arrPDescuento[i]),
                        ptasaoriginal: Number(arrPTasa[i]),
                        ptcea: Number(arrPTcea[i]),
                        tbanco: tbanco
                    }
                })
                .then(data => {
                    guardarCostos();
                })
                .catch(function(error){console.log(error);});
            }
        }).catch(function(error){console.log(error);});
    })
    .then(data => {})
    .catch(function(error){console.log(error);});

    //Despues de ejecutar el registro
    limpiarCostos();
    limpiarFacturas();
}

//Encuentra las facturas que fueron seleccionadas
function obtenerFacturasARegistrar() {
    var cbFactura;

    for(var i = 1; i <= ultimaFactura; i++)
    {
        if(cbFactura = document.getElementById('cbFactura'+i) != null)
        {
            cbFactura = document.getElementById('cbFactura'+i).checked;
            if(cbFactura)
            {
                arrFacturas.push(i);
                arrDVencimiento.push(facturas[i].dvencimiento);
                arrVNominal.push(facturas[i].mvalornominal);
            }
        }
    }

    console.log(arrFacturas);
}

//Devuelve todas las variables a su etado original
function limpiarCostos() {
    arrTipoCostosIniciales = [];
    arrTipoCostosFinales = [];
    arrValorCostosIniciales = [];
    arrValorCostosFinales = [];
    totalCostosIniciales = 0;
    totalCostosFInales = 0;
    numCostosIniciales = 0;
    numCostosFinales = 0;
}

//Elimina los inputs de los costos
function limpiarInputsCostos() {
    var costosIniciales = document.getElementById('costosIniciales');
    var costosFinales = document.getElementById('costosFinales');

    costosIniciales.innerHTML = '';
    costosFinales.innerHTML = '';
}

//Elimina las variables de las facturas y desmarca los checkbox
function limpiarFacturas() {
    arrFacturas = [];
}
function limpiarInputsFacturas() {
    for(indice of arrFacturas)
    {
        console.log(indice);
        document.getElementById('cbFactura'+indice).checked = false;
    }
}

//Se habilitan los botones para los costos
function habilitarBotonesCostos() {
    var btnRegistrarInicial = document.getElementById('btnRegistrarInicial');
    var btnAgregarInicial = document.getElementById('btnAgregarInicial');
    var btnRegistrarFinal = document.getElementById('btnRegistrarFinal');
    var btnAgregarFinal = document.getElementById('btnAgregarFinal');

    btnRegistrarInicial.disabled = false;
    btnAgregarInicial.disabled = false;
    btnRegistrarFinal.disabled = false;
    btnAgregarFinal.disabled = false;
}

//Variables globales para los listener
var tipoTasa = document.getElementById('tipoTasa');
var seccionCapitalizacion = document.getElementById('seccionCapitalizacion');

//LISTENER POR SI CAMBIA EL TIPO DE TASA
tipoTasa.addEventListener('change', (e) => {
    if(tipoTasa.value == 1)
    {
        seccionCapitalizacion.hidden = true;
    }
    else
    {
        seccionCapitalizacion.hidden = false;
    }
})

///////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables para calculos intermedios
var tipotasa = 0;
var ddescuento = 0;
var td = 0;
var frec_origin = 0;
var tasa_origin = 0;
var frec_capit = 0;
var tetd = 0;
var pdescuento = 0;
var mdescuento = 0;
var mneto = 0;
var mrecibido = 0;
var mentregado = 0;
var tcea = 0;

function convert_efectiva_efectiva(td,frec_origin,tasa_origin){
    var nueva_tasa;
    nueva_tasa=(Math.pow((1+(tasa_origin/100)),(td/frec_origin))-1)*100;
    return nueva_tasa;
}

function convert_nominal_efectiva(td,frec_origin,tasa_origin,frec_capit){
    var nueva_tasa;
    var m;
    var n
    m=frec_origin/frec_capit;
    n=td/frec_capit;
    nueva_tasa=(Math.pow((1+(tasa_origin/(m*100))),n)-1)*100;
    return nueva_tasa;
}

function calc_porcentaje_descuento(tetd){
    var pdescuento;
    pdescuento=((tetd/100)/(1+(tetd/100)))*100;
    return pdescuento;
}

function calc_valor_descuento(vnominal,pdescuento){
    var mdescuento;
    mdescuento=vnominal*(pdescuento/100);
    return mdescuento;
}

function calc_valor_neto(vnominal,mdescuento){
    var vneto;
    vneto=Number(vnominal)-Number(mdescuento);
    return vneto;
}

function calc_val_recibido(mneto,mgastosinit){
    var mrecibido;
    mrecibido=Number(mneto)-Number(mgastosinit);
    return mrecibido;
}

function calc_val_entregado(vnominal,mgastosfin){
    var mentregado;
    mentregado=Number(vnominal)+Number(mgastosfin);
    return mentregado;
}

function calc_tcea(mentregado,mrecibido,td){
    var tcea;
    tcea=(Math.pow((mentregado/mrecibido),(360/td))-1)*100;
    return tcea;
}

function Calcular_Factoring(indice){
    tetd=0;
    pdescuento=0;
    mdescuento=0;
    mneto=0;
    mrecibido=0;
    mentregado=0;
    totalMontoCostosFInales=0;
    totalMontoCostosIniciales=0;
    tcea=0;
    
    ddescuento=$('#fdescuentoCartera').val();
    ddescntTodos=ddescuento;
    var fecha1=moment(ddescuento);
    var fecha2=moment(arrDVencimiento[indice]);
    td=Number(fecha2.diff(fecha1,'days'))+1;//el +1 es porque por alguna razon de la bd viene con un dia de retraso gaaaaa
    tasa_origin=Number(document.getElementById('porcentajeTasa').value);
    tasa_origin=Number(tasa_origin.toFixed(7));

    console.log(tipotasa);
    console.log(ddescuento);
    console.log(td);
    console.log(frec_origin);
    console.log(frec_capit);
    console.log(tasa_origin);
    console.log(gastosinit_id);
    console.log(gastosinit_costo);
    console.log(gastosfin_id);
    console.log(gastosfin_costo);

    //CALCULO DATOS INTERMEDIOS
    if(tipotasa==1){
        tetd=convert_efectiva_efectiva(td,frec_origin,tasa_origin);
        frec_capit=-1;
    }
    else{
        tetd=convert_nominal_efectiva(td,frec_origin,tasa_origin,frec_capit);
    }

    tetd=Number(tetd.toFixed(7));
    pdescuento=calc_porcentaje_descuento(tetd);
    pdescuento=Number(pdescuento.toFixed(7));

    //CALCULO DATOS FINALES
    mdescuento=calc_valor_descuento(Number(arrVNominal[indice]),Number(pdescuento));
    mdescuento=Number(mdescuento.toFixed(2));
    mneto=calc_valor_neto(Number(arrVNominal[indice]),Number(mdescuento));

    for(var i=0;i<numCostosIniciales;i++){
        totalMontoCostosIniciales+=arrValorCostosIniciales[i];
    }
    mrecibido=calc_val_recibido(Number(mneto),Number(totalMontoCostosIniciales));

    for(var j=0;j<numCostosFinales;j++){
        totalCostosFInales+=arrValorCostosFinales[j];
    }
    mentregado=calc_val_entregado(Number(arrVNominal[indice]),Number(totalCostosFInales));
    tcea=calc_tcea(mentregado,mrecibido,td);
    tcea=Number(tcea.toFixed(7));

    console.log("calculos desde aqui");
    console.log("cont",cont);
    console.log("cont2",cont2);
    console.log(tetd);
    console.log(pdescuento);
    console.log(mdescuento);
    console.log(mneto);
    console.log(mrecibido);
    console.log(mgastosfinales);
    console.log(mentregado);
    console.log(tcea);

    arrMDescuento.push(mdescuento);
    arrMEntregado.push(mentregado);
    arrMNeto.push(mneto);
    arrMRecibido.push(mrecibido);
    arrFrecCap.push(frec_capit);
    arrFrecTasa.push(frec_origin);
    arrPDescuento.push(pdescuento);
    arrPTasa.push(tasa_origin);
    arrPTcea.push(tcea);

}

function guardarCostos(){
    var rutaCostos='http://localhost:8085/gasto_factorings';
    for(var i=0;i<arrFacturas.length;i++){
        for(var j=0;j<numCostosIniciales;j++){
            axios({
                method:'POST',
                url: rutaCostos,
                data:{
                    cfactoring:Number(ultimoFactoring+i+1),
                    cgasto:{
                        cgasto:Number(arrTipoCostosIniciales[j])
                    },
                    ftipogasto: true,
                    mgasto: Number(arrValorCostosIniciales[j])
                }
            }).then(data => {}).catch(function(error){console.log(error);});

            axios({
                method:'POST',
                url: rutaCostos,
                data:{
                    cfactoring:Number(ultimoFactoring+i+1),
                    cgasto:{
                        cgasto:Number(arrTipoCostosFinales[j])
                    },
                    ftipogasto: true,
                    mgasto: Number(arrValorCostosFinales[j])
                }
            }).then(data => {}).catch(function(error){console.log(error);});
        }
    }
}