

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
    var fecha1=moment(fct.cfactura.dfirma);
    var fecha2=moment(fct.cfactura.dvencimiento);
    var td=fecha2.diff(fecha1,'days');
    var tetd;
    var pdescuento;
    var mdescuento;
    var mneto;
    var mrecibido;

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
    mrecibido=calc_val_recibido(mneto,mgastosinit);

}