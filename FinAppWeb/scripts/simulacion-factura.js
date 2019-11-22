document.addEventListener("DOMContentLoaded", listar_facturas, false);

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
//VARIABLES GLOBALES-SECUNDARIAS
var form_costosiniciales;
var form_costosfinales;
var form_idcostos;
var form_mcostos;
var cont;
var auxcont;
var cont2;
var auxcont2;

function listar_facturas(){//MAIN
    var usuario = localStorage.getItem('idUsuario');

    var ruta = 'http://localhost:8085/facturas/usuario/'+usuario;
    var listar=document.getElementById("lista");
    //listar.innerHTML='';


    tipotasa=1;
    frec_origin=1;
    frec_capit=1;
    form_costosiniciales=document.getElementById('form_costosiniciales');
    form_costosfinales=document.getElementById('form_costosfinales');
    cont=0;
    cont2=0;
    auxcont=0;
    auxcont2=0;

    agregarCI();
    agregarCF();

    fetch(ruta)
    .then(res => res.json())
    .then(fcts => {

        for(let fct of fcts){
            //CTMCTMCTMCTMCT
            localStorage.setItem('idFactura',Number(fct.cfactura));
            localStorage.setItem('dvencimiento',fct.dvencimiento);
            
        }
    }).then(data=>{console.log(localStorage.getItem('dvencimiento'));})///borrarluego
    .catch(function(error)
    {
        console.log(error);
    });
    
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
    vneto=vnominal-mdescuento;
    return 
}

function calc_val_recibido(mneto,mgastosinit){
    var mrecibido;
    mrecibido=mneto-mgastosinit;
    return mrecibido;
}

function calc_val_entregado(vnominal,mgastosfin){
    var mentregado;
    mentregado=vnominal+mgastosfin;
    return mentregado;
}

function calc_tcea(mentregado,mrecibido,td){
    var tcea;
    tcea=(Math.pow((mentregado/mrecibido),(360/td))-1)*100;
    return tcea;
}

function Calcular_Factoring(){
    //var fecha1=moment(fct.cfactura.ddescuento);
    //var fecha2=moment(fct.cfactura.dvencimiento);
    //var td=fecha2.diff(fecha1,'days');
    var tetd;
    var pdescuento;
    var mdescuento;
    var mneto;
    var mrecibido;



    ddescuento=$('#ddescuento').val();

    var fecha1=moment(ddescuento);
    var fecha2=moment(localStorage.getItem('dvencimiento'));
    td=Number(fecha2.diff(fecha1,'days'))+1;//el +1 es porque por alguna razon de la bd viene con un dia de retraso gaaaaa
    tasa_origin=Number(document.getElementById('tasa_origin').value);
    tasa_origin=Number(tasa_origin.toFixed(7));

    console.log(tipotasa);
    console.log(ddescuento);
    console.log(td);
    console.log(frec_origin);
    console.log(frec_capit);
    console.log(tasa_origin);

    /*var selectedtasa=document.getElementById('tipotasa');
    selectedtasa.addEventListener('change',function(){
        var tipotasa=this.options[selectedtasa.selectedIndex];
        console.log(tipotasa.text);
        console.log("aea");
    });*/
/*
    //CALCULO DATOS INTERMEDIOS
    if(fct.ctipointeres.ctipointeres==1){
        tetd=convert_efectiva_efectiva(td,frec_origin,tasa_origin);
    }
    else{
        tetd=convert_nominal_efectiva(td,frec_origin,tasa_origin,frec_capit);
    }

    pdescuento=calc_porcentaje_descuento(tetd);
    pdescuento=Number(pdescuento.toFixed(7));

    //CALCULO DATOS FINALES
    mdescuento=calc_valor_descuento(vnominal,pdescuento);
    mdescuento=Number(mdescuento.toFixed(2));
    mneto=calc_valor_neto(vnominal,mdescuento);
    mrecibido=calc_val_recibido(mneto,mgastosinit);*/

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
    form_costosiniciales.innerHTML+=`
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
    `;
    cont++;
    auxcont++;
}

function agregarCF(){
    form_costosfinales.innerHTML+=`
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
    `;
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

    console.log(gastosinit_costo);
    console.log(gastosinit_id);
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

    console.log(gastosfin_costo);
    console.log(gastosfin_id);
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
        console.log(e);
    }  
}