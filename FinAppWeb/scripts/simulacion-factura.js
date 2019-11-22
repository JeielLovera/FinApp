document.addEventListener("DOMContentLoaded", main, false);

//VARIABLES GLOBALES-PRINCIPALES
var tipotasa;
var ddescuento;
var td;
var frec_origin;
var tasa_origin;
var frec_capit;
var gastosinit_id=[];
var gastosinit_costo=[];
var gastosfin_id=[];
var gastosfin_costo=[];
var tetd;
var pdescuento;
var mdescuento;
var mneto;
var mrecibido;
var mentregado;
var mgastosiniciales;
var mgastosfinales;
var tcea;

//VARIABLES GLOBALES-SECUNDARIAS
var form_costosiniciales;
var form_costosfinales;
var form_idcostos;
var form_mcostos;
var cont;
var auxcont;
var cont2;
var auxcont2;

function main(){//MAIN

    var ruta='http://localhost:8085/facturas/'+localStorage.getItem('seleccionFactura');
    var seccionCapitalizacion = document.getElementById('seccionCapitalizacion');

    tipotasa=1;
    frec_origin=1;
    frec_capit=1;
    form_costosiniciales=document.getElementById('form_costosiniciales');
    form_costosfinales=document.getElementById('form_costosfinales');
    cont=0;
    cont2=0;
    auxcont=0;
    auxcont2=0;

    tetd=0;
    pdescuento=0;
    mdescuento=0;
    mneto=0;
    mrecibido=0;
    mentregado=0;
    mgastosiniciales=0;
    mgastosfinales=0;
    tcea=0;


    fetch(ruta)
    .then(res => res.json())
    .then(fct => {
        localStorage.setItem('idFactura',Number(fct.cfactura));
        localStorage.setItem('dvencimiento',fct.dvencimiento);
        localStorage.setItem('vnominal',Number(fct.mvalornominal));
        localStorage.setItem('moneda',fct.ttipomoneda);
        var titulo=document.getElementById('titulofactura');
        titulo.innerHTML='';
        titulo.innerHTML+=`<label for="">${fct.ntitulofactura}</label>`;  
    })
    .catch(function(error)
    {
        console.log(error);
    });

    var fdescuentoCartera = document.getElementById('ddescuento');
    var fechaHoy = new Date();

    var anio = fechaHoy.toLocaleString().split(' ')[0].split('/')[2];
    var mes = fechaHoy.toLocaleString().split(' ')[0].split('/')[1];
    var dia = fechaHoy.toLocaleString().split(' ')[0].split('/')[0];

    fdescuentoCartera.value = anio+'-'+mes+'-'+dia;

    seccionCapitalizacion.hidden = true;
}

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

function Calcular_Factoring(){
    tetd=0;
    pdescuento=0;
    mdescuento=0;
    mneto=0;
    mrecibido=0;
    mentregado=0;
    mgastosiniciales=0;
    mgastosfinales=0;
    tcea=0;
    
    ddescuento=$('#ddescuento').val();

    var fecha1=moment(ddescuento);
    var fecha2=moment(localStorage.getItem('dvencimiento'));
    td=Number(fecha2.diff(fecha1,'days'))+1;//el +1 es porque por alguna razon de la bd viene con un dia de retraso 
    tasa_origin=Number(document.getElementById('tasa_origin').value);
    tasa_origin=Number(tasa_origin.toFixed(7));


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
    mdescuento=calc_valor_descuento(Number(localStorage.getItem('vnominal')),Number(pdescuento));
    mdescuento=Number(mdescuento.toFixed(2));
    mneto=calc_valor_neto(Number(localStorage.getItem('vnominal')),Number(mdescuento));
    mgastosiniciales=0;
    for(var i=0;i<cont;i++){
        mgastosiniciales+=gastosinit_costo[i];
    }
    mrecibido=calc_val_recibido(Number(mneto),Number(mgastosiniciales));
    mgastosfinales=0;
    for(var j=0;j<cont2;j++){
        mgastosfinales+=gastosfin_costo[j];
    }
    mentregado=calc_val_entregado(Number(localStorage.getItem('vnominal')),Number(mgastosfinales));
    tcea=calc_tcea(mentregado,mrecibido,td);
    tcea=Number(tcea.toFixed(7));



    var divdescuento=document.getElementById('divdescuento');
    divdescuento.innerHTML='';
    divdescuento.innerHTML+=`<p align="left">${mdescuento} ${localStorage.getItem('moneda')}</p>`;
    var divneto=document.getElementById('divneto');
    divneto.innerHTML='';
    divneto.innerHTML+=`<p align="left">${mneto} ${localStorage.getItem('moneda')}</p>`;
    var divrecibido=document.getElementById('divrecibido');
    divrecibido.innerHTML='';
    divrecibido.innerHTML+=`<p align="left">${mrecibido} ${localStorage.getItem('moneda')}</p>`;
    var diventregado=document.getElementById('diventregado');
    diventregado.innerHTML='';
    diventregado.innerHTML+=`<p align="left">${mentregado} ${localStorage.getItem('moneda')}</p>`;
    var divtcea=document.getElementById('divtcea');
    divtcea.innerHTML='';
    divtcea.innerHTML+=`<p align="left">${tcea} %</p>`;

    limpiar_datos();

}

function setTipoTasa(){
    var selectedtasa=document.getElementById('tipotasa');
    tipotasa=Number(selectedtasa.value);
}

function setFrecOrigin(){
    var selectedorigin=document.getElementById('frec_origin');
    frec_origin=Number(selectedorigin.value);
}

function setFrecCapit(){
    var selectedcapit=document.getElementById('frec_capit');
    frec_capit=Number(selectedcapit.value);
}

function agregarCI(){
    form_costosiniciales.insertAdjacentHTML("beforeend", `
    <div class="col-md-8" >
        <select class="form-control" id="idcosto${cont}" onchange="setIDs(${cont})">
            <option value="" disabled selected>Gastos Iniciales</option>
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
            <input type="text" class="form-control" id="mcosto${cont}" placeholder="Valor en efectivo" style="height: 38px;">
        </div>
    </div>
    `);
    cont++;
    auxcont++;
}

function agregarCF(){
    form_costosfinales.insertAdjacentHTML("beforeend", `
    <div class="col-md-8">
        <select class="form-control" id="idcostof${cont2}" onchange="setIDFs(${cont2})">
            <option value="" disabled selected>Gastos Finales</option>
            <option value="1">Portes</option>                                                
            <option value="5">Gastos de administración</option>
            <option value="8">Otros gastos</option>                                                
        </select>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <input type="text" class="form-control" id="mcostof${cont2}" placeholder="Valor en efectivo" style="height: 38px;">
        </div>
    </div>
    `);
    cont2++;
    auxcont2++;
}

function setCI(){
    for(var i=0;i<cont;i++){
        var tempcosto=Number(document.getElementById('mcosto'+String(i)).value);
        gastosinit_costo.push(Number(tempcosto.toFixed(2)));
    }
    var btn=document.getElementById('rg');
    btn.disabled=true;
    btn=document.getElementById('agr');
    btn.disabled=true;
}

function setCF(){
    for(var i=0;i<cont2;i++){
        var tempcostof=Number(document.getElementById('mcostof'+String(i)).value);
        gastosfin_costo.push(Number(tempcostof.toFixed(2)));
    }
    var btn=document.getElementById('rg2');
    btn.disabled=true;
    btn=document.getElementById('agr2');
    btn.disabled=true;

}

function setIDs(e){
    auxcont++;
    if(auxcont-cont==1){
        var selectid=document.getElementById('idcosto'+String(e));
        gastosinit_id.push(Number(selectid.value));
    }
    else{
        var selectid=document.getElementById('idcosto'+String(e));
        gastosinit_id[Number(e)]=Number(selectid.value);
    }  
}

function setIDFs(e){
    auxcont2++;
    if(auxcont2-cont2==1){
        var selectid2=document.getElementById('idcostof'+String(e));
        gastosfin_id.push(Number(selectid2.value));
    }
    else{
        var selectid2=document.getElementById('idcostof'+String(e));
        gastosfin_id[Number(e)]=Number(selectid2.value);
    }  
}

function limpiar_datos(){
    form_costosiniciales.innerHTML='';
    form_costosfinales.innerHTML='';
}

function registrar_factoring(){
    var titulofactoring=document.getElementById('titulofactoring').value;
    var tbanco=document.getElementById('tbanco').value;
    var ruta='http://localhost:8085/factorings';

    axios({
        method:'POST',
        url:ruta,
        data:{
            ccartera_factura: null,
            cfactura:{
                cfactura: Number(localStorage.getItem('idFactura'))
            } ,
            ctipointeres:{
                ctipointeres: Number(tipotasa)
            },
            ddescuento: ddescuento,
            mdescuento: Number(mdescuento),
            mentregado: Number(mentregado),
            mneto: Number(mneto),
            mrecibido: Number(mrecibido),
            nfactoring: titulofactoring,
            numfrecuenciacapitalizacion: Number(frec_capit),
            numfrecuenciatasaoriginal: Number(frec_origin),
            pdescuento: Number(pdescuento),
            ptasaoriginal: Number(tasa_origin),
            ptcea: Number(tcea),
            tbanco: tbanco
        }
    })
    .then(data => {
        var fct=[];
        var ruta2='http://localhost:8085/factorings';
        axios.get(ruta2)
        .then(data => {
            for(var i=0;i<cont;i++){
                var ruta3='http://localhost:8085/gasto_factorings';
                fct=data.data[data.data.length-1];
                axios({
                    method:'POST',
                    url: ruta3,
                    data:{
                        cfactoring:fct,
                        cgasto:{
                            cgasto:Number(gastosinit_id[i])
                        },
                        ftipogasto: true,
                        mgasto: Number(gastosinit_costo[i])
                    }
                }).then(data => {}).catch(function(error){console.log(error);});
            }
        })
        .catch(function(error){console.log(error);});
    })
    .then(data => {
        var fct=[];
        var ruta2='http://localhost:8085/factorings';
        axios.get(ruta2)
        .then(data => {
            for(var i=0;i<cont2;i++){
                var ruta3='http://localhost:8085/gasto_factorings';
                fct=data.data[data.data.length-1];
                axios({
                    method:'POST',
                    url: ruta3,
                    data:{
                        cfactoring:fct,
                        cgasto:{
                            cgasto:Number(gastosfin_id[i])
                        },
                        ftipogasto: false,
                        mgasto: Number(gastosfin_costo[i])
                    }
                }).then(data => { }).catch(function(error){console.log(error);});
            }
        })
        .catch(function(error){console.log(error);});
    })
    .then(data => {
    })
    .catch(function(error){console.log(error);});

    window.location="./lista-facturas.html";

}

function cancelar(){
    window.location="./lista-facturas.html";
}

//Variables globales para los listener
var tipoTasa = document.getElementById('tipotasa');
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