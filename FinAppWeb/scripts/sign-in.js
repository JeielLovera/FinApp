document.addEventListener("DOMContentLoaded", limpiarMemoria, false);

function limpiarMemoria() {
    localStorage.clear();
}

function loguearse() {
  var usuario = document.getElementById('usuario').value;
  var contraseña = document.getElementById('contraseña').value;

  var ruta = 'http://localhost:8085/usuarios/'+usuario+'/'+contraseña;

  fetch(ruta)
    .then(res => res.json())
    .then(datos => {
        for(let valor of datos)
        {
            localStorage.setItem("idUsuario",Number(valor.cusuario)); 
            localStorage.setItem("nombreUsuario", valor.nusuario);
            window.location="./lista-facturas.html";
        }
    })
    .catch(function(error)
    {
        console.log(error);
        alert('Usuario o contraseña incorrecta');
    });    
}