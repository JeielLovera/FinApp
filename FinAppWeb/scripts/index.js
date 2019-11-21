document.addEventListener("DOMContentLoaded", obtenerContratos, false);
var contratos = [];
var diacontratos =  [];

function obtenerContratos(){
    var rutaContrato = 'http://localhost:8081/contratos';
    var rutaDiaContrato = 'http://localhost:8081/diacontratos/cliente/'+localStorage.getItem("idCliente");

    axios.get(rutaDiaContrato)
    .then(diaCont => {
        for(var dias of diaCont.data)
        {
            diacontratos.push(dias);
            axios.get(rutaContrato+'/'+dias.ccontrato.ccontrato)
            .then(valor => {
                contratos.push(valor.data);
                listarContratos();
            })
        }
    })
}



function listarContratos(){
    console.log(contratos);
    console.log(diacontratos);
    var listaContratos = document.getElementById('listaContratos');

    listaContratos.innerHTML = '';
    
    for(var i = 0; i < contratos.length; i++)
    {
        listaContratos.insertAdjacentHTML("beforeend", `
        <tr>
            <td width="23%">
                <img src="${diacontratos[i].ccontrato.cenfermero.imgurl}" alt="" class="img-fluid">
            </td>
            <td width="33%">
                <p style="font-size:180%;">${diacontratos[i].ccontrato.cenfermero.nenfermero}</p>
                <div class="row">
                    <div class="col-md-5">
                        <label class="font-weight-bold">Nombre del familiar:</label></div>
                    <div class="col-md-3">
                        <p align="left">${diacontratos[i].ccontrato.cusuario.nusuario}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <label class="font-weight-bold">Fecha: </label></div>
                    <div class="col-md-3">
                        <p align="left">${diacontratos[i].dfecha}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <label class="font-weight-bold">Hora de Inicio: </label></div>
                    <div class="col-md-3">
                        <p align="left">${diacontratos[i].dinicio}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <label class="font-weight-bold">Hora de Fin: </label></div>
                    <div class="col-md-3">
                        <p align="left">${diacontratos[i].dfin}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <label class="font-weight-bold">Actividades:</label></div>
                    <div id="listaActividades${diacontratos[i].ccontrato.ccontrato}"
                    </div>
                </div>
            </td>
            <td width="33%">
                <div>
                    <p class="font-weight-bold">Rating: </p>
                    <form class="rating">
                        <label>
                            <input type="radio" name="stars" value="1" />
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                        </label>
                        <label>
                            <input type="radio" name="stars" value="2" />
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                        </label>
                        <label>
                            <input type="radio" name="stars" value="3" />
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                        </label>
                        <label>
                            <input type="radio" name="stars" value="4" />
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                        </label>
                        <label>
                            <input type="radio" name="stars" value="5" />
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                            <span class="icon"><i class="zmdi zmdi-star"></i></span>
                        </label>
                    </form>
                </div>
                <div>
                    <p></p>
                </div>
                <button type="button" onclick="abrirReclamo(${diacontratos[i].ccontrato.ccontrato})" class="btn btn-success waves-effect" >Realizar Reclamo</button>
            </td>
        </tr>`);

        listarActividades(diacontratos[i].ccontrato.ccontrato);
    }
}

function listarActividades(ccontrato) {
    var rutaGETActividades = 'http://localhost:8081/actividades/contrato/'+ccontrato;
    var listaActividades = document.getElementById('listaActividades'+ccontrato);

    axios.get(rutaGETActividades)
    .then(data => {
        for(valor of data.data)
        {
            listaActividades.insertAdjacentHTML("beforeend", `
            <span class="badge badge-info">${valor.nactividad}</span>
            `)
        }
    })

}