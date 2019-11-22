document.addEventListener("DOMContentLoaded", main, false);
var idUsuario=localStorage.getItem("idUsuario");
var lstaFactorings=[];
var lstaCarteras=[];

function DireccionarDetalleCartera(_idCartera){
    alert(_idCartera);
    localStorage.setItem("idCartera",_idCartera);
    for (var i=0;i<lstaCarteras.length;i++){
        if (lstaCarteras[i].ccarterafactura==_idCartera){ 
            localStorage.setItem("atributosCarteraSeleccionada", JSON.stringify(lstaCarteras[i]));
        }
    }
    window.location="./DetalleCartera.html";
}
function AtributosCartera(_idCartera){
    var atributosCartera={
        idCartera:_idCartera,
        TipoDeTasa:"",
        PorcentajeTasa:"",
        FechaDescuento:"",
    };
    for (var i=0;i<lstaFactorings.length;i++){
        if (lstaFactorings[i].ccarterafactura.ccarterafactura==_idCartera){
            atributosCartera={
                idCartera:_idCartera,
                TipoDeTasa:lstaFactorings[i].ctipointeres.ntipointeres,
                PorcentajeTasa:lstaFactorings[i].ptasaoriginal,
                FechaDescuento:lstaFactorings[i].ddescuento,
            };
            return atributosCartera;
        }
    }
    return null;
}
function main (){
    //LISTAR FACTORINGS SIN CARTERA
    var rutaGETFactorings='http://localhost:8085/factorings/usuario/'+String(idUsuario); 
    axios.get(rutaGETFactorings)
    .then(data => {        
        lstaFactorings=data.data;
        console.log(lstaFactorings);
        for (var i=0;i<lstaFactorings.length;i++){
            if (lstaFactorings[i].ccarterafactura==null){
                var contTabla=document.getElementById("contTabla");            
                contTabla.innerHTML+=`
                <tr>
                <td width="5%">
                    <i class="zmdi zmdi-file-text zmdi-hc-4x" style="color:#04BE5B;"></i>
                </td>
                <td width="30%">
                        <div class="col-md-6">
                    <p class="font-weight-bold">${lstaFactorings[i].nfactoring}</p></div>                                            
                </td>
                <td width="60%">
                    <div class="row">
                        <div class="col-md-4">
                            <label class="font-weight-bold">Fecha de Descuento:</label></div>
                        <div class="col-md-3">
                            <p align="left">${lstaFactorings[i].ddescuento}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="font-weight-bold">Tipo de tasa: </label></div>
                        <div class="col-md-3">
                            <!--depende de la tasa-->
                            <!--<p align="left">Nominal Anual Capitalización diaria </p>-->
                            <p align="left">${lstaFactorings[i].ctipointeres.ntipointeres}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="font-weight-bold">Valor a Recibir: </label></div>
                        <div class="col-md-3">
                            <p align="left">${lstaFactorings[i].mrecibido} </p> 
                                                                             
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="font-weight-bold">Valor a Entregar: </label></div>
                        <div class="col-md-3">
                            <p align="left">${lstaFactorings[i].mentregado}</p>
                        </div>
                    </div>                                            
                    <div class="row">
                        <div class="col-md-4">
                            <label class="font-weight-bold">TCEA: </label></div>
                        <div class="col-md-3">
                            <p align="left">${lstaFactorings[i].ptcea}%</p>
                        </div>
                    </div>
                </td>
            </tr>`;
            }       
        }
    }).then(function()
    {
    //LISTAR CARTERAS
    var rutaGETCarteras='http://localhost:8085/factorings/cartera/usuario/'+String(idUsuario); 
    axios.get(rutaGETCarteras)
    .then(data => {           
        lstaCarteras=data.data;
        console.log(lstaCarteras);

        for (var i=0;i<lstaCarteras.length;i++){
            var atributosCartera=AtributosCartera(lstaCarteras[i].ccarterafactura);
        contTabla.innerHTML+=`
        <tr onclick="DireccionarDetalleCartera(${lstaCarteras[i].ccarterafactura})">
        <td width="5%">
            <i class="zmdi zmdi-card-travel zmdi-hc-4x" style="color:#04BE5B;"></i>
        </td>
        <td width="30%">
            <div class="col-md-6">
                <p class="font-weight-bold">${lstaCarteras[i].ncarterafactura}</p>
            </div>
        </td>
        <td width="60%">
            <div class="row">
                <div class="col-md-4">
                    <label class="font-weight-bold">Fecha de Descuento:</label></div>
                <div class="col-md-3">
                    <p align="left">${atributosCartera.FechaDescuento}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label class="font-weight-bold">Tipo de tasa: </label></div>
                <div class="col-md-3">
                    <p align="left">${atributosCartera.TipoDeTasa} </p>
                    <!--<p align="left">Efectiva Anual </p>-->
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label class="font-weight-bold">Porcentaje de Tasa: </label></div>
                <div class="col-md-3">
                    <p align="left">${atributosCartera.PorcentajeTasa}%</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label class="font-weight-bold">TIR: </label></div>
                <div class="col-md-3">
                    <p align="left">${lstaCarteras[i].pTir}%</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label class="font-weight-bold">TCEA: </label></div>
                <div class="col-md-3">
                    <p align="left">${lstaCarteras[i].ptotalTcea}%</p>
                </div>
            </div>
        </td>
    </tr>`;
        }
    });
}
    );
    
}

