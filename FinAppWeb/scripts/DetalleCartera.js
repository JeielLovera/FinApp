document.addEventListener("DOMContentLoaded", main, false);
var lstaFactorings=[];
var _atributosCarteraSeleccionada = localStorage.getItem('atributosCarteraSeleccionada');
var atributosCarteraSeleccionada=JSON.parse(_atributosCarteraSeleccionada);
function main(){
    var idCartera=localStorage.getItem("idCartera");
    var rutaGETFactorings='http://localhost:8085/factorings/cartera/'+String(idCartera); 
    axios.get(rutaGETFactorings)
    .then(data => {  
        lstaFactorings=data.data;
        console.log(lstaFactorings);
        for (var i=0;i<lstaFactorings.length;i++){           
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

    });  
    var nCartera=document.getElementById("nCartera");console.log(atributosCarteraSeleccionada);
    nCartera.innerHTML=`<h2 >Nombre de Cartera: ${atributosCarteraSeleccionada.ncarterafactura}</h2>`;
}