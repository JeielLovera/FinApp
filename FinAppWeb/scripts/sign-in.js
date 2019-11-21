document.addEventListener("DOMContentLoaded", limpiarMemoria, false);

function limpiarMemoria() {
    localStorage.clear();
}

function loguearse() {
  var usuario = document.getElementById('usuario').value;
  var contraseña = document.getElementById('contraseña').value;

  var ruta = 'http://localhost:8081/clientes/'+usuario+'/'+contraseña;

  fetch(ruta)
    .then(res => res.json())
    .then(datos => {
        for(let valor of datos)
        {
            localStorage.setItem("idCliente",Number(valor.ccliente)); 
            localStorage.setItem("nombreCliente", valor.ncliente);
            window.location="./index.html";
        }
    })
    .catch(function(error)
    {
        console.log(error);
        alert('Usuario o contraseña incorrecta');
    });    
}